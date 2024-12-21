package ru.job4j.ood.lsp2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.job4j.ood.lsp2.utils.CarUtil.PASS_CAR_SIZE_MAX;
import static ru.job4j.ood.lsp2.utils.CarUtil.isPassCar;

public class Parking {

    private static int PASS_CAR_COUNT_PER_TRACK = 3;
    private int trackPlaceCount;
    private int passCarPlaceCount;

    private List<Car> totalPlaceCarList;

    public Parking(List<Car> places) {
        totalPlaceCarList = new ArrayList<>();
        totalPlaceCarList.addAll(places);
    }

    /**
     * Конструктор создает парковку с указанным количеством мест под каждый вид транспорта, со случайным
     * распределением для легковых и грузовых машин
     *
     * @param trackPlaceCount   кол-во парковочных мест под грузовики
     * @param passCarPlaceCount кол-во парковочных мест под легковые машины
     */
    public Parking(int trackPlaceCount, int passCarPlaceCount) {
        this.trackPlaceCount = trackPlaceCount;
        this.passCarPlaceCount = passCarPlaceCount;
        totalPlaceCarList = new ArrayList<>();
        for (int i = 0; i < trackPlaceCount; i++) {
            totalPlaceCarList.add(new Car(PASS_CAR_SIZE_MAX + 1));
        }
        for (int i = 0; i < passCarPlaceCount; i++) {
            totalPlaceCarList.add(new Car(PASS_CAR_SIZE_MAX));
        }
        Collections.shuffle(totalPlaceCarList);
    }

    public List<Car> getTotalPlaceCarList() {
        return totalPlaceCarList;
    }

    /**
     * Метод размещает машину на парковку
     *
     * @param carNew машина, которую надо разместить
     * @return возвращает true,В случае успешного размещения
     */
    public boolean takeParkPlace(Car carNew) {
        boolean result = false;
        int ind = -1;
        for (Car place : totalPlaceCarList) {
            ind++;
            if (place.getVin() == null) {
                if ((isPassCar(carNew) && isPassCar(place)) || (!isPassCar(carNew) && !isPassCar(place))) {
                    totalPlaceCarList.set(ind, carNew);
                    result = true;
                    break;
                }
            }
        }
        /* если автобомиль грузовой и не было найдено грузовой парковки, то ищем 3 свобоных смежных места для парковки легковых атвомобилей */
        if (!result && !isPassCar(carNew)) {
            List<Integer> passFreeList = new ArrayList<>();
            int previous = -1;
            ind = -1;
            for (Car place : totalPlaceCarList) {
                ind++;
                if (isPassCar(place) && place.getVin() == null) {
                    if (ind != previous + 1) {
                        passFreeList.clear();
                    }
                    passFreeList.add(ind);
                    previous = ind;
                    if (passFreeList.size() == PASS_CAR_COUNT_PER_TRACK) {
                        result = true;
                        break;
                    }
                }
            }
            if (result) {
                for (Integer p : passFreeList) {
                    totalPlaceCarList.set(p, carNew);
                }
            }
        }
        return result;
    }

    /**
     * Метод освобождает парковочное место после отбытия машины
     *
     * @param carNew машина, место который надо пометить, ка совбодное
     */
    public void freeUpParkPlace(Car carNew) {
        Car carEmpty = new Car(isPassCar(carNew) ? 1 : 2);
        totalPlaceCarList.replaceAll(car -> carNew.getVin().equals(car.getVin()) ? carEmpty : car);
    }
}
