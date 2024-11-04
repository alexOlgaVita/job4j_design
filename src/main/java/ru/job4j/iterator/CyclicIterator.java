package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return data.size() > 0 ? true : false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
        index = (index == data.size()) ? 0 : index;
        return data.get(index);
    }
}