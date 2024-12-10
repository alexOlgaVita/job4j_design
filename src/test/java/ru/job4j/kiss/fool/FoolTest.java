package ru.job4j.kiss.fool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoolTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream standartOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(standartOut);
    }

    @Test
    public void testWhenOk() {
        StringBuilder sbPlayer2 = new StringBuilder();
        sbPlayer2.append("2").append(System.lineSeparator());
        sbPlayer2.append("4").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("8").append(System.lineSeparator());
        sbPlayer2.append("Buzz").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("14").append(System.lineSeparator());
        sbPlayer2.append("16").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("Buzz").append(System.lineSeparator());
        sbPlayer2.append("22").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        String dataPlayer2 = sbPlayer2.toString();
        StringBuilder sbPlayer1 = new StringBuilder();
        sbPlayer1.append("Игра FizzBuzz.").append(System.lineSeparator());
        sbPlayer1.append("1").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("Buzz").append(System.lineSeparator());
        sbPlayer1.append("7").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("11").append(System.lineSeparator());
        sbPlayer1.append("13").append(System.lineSeparator());
        sbPlayer1.append("FizzBuzz").append(System.lineSeparator());
        sbPlayer1.append("17").append(System.lineSeparator());
        sbPlayer1.append("19").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("23");
        String dataPlayer1 = sbPlayer1.toString();
        System.setIn(new ByteArrayInputStream(dataPlayer2.getBytes()));
        Fool.main(new String[0]);
        assertEquals(dataPlayer1, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWhenError() {
        StringBuilder sbPlayer2 = new StringBuilder();
        sbPlayer2.append("2").append(System.lineSeparator());
        sbPlayer2.append("1").append(System.lineSeparator());
        sbPlayer2.append("2").append(System.lineSeparator());
        sbPlayer2.append("4").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("8").append(System.lineSeparator());
        sbPlayer2.append("Buzz").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("14").append(System.lineSeparator());
        sbPlayer2.append("16").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        sbPlayer2.append("Buzz").append(System.lineSeparator());
        sbPlayer2.append("22").append(System.lineSeparator());
        sbPlayer2.append("Fizz").append(System.lineSeparator());
        String dataPlayer2 = sbPlayer2.toString();
        StringBuilder sbPlayer1 = new StringBuilder();
        sbPlayer1.append("Игра FizzBuzz.").append(System.lineSeparator());
        sbPlayer1.append("1").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("Ошибка. Начинай снова.").append(System.lineSeparator());
        sbPlayer1.append("1").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("Buzz").append(System.lineSeparator());
        sbPlayer1.append("7").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("11").append(System.lineSeparator());
        sbPlayer1.append("13").append(System.lineSeparator());
        sbPlayer1.append("FizzBuzz").append(System.lineSeparator());
        sbPlayer1.append("17").append(System.lineSeparator());
        sbPlayer1.append("19").append(System.lineSeparator());
        sbPlayer1.append("Fizz").append(System.lineSeparator());
        sbPlayer1.append("23");
        String dataPlayer1 = sbPlayer1.toString();
        System.setIn(new ByteArrayInputStream(dataPlayer2.getBytes()));
        Fool.main(new String[0]);
        assertEquals(dataPlayer1, outputStreamCaptor.toString().trim());
    }
}