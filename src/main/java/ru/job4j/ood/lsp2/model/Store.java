package ru.job4j.ood.lsp2.model;

import java.util.List;

public interface Store extends AutoCloseable {

    Car add(Car car);

    void delete(int id);

    List<Car> findAll();


    Car findById(int id);

    public List<Car> findByVin(String vin);
}
