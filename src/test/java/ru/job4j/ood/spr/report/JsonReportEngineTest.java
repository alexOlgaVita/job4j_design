package ru.job4j.ood.spr.report;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.formatter.ReportDateTimeParser;
import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.MemoryStore;
import ru.job4j.ood.spr.model.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class JsonReportEngineTest {
    @Test
    void when2RowsJsonGenerated() throws JAXBException {
        Store store = new MemoryStore();
        Employee employee = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        Employee employee1 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
        store.add(employee);
        store.add(employee1);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new JsonReportEngine(store, parser);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }

    @Test
    void when1RowJsonGenerated() throws JAXBException {
        Store store = new MemoryStore();
        Employee employee = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        store.add(employee);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new JsonReportEngine(store, parser);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }

    @Test
    void whenEmptyJsonGenerated() throws JAXBException {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new JsonReportEngine(store, parser);
        String ex = "[]";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }
}