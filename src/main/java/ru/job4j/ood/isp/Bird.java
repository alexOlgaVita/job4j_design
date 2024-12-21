package ru.job4j.ood.isp;

public class Bird implements Animal {

    @Override
    public void swim() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fly() {
        System.out.println("Мы летим.");
    }

    public void eat() {
        System.out.println("Мы кушаем.");
    }

    public void foot() {
        System.out.println("Мы ходим.");
    }

    public void makeSound() {
        System.out.println("Мы говорим на разные голоса.");
    }
}
