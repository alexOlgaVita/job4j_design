package ru.job4j.ood.spr.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.spr.currency.Currency;
import ru.job4j.ood.spr.currency.CurrencyConverter;
import ru.job4j.ood.spr.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.formatter.ReportDateTimeParser;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineBuhTest {

    @Test
    public void when2RowsBuhGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Calendar now2 = Calendar.getInstance();
        now2.add(Calendar.MONTH, -1);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Olga", now2, now2, 101);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Report engine = new ReportEngineBuh(store, parser, currencyConverter, Currency.RUB, Currency.EUR);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currencyConverter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(parser.parse(worker2.getHired())).append(" ")
                .append(parser.parse(worker2.getFired())).append(" ")
                .append(currencyConverter.convert(Currency.RUB, worker2.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenEmptyBuhGenerated() {
        MemoryStore store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Report engine = new ReportEngineBuh(store, parser, currencyConverter, Currency.RUB, Currency.EUR);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void when1RowBuhGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Report engine = new ReportEngineBuh(store, parser, currencyConverter, Currency.RUB, Currency.EUR);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currencyConverter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}