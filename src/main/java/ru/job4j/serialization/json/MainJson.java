package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJson {
    public static void main(String[] args) {
        JSONObject jsonPet = new JSONObject("{\"nameAnimal\":\"Cat\","
                + " \"name\":\"Margo\", "
                + "\"age\":\"10\"}");
        List<String> list = new ArrayList<>();
        list.add("Cat");
        list.add("Dog");
        list.add("Turtle");
        JSONArray jsonAnimalTypes = new JSONArray(list);
        final Zoo zoo = new Zoo(false, 10, new Pet("Margo", "Cat", 2),
                new String[]{"Cat", "Dog", "Turtle"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("municipal", zoo.getMunicipal());
        jsonObject.put("period", zoo.getPeriod());
        jsonObject.put("pet", jsonPet);
        jsonObject.put("statuses", jsonAnimalTypes);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(zoo).toString());
    }
}
