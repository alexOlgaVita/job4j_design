package ru.job4j.ood.spr.serialization;

import com.google.gson.*;
import ru.job4j.ood.spr.formatter.DateTimeParser;
import ru.job4j.ood.spr.model.Employee;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Calendar;

import static ru.job4j.ood.spr.formatter.ReportDateTimeParser.getDateFormat;

public class EmployeeConverter implements JsonSerializer<Employee>, JsonDeserializer<Employee> {

    private final DateTimeParser<Calendar> dateTimeParser;

    public EmployeeConverter(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public JsonElement serialize(Employee src, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("name", src.getName());
        object.addProperty("hired", dateTimeParser.parse(src.getHired()));
        object.addProperty("fired", dateTimeParser.parse(src.getFired()));
        object.addProperty("salary", src.getSalary());
        return object;
    }

    public Employee deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String name = object.get("name").getAsString();
        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();
        try {
            hired.setTime(getDateFormat().parse(object.get("hired").getAsString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            fired.setTime(getDateFormat().parse(object.get("fired").getAsString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        double salary = object.get("salary").getAsDouble();
        return new Employee(name, hired, fired, salary);
    }
}
