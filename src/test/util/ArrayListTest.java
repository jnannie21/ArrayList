package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        String expected;
        String actual;

        java.util.ArrayList<String> realList = new java.util.ArrayList<>();
        expected = realList.toString();
        util.ArrayList<String> myList = new util.ArrayList<>();
        actual = myList.toString();
        Assertions.assertEquals(expected, actual);

        realList.add("hello");
        expected = realList.toString();
        myList.add("hello");
        actual = myList.toString();
        Assertions.assertEquals(expected, actual);

        realList.add("there");
        expected = realList.toString();
        myList.add("there");
        actual = myList.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ensureCapacity() {
    }

    @Test
    void testToString() {
    }
}