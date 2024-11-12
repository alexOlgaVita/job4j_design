package ru.job4j.io;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> results = getUnavailableList(getSortedByLocalTimeList(source));
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String result : results) {
                output.write(result + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Map.Entry<LocalTime, String>> getSortedByLocalTimeList(String source) {
        List<Map.Entry<LocalTime, String>> valueList = Collections.emptyList();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            List<String> list = reader.lines().toList();
            DateTimeFormatter dfm = DateTimeFormatter.ofPattern("HH:mm:ss");
            Map<LocalTime, String> values =
                    list.stream()
                            .map(e -> e.trim())
                            .map(e -> e.split(" ", 2))
                            .collect(Collectors.toMap(e -> LocalTime.parse(e[1], dfm), e -> e[0]))
                            .entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey())
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new));
            valueList = values.entrySet().stream().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valueList;
    }

    private List<String> getUnavailableList(List<Map.Entry<LocalTime, String>> valueList) {
        List<String> results = new ArrayList<>();
        int i = 0;
        while (i < valueList.size()) {
            String start = "";
            String end = "";
            while (i < valueList.size() && (!valueList.get(i).getValue().equals("500") && !valueList.get(i).getValue().equals("400"))) {
                i++;
            }
            if (i < valueList.size()) {
                start = valueList.get(i).getKey().toString();
                i++;
            }
            while (i < valueList.size() && (valueList.get(i).getValue().equals("500") || valueList.get(i).getValue().equals("400"))) {
                i++;
            }
            if (i < valueList.size()) {
                end = valueList.get(i).getKey().toString();
                i++;
            }
            if (!start.equals("")) {
                results.add(String.format("%s%s%s", start, ";", end));
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
