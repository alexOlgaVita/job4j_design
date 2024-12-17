package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    public void whenAddNewFoodThenShopHasSameFood() {
        Shop shop = new Shop();
        Food food = new Food();
        food.setName("Молоко");
        food.setDiscount(0F);
        food.setCreateDate(LocalDate.now().minusDays(10));
        food.setExpiryDate(LocalDate.now().minusDays(1));
        food.setPrice(98.2F);
        shop.add(food);
        Food result = shop.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindById() {
        Shop shop = new Shop();
        Food cheese = new Food("Сыр",
                LocalDate.now().minusDays(9),
                LocalDate.now().minusDays(2),
                108.18F);
        Food food = shop.add(cheese);
        Food result = shop.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindAll() {
        Shop shop = new Shop();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        shop.add(first);
        shop.add(second);
        assertThat(shop.findAll().get(0).getName()).isEqualTo(first.getName());
        assertThat(shop.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Shop shop = new Shop();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        shop.add(first);
        shop.add(second);
        shop.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        shop.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                188.19F));
        shop.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        List<Food> result = shop.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondFoodName() {
        Shop shop = new Shop();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        shop.add(first);
        shop.add(second);
        shop.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        shop.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F));
        shop.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        assertThat(shop.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplaceFoodIsSuccessful() {
        Shop shop = new Shop();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        shop.add(food);
        int id = food.getId();
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        shop.replace(id, updateFood);
        assertThat(shop.findById(id).getName()).isEqualTo("Творог полезный");
    }

    @Test
    public void whenReplaceFoodIsNotSuccessful() {
        Shop shop = new Shop();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        shop.add(food);
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        boolean result = shop.replace(1000, updateFood);
        assertThat(shop.findById(food.getId()).getName()).isEqualTo("Творог");
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteFoodIsSuccessful() {
        Store shop = new Shop();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        shop.add(food);
        int id = food.getId();
        shop.delete(id);
        assertThat(shop.findById(id)).isNull();
    }

    @Test
    public void whenDeleteFoodIsNotSuccessful() {
        Store shop = new Shop();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        shop.add(food);
        shop.delete(1000);
        assertThat(shop.findById(food.getId()).getName()).isEqualTo("Творог");
    }
}