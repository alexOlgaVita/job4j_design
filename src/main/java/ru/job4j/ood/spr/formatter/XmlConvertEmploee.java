package ru.job4j.ood.spr.formatter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.Employees;
import ru.job4j.ood.spr.serialization.CalendarAdapter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

public class XmlConvertEmploee implements DataConvert<Employee> {

    private final DateTimeParser<Calendar> dateTimeParser;

    public XmlConvertEmploee(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String convert(List<Employee> data) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setAdapter(new CalendarAdapter(dateTimeParser));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        Employees employees = new Employees();
        employees.setEmployees(data);
        /*
        marshaller.marshal(employees, System.out);
        */
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
