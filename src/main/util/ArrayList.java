package util;

import java.util.*;

/**
 * Implementation of {@link ArrayList lava.util.ArrayList}
 * @param <E> Type of elements
 * @author Dmitry Polushkin
 * @see ArrayList
 */
public class ArrayList<E> implements util.List<E> {
    /**
     * Array of elements contained in ArrayList.
     */
    E[] elementData;

    /**
     * Amount of elements contained in ArrayList.
     */
    private int size = 0;

    /**
     * Default constructor's initial capacity.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    /**
     * Default constructor.
     * Initialize a list with default capacity of 10.
     */
    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Creates a list and initializes it with passed Collection elements.
     * Elements are not cloned.
     * @param c Collection to initialize this list.
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = (E[])c.toArray();
        size = elementData.length;
    }

    /**
     * Creates a list with specified initial capacity
     * @param initialCapacity initial capacity.
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = (E[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Add specified element to the end of the list.
     * @param e element to add.
     * @return true if there's no exceptions happened.
     */
    public boolean add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * Adding element after specified index.
     * @param index index.
     * @param element element.
     */
    public void add(int index, E element) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Get element at specified index.
     * @param index index of element.
     * @return element at the index.
     */
    public E get(int index) {
        return elementData[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArrayList))
            return false;
        ArrayList<?> arrayList = (ArrayList<?>) o;
        if (size != arrayList.size())
            return false;
        for (int idx = 0; idx < size; idx++) {
            if (!elementData[idx].equals(arrayList.elementData[idx]))
                return false;
        }
        return true;
    }

    /**
     * The same implementation as in {@link List#hashCode() lava.util.List.hashCode()}.
     * @return generated hash code.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : elementData)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    /**
     * Increases the list capacity to at least specified capacity.
     * @param minCapacity specified capacity.
     */
    public void ensureCapacity(int minCapacity) {
        int newCapacity;
        if (minCapacity > elementData.length) {
            int growth = elementData.length / 2 + 1;
            if (Integer.MAX_VALUE - elementData.length < growth) {
                newCapacity = Integer.MAX_VALUE;
            } else {
                newCapacity = elementData.length + growth;
                if (minCapacity > newCapacity) {
                    newCapacity = minCapacity;
                }
            }
            E[] oldData = elementData;
            elementData = (E[])new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, oldData.length);
        }
    }

    /**
     * Removes element at specified index.
     * @param index index.
     * @return removed element.
     */
    public E remove(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        size--;
        E ret = elementData[size];
        elementData[size] = null;
        return ret;
    }

    /**
     * Removes first occurrence of specified element in this list
     * or does nothing if there is no such element.
     * @param o element to remove.
     * @return true if element is found, false otherwise.
     */
    public boolean remove(Object o) {
        for (int index = 0; index < elementData.length; index++) {
            if (elementData[index].equals(o)) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * Get current amount of elements in this list.
     * @return amount of elements.
     */
    public int size() {
        return size;
    }

    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index] = element;
    }

    /**
     * Sort elements in this list in ascending order using specified Comparator.
     * If comparator is null, sort in natural order.
     * @param c comparator used to compare elements.
     */
    public void sort(Comparator<? super E> c) {
        if (c == null) {
            c = (o1, o2) -> ((Comparable)o1).compareTo((Comparable)o2);
        }
        quickSort(c, elementData, 0, size - 1);
    }

    /**
     * Actual sorting with not null Comparator.
     * @param c comparator used to compare elements.
     */
    private void quickSort(Comparator<? super E> c, E[] elements, int start, int end) {
        if (start >= end) {
            return ;
        }

        int idx = start;
        int pivotIdx = end;
        while (idx != pivotIdx) {
            if ((idx < pivotIdx && c.compare(elements[idx], elements[pivotIdx]) > 0) ||
                    (idx > pivotIdx && c.compare(elements[idx], elements[pivotIdx]) <= 0)) {

                Collections.swap(Arrays.asList(elements), idx, pivotIdx);
                int tmp = idx;
                idx = pivotIdx;
                pivotIdx = tmp;
            }
            idx += (idx < pivotIdx) ? 1 : -1;
        }

        quickSort(c, elements, start, pivotIdx - 1);
        quickSort(c, elements, pivotIdx + 1, end);
    }

    /**
     * Get String representation of this list.
     * @return String representation of this list.
     */
    @Override
    public String toString() {
        String str = "[";

        for (int i = 0; i < size; i++) {
            str += elementData[i];
            if (i + 1 < size) {
                str += ", ";
            }
        }

        str += "]";
        return str;
    }

//    /**
//     * Taken from https://www.baeldung.com/java-generic-array
//     * @param a
//     * @param <E>
//     * @return
//     */
//    public <E> E[] toArray(E[] a) {
//        return (E[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
//    }
}
