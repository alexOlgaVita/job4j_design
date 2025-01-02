package ru.job4j.ood.lsp.model;

import static ru.job4j.ood.lsp.utils.Calculator.getUsageDaysPercent;

public class Trash extends AbstractStore {

    @Override
    public boolean matchToPlace(Food food) {
        return getUsageDaysPercent(food) >= PERCENT_TO_TRASH_MIN;
    }

    @Override
    public Food add(Food food) {
        food.setDiscount(0);
        return super.add(food);
    }
}
