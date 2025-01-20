package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenEmptyThenEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }

    @Test
    void when2SameThenOk() {
        int[] array = {4, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 4);
    }

    @Test
    void when3SameThenOk() {
        int[] array = {4, 4, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 4, 4);
    }

    @Test
    void when4SameThenOk() {
        int[] array = {4, 4, 4, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 4, 4, 4);
    }

    @Test
    void whenSortedThenOk11() {
        int[] array = {10, 4, 6};
        assertThat(Merge.mergesort(array)).containsExactly(4, 6, 10);
    }

    @Test
    void whenSortedThenOk12() {
        int[] array = {10, 6, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 6, 10);
    }

    @Test
    void whenSortedThenOk13() {
        int[] array = {4, 6, 4, 3};
        assertThat(Merge.mergesort(array)).containsExactly(3, 4, 4, 6);
    }

    @Test
    void whenSortedThenOk14() {
        int[] array = {4, 6, 5, 3};
        assertThat(Merge.mergesort(array)).containsExactly(3, 4, 5, 6);
    }

    @Test
    void whenSortedThenOk2() {
        int[] array = {10, 4};
        assertThat(Merge.mergesort(array)).containsExactly(4, 10);
    }

    @Test
    void whenSortedThenOk22() {
        int[] array = {4, 10};
        assertThat(Merge.mergesort(array)).containsExactly(4, 10);
    }

    @Test
    void whenSortedThenOk3() {
        int[] array = {10, 4, 5, 3};
        assertThat(Merge.mergesort(array)).containsExactly(3, 4, 5, 10);
    }

    @Test
    void whenSortedThenOk4() {
        int[] array = {10, 4, 5};
        assertThat(Merge.mergesort(array)).containsExactly(4, 5, 10);
    }
}