package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, Set<String>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
            files.computeIfAbsent(fileProp, e -> new HashSet<>()).add(file.toFile().getAbsolutePath());
        }
        return super.visitFile(file, attributes);
    }

    public void print() {
        Map<FileProperty, Set<String>> duplicatesFiles = files.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (Map.Entry<FileProperty, Set<String>> duplicatesFile : duplicatesFiles.entrySet()) {
            System.out.printf("%s - %s%n", duplicatesFile.getKey().getName(), duplicatesFile.getKey().getSize());
            duplicatesFile.getValue().forEach(System.out::println);
        }
    }
}
