package ru.job4j.ood.spr.report;

import ru.job4j.ood.spr.currency.Currency;
import ru.job4j.ood.spr.currency.CurrencyConverter;
import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngineBuh implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    private final CurrencyConverter currencyConverter;
    private final Currency source;
    private final Currency target;

    public ReportEngineBuh(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter currencyConverter, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
