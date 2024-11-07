package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        int i = 0;
        if (set.size() != 0) {
            while (i < set.size()) {
                if (set.get(i) == null || set.get(i).equals(value)) {
                    return false;
                }
                i++;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        int i = 0;
        while (i < set.size()) {
            if (set.get(i) == null || set.get(i).equals(value)) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}