package ru.job4j.ood.lsp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {
    @Test
    public void whenAddNewFoodThenTrashHasSameFood() {
        Trash trash = new Trash();
        Food food = new Food();
        food.setName("Молоко");
        food.setDiscount(0F);
        food.setCreateDate(LocalDate.now().minusDays(10));
        food.setExpiryDate(LocalDate.now().minusDays(1));
        food.setPrice(98.2F);
        trash.add(food);
        Food result = trash.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindById() {
        Trash trash = new Trash();
        Food cheese = new Food("Сыр",
                LocalDate.now().minusDays(9),
                LocalDate.now().minusDays(2),
                108.18F);
        Food food = trash.add(cheese);
        Food result = trash.findById(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenTestFindAll() {
        Trash trash = new Trash();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        trash.add(first);
        trash.add(second);
        assertThat(trash.findAll().get(0).getName()).isEqualTo(first.getName());
        assertThat(trash.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Trash trash = new Trash();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        trash.add(first);
        trash.add(second);
        trash.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        trash.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                188.19F));
        trash.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        List<Food> result = trash.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondFoodName() {
        Trash trash = new Trash();
        Food first = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        Food second = new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F);
        trash.add(first);
        trash.add(second);
        trash.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        trash.add(new Food("Хлеб",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(5),
                88.19F));
        trash.add(new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F));
        assertThat(trash.findAll().get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplaceFoodIsSuccessful() {
        Trash trash = new Trash();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        trash.add(food);
        int id = food.getId();
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        trash.replace(id, updateFood);
        assertThat(trash.findById(id).getName()).isEqualTo("Творог полезный");
    }

    @Test
    public void whenReplaceFoodIsNotSuccessful() {
        Trash trash = new Trash();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        trash.add(food);
        Food updateFood = new Food("Творог полезный",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        boolean result = trash.replace(1000, updateFood);
        assertThat(trash.findById(food.getId()).getName()).isEqualTo("Творог");
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteFoodIsSuccessful() {
        Store trash = new Trash();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        trash.add(food);
        int id = food.getId();
        trash.delete(id);
        assertThat(trash.findById(id)).isNull();
    }

    @Test
    public void whenDeleteFoodIsNotSuccessful() {
        Store trash = new Trash();
        Food food = new Food("Творог",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(3),
                108.18F);
        trash.add(food);
        trash.delete(1000);
        assertThat(trash.findById(food.getId()).getName()).isEqualTo("Творог");
    }
}