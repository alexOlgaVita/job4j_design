package ru.job4j.ood.isp;

public class RubberBoat implements Boat {

    @Override
    public void engineStart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void row() {
        System.out.println("Гребем в свое удовольствие.");
    }
}
