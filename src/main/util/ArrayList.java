package util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Implementation of {@link ArrayList lava.util.ArrayList}
 * @param <E> Type of elements
 * @author Dmitry Polushkin
 * @see ArrayList
 */
@SuppressWarnings("unchecked")
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
        add(size, e);
//        ensureCapacity(size + 1);
//        elementData[size++] = e;
        return true;
    }

    /**
     * Adding element after specified index.
     * @param index index.
     * @param element element.
     * @throws IndexOutOfBoundsException if index < 0 or index > size.
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
     * @throws IndexOutOfBoundsException if index < 0 or index >= size.
     */
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof util.List))
            return false;
        util.List<E> list = (util.List<E>) o;
        if (size != list.size())
            return false;
        for (int idx = 0; idx < size; idx++) {
            if (!compareElements(elementData[idx], list.get(idx)))
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
        for (E e : elementData) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
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
     * @throws IndexOutOfBoundsException if index < 0 or index >= size.
     */
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int toMove = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, toMove);
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
            if (compareElements(elementData[index], o)) {
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

    /**
     * Set specified element at specified index.
     * @param index index at which element must be set.
     * @param element element to set.
     * @return previous element at specified index.
     * @throws IndexOutOfBoundsException if index < 0 or index >= size.
     */
    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E ret = elementData[index];
        elementData[index] = element;
        return ret;
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
        if (checkIfListAlreadySorted(c)) {
            return ;
        }
        quickSort(c, elementData, 0, size - 1);
    }

    /**
     * Check if this list is already sorted.
     * @param c comparator to compare elements.
     * @return true if this list already sorted.
     */
    private boolean checkIfListAlreadySorted(Comparator<? super E> c) {
        for (int i = 1; i < size; i++) {
            if (c.compare(elementData[i], elementData[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Actual sorting with not null Comparator.
     * Sorts using quick sort algorithm.
     * @param c comparator used to compare elements.
     */
    private void quickSort(Comparator<? super E> c, E[] elements, int start, int end) {
        if (start >= end) {
            return ;
        }

        int idx = start;
        int pivotIdx = choosePivotElement(c, start, end);
        swapElements(pivotIdx, end);
        pivotIdx = end;
        while (idx != pivotIdx) {
            if ((idx < pivotIdx && c.compare(elements[idx], elements[pivotIdx]) > 0) ||
                    (idx > pivotIdx && c.compare(elements[idx], elements[pivotIdx]) <= 0)) {

                swapElements(idx, pivotIdx);
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
     * Swap elements of this list at specified indexes.
     */
    private void swapElements(int i, int j) {
        E tmp = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = tmp;
    }

    /**
     * Choosing pivot element from first, last and middle element.
     * @param c comparator used to compare elements.
     * @return index of pivot element.
     */
    private int choosePivotElement(Comparator<? super E> c, int first, int last) {
        int middle = first + (last - first) / 2;
        if (c.compare(elementData[middle], elementData[first]) > 0) { // first, middle ? last
            if (c.compare(elementData[last], elementData[middle]) > 0) { // first, middle, last
                return middle;
            }
            return (c.compare(elementData[last], elementData[first]) > 0) ? last : first;
        }

        // middle, first ? last
        if (c.compare(elementData[last], elementData[first]) > 0) {
            return last; //middle, first, last
        }
        return (c.compare(elementData[last], elementData[middle]) > 0) ? last : middle;
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

    /**
     * Remove all elements from this list.
     * Will not change current list capacity.
     */
    public void clear() {
        for (int idx = 0; idx < size; idx++) {
            elementData[idx] = null;
        }
        size = 0;
    }

    /**
     * Check if specified Object is in this list.
     * @param o object to find.
     * @return true if object found in the list, false otherwise.
     */
    public boolean contains(Object o) {
        for (int idx = 0; idx < size; idx++) {
            if (compareElements(elementData[idx], o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns index of specified Object if it is in the list, -1 otherwise.
     * @param o object to find.
     * @return index of element if found, -1 otherwise.
     */
    public int indexOf(Object o) {
        for (int idx = 0; idx < size; idx++) {
            if (compareElements(elementData[idx], o)) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * Checks if this list is empty.
     * @return true if list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns index of last occurrence of element, or -1.
     * @param o element to search.
     * @return index of last occurrence of element, or -1.
     */
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (compareElements(elementData[i], o)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Returns array of elements of this list.
     * @return array of elements of this list
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Returns array with elements of this list.
     * If list fits in specified array, its copied there and the array is returned.
     * @param a array in which list copied if fits.
     * @param <E> type of elements.
     * @return passed as argument array if list elements fit in it, or newly created array.
     */
    public <E> E[] toArray(E[] a) {
        if (a.length >= size) {
            System.arraycopy(elementData,0, a, 0, size);
            return a;
        }
        return (E[])Arrays.copyOf(elementData, size);
    }

    /**
     * Makes capacity of this list equal to size.
     */
    public void trimToSize() {
        if (elementData.length > size) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Appends elements of specified collection to the end of this list.
     * @param c collection to append.
     * @return true if this list changed.
     * @throws NullPointerException if specified collection is null.
     */
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    /**
     * Inserts elements of specified collection at specified index of this list.
     * All elements of this list starting with index will be moved right, including element at index.
     * @param index index at what collection will be inserted.
     * @param c collection to insert.
     * @return true if this list changed.
     * @throws IndexOutOfBoundsException if index < 0 or index > size.
     * @throws NullPointerException if specified collection is null.
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        int collectionLength = 0;
        for (E e : c) {
            collectionLength++;
        }
        ensureCapacity(collectionLength + size);
        System.arraycopy(elementData, index, elementData, index + collectionLength, size - index);
        for (E e : c) {
            elementData[index++] = e;
        }
        size += collectionLength;
        return true;
    }

    /**
     * Searches and removes all elements that present in this list and specified collection.
     * @param c collection, which elements is searched in this list to remove.
     * @return true if this list is changed.
     */
    public boolean removeAll(Collection<?> c) {
        for (Object e : c) {
            remove(e);
        }
        return true;
    }

    /**
     * Removes all elements that satisfy given predicate.
     * @param filter predicate that used to check if element should be removed.
     * @return true if any element was removed.
     */
    public boolean removeIf(Predicate<? super E> filter) {
        boolean anyRemoved = false;
        for (int i = 0; i < size; i++) {
            if (filter.test(elementData[i])) {
                remove(i);
                anyRemoved = true;
            }
        }
        return anyRemoved;
    }

    /**
     * Replaces all elements of this list with result of applying of operator.
     * @param operator UnaryOperator that applies to all elements of this list.
     */
    public void replaceAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            elementData[i] = operator.apply(elementData[i]);
        }
    }

    /**
     * Retains only elements in this list that specified collection contains.
     * @param c collection, which elements compared to this list's elements.
     * @return true if this list is changed.
     */
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elementData[i])) {
                remove(i--);
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * Applies specified action to all elements of this list.
     * @param action action to apply to all elements of this list.
     */
    public void forEach(Consumer<? super E> action) {
        for (E t : elementData) {
            action.accept(t);
        }
    }

    /**
     * Check if this list contains all elements of specified collection.
     * @param c collection, elements of which is checked.
     * @return true if this list contains all elements of specified collection.
     */
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Element comparison as in java.util.ArrayList
     * @param o1 first element to compare.
     * @param o2 second element to compare.
     * @return true if elements equals or if both null.
     */
    private boolean compareElements(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }
}
