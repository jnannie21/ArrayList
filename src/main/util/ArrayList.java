package util;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of {@link ArrayList lava.util.ArrayList}
 * @param <E> Type of elements
 * @author Dmitry Polushkin
 * @see ArrayList
 */
public class ArrayList<E> {
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
    ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
//        System.out.println("DEFAULT_INITIAL_CAPACITY is " + DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Creates a list and initializes it with passed Collection elements.
     * Elements are not cloned.
     * @param c Collection to initialize this list.
     */
    ArrayList(Collection<? extends E> c) {
        elementData = (E[])Arrays.copyOf(c.toArray(), c.size());

//        this(c.size());
//
//        int i = 0;
//        for (E el : c) {
//            elementData[i++] = el;
//        }

        size = elementData.length;
    }

    /**
     * Creates a list with specified initial capacity
     * @param initialCapacity initial capacity.
     */
    ArrayList(int initialCapacity) {
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
     * Increases the list capacity to at least specified capacity.
     * @param minCapacity specified capacity.
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length + elementData.length / 2 + 1;
            if (minCapacity > newCapacity) {
                newCapacity = minCapacity;
            }
            E[] oldData = elementData;
            elementData = (E[])new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, oldData.length);
//            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

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
}
