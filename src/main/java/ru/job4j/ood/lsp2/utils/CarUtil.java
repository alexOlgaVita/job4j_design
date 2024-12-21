package ru.job4j.ood.lsp2.utils;

import ru.job4j.ood.lsp2.model.Car;

public class CarUtil {
    public static final int PASS_CAR_SIZE_MAX = 1;

    public static boolean isPassCar(Car car) {
        return car.getSize() == PASS_CAR_SIZE_MAX;
    }
}
