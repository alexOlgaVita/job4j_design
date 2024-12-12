package ru.job4j.ood.spr.report;

import ru.job4j.ood.spr.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
