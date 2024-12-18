package ru.job4j.ood.lsp.utils;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;
import java.time.Period;

public class Calculator {
    private static int getDaysDiff(LocalDate endDate, LocalDate startDay) {
        Period period = Period.between(endDate, startDay);
        return Math.abs(period.getDays());
    }

    private static int getUsageDaysPercent(int totalCount, int usageCount) {

        return (usageCount * 100) / totalCount;
    }

    public static int getUsageDaysPercent(Food food) {
        int allDaysCount = getDaysDiff(food.getExpiryDate(), food.getCreateDate());
        int usageDaysCount = getDaysDiff(LocalDate.now(), food.getCreateDate());
        return getUsageDaysPercent(allDaysCount, usageDaysCount);
    }
}
