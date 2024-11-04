package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    Iterator<T> iterator;

    public CyclicIterator(List<T> data) {
        this.data = data;
        iterator = this.data.iterator();
    }

    @Override
    public boolean hasNext() {
        boolean result = !data.isEmpty();
        if (!iterator.hasNext() && result) {
            iterator = data.iterator();
        }
        return result;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}