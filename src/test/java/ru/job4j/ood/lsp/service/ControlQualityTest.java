package ru.job4j.ood.lsp.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.lsp.model.Shop.DISCOUNT;

class ControlQualityTest {
    @Test
    void whenLess25PercentThenWarehouse() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(16),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().get(0)).isEqualTo(food1);
        assertThat(warehouse.findAll().get(0).getDiscount()).isEqualTo(0);
        assertThat(warehouse.findAll().size()).isEqualTo(1);
        assertThat(shop.findAll().size()).isEqualTo(0);
        assertThat(trash.findAll().size()).isEqualTo(0);
    }

    @Test
    void whenEqual25PercentThenShopWhithoutDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(15),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().size()).isEqualTo(0);
        assertThat(shop.findAll().get(0)).isEqualTo(food1);
        assertThat(shop.findAll().get(0).getDiscount()).isEqualTo(0.0);
        assertThat(shop.findAll().size()).isEqualTo(1);
        assertThat(trash.findAll().size()).isEqualTo(0);
    }

    @Test
    void whenEqual75PercentThenShopWhithoutDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(5),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().size()).isEqualTo(0);
        assertThat(shop.findAll().get(0)).isEqualTo(food1);
        assertThat(shop.findAll().get(0).getDiscount()).isEqualTo(0);
        assertThat(shop.findAll().size()).isEqualTo(1);
        assertThat(trash.findAll().size()).isEqualTo(0);
    }

    @Test
    void whenEqual76PercentThenShopWhithDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(4),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().size()).isEqualTo(0);
        assertThat(shop.findAll().get(0)).isEqualTo(food1);
        assertThat(shop.findAll().get(0).getDiscount()).isEqualTo(DISCOUNT);
        assertThat(shop.findAll().size()).isEqualTo(1);
        assertThat(trash.findAll().size()).isEqualTo(0);
    }

    @Test
    void whenEqual99PercentThenShopWhithDiscount() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(99),
                LocalDate.now().plusDays(1),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().size()).isEqualTo(0);
        assertThat(shop.findAll().get(0)).isEqualTo(food1);
        assertThat(shop.findAll().get(0).getDiscount()).isEqualTo(DISCOUNT);
        assertThat(shop.findAll().size()).isEqualTo(1);
        assertThat(trash.findAll().size()).isEqualTo(0);
    }

    @Test
    void whenEqual100PercentThenTrash() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        Store[] stores = new Store[]{warehouse, shop, trash};
        Food food1 = new Food("Молоко",
                LocalDate.now().minusDays(15),
                LocalDate.now(),
                98.2F);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.execute(food1, stores);
        assertThat(warehouse.findAll().size()).isEqualTo(0);
        assertThat(shop.findAll().size()).isEqualTo(0);
        assertThat(trash.findAll().size()).isEqualTo(1);
        assertThat(trash.findAll().get(0)).isEqualTo(food1);
        assertThat(trash.findAll().get(0).getDiscount()).isEqualTo(0);
    }

    @Test
    void whenSomeFoodsToDiffStores() {
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
        assertThat(warehouse.findAll().get(0)).isEqualTo(food4);
    }
}