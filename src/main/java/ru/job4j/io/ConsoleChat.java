package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        Scanner input = new Scanner(System.in);
        boolean pause = false;
        String hello = "Привет! Давай поговорим. Что скажешь?";
        log.add(hello);
        System.out.println(hello);
        String speach = input.nextLine();
        log.add(speach);
        Random random = new Random();
        while (!OUT.equals(speach)) {
            if (STOP.equals(speach)) {
                pause = true;
            }
            if (CONTINUE.equals(speach)) {
                pause = false;
            }
            if (!pause) {
                String answer = answers.get(random.nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
            }
            speach = input.nextLine();
            log.add(speach);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            readLines = reader.lines().filter(e -> !"".equals(e.trim())).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLines;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botLog.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}
