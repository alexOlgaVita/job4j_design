package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            List<String> list = reader.lines().toList();
            if (!checkCorrect(list)) {
                throw new IllegalArgumentException("Некорректный формат");
            }
            this.values = list.stream()
                    .map(e -> e.trim())
                    .filter(e -> !e.equals(""))
                    .filter(e -> !e.startsWith("#"))
                    .map(e -> e.split("=", 2))
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
            System.out.println(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkCorrect(List<String> list) {
        return list.stream().map(e -> e.trim())
                .filter(e -> e.startsWith("=")
                        || (e.endsWith("=") && e.split("=").length == 1)
                        || (e.length() > 0 && !e.startsWith("#") && !e.contains("="))).count() == 0;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        // System.out.println(new Config("data/app.properties"));
        System.out.println(new Config("data/pair_without_comment.properties"));
    }
}
