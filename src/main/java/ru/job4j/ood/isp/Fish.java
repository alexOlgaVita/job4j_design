package ru.job4j.ood.isp;

public class Fish implements Animal {

    @Override
    public void swim() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    public void eat() {
        System.out.println("Мы кушаем.");
    }

    public void foot() {
        throw new UnsupportedOperationException();
    }

    public void makeSound() {
        throw new UnsupportedOperationException();
    }
}
