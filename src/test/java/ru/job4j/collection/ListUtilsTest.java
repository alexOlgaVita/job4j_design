package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveAllNotEmpyRemoveFirst() {
        ListUtils.removeAll(input, List.of(1));
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveAllNotEmpyRemoveLast() {
        ListUtils.removeAll(input, List.of(3));
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveAllNotEmpyRemoveAll() {
        ListUtils.removeAll(input, List.of(3, 1));
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAllNotEmpyRemoveNotElements() {
        ListUtils.removeAll(input, List.of(7, 10));
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAllEmpy() {
        input = new ArrayList<>();
        ListUtils.removeAll(input, List.of(3, 1));
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveIfFirst() {
        ListUtils.removeIf(input, x -> x < x * x);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveIfLast() {
        ListUtils.removeIf(input, x -> x == x * x);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveIfAll() {
        ListUtils.removeIf(input, x -> x <= x * x);
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveIfNot() {
        ListUtils.removeIf(input, x -> x > x * x);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfEmpty() {
        input = new ArrayList<>();
        ListUtils.removeIf(input, x -> x.equals(x));
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenReplaceIfFirst() {
        ListUtils.replaceIf(input, x -> x < x * x, 7);
        assertThat(input).hasSize(2).containsSequence(1, 7);
    }

    @Test
    void whenReplaceIfLast() {
        ListUtils.replaceIf(input, x -> x == x * x, 7);
        assertThat(input).hasSize(2).containsSequence(7, 3);
    }

    @Test
    void whenReplaceeIfAll() {
        ListUtils.replaceIf(input, x -> x <= x * x, 7);
        assertThat(input).hasSize(2).containsSequence(7, 7);
    }

    @Test
    void whenReplaceIfNot() {
        ListUtils.replaceIf(input, x -> x > x * x, 7);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfEmpty() {
        input = new ArrayList<>();
        ListUtils.replaceIf(input, x -> x.equals(x), 7);
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }
}