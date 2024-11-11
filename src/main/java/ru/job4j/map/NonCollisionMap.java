package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    boolean hasNull = false;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        if ((index == 0 && table[index] == null) || (index != 0 && table[index] == null)) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
            hasNull = index == 0 || hasNull;
        }
        return result;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        return isKeyEquals(table[index], key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (isKeyEquals(table[index], key)) {
            table[index] = null;
            count--;
            modCount++;
            hasNull = index != 0 && hasNull;
            result = true;
        }
        return result;
    }

    private boolean isKeyEquals(MapEntry<K, V> element, K key) {
        return ((element != null)
                && Objects.hashCode(element.key) == Objects.hashCode(key)) && (Objects.equals(element.key, key));
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {
            private int index;
            private boolean hasNullValue = hasNull;
            int expectedModCount = modCount;
            private K[] data;

            {
                ArrayList<K> list = new ArrayList();
                for (int i = 0; i < table.length; i++) {
                    if (table[i] != null) {
                        if (table[i].key != null && i != 0) {
                            list.add(table[i].key);
                        } else if (table[i].key == null && table[i].value != null) {
                            hasNullValue = true;
                        }
                    }
                }
                data = (K[]) list.toArray();
                if (hasNullValue) {
                    K[] array1 = Arrays.copyOf(data, 1);
                    K[] array2 = Arrays.copyOf(data, data.length);
                    K[] result = Arrays.copyOf(array1, array1.length + array2.length);
                    System.arraycopy(array2, 0, result, array1.length, array2.length);
                    data = result;
                }
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while ((index < data.length) && (data[index] == null)) {
                    index++;
                }
                return (index < data.length);
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (hasNullValue) {
                    hasNullValue = false;
                    index++;
                    return null;
                } else {
                    return data[index++];
                }
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                indexFor(hash(Objects.hashCode(table[i].key)));
                newTable[indexFor(hash(Objects.hashCode(table[i].key)))] = table[i];
            }
        }
        table = newTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}