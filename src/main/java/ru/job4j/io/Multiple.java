package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 11; i++) {
                int result = 1 * i;
                output.write(("1 * " + i + " = " + result).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
