package ru.job4j.newcoll.fortaskavl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.put(2, "22")).isTrue();
        assertThat(tree.put(3, "33")).isTrue();
        assertThat(tree.put(4, "44")).isTrue();
        assertThat(tree.put(5, "55")).isTrue();
        assertThat(tree.put(6, "66")).isTrue();
        assertThat(tree.put(7, "77")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "55", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenDeleteKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(0)).isFalse();
        assertThat(tree.values()).hasSize(6)
                .containsExactly("11", "22", "33", "44", "66", "77");
        assertThat(tree.keySet()).hasSize(6)
                .containsExactly(1, 2, 3, 4, 6, 7);
    }

    @Test
    void whenDeleteEmptyTreeThenEmptyOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.remove(5)).isFalse();
        assertThat(tree.values()).hasSize(0);
        assertThat(tree.keySet()).hasSize(0);
    }

    @Test
    void whenDeleteRootKeyThenEmptyOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(5, "11");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.values()).hasSize(0);
        assertThat(tree.keySet()).hasSize(0);
    }

    @Test
    void whenDeleteAllKeysThenEmptyOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(5, "11");
        tree.put(1, "7");
        tree.put(7, "6");
        tree.put(2, "9");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(1)).isTrue();
        assertThat(tree.remove(2)).isTrue();
        assertThat(tree.remove(7)).isTrue();
        assertThat(tree.values()).hasSize(0);
        assertThat(tree.keySet()).hasSize(0);
    }

    @Test
    void whenDeleteNotExistKeyThenNotDeleted() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(5, "11");
        assertThat(tree.remove(1)).isFalse();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(5);
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.get(5)).isEqualTo("55");
        assertThat(tree.get(0)).isNull();
    }

    @Test
    void whenEmptyTreeGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.get(0)).isNull();
    }
}