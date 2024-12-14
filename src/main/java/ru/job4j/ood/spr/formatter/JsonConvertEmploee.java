package ru.job4j.ood.spr.formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.spr.model.Employee;

import java.util.List;

public class JsonConvertEmploee implements DataConvert<Employee> {

    private Object converter;

    public JsonConvertEmploee(Object converter) {
        this.converter = converter;
    }

    @Override
    public String convert(List<Employee> data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (converter != null) {
            gsonBuilder.registerTypeAdapter(Employee.class, converter);
        }
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson.toJson(data);
    }
}
