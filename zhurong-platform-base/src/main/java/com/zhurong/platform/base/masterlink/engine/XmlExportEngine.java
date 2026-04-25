package com.zhurong.platform.base.masterlink.engine;

import com.zhurong.platform.base.masterlink.core.IXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlCondition;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XmlExportEngine {

    private static final byte[] UTF8_BOM = new byte[] {
            (byte) 0xEF,
            (byte) 0xBB,
            (byte) 0xBF
    };

    public void export(String filePath, List<? extends IXmlCommand> commands) throws Exception {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try (FileOutputStream fos = new FileOutputStream(filePath)) {

            // 写入 UTF-8 BOM：EF BB BF
            fos.write(UTF8_BOM);

            XMLStreamWriter writer = factory.createXMLStreamWriter(fos, StandardCharsets.UTF_8.name());

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("DATAEX");

            for (IXmlCommand cmd : commands) {
                writeCommand(writer, cmd);
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }
    }
    public String export(List<? extends IXmlCommand> commands) throws Exception {
        Path tempFile = Files.createTempFile("masterlink_", ".xml");
        // 转成字符串路径
        String filePath = tempFile.toAbsolutePath().toString();
        export(filePath, commands);
        return filePath;
    }
    private boolean isEmptyField(XmlField field) {
        return true;
    }
    private void writeCommand(XMLStreamWriter writer, IXmlCommand cmd) throws Exception {

        writer.writeStartElement("COMMAND");
        writer.writeAttribute("Name", cmd.getCommandName());
        writer.writeAttribute("TblRef", cmd.getTableName());

        for (XmlField field : cmd.getFields()) {
            if (isEmptyField(field)) {
                writer.writeEmptyElement("FIELD");
            } else {
                writer.writeStartElement("FIELD");
            }

            writer.writeAttribute("FldRef", field.getFldRef());
            writer.writeAttribute("FldValue", String.valueOf(field.getValue()));
            writer.writeAttribute("FldType", field.getFldType());

            if (!isEmptyField(field)) {
                writer.writeEndElement();
            }
        }

        if (cmd.getConditions() != null) {
            for (XmlConditions block : cmd.getConditions()) {

                writer.writeStartElement("CONDITIONS");

                for (XmlCondition c : block.getConditions()) {

                    writer.writeStartElement("CONDITION");

                    writer.writeStartElement("FIELD");
                    writer.writeAttribute("FldRef", c.getFieldRef());
                    writer.writeEndElement();

                    writer.writeStartElement(c.getOperator());
                    writer.writeEndElement();

                    writer.writeStartElement("CONSTANT");
                    writer.writeAttribute("Value", String.valueOf(c.getValue()));
                    writer.writeAttribute("FldType", c.getFldType());
                    writer.writeEndElement();

                    writer.writeEndElement();
                }

                writer.writeEndElement();
            }
        }

        writer.writeEndElement();
    }
}