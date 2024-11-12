package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(target)) {
            String start = "";
            String end = "";
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] elements = line.split(" ");
                if ("400".equals(elements[0]) || "500".equals(elements[0])) {
                    start = "".equals(start) ? elements[1] : start;
                }
                if (!"400".equals(elements[0]) && !"500".equals(elements[0])) {
                    end = "".equals(start) ? "" : elements[1];
                }
                if (!"".equals(start) && !"".equals(end)) {
                    String s = start + ";" + end + System.lineSeparator();
                    writer.println(s);
                    System.out.println(s);
                    start = "";
                    end = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
