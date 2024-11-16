package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        try {
            var scanner = new Scanner(new BufferedInputStream(new FileInputStream(Paths.get(argsName.get("path")).toFile())))
                    .useDelimiter(System.lineSeparator());
            List<String> data = new ArrayList<>();
            while (scanner.hasNext()) {
                data.add(scanner.next());
            }
            List<String> headers = Arrays.asList(data.get(0).split(argsName.get("delimiter")));
            data.remove(0);
            List<String> filterHeaders = Arrays.asList(argsName.get("filter").split(","));
            Map<String, Integer> filterHeadsMap = filterHeaders.stream()
                    .sorted(Comparator.comparingInt(h -> headers.indexOf(filterHeaders)))
                    .collect(Collectors.toMap(
                                    value -> value,
                                    headers::indexOf,
                                    (s, a) -> Integer.valueOf(s + ", " + a), LinkedHashMap::new
                            )
                    );
            List<Integer> indexByFilterList = filterHeadsMap.values().stream().toList();
            List<String> result = new ArrayList<>();
            result.add(filterHeaders.stream()
                    .collect(Collectors.joining(argsName.get("delimiter"))));
            for (String d : data) {
                List<String> line = Arrays.asList(d.split(argsName.get("delimiter")));
                result.add(line.stream()
                        .filter(e -> filterHeadsMap.containsValue(line.indexOf(e)))
                        .sorted(Comparator.comparingInt(h -> indexByFilterList.indexOf(line.indexOf(h))))
                        .collect(Collectors.joining(argsName.get("delimiter"))));
            }
            out(result, argsName.get("out"));
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    private static void out(List<String> result, String out) throws IOException {
        if ("stdout".equals(out)) {
            result.forEach(System.out::println);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8, true))) {
            result.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        ArgsName jvm = ArgsName.of(args);
        if (!Paths.get(jvm.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("The file does not exist.");
        }
        if (jvm.get("delimiter").length() != 1) {
            throw new IllegalArgumentException("The delimiter does not a single symbol.");
        }
        if (!List.of(",", ";", "\\t").contains(jvm.get("delimiter"))) {
            throw new IllegalArgumentException("The delimiter is not permitted.");
        }
        if (jvm.get("out") == null) {
            throw new IllegalArgumentException("An outstream is not defined.");
        }
        if (!"stdout".equals(jvm.get("out"))) {
            if (!jvm.get("out").endsWith(".[a-z]")) {
                throw new IllegalArgumentException("The file name does not have an correct extension.");
            }
        }
    }
}
