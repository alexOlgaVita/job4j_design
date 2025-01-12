package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParentFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(3, 2)).isFalse();
        assertThat(tree.findBy(2)).isEmpty();
    }

    @Test
    void whenParentEqualChildThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 1)).isFalse();
        assertThat(tree.findBy(1)).isPresent();
    }

    @Test
    void whenRootOnlyThenBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(((SimpleTree<Integer>) tree).isBinary()).isTrue();
    }

    @Test
    void when3LeafThenNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(((SimpleTree<Integer>) tree).isBinary()).isFalse();
    }

    @Test
    void when2LeafThenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        assertThat(((SimpleTree<Integer>) tree).isBinary()).isTrue();
    }

    @Test
    void when1LeafOnlyThenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(3, 4);
        assertThat(((SimpleTree<Integer>) tree).isBinary()).isTrue();
    }

    @Test
    void when2LeafAllThenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(((SimpleTree<Integer>) tree).isBinary()).isTrue();
    }
}