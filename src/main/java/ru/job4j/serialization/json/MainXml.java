package ru.job4j.serialization.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class MainXml {
    public static void main(String[] args) throws Exception {
        Zoo zoo = new Zoo(false, 10, new Pet("Margo", "Cat", 2),
                new String[]{"Cat", "Dog", "Turtle"});
        JAXBContext context = JAXBContext.newInstance(Zoo.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(zoo, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Zoo result = (Zoo) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
