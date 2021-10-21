package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.SimpleTimeZone;

class ArrayListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testConstructingWithNullElement() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, -1));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(null, list.get(1));
    }

    @Test
    void testAdding5Elements() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(-1, list.get(2));
    }

    @Test
    void testAddingNullElement() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.add(null);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(null, list.get(0));
    }

    @Test
    void testAddingElementAtIndexThatEqualsSize() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        list.add(1, 13);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(1));
    }

    @Test
    void testRemovingFirstElement() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        list.remove(0);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(0));
    }

    @Test
    void testRemovingLastElement() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        list.remove(2);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(1));
    }

    @Test
    void testRemovingElementFromEmptyList() {
        util.ArrayList<Integer> list = new util.ArrayList<>();

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(0),
                "Expected remove() to throw, but it didn't"
        );
    }

    @Test
    void testSizeOfEmptyList() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testSizeOfListOf1000000Elements() {
        util.ArrayList<Integer> list = new util.ArrayList<>(1000000);
        Random rand = new Random();
        for (int idx = 0; idx < 1000000; idx++) {
            list.add(rand.nextInt());
        }
        Assertions.assertEquals(1000000, list.size());
    }

    @Test
    void testSortingListEmptyList() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.sort(null);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testSortingList1Elements() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(7));
        list.sort(null);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(7, list.get(0));
    }

    @Test
    void testSortingList5Elements() {
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(-1, 7, 11, 13, 13));
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(11, 7, 13, -1, 13));
        actual.sort(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSortingSortedList5Elements() {
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(7, 11, 13, 13, 17));
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(7, 11, 13, 13, 17));
        actual.sort(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSortingList1000000ElementsNoExceptions() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>(1000000);
        Random rand = new Random();
        for (int idx = 0; idx < 1000000; idx++) {
            list.add(rand.nextInt());
        }
        util.ArrayList<Integer> actual = new util.ArrayList<>(list);
        actual.sort(null);
    }

    @Test
    void set_ValidIndex_Ok() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(-1));
        Assertions.assertEquals(123, list.set(0, 123));
    }

    @Test
    void set_InvalidIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(0, 123),
                "Expected set() to throw, but it didn't"
        );
    }

    @Test
    void testToStringEmptyList() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void testToString() {
        util.ArrayList<String> list = new util.ArrayList<>();

        list.add("hello");
        Assertions.assertEquals("[hello]", list.toString());

        list.add("there");
        Assertions.assertEquals("[hello, there]", list.toString());
    }

    @Test
    void testAddAllWithNullThrowsException() {
        util.ArrayList<String> list = new util.ArrayList<>();
        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> list.addAll(null),
                "Expected addAll() to throw, but it didn't"
        );
    }
}