package ru.job4j.ood.isp;

public class ClovenHoofed implements Animal {

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
        System.out.println("Мы ходим, топаем и скачем.");
    }

    public void makeSound() {
        System.out.println("Мы говорим на разные голоса.");
    }
}