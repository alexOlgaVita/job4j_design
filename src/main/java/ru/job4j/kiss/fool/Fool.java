package ru.job4j.kiss.fool;

import java.util.Scanner;

import static java.lang.System.in;

public class Fool {

    public static int count = 100;

    static class Answer {
        private int number;

        public Answer(int num) {
            this.number = num;
        }

        public String label() {
            String result = String.valueOf(number);
            if (number % 3 == 0 && number % 5 == 0) {
                result = "FizzBuzz";
            } else if (number % 3 == 0) {
                result = "Fizz";
            } else if (number % 5 == 0) {
                result = "Buzz";
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(in);
        Fool fool = new Fool();
        while (startAt < fool.count) {
            System.out.println(new Answer(startAt++).label());
            if (!io.nextLine().equals(new Answer(startAt).label())) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
