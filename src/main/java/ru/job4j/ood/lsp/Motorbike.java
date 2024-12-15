package ru.job4j.ood.lsp;

public class Motorbike extends Transport {
    private int id;

    @Override
    boolean techCheck() {
        System.out.println("Результат тех. осмотра");
        return getResultById(id);
    }

    private boolean getResultById(int id) {
        return true;
    }
}
