package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pet")
public class Pet {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String nameAnimal;
    @XmlAttribute
    private int age;

    public Pet(String name, String nameAnimal, int age) {
        this.name = name;
        this.nameAnimal = nameAnimal;
        this.age = age;
    }

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{"
                + "nameAnimal='" + nameAnimal + '\''
                + "name='" + name + '\''
                + "age='" + age + '\''
                + '}';
    }

    public String getName() {
        return name;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public int getAge() {
        return age;
    }
}
