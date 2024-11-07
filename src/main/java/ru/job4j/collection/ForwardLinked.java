package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> lastNode = head;
            Node<T> currNode = lastNode;
            while (currNode != null) {
                lastNode = currNode;
                currNode = currNode.next;
            }
            lastNode.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = head;
        int i = 0;
        while (i < index) {
            currentNode = currentNode.next;
            i++;
        }
        return currentNode.item;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            head = new Node<>(value, head);
            size++;
            modCount++;
        }
    }

    public T deleteFirst() {
        T deletedValue;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            deletedValue = head.item;
            Node<T> newHead = head.next;
            head.next = null;
            head.item = null;
            head = newHead;
            size--;
            modCount++;
        }
        return deletedValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            private Node<T> currentNode = null;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (currentNode == null && head != null) || (currentNode != null && currentNode.next != null);
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = (currentNode == null && head != null) ? head : currentNode.next;
                return currentNode.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
