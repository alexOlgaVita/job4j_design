package ru.job4j.ood.lsp.model;

import static ru.job4j.ood.lsp.utils.Calculator.getUsageDaysPercent;

public class Warehouse extends AbstractStore {

    @Override
    public boolean matchToPlace(Food food) {
        return getUsageDaysPercent(food) < PERCENT_TO_SHOP_WITHOUT_DISCOUNT_MIN;
    }

    @Override
    public Food add(Food food) {
        food.setDiscount(0);
        return super.add(food);
    }
}
