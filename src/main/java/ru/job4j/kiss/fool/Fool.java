package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.Predicate;

public class Fool {

    private static final int COUNT = 25;

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        Predicate<Integer> fizzBuzzPredicate = num -> num % 3 == 0 && num % 5 == 0;
        Predicate<Integer> fizzPredicate = num -> num % 3 == 0 && num % 5 != 0;
        Predicate<Integer> buzzPredicate = num -> num % 5 == 0 && num % 3 != 0;
        Predicate<Integer> otherPredicate = num -> num % 3 != 0 && num % 5 != 0;
        while (startAt < COUNT) {
            predPlayer1(startAt, fizzBuzzPredicate, "FizzBuzz");
            predPlayer1(startAt, fizzPredicate, "Fizz");
            predPlayer1(startAt, buzzPredicate, "Buzz");
            predPlayer1(startAt, otherPredicate, String.valueOf(startAt));
            startAt++;
            var answer = input.nextLine();
            var setZero = false;
            setZero = predPlayer2(startAt, fizzBuzzPredicate, answer, "FizzBuzz");
            setZero = setZero || predPlayer2(startAt, fizzPredicate, answer, "Fizz");
            setZero = setZero || predPlayer2(startAt, buzzPredicate, answer, "Buzz");
            setZero = setZero || predPlayer2(startAt, otherPredicate, answer, String.valueOf(startAt));
            startAt = setZero ? 0 : startAt;
            startAt++;
        }
    }

    static void predPlayer1(int number, Predicate<Integer> predicate, String s) {
        if (predicate.test(number)) {
            System.out.println(s);
        }
    }

    static boolean predPlayer2(int number, Predicate<Integer> predicate, String answer, String textChecked) {
        boolean result = false;
        if (predicate.test(number)) {
            if (!String.valueOf(textChecked).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                result = true;
            }
        }
        return result;
    }
}
