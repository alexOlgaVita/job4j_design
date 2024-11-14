package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles();
        Files.walkFileTree(root, searcher);
        return searcher.getPaths().stream().filter(condition).toList();
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER.");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("File extension is null. Please enter a value.");
        }
        Path path = Paths.get(args[0]);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", path.toFile().getAbsoluteFile()));
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", path.toFile().getAbsoluteFile()));
        }
    }
}
