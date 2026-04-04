package com.zhurong.platform.core.lantek.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.masterlink.builders.ProductCommandBuilder;
import com.zhurong.platform.base.masterlink.builders.RemnantCommandBuilder;
import com.zhurong.platform.base.masterlink.core.IXmlCommand;
import com.zhurong.platform.base.masterlink.engine.XmlExportEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/core")
public class TestCoreController {

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-core";
    }

    @GetMapping("/demo")
    public ApiResponse<String> demo() throws Exception {

        XmlExportEngine engine = new XmlExportEngine();

        // ========================
        // 1. Manufacturing 导出
        // ========================
        com.zhurong.platform.base.masterlink.commands.ManufacturingCommand manufacturing = new com.zhurong.platform.base.masterlink.commands.ManufacturingCommand();
        manufacturing.setCDate(LocalDateTime.now().toLocalDate());
        manufacturing.setDisJobRef("JOB000011");
        manufacturing.setWrkRef("Lantek laser");
        manufacturing.setMnORef("20260224-01");
        manufacturing.setPrdRefDst("PART-2026-0224-01");
        manufacturing.setOrdRef("Ord-01");
        manufacturing.setRq(10);
        manufacturing.setPriority(1);
        manufacturing.setCusRef("CUST-01");
        manufacturing.setDisMatRef("Q345B");
        manufacturing.setDisThickness(2.5f);

        engine.export(
                "D:/TEMP/MasterlinkXmlDemo/java/manufacturing.xml",
                List.of(manufacturing)
        );

        // ========================
        // 2. Product 导出（Builder）
        // ========================
        com.zhurong.platform.base.masterlink.commands.ProductsCommand product = new com.zhurong.platform.base.masterlink.commands.ProductsCommand();
        product.setPrdRef("PART-2026-0224-01");
        product.setPrdName("PART-2026-0224-01");
        product.setDisMatRef("Q235B");
        product.setDisThickness(10f);
        product.setDisUData1Prt("U1");
        product.setDisUData2Prt("U2");
        product.setDisUData3Prt("U3");
        product.setDisUData4Prt("U4");
        product.setDisUData5Prt("U5");
        product.setDisUData6Prt("U6");
        product.setDisUData7Prt("U7");
        product.setDisUData8Prt("U8");
        product.setWrkRef("Lantek laser");
        product.setOperation("2D Cut");
        product.setGeometryPath("C:\\LantekV44\\Expert\\Samples\\DXF\\52.DXF");
        product.setGeometryType("DXF");

        List<IXmlCommand> productCommands = new ProductCommandBuilder()
                .withProduct(product)
                .withOperations(product)
                .withGeometry(product)
                .build();

        engine.export(
                "D:/TEMP/MasterlinkXmlDemo/java/products.xml",
                productCommands
        );

        // ========================
        // 3. Delete
        // ========================
        com.zhurong.platform.base.masterlink.commands.ManufacturingDeleteCommand deleteCmd =
                new com.zhurong.platform.base.masterlink.commands.ManufacturingDeleteCommand(
                        new com.zhurong.platform.base.masterlink.commands.ManufacturingCommand() {{
                            mnORef = "20260224-01";
                        }}
                );

        engine.export(
                "D:/TEMP/MasterlinkXmlDemo/java/manufacturing_delete.xml",
                List.of(deleteCmd)
        );

        // ========================
        // 4. Update
        // ========================
        com.zhurong.platform.base.masterlink.commands.ManufacturingCommand updateSource = new com.zhurong.platform.base.masterlink.commands.ManufacturingCommand();
        updateSource.setCDate(LocalDateTime.now().toLocalDate());
        updateSource.setWrkRef("Lantek laser");
        updateSource.setMnORef("20260224-01");
        updateSource.setRq(20);
        updateSource.setPriority(2);
        updateSource.setCusRef("CUST-02");
        updateSource.setDisMatRef("Q460");
        updateSource.setDisThickness(8f);

        com.zhurong.platform.base.masterlink.commands.ManufacturingUpdateCommand updateCmd =
                new com.zhurong.platform.base.masterlink.commands.ManufacturingUpdateCommand(updateSource);

        engine.export(
                "D:/TEMP/MasterlinkXmlDemo/java/manufacturing_update.xml",
                List.of(updateCmd)
        );

        // ========================
        // 5. Sheets（Remnant）
        // ========================
        com.zhurong.platform.base.masterlink.commands.SheetsCommand sheet1 = new com.zhurong.platform.base.masterlink.commands.SheetsCommand();
        sheet1.setPrdRef("RSheet-01");
        sheet1.setPrdName("RSheet-01");
        sheet1.setDisMatRef("Q235B");
        sheet1.setDisThickness(10f);
        sheet1.setDisCamQuan(100);
        sheet1.setDisUData1Sht("RU1");
        sheet1.setDisUData2Sht("RU2");
        sheet1.setDisUData3Sht("RU3");
        sheet1.setGeometryPath("C:\\LantekV44\\Expert\\Samples\\DXF\\28.DXF");
        sheet1.setGeometryType("DXF");

        com.zhurong.platform.base.masterlink.commands.SheetsCommand sheet2 = new com.zhurong.platform.base.masterlink.commands.SheetsCommand();
        sheet2.setPrdRef("RSheet-02");
        sheet2.setPrdName("RSheet-02");
        sheet2.setDisMatRef("Q235B");
        sheet2.setDisThickness(20f);
        sheet2.setDisCamQuan(200);
        sheet2.setDisUData1Sht("RU11");
        sheet2.setDisUData2Sht("RU22");
        sheet2.setDisUData3Sht("RU33");
        sheet2.setGeometryPath("C:\\LantekV44\\Expert\\Samples\\DXF\\33.DXF");
        sheet2.setGeometryType("DXF");

        List<IXmlCommand> remnantCommands = new RemnantCommandBuilder()
                .with(List.of(sheet1, sheet2))
                .build();

        engine.export(
                "D:/TEMP/MasterlinkXmlDemo/java/remnants.xml",
                remnantCommands
        );

        return ApiResponse.success("true");
    }
}
