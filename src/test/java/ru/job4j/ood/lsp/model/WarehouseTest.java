package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {
    @Test
    public void whenAddNewFoodThenWarehouseHasSameFood() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food();
        food.setName("Молоко");
        food.setDiscount(0F);
        food.setCreateDate(LocalDate.now().minusDays(10));
        food.setExpiryDate(LocalDate.now().minusDays(1));
        food.setPrice(98.2F);
        warehouse.add(food);
        Food result = warehouse.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindById() {
        Warehouse warehouse = new Warehouse();
        Food cheese = new Food("Сыр",
                LocalDate.now().minusDays(9),
                LocalDate.now().minusDays(2),
                108.18F);
        Food food = warehouse.add(cheese);
        Food result = warehouse.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindAll() {
        Warehouse warehouse = new Warehouse();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        warehouse.add(first);
        warehouse.add(second);
        assertThat(warehouse.findAll().get(0).getName()).isEqualTo(first.getName());
        assertThat(warehouse.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Warehouse warehouse = new Warehouse();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        warehouse.add(first);
        warehouse.add(second);
        warehouse.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        warehouse.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                188.19F));
        warehouse.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        List<Food> result = warehouse.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondFoodName() {
        Warehouse warehouse = new Warehouse();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        warehouse.add(first);
        warehouse.add(second);
        warehouse.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        warehouse.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F));
        warehouse.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        assertThat(warehouse.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplaceFoodIsSuccessful() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        warehouse.add(food);
        int id = food.getId();
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        warehouse.replace(id, updateFood);
        assertThat(warehouse.findById(id).getName()).isEqualTo("Творог полезный");
    }

    @Test
    public void whenReplaceFoodIsNotSuccessful() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        warehouse.add(food);
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        boolean result = warehouse.replace(1000, updateFood);
        assertThat(warehouse.findById(food.getId()).getName()).isEqualTo("Творог");
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteFoodIsSuccessful() {
        Store warehouse = new Warehouse();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        warehouse.add(food);
        int id = food.getId();
        warehouse.delete(id);
        assertThat(warehouse.findById(id)).isNull();
    }

    @Test
    public void whenDeleteFoodIsNotSuccessful() {
        Store warehouse = new Warehouse();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        warehouse.add(food);
        warehouse.delete(1000);
        assertThat(warehouse.findById(food.getId()).getName()).isEqualTo("Творог");
    }
}