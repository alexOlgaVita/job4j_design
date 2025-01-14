package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        AtomicInteger count = new AtomicInteger();
        findAll(root).forEach(i -> count.getAndIncrement());
        return count.get();
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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        Optional<Node<T>> parentNode = findByKey(root, parent);
        if (parentNode.isEmpty()) {
            return false;
        }
        Optional<Node<T>> childNode = findByKey(root, child);
        if (childNode.isPresent()) {
            return false;
        }
        Node<T> newChild = new Node<>(child);
        parentNode.get().getChildren().add(newChild);
        return true;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        Predicate<Node<T>> eq = i -> (i.getValue().equals(key));
        return findByKeyWithPredicate(root, eq);
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        Optional<Node<T>> foundElement = findByKey(root, key);
        if (foundElement.isPresent()) {
            Predicate<Node<T>> contains = i -> (i.getChildren().contains(foundElement.get()));
            Optional<Node<T>> result = findByKeyWithPredicate(root, contains);
            if (result.isPresent()) {
                System.out.println("parent = " + result);
                result.get().getChildren().remove(foundElement.get());
            }
        }
        return foundElement;
    }

    private Optional<Node<T>> findByKeyWithPredicate(Node<T> root, Predicate<Node<T>> predicate) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        Node<T> result = null;
        if (root == null) {
            throw new IllegalArgumentException("Пустое дерево");
        }
        stack.push(root);
        int size = 0;
        Node<T> node = root;

        while (result == null && node != null) {
            if (predicate.test(node)) {
                result = node;
            } else {
                for (Node<T> element : node.getChildren()) {
                    stack.push(element);
                    size++;
                }
                if (size > 0) {
                    node = stack.pop();
                    size--;
                } else {
                    node = null;
                }
            }
        }
        return Optional.ofNullable(result);
    }
}
