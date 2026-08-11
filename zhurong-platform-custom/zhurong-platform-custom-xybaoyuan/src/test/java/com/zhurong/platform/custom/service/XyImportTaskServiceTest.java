package com.zhurong.platform.custom.service;

import com.zhurong.platform.custom.entity.XyManufacturingOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XyImportTaskServiceTest {
    @Test
    void compositeIdentityPreservesHyphensInPlanNumber() {
        String composite = XyImportTaskService.combineErpIdentity("12345", "PLAN-2026-08");
        XyImportTaskService.ErpIdentity identity = XyImportTaskService.splitErpIdentity(composite);
        assertThat(identity.erpInternalCode()).isEqualTo("12345");
        assertThat(identity.planNumber()).isEqualTo("PLAN-2026-08");
    }

    @Test
    void legacyCusRefWithoutSeparatorRemainsReadable() {
        XyImportTaskService.ErpIdentity identity = XyImportTaskService.splitErpIdentity("12345");
        assertThat(identity.erpInternalCode()).isEqualTo("12345");
        assertThat(identity.planNumber()).isEmpty();
    }

    @Test
    void importedOrderIdentityUsesErpCodeInsteadOfRepeatedOrderNumber() {
        String first = XyImportTaskService.importedOrderKey("10001-PLAN-001", "JOB-01");
        String second = XyImportTaskService.importedOrderKey("10002-PLAN-001", "JOB-01");

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    void rejectsOrdersWithoutJobOrMachineBeforeImportTaskCreation() {
        XyManufacturingOrder noJob = order("MO-001", null, "LASER-01");
        XyManufacturingOrder noMachine = order("MO-002", "100000001", null);

        assertThatThrownBy(() -> XyImportTaskService.validateOrderAssignments(List.of(noJob, noMachine)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("未设置作业: MO-001")
                .hasMessageContaining("未设置设备: MO-002");
    }

    private static XyManufacturingOrder order(String orderNumber, String jobRef, String wrkRef) {
        XyManufacturingOrder order = new XyManufacturingOrder();
        order.setProductionOrderNumber(orderNumber);
        order.setJobRef(jobRef);
        order.setWrkRef(wrkRef);
        return order;
    }
}
