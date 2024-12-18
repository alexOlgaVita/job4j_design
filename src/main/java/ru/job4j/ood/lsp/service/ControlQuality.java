package ru.job4j.ood.lsp.service;

import ru.job4j.ood.lsp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ControlQuality {

    void execute(Food food, Store[] stores) {
        Arrays.stream(stores)
                .filter(s -> s.matchToPlace(food))
                .forEach(s -> s.add(food));
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
