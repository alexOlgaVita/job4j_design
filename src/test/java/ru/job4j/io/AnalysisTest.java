package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void whenNotDataThenNotLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("").hasToString(result.toString());
    }

    @Test
    void whenNotUnvialableThenNotLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("202 09:28:13");
            output.println("200 10:04:17");
            output.println("305 10:04:18");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("").hasToString(result.toString());
    }

    @Test
    void whenNotAvialableThenNotLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("400 09:28:13");
            output.println("400 10:04:17");
            output.println("500 10:04:18");
            output.println("500 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("09:28:13;").hasToString(result.toString());
    }

    @Test
    void whenFirstAvialableThenOneLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("202 09:28:13");
            output.println("400 10:04:17");
            output.println("500 10:04:18");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:04:17;11:02:02").hasToString(result.toString());
    }

    @Test
    void whenFirstUnavialableThenOneLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("500 09:28:13");
            output.println("400 10:04:17");
            output.println("500 10:04:18");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("09:28:13;11:02:02").hasToString(result.toString());
    }

    @Test
    void whenTwoPeriodsThenTwoLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("500 09:28:13");
            output.println("400 10:04:17");
            output.println("500 10:04:18");
            output.println("300 11:02:02");
            output.println("500 11:31:08");
            output.println("200 11:31:09");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().map(e -> e + System.lineSeparator()).forEach(result::append);
        }
        assertThat("09:28:13;11:02:02" + System.lineSeparator()
                + "11:31:08;11:31:09" + System.lineSeparator()).hasToString(result.toString());
    }
}