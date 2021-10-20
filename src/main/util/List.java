package util;

import java.util.Comparator;

public interface List<E> {
    boolean add(E e);
    void add(int index, E element);
    E get(int index);
    E remove(int index);
    boolean remove(Object o);
    int size();
    E set(int index, E element);
    void sort(Comparator<? super E> c);
}
