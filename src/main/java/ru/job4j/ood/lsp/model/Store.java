package ru.job4j.ood.lsp.model;

import java.util.List;

public interface Store extends AutoCloseable {

    Food add(Food food);

    void delete(int id);

    List<Food> findAll();

    List<Food> findByName(String key);

    Food findById(int id);

    boolean matchToPlace(Food food);
}
