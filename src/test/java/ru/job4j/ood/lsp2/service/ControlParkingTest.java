package ru.job4j.ood.lsp2.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp2.model.Car;
import ru.job4j.ood.lsp2.model.Parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.lsp2.utils.CarUtil.PASS_CAR_SIZE;
import static ru.job4j.ood.lsp2.utils.CarUtil.TRACK_SIZE_MIN;

class ControlParkingTest {
    @Test
    void when1PassCar1TrackNotPassCarPlacesAre2TrackPlaces() {
        Car place1 = new Car(TRACK_SIZE_MIN);
        Car place2 = new Car(TRACK_SIZE_MIN);
        List<Car> places = Arrays.asList(place1, place2);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(1).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(1).getSize()).isEqualTo(TRACK_SIZE_MIN);
    }

    @Test
    void when1PassCar1TrackAre3PassCarPlacesNotTrackPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(PASS_CAR_SIZE);
        Car place3 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        /* не используем это, т.к. не гарантирует прядок элементов
         List<Car> cars = Arrays.asList(car2, car1);
         */
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car1);
        assertThat(parking.getTotalPlaceCarList().get(1).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(1).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(2).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(2).getSize()).isEqualTo(1);
    }

    @Test
    void when1Track1PassCarAre3PassCarPlacesNotTrackPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(PASS_CAR_SIZE);
        Car place3 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car2);
        cars.add(car1);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2)).isEqualTo(car2);
    }

    @Test
    void when1PassCar1TrackAre4PassCarPlacesNotTrackPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(PASS_CAR_SIZE);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3, place4);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car1);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(3)).isEqualTo(car2);
    }

    @Test
    void when1Track1PassCarAre4PassCarPlacesNotTrackPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(PASS_CAR_SIZE);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3, place4);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car2);
        cars.add(car1);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(3)).isEqualTo(car1);
    }

    @Test
    void when1PassCar1TrackAre4PassCarPlaces1TrackPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(PASS_CAR_SIZE);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        Car place5 = new Car(TRACK_SIZE_MIN);
        List<Car> places = Arrays.asList(place1, place2, place3, place4, place5);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car1);
        assertThat(parking.getTotalPlaceCarList().get(1).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(1).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(2).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(2).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(3).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(3).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(4)).isEqualTo(car2);
    }

    @Test
    void whenNotEnoughPassCarPlacesPer1Track() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(TRACK_SIZE_MIN);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        Car place5 = new Car(PASS_CAR_SIZE);
        Car place6 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3, place4, place5, place6);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        Car car3 = new Car(4, "X7LLSRB1HAH548714");
        Car car4 = new Car(2, "X7LLSRB1HAH548715");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car1);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2)).isEqualTo(car3);
        assertThat(parking.getTotalPlaceCarList().get(3)).isEqualTo(car3);
        assertThat(parking.getTotalPlaceCarList().get(4)).isEqualTo(car3);
        assertThat(parking.getTotalPlaceCarList().get(5).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(5).getSize()).isEqualTo(1);
    }

    @Test
    void whenFreeUpPassCarThenFree1PassCarPlace() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(TRACK_SIZE_MIN);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        Car place5 = new Car(PASS_CAR_SIZE);
        Car place6 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3, place4, place5, place6);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        parking.freeUpParkPlace(car1);
        assertThat(parking.getTotalPlaceCarList().get(0).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(0).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(2).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(3).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(3).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(4).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(4).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(5).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(5).getSize()).isEqualTo(1);
    }

    @Test
    void whenFreeUpTrackThenFree3PassCarPlaces() {
        Car place1 = new Car(PASS_CAR_SIZE);
        Car place2 = new Car(TRACK_SIZE_MIN);
        Car place3 = new Car(PASS_CAR_SIZE);
        Car place4 = new Car(PASS_CAR_SIZE);
        Car place5 = new Car(PASS_CAR_SIZE);
        Car place6 = new Car(PASS_CAR_SIZE);
        List<Car> places = Arrays.asList(place1, place2, place3, place4, place5, place6);
        Parking parking = new Parking(places);
        Car car1 = new Car(1, "X7LLSRB1HAH548712");
        Car car2 = new Car(2, "X7LLSRB1HAH548713");
        Car car3 = new Car(4, "X7LLSRB1HAH548714");
        Car car4 = new Car(2, "X7LLSRB1HAH548715");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        ControlParking controlParking = new ControlParking();
        for (Car car : cars) {
            controlParking.execute(car, parking);
        }
        parking.freeUpParkPlace(car3);
        assertThat(parking.getTotalPlaceCarList().get(0)).isEqualTo(car1);
        assertThat(parking.getTotalPlaceCarList().get(1)).isEqualTo(car2);
        assertThat(parking.getTotalPlaceCarList().get(2).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(2).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(3).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(3).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(4).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(4).getSize()).isEqualTo(1);
        assertThat(parking.getTotalPlaceCarList().get(5).getVin()).isNull();
        assertThat(parking.getTotalPlaceCarList().get(5).getSize()).isEqualTo(1);
    }
}