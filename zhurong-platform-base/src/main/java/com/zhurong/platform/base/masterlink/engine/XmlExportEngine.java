package com.zhurong.platform.base.masterlink.engine;

import com.zhurong.platform.base.masterlink.core.IXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlCondition;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class XmlExportEngine {

    public void export(String filePath, List<IXmlCommand> commands) throws Exception {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try (FileOutputStream fos = new FileOutputStream(filePath)) {

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