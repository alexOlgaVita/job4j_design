package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = 0;
        while (i < size - 1) {
            output.push(input.pop());
            i++;
        }
        T deleted = input.pop();
        i = 0;
        while (i < size - 1) {
            input.push(output.pop());
            i++;
        }
        size--;
        return deleted;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}
