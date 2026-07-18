package com.zhurong.platform.base.lantek.expert.lstx;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public class ExpertProductXmlExporter {

    public String export(Collection<ExpertProductXmlItem> products) throws IOException, XMLStreamException {
        Path xmlFile = Files.createTempFile("expert_products_", ".xml");
        return export(products, xmlFile);
    }

    public String export(Collection<ExpertProductXmlItem> products, Path xmlFile) throws IOException, XMLStreamException {
        return export((Iterable<ExpertProductXmlItem>) products, xmlFile);
    }

    public String export(Iterable<ExpertProductXmlItem> products, Path xmlFile) throws IOException, XMLStreamException {
        if (xmlFile.getParent() != null) {
            Files.createDirectories(xmlFile.getParent());
        }

        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try (BufferedWriter output = Files.newBufferedWriter(xmlFile, StandardCharsets.UTF_8)) {
            output.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            XMLStreamWriter writer = factory.createXMLStreamWriter(output);
            try {
                writeList(writer, products);
            } finally {
                writer.close();
            }
        }
        return xmlFile.toAbsolutePath().toString();
    }

    private void writeList(XMLStreamWriter writer, Iterable<ExpertProductXmlItem> products) throws XMLStreamException {
        newline(writer, 0);
        writer.writeStartElement("List");
        for (ExpertProductXmlItem product : products) {
            writeProduct(writer, product);
        }
        newline(writer, 0);
        writer.writeEndElement();
    }

    private void writeProduct(XMLStreamWriter writer, ExpertProductXmlItem product) throws XMLStreamException {
        newline(writer, 1);
        writer.writeStartElement("Product");
        for (Map.Entry<String, String> attribute : product.productAttributes().entrySet()) {
            writer.writeAttribute(attribute.getKey(), attribute.getValue());
        }

        writeRotations(writer, product);
        writeFills(writer, product);
        writeAuxiliarData(writer, product);

        newline(writer, 1);
        writer.writeEndElement();
    }

    private void writeRotations(XMLStreamWriter writer, ExpertProductXmlItem product) throws XMLStreamException {
        newline(writer, 2);
        writer.writeStartElement("Rotations");

        writeRotationGroup(writer, "Normal", product.normalRotations());
        writeRotationGroup(writer, "Symmetrics", product.symmetricRotations());

        newline(writer, 2);
        writer.writeEndElement();
    }

    private void writeRotationGroup(
            XMLStreamWriter writer,
            String groupName,
            ExpertProductXmlItem.RotationValues values
    ) throws XMLStreamException {
        newline(writer, 3);
        writer.writeStartElement(groupName);

        writeValueElement(writer, "Rotation0", values.rotation0(), 4);
        writeValueElement(writer, "Rotation90", values.rotation90(), 4);
        writeValueElement(writer, "Rotation180", values.rotation180(), 4);
        writeValueElement(writer, "Rotation270", values.rotation270(), 4);
        writeValueElement(writer, "Others", values.others(), 4);

        newline(writer, 3);
        writer.writeEndElement();
    }

    private void writeValueElement(XMLStreamWriter writer, String elementName, String value, int level)
            throws XMLStreamException {
        newline(writer, level);
        writer.writeEmptyElement(elementName);
        writer.writeAttribute("value", value);
    }

    private void writeFills(XMLStreamWriter writer, ExpertProductXmlItem product) throws XMLStreamException {
        newline(writer, 2);
        writer.writeEmptyElement("Fills");
        writer.writeAttribute("value", product.fillsValue());
        writer.writeAttribute("hollows", product.hollows());
    }

    private void writeAuxiliarData(XMLStreamWriter writer, ExpertProductXmlItem product) throws XMLStreamException {
        newline(writer, 2);
        writer.writeStartElement("AuxiliarData");
        for (Map.Entry<String, String> item : product.auxiliarData().entrySet()) {
            newline(writer, 3);
            writer.writeEmptyElement("Data");
            writer.writeAttribute("DBField", item.getKey());
            writer.writeAttribute("value", item.getValue());
        }
        newline(writer, 2);
        writer.writeEndElement();
    }

    private void newline(XMLStreamWriter writer, int level) throws XMLStreamException {
        writer.writeCharacters(System.lineSeparator());
        writer.writeCharacters("\t".repeat(level));
    }
}
