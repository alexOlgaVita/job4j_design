package ru.job4j.ood.lsp2.utils;

import ru.job4j.ood.lsp2.model.Car;

public class CarUtil {
    public static final int PASS_CAR_SIZE = 1;
    public static final int TRACK_SIZE_MIN = 2;

    public static boolean isPassCar(Car car) {
        return car.getSize() == PASS_CAR_SIZE;
    }
}
