package ru.job4j.ood.isp;

public class GasKitchenStove implements KitchenStove {

    public void gasTurnOn() {
        System.out.println("Включаем газ");
    }

    public void gasTurnOff() {
        System.out.println("Выключаем газ");
    }

    public void electricHotplateTurnOn() {
        throw new UnsupportedOperationException();
    }

    public void electricHotplateTurnOff() {
        throw new UnsupportedOperationException();
    }
}
