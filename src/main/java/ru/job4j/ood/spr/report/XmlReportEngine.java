package ru.job4j.ood.spr.report;

import jakarta.xml.bind.JAXBException;
import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.formatter.XmlConvertEmploee;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XmlReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        XmlConvertEmploee xmlConvertEmploee = new XmlConvertEmploee(dateTimeParser);
        try {
            return xmlConvertEmploee.convert(store.findBy(filter));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
