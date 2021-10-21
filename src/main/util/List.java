package util;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface List<E> {
    boolean add(E e);
    void add(int index, E element);
    E get(int index);
    E remove(int index);
    boolean remove(Object o);
    int size();
    E set(int index, E element);
    void sort(Comparator<? super E> c);
    void clear();
    boolean contains(Object o);
    int indexOf(Object o);
    boolean isEmpty();
    int lastIndexOf(Object o);
    Object[] toArray();
    <E> E[] toArray(E[] a);
    void trimToSize();
    boolean addAll(Collection<? extends E> c);
    boolean addAll(int index, Collection<? extends E> c);
    boolean removeAll(Collection<?> c);
    boolean removeIf(Predicate<? super E> filter);
    void replaceAll(UnaryOperator<E> operator);
    boolean retainAll(Collection<?> c);
}
