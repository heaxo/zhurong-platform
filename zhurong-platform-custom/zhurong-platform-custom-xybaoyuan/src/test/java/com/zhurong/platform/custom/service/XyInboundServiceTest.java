package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.entity.XyBasePart;
import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import com.zhurong.platform.custom.mapper.XyBasePartMapper;
import com.zhurong.platform.custom.mapper.XyManufacturingOrderMapper;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class XyInboundServiceTest {
    @TempDir
    Path temporaryDirectory;

    private final XyBasePartMapper basePartMapper = mock(XyBasePartMapper.class);
    private final XyManufacturingOrderMapper orderMapper = mock(XyManufacturingOrderMapper.class);
    private final IPprrPprr00000100Service partService = mock(IPprrPprr00000100Service.class);
    private final XyBaoyuanProperties properties = new XyBaoyuanProperties();

    @Test
    void basePartInboundPersistsAndProducesCompatibleWorkbook() throws Exception {
        properties.getInbound().setBasePartExcelDirectory(temporaryDirectory.toString());
        properties.getInbound().setDrawingRoot("\\\\drawing-server\\library\\");
        properties.getInbound().setDrawingExtension("dxf");
        when(partService.list(any(Wrapper.class))).thenReturn(List.of());
        when(basePartMapper.selectList(any(Wrapper.class))).thenReturn(List.of());
        when(basePartMapper.insert(any(XyBasePart.class))).thenReturn(1);

        XyInboundRequests.BaseParts request = new XyInboundRequests.BaseParts();
        request.setParts(List.of(basePart("P-001", "D-001", "ERP-001")));

        assertThat(service().receiveBaseParts(request)).isTrue();
        ArgumentCaptor<XyBasePart> entity = ArgumentCaptor.forClass(XyBasePart.class);
        verify(basePartMapper).insert(entity.capture());
        assertThat(entity.getValue().getDrawingCode()).isEqualTo("D-001");
        assertThat(entity.getValue().getUdata3()).isEqualTo("ERP-001");

        Path workbookPath = temporaryDirectory.resolve("零件基础信息" + LocalDate.now() + ".xlsx");
        assertThat(workbookPath).exists();
        try (Workbook workbook = WorkbookFactory.create(Files.newInputStream(workbookPath))) {
            var row = workbook.getSheet("Sheet1").getRow(1);
            assertThat(row.getCell(0).getStringCellValue()).isEqualTo("D-001");
            assertThat(row.getCell(7).getStringCellValue()).isEqualTo("ERP-001");
            assertThat(row.getCell(8).getCellFormula()).isEqualTo("CONCATENATE(J2,A2,K2)");
            assertThat(row.getCell(9).getStringCellValue()).isEqualTo("\\\\drawing-server\\library\\");
            assertThat(row.getCell(10).getStringCellValue()).isEqualTo(".dxf");
        }
    }

    @Test
    void manufacturingOrderInboundMapsFieldsAndUsesLegacyRouteTruncation() {
        when(orderMapper.selectCount(any(Wrapper.class))).thenReturn(0L);
        when(orderMapper.insert(any(XyManufacturingOrder.class))).thenReturn(1);
        XyInboundRequests.ManufacturingOrders request = new XyInboundRequests.ManufacturingOrders();
        XyInboundRequests.ManufacturingOrder item = order("MO-001", "10001");
        item.setRouRef("A".repeat(39) + "中B");
        request.setProductionOrders(List.of(item));

        assertThat(service().receiveManufacturingOrders(request)).isTrue();
        ArgumentCaptor<XyManufacturingOrder> entity = ArgumentCaptor.forClass(XyManufacturingOrder.class);
        verify(orderMapper).insert(entity.capture());
        assertThat(entity.getValue().getRouRef()).isEqualTo("A".repeat(39));
        assertThat(entity.getValue().getQuantity()).isEqualTo(2.5D);
        assertThat(entity.getValue().getProductionOrderErpInternalCode()).isEqualTo("10001");
        assertThat(entity.getValue().getReadState()).isFalse();
    }

    @Test
    void existingManufacturingOrderMakesWholeInboundBatchIdempotent() {
        when(orderMapper.selectCount(any(Wrapper.class))).thenReturn(1L);
        XyInboundRequests.ManufacturingOrders request = new XyInboundRequests.ManufacturingOrders();
        request.setProductionOrders(List.of(order("MO-001", "10001"), order("MO-002", "10002")));

        assertThat(service().receiveManufacturingOrders(request)).isTrue();
        verify(orderMapper, never()).insert(any(XyManufacturingOrder.class));
    }

    @Test
    void duplicateOrderNumbersWithDifferentErpCodesAreAccepted() {
        when(orderMapper.selectCount(any(Wrapper.class))).thenReturn(0L);
        when(orderMapper.insert(any(XyManufacturingOrder.class))).thenReturn(1);
        XyInboundRequests.ManufacturingOrders request = new XyInboundRequests.ManufacturingOrders();
        request.setProductionOrders(List.of(order("MO-001", "10001"), order("MO-001", "10002")));

        assertThat(service().receiveManufacturingOrders(request)).isTrue();
        verify(orderMapper, times(2)).insert(any(XyManufacturingOrder.class));
    }

    @Test
    void duplicateErpCodesAreRejectedEvenWhenOrderNumbersDiffer() {
        XyInboundRequests.ManufacturingOrders request = new XyInboundRequests.ManufacturingOrders();
        request.setProductionOrders(List.of(order("MO-001", "10001"), order("MO-002", "10001")));

        assertThatThrownBy(() -> service().receiveManufacturingOrders(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("生产订单ERP内码不能重复");
        verify(orderMapper, never()).selectCount(any(Wrapper.class));
        verify(orderMapper, never()).insert(any(XyManufacturingOrder.class));
    }

    private XyInboundService service() {
        return new XyInboundService(basePartMapper, orderMapper, partService, properties);
    }

    private static XyInboundRequests.BasePart basePart(String prdRef, String drawingCode, String erpCode) {
        XyInboundRequests.BasePart item = new XyInboundRequests.BasePart();
        item.setPrdRef(prdRef);
        item.setPrdName("零件");
        item.setDrawingCode(drawingCode);
        item.setMatRef("Q235");
        item.setThickness(2D);
        item.setUdata1("1");
        item.setUdata2("10");
        item.setUdata3(erpCode);
        return item;
    }

    private static XyInboundRequests.ManufacturingOrder order(String orderNumber, String erpCode) {
        XyInboundRequests.ManufacturingOrder item = new XyInboundRequests.ManufacturingOrder();
        item.setProductionOrderNumber(orderNumber);
        item.setProductionOrderLineId("1");
        item.setProductionOrderErpInternalCode(erpCode);
        item.setPrdRef("P-001");
        item.setPrdName("零件");
        item.setQuantity(2.5D);
        item.setMatRef("Q235");
        item.setThickness(2D);
        item.setDeliveryDate(LocalDateTime.of(2026, 8, 11, 8, 0));
        item.setCusRef("PLAN-001");
        item.setUdata1("1");
        item.setUdata2("10");
        item.setProductionWorkshopCode("W01");
        item.setProductionWorkshopName("下料车间");
        return item;
    }
}
