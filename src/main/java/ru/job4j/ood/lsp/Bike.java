package ru.job4j.ood.lsp;


/* Нарушение принципа LSP в том, что для данного наследника нет полноценной реализации метода "тех.проверка", т.к.
этот тип трапсорта не предусматривает этот тех.осмотр */
public class Bike extends Transport {

    @Override
    boolean techCheck() {
        System.out.println("Тех. осмотр не предусмотрен для этого транспорта и поэтому результат не определен");
        throw new IllegalArgumentException();
    }
}
