package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.insert("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddThreeWithRepeat1ToEmptyTreeThenListContainsTwoElement2() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.insert("second")).isTrue();
        assertThat(tree.insert("first")).isFalse();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        AvlTree<String> tree = new AvlTree<>();
        tree.insert("first");
        tree.insert("second");
        tree.insert("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveRootWithoutChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4}) {
            tree.insert(element);
        }
        tree.remove(4);
        assertThat(tree.inSymmetricalOrder()).hasSize(0)
                .containsExactly();
    }

    @Test
    void whenRemoveRoot2ChildsWithChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.remove(4);
        assertThat(tree.inSymmetricalOrder()).hasSize(6)
                .containsExactly(1, 2, 3, 5, 6, 7);
    }

    @Test
    void whenRemoveNotChildThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.remove(3);
        assertThat(tree.inSymmetricalOrder()).hasSize(6)
                .containsExactly(1, 2, 4, 5, 6, 7);
    }

    @Test
    void whenRemove2ChildWithoutChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.remove(2);
        assertThat(tree.inSymmetricalOrder()).hasSize(6)
                .containsExactly(1, 3, 4, 5, 6, 7);
    }

    @Test
    void whenRemove2ChildWithChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7}) {
            tree.insert(element);
        }
        tree.remove(10);
        assertThat(tree.inSymmetricalOrder()).hasSize(16)
                .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17);
    }

    @Test
    void whenClearRootWithoutChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4}) {
            tree.insert(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }

    @Test
    void whenClearRoot2ChildsWithChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }

    @Test
    void whenClearNotChildThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }

    @Test
    void whenClear2ChildWithoutChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }

    @Test
    void whenClear2ChildWithChildsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7}) {
            tree.insert(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }
}