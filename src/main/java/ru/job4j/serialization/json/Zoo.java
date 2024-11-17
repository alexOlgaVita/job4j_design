package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "zoo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Zoo {
    @XmlAttribute
    private boolean municipal;
    @XmlAttribute
    private int period;
    private Pet pet;
    @XmlElementWrapper(name = "animalTypes")
    @XmlElement(name = "animalType")
    private String[] animalTypes;

    public Zoo(boolean municipal, int period, Pet pet, String[] animalTypes) {
        this.municipal = municipal;
        this.period = period;
        this.pet = pet;
        this.animalTypes = animalTypes;
    }

    public Zoo() {
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

    public boolean getMunicipal() {
        return municipal;
    }

    public int getPeriod() {
        return period;
    }

    public Pet getPet() {
        return pet;
    }

    public String[] getAnimalTypes() {
        return animalTypes;
    }
}
