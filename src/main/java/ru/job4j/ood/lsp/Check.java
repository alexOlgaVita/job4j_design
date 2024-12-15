package ru.job4j.ood.lsp;

public class Check {

    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.setWidth(2);
        r.setHeight(5);
        System.out.printf("Площадь прямоугольника = %s", r.calculateArea());
        r = new Square();
        r.setWidth(2);
        r.setHeight(5);
        System.out.printf("Площадь квадрата = %s", r.calculateArea());
    }
}
