package ru.job4j.ood.spr.report;

import ru.job4j.ood.spr.model.Employee;
import ru.job4j.ood.spr.model.Store;

import java.util.function.Predicate;

import static java.util.Comparator.comparingDouble;

public class ReportEngineHr implements Report {

    private final Store store;

    public ReportEngineHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter).stream()
                .sorted(comparingDouble(Employee::getSalary).reversed())
                .toList()) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
