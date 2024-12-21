package ru.job4j.ood.isp;

public class ElectricKitchenStove implements KitchenStove {

    public void gasTurnOn() {
        throw new UnsupportedOperationException();
    }

    public void gasTurnOff() {
        throw new UnsupportedOperationException();
    }

    public void electricHotplateTurnOn() {
        System.out.println("Включаем электрическую конфорку");
    }

    public void electricHotplateTurnOff() {
        System.out.println("Выключаем электрическую конфорку");
    }
}
