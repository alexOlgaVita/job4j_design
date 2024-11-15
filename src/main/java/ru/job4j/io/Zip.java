package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        /* запуск архивации с параметрами */
        ArgsName jvm = ArgsName.of(args);
        List<Path> packList = search(Paths.get(jvm.get("d")), path -> !path.toFile().getName().endsWith(jvm.get("e")));
        packFiles(packList, new File(jvm.get("o")));
    }

    private static void validate(String[] args) {
        ArgsName jvm = ArgsName.of(args);
        jvm.get("d");
        if (!Paths.get(jvm.get("d")).toFile().exists()) {
            throw new IllegalArgumentException("The directory does not exist.");
        }

        if (!jvm.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The extension in exclude-parameter does not start with a '.' character");
        }
        if (!jvm.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The extension in output-parameter does not end with a '.zip'");
        }
    }
}
