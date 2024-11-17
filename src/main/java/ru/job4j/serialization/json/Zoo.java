package ru.job4j.serialization.json;

import java.util.Arrays;

public class Zoo {
    private final boolean municipal;
    private final int period;
    private final Pet pet;
    private final String[] animalTypes;

    public Zoo(boolean municipal, int period, Pet pet, String[] animalTypes) {
        this.municipal = municipal;
        this.period = period;
        this.pet = pet;
        this.animalTypes = animalTypes;
    }

    @Override
    public String toString() {
        return "Zoo{"
                + "municipal=" + municipal
                + ", period=" + period
                + ", pet=" + pet
                + ", animalTypes=" + Arrays.toString(animalTypes)
                + '}';
    }
}
