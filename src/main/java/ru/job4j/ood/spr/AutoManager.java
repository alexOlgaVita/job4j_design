package ru.job4j.ood.spr;

/* нарушение принципа SPR в наличии 2 целей вместо 1: обновить информацию по автомобилю и проведение тест-драйва*/
public class AutoManager {
    private String autoData;

    public void updateAuto(String data) {
        /* Логика управления автомобилем */
    }

    public boolean testDrive() {
        /* Логика проведения тест-драйва */
        return false;
    }
}
