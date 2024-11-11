package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line + " - " + (isEven(Integer.parseInt(line)) ? "четное" : "нечетное") +  " число");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
