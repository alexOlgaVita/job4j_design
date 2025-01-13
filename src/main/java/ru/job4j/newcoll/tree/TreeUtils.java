package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        int count = 0;
        for (T t : findAll(root)) {
            count++;
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> keys = new ArrayList<>();
        queue.push(root);
        if (root == null) {
            throw new IllegalArgumentException("Пустое дерево");
        }
        Node<T> element = queue.poll();
        int size = 0;
        do {
            keys.add(element.getValue());
            for (Node<T> node : element.getChildren()) {
                queue.push(node);
                size++;
            }
            if (size > 0) {
                element = queue.poll();
                size--;
            } else {
                element = null;
            }
        } while (element != null);
        return keys;
    }
}
