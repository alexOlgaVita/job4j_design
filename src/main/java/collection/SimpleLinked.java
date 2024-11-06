package collection;

public interface SimpleLinked<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}
