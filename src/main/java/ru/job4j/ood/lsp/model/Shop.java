package ru.job4j.ood.lsp.model;

import ru.job4j.ood.lsp.utils.Calculator;

import static ru.job4j.ood.lsp.utils.Calculator.getUsageDaysPercent;

public class Shop extends AbstractStore {

    public static final int DISCOUNT = 20;

    @Override
    public boolean matchToPlace(Food food) {
        return getUsageDaysPercent(food) >= PERCENT_TO_SHOP_WITHOUT_DISCOUNT_MIN
                && getUsageDaysPercent(food) < PERCENT_TO_TRASH_MIN;
    }

    @Override
    public Food add(Food food) {
        if (Calculator.getUsageDaysPercent(food) > PERCENT_TO_SHOP_WITHOUT_DISCOUNT_MAX) {
            food.setDiscount(DISCOUNT);
        }
        return super.add(food);
    }
}
