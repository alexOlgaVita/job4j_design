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

class XmlReportEngineTest {
    @Test
    void when2RowsGenerated() throws JAXBException {
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
        Report report = new XmlReportEngine(store, parser);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Doe</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>5000.0</salary>
                    </employee>
                    <employee>
                        <name>Jane Smith</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>6000.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    void when1RowGenerated() throws JAXBException {
        Store store = new MemoryStore();
        Employee employee = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        store.add(employee);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report report = new XmlReportEngine(store, parser);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Doe</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>5000.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    void whenEmptyRowGenerated() throws JAXBException {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report report = new XmlReportEngine(store, parser);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees/>
                """;
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }
}