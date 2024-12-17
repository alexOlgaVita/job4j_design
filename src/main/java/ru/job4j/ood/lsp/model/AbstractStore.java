package ru.job4j.ood.lsp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();
    private int ids = 1;

    private int indexOf(int id) {
        int result = -1;
        for (int index = 0; index < foods.size(); index++) {
            if (foods.get(index).getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

    public Food add(Food food) {
        food.setId(ids++);
        foods.add(food);
        return food;
    }

    public Food findById(int id) {
        int index = indexOf(id);
        return index != -1 ? foods.get(index) : null;
    }

    public List<Food> findByName(String key) {
        List<Food> result = new ArrayList<>();
        for (Food food : foods) {
            if (food.getName().equals(key)) {
                result.add(food);
            }
        }
        return result;
    }

    public List<Food> findAll() {
        return List.copyOf(foods);
    }

    public boolean replace(int id, Food food) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            food.setId(id);
            foods.set(index, food);
        }
        return result;
    }

    public void delete(int id) {
        int i = indexOf(id);
        if (i != -1) {
            foods.remove(i);
        }
    }

    @Override
    public void close() throws Exception {
    }
}
