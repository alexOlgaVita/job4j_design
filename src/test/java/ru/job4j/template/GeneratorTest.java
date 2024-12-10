package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorTest {
    @Test
    public void whenAllValuesExist() {
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Olga Ilyina, Who are you?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Olga Ilyina");
        args.put("subject", "you");
        Generator generator = new ValueGenerator();
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    public void whenSomeKeyNotExist() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Olga Ilyina");
        Generator generator = new ValueGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Some keys there are not in the keymap");
    }

    @Test
    public void whenRedundantKeysExist() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Olga Ilyina");
        args.put("subject", "you");
        args.put("name2", "aha");
        Generator generator = new ValueGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are redundant keys in the keymap");
    }
}