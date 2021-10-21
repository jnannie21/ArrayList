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
    void testForEqualityOfEmptyArrayLists() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>();
        util.ArrayList<Integer> list2 = new util.ArrayList<>();
        Assertions.assertEquals(list1, list2);
    }

    @Test
    void constructor_ConstructingWithNullElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, -1));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(null, list.get(1));
    }

    @Test
    void add_AddingNullElement_Added() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.add(null);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(null, list.get(0));
    }

    @Test
    void add_AddingElementAtIndexThatEqualsSize_Added() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        list.add(1, 13);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(1));
    }

    @Test
    void remove_RemovingFirstElementByIndex_Removed() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        list.remove(0);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(0));
    }

    @Test
    void remove_RemovingLastElementByIndex_Removed() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        list.remove(2);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(1));
    }

    @Test
    void remove_RemovingElementFromEmptyList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(0),
                "Expected remove() to throw, but it didn't"
        );
    }

    @Test
    void size_CheckSizeOfEmptyList_IsZero() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void size_CheckSizeOfListWith1000000ElementsAdded_1000000ElementsAdded() {
        util.ArrayList<Integer> list = new util.ArrayList<>(1000000);
        Random rand = new Random();
        for (int idx = 0; idx < 1000000; idx++) {
            list.add(rand.nextInt());
        }
        Assertions.assertEquals(1000000, list.size());
    }

    @Test
    void sort_SortingListEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.sort(null);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void sort_SortingListWith1Element_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(7));
        list.sort(null);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(7, list.get(0));
    }

    @Test
    void sort_SortingListWith5Elements_Success() {
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(-1, 7, 11, 13, 13));
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(11, 7, 13, -1, 13));
        actual.sort(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sort_SortingSortedListWith5Elements_Success() {
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(7, 11, 13, 13, 17));
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(7, 11, 13, 13, 17));
        actual.sort(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sort_SortingListWith1000000Elements_Success() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>(1000000);
        Random rand = new Random();
        for (int idx = 0; idx < 1000000; idx++) {
            list.add(rand.nextInt());
        }
        util.ArrayList<Integer> actual = new util.ArrayList<>(list);
        actual.sort(null);
    }

    @Test
    void set_SetElementAtValidIndex_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(-1));
        Assertions.assertEquals(-1, list.set(0, 123));
        Assertions.assertEquals(123, list.get(0));
    }

    @Test
    void set_SetElementAtInvalidIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(0, 123),
                "Expected set() to throw, but it didn't"
        );
    }

    @Test
    void toString_ToStringWithEmptyList_MustReturnBrackets() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void toString_StringTypeWithSeveralElements_MustReturnElements() {
        util.ArrayList<String> list = new util.ArrayList<>(Arrays.asList("hello", "there"));
        Assertions.assertEquals("[hello, there]", list.toString());
    }

    @Test
    void toString_IntegerTypeWithSeveralElements_MustReturnElements() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13));
        Assertions.assertEquals("[11, 13]", list.toString());
    }

    @Test
    void addAll_AddAllWithNull_ThrowsException() {
        util.ArrayList<String> list = new util.ArrayList<>();
        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> list.addAll(null),
                "Expected addAll() to throw, but it didn't"
        );
    }
}