package ru.job4j.ood.spr.report;

import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.formatter.JsonConvertEmploee;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.Store;
import ru.job4j.ood.spr.serialization.EmployeeConverter;

import java.util.Calendar;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public JsonReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        EmployeeConverter employeeConverter = new EmployeeConverter(dateTimeParser);
        JsonConvertEmploee jsonConvert = new JsonConvertEmploee(employeeConverter);
        return jsonConvert.convert(store.findBy(filter));
    }
}
