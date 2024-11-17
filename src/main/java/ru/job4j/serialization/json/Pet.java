package ru.job4j.serialization.json;

public class Pet {
    private final String name;
    private final String nameAnimal;
    private final int age;

    public Pet(String name, String nameAnimal, int age) {
        this.name = name;
        this.nameAnimal = nameAnimal;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{"
                + "nameAnimal='" + nameAnimal + '\''
                + "name='" + name + '\''
                + "age='" + age + '\''
                + '}';
    }
}
