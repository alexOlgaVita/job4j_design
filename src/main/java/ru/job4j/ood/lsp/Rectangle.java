package ru.job4j.ood.lsp;

/* Нарушение принципа LSP в том, что реализация этой геометрической фигуры "квадрат" переопредяелт один из параметров -
сторону или ширину, в резльутате его корректное вычисление площади невозможно */
public class Rectangle {
    protected int width;
    protected int height;

    public int calculateArea() {
        return width * height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
