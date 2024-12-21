package ru.job4j.ood.lsp2.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CarStore implements Store {

    private final List<Car> cars = new ArrayList<>();
    private int ids = 1;

    private int indexOf(int id) {
        int result = -1;
        for (int index = 0; index < cars.size(); index++) {
            if (cars.get(index).getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

    public Car add(Car car) {
        car.setId(ids++);
        cars.add(car);
        return car;
    }

    public Car findById(int id) {
        int index = indexOf(id);
        return index != -1 ? cars.get(index) : null;
    }

    public List<Car> findByVin(String vin) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> findAll() {
        return List.copyOf(cars);
    }

    public boolean replace(int id, Car car) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            car.setId(id);
            cars.set(index, car);
        }
        return result;
    }

    public void delete(int id) {
        int i = indexOf(id);
        if (i != -1) {
            cars.remove(i);
        }
    }

    @Override
    public void close() throws Exception {
    }
}
