package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Zoo zoo = new Zoo(false, 10, new Pet("Margo", "Cat", 2),
                new String[] {"Cat", "Dog", "Turtle"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(zoo));
        final String zooJson =
                "{"
                        + "\"municipal\":true,"
                        + "\"period\":7,"
                        + "\"pet\":"
                        + "{"
                        + "\"name\":\"Amaday\","
                        + "\"nameAnimal\":\"Dog\","
                        + "\"age\":12"
                        + "},"
                        + "\"animalTypes\":"
                        + "[\"Dog\",\"Hamster\"]"
                        + "}";
        final Zoo zooMod = gson.fromJson(zooJson, Zoo.class);
        System.out.println(zooMod);
    }
}
