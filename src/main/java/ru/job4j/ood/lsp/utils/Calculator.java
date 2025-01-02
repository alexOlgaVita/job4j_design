package ru.job4j.ood.lsp.utils;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculator {
    private static int getDaysDiff(LocalDate endDate, LocalDate startDay) {
        return (int) ChronoUnit.DAYS.between(endDate, startDay);
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
