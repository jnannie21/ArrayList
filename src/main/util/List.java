package util;

public interface List<E> {
    boolean add(E e);
    void add(int index, E element);
    E get(int index);
    E remove(int index);
    boolean remove(Object o);
}
