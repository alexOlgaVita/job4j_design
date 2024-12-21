package ru.job4j.ood.lsp2.service;

import ru.job4j.ood.lsp2.model.*;

import java.util.Arrays;
import java.util.List;

public class ControlParking {

    void execute(Car car, Parking parking) {
        parking.takeParkPlace(car);
    }

    public static void main(String[] args) {
        Store passcarPlace = new PassCarPlace();
        Store trackPlace = new TrackPlace();
        /* вариант распределения мест под разные типы машин  случайным образом
        Parking parking = new Parking(2, 6);
         */
        Car place1 = new Car(1);
        Car place2 = new Car(2);
        Car place3 = new Car(1);
        Car place4 = new Car(1);
        Car place5 = new Car(1);
        Car place6 = new Car(1);
        List<Car> places = Arrays.asList(place1, place2, place3, place4, place5, place6);
        Parking parking = new Parking(places);

        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        Car car3 = new Car(4, "X7LLSRB1HAH548714");
        Car car4 = new Car(2, "X7LLSRB1HAH548715");
        List<Car> cars = Arrays.asList(car1, car2, car3, car4);

        System.out.println("Машины:");
        for (Car car : cars) {
            System.out.println("сar:" + car);
        }

        System.out.println("Места на парковке до распределения машин:");
        for (Car place : parking.getTotalPlaceCarList()) {
            System.out.println(place);
        }

        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }

        System.out.println("Места на парковке после распределения машин:");
        for (Car place : parking.getTotalPlaceCarList()) {
            System.out.println(place);
        }

        parking.freeUpParkPlace(car3);
        System.out.println("Отъехала машина:" + car3);

        System.out.println("Места на парковке после отъезда машины машин:");
        for (Car place : parking.getTotalPlaceCarList()) {
            System.out.println(place);
        }
    }
}
