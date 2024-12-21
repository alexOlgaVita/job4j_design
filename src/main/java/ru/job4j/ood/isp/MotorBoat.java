package ru.job4j.ood.isp;

public class MotorBoat implements Boat {

    @Override
    public void engineStart() {
        System.out.println("Вдарим по газам.");
    }

    @Override
    public void row() {
        throw new UnsupportedOperationException();
    }
}
