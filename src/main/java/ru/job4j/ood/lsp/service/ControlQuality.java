package ru.job4j.ood.lsp.service;

import ru.job4j.ood.lsp.model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class ControlQuality {

    public static final int SHOP_DISCOUNT = 20;

    void execute(Food food, Store[] stores) {
        /*        void execute(Food food, Store warehouse, Store shop, Store trash) { */
        int allDaysCount = getDaysDiff(food.getExpiryDate(), food.getCreateDate());
        int usageDaysCount = getDaysDiff(LocalDate.now(), food.getCreateDate());
        int usagePercent = getUsageDaysPercent(allDaysCount, usageDaysCount);
        if (usagePercent < 25) {
            /* Warehouse */
            moveToStore(food, stores[0]);
        } else if (usagePercent <= 75) {
            /* Shop */
            moveToStore(food, stores[1]);
        } else if (usagePercent < 100) {
            /* Shop */
            moveToStore(food, stores[1], SHOP_DISCOUNT);
        } else {
            /* Trash */
            moveToStore(food, stores[2]);
        }
    }

    private int getDaysDiff(LocalDate endDate, LocalDate startDay) {
        Period period = Period.between(endDate, startDay);
        return Math.abs(period.getDays());
    }

    private int getUsageDaysPercent(int totalCount, int usageCount) {
        return (usageCount * 100) / totalCount;
    }

    private void moveToStore(Food food, Store store) {
        store.add(food);
    }

    private void moveToStore(Food food, Store store, int discount) {
        food.setDiscount(discount);
        store.add(food);
    }

    public static void main(String[] args) {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(1),
                98.2F);
        Food food2 = new Food("Хлеб",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(1),
                52.43F);
        Food food3 = new Food("Сыр",
                LocalDate.now().minusDays(10),
                LocalDate.now(),
                109.17F);
        Food food4 = new Food("Картошка",
                LocalDate.now(),
                LocalDate.now().plusDays(12),
                49.87F);
        List<Food> foods = Arrays.asList(food1, food2, food3, food4);
        ControlQuality controlQuality = new ControlQuality();
        for (Food f : foods) {
            controlQuality.execute(f, stores);
        }
        System.out.println("Warehouse:");
        List<Food> wareHouseFoods = warehouse.findAll();
        for (Food f : wareHouseFoods) {
            System.out.println("f = " + f);
        }
        System.out.println("Shop:");
        List<Food> shopFoods = shop.findAll();
        for (Food f : shopFoods) {
            System.out.println("f = " + f);
        }

        System.out.println("Trash:");
        List<Food> trashFoods = trash.findAll();
        for (Food f : trashFoods) {
            System.out.println("f = " + f);
        }
    }
}
