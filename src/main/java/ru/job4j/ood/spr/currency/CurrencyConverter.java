package ru.job4j.ood.spr.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
