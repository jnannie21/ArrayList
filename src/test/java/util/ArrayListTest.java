package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ArrayListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructor_ConstructingWithCollectionWhichContainsNullElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, -1));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(null, list.get(1));
    }

    @Test
    void constructor_ConstructingWithEmptyCollection_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList());
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void constructor_ConstructingWithOneElementCollection_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Collections.singletonList(null));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(null, list.get(0));
    }

    @Test
    void constructor_ConstructingWithNull_ExceptionThrown() {
        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> new util.ArrayList<>(null),
                "Expected new util.ArrayList<>(null) to throw, but it didn't"
        );
    }

    @Test
    void constructor_ConstructingZeroCapacity_DoesntThrow() {
        Assertions.assertDoesNotThrow(
                () -> new util.ArrayList<>(0),
                "Expected new util.ArrayList<>(0) does not throw, but it throws"
        );
    }

    @Test
    void equals_CheckEqualityOfEmptyLists_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>();
        util.ArrayList<Integer> list2 = new util.ArrayList<>();
        Assertions.assertTrue(list1.equals(list2));
    }

    @Test
    void equals_CheckEqualityOfOneElementLists_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>(Collections.singletonList(null));
        util.ArrayList<Integer> list2 = new util.ArrayList<>(Collections.singletonList(null));
        Assertions.assertTrue(list1.equals(list2));
    }

    @Test
    void equals_CheckEqualityOfNotEmptyLists_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>(Arrays.asList(null, 1, null, -123));
        util.ArrayList<Integer> list2 = new util.ArrayList<>(Arrays.asList(null, 1, null, -123));
        Assertions.assertTrue(list1.equals(list2));
    }

    @Test
    void equals_CheckEqualityWithNull_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>(Arrays.asList(null, 1, null, -123));
        Assertions.assertFalse(list1.equals(null));
    }

    @Test
    void equals_CheckEqualityWithItself_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>(Arrays.asList(null, 1, null, -123));
        Assertions.assertTrue(list1.equals(list1));
    }

    @Test
    void get_GetElementWithNotValidIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(0),
                "Expected get() to throw, but it didn't"
        );
    }

    @Test
    void get_GetElementWithNegativeIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(-1),
                "Expected get() to throw, but it didn't"
        );
    }

    @Test
    void get_GetElementWithValidIndex_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(11, list.get(0));
    }

    @Test
    void add_AddingSeveralNullElements_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.add(null);
        list.add(null);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(null, list.get(0));
    }

    @Test
    void add_AddingAtNegativeIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(-1, 13),
                "Expected add() to throw, but it didn't"
        );
    }

    @Test
    void add_AddingElementAtIndexThatEqualsSize_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        list.add(1, 13);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(1));
    }

    @Test
    void add_AddingElementAtIndexThatBiggerThanSize_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(2, 13),
                "Expected add() to throw, but it didn't"
        );
    }

    @Test
    void remove_RemovingFirstElementByIndex_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, -1));
        list.remove(0);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(13, list.get(0));
    }

    @Test
    void remove_RemovingLastElementByIndex_Success() {
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
    void remove_RemovingElementByInvalidIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13));

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(2),
                "Expected remove() to throw, but it didn't"
        );
    }

    @Test
    void remove_RemovingElementByNegativeIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13));

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(-1),
                "Expected remove() to throw, but it didn't"
        );
    }

    @Test
    void remove_RemovingElementByNullObject_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 7));
        Assertions.assertTrue(list.contains(null));
        list.remove(null);
        Assertions.assertEquals(2, list.size());
        Assertions.assertFalse(list.contains(null));
    }

    @Test
    void remove_RemovingInteger_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 7));
        Assertions.assertTrue(list.contains(7));
        list.remove(Integer.valueOf(7));
        Assertions.assertEquals(2, list.size());
        Assertions.assertFalse(list.contains(7));
    }

    @Test
    void remove_RemovingString_Success() {
        util.ArrayList<String> list = new util.ArrayList<>(Arrays.asList("cem"));
        Assertions.assertTrue(list.contains("cem"));
        list.remove("cem");
        Assertions.assertEquals(0, list.size());
        Assertions.assertFalse(list.contains("cem"));
    }

    @Test
    void remove_RemovingWithSeveralEqualsElements_Success() {
        util.ArrayList<String> list = new util.ArrayList<>(Arrays.asList("cem", "odin", "cem"));
        list.remove("cem");
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("cem", list.get(1));
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
    void set_SetElementWithIndexEqualSize_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 0));
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(3, 22),
                "Expected set() to throw, but it didn't"
        );
    }

    @Test
    void set_SetElementWithNegativeIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 0));
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(-1, 22),
                "Expected set() to throw, but it didn't"
        );
    }

    @Test
    void set_SetElementAtEmptyList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();

        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(0, 123),
                "Expected set() to throw, but it didn't"
        );
    }

    @Test
    void set_SetElementAtValidIndex_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(-1));
        Assertions.assertEquals(-1, list.set(0, 123));
        Assertions.assertEquals(123, list.get(0));
    }

    @Test
    void sort_SortingEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.sort(null);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void sort_SortingWithOneElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(7));
        list.sort(null);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(7, list.get(0));
    }

    @Test
    void sort_SortingListWithSeveralElements_Success() {
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(-1, 7, 11, 13, 13));
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(11, 7, 13, -1, 13));
        actual.sort(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sort_SortingListWithSeveralSortedElements_Success() {
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
    void clear_ClearEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void clear_ClearNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, null, 0));
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void contains_CheckIfEmptyListContainsElement_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertFalse(list.contains(13));
    }

    @Test
    void contains_CheckIfListContainsElement_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, null, 0));
        Assertions.assertTrue(list.contains(0));
    }

    @Test
    void contains_CheckIfListContainsNull_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, null, 0));
        Assertions.assertTrue(list.contains(null));
    }

    @Test
    void indexOf_FindIndexInEmptyList_Failure() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals(-1, list.indexOf(13));
    }

    @Test
    void indexOf_FindIndexOfExistingElementInList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        Assertions.assertEquals(1, list.indexOf(null));
    }

    @Test
    void indexOf_FindIndexOfNotExistingElementInList_Failure() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        Assertions.assertEquals(-1, list.indexOf(22));
    }

    @Test
    void isEmpty_CheckIfEmptyListIsEmpty_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty_CheckIfNotEmptyListIsEmpty_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void lastIndexOf_CheckInNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11, 11));
        Assertions.assertEquals(4, list.lastIndexOf(11));
    }

    @Test
    void lastIndexOf_CheckInEmptyList_Failure() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertEquals(-1, list.lastIndexOf(null));
    }

    @Test
    void lastIndexOf_CheckNullInNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11, 11));
        Assertions.assertEquals(2, list.lastIndexOf(null));
    }

    @Test
    void toArray_CheckNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11, 11));
        Assertions.assertArrayEquals(new Integer[]{13, null, null, 11, 11}, list.toArray());
    }

    @Test
    void toArray_CheckEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertArrayEquals(new Integer[]{}, list.toArray());
    }

    @Test
    void toArray_CheckOneNullElementList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.add(null);
        Assertions.assertArrayEquals(new Integer[]{null}, list.toArray());
    }

    @Test
    void toArray_CheckWithArrayAsArgumentWithEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Integer[] expected = new Integer[10];
        Assertions.assertArrayEquals(expected, list.toArray(expected));
    }

    @Test
    void toArray_CheckWithExactSizeArrayAsArgumentWithNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13, null, null));
        Integer[] expected = new Integer[4];
        Assertions.assertTrue(expected == list.toArray(expected));
        Assertions.assertEquals(null, expected[3]);
    }

    @Test
    void toArray_CheckWithBiggerSizeArrayAsArgumentWithNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(0, -1, null, 11));
        Integer[] expected = new Integer[10];
        expected[5] = 13;
        Assertions.assertTrue(expected == list.toArray(expected));
        Assertions.assertEquals(11, expected[3]);
        Assertions.assertEquals(null, expected[4]);
    }

    @Test
    void addAll_AddNullAsCollection_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));

        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> list.addAll(null),
                "Expected addAll() to throw, but it didn't"
        );
    }

    @Test
    void addAll_AddCollectionInEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.addAll(Arrays.asList(13, null, null, 11));
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(11, list.get(3));
    }

    @Test
    void addAll_AddCollectionToNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        list.addAll(Arrays.asList(13, null, null, 11));
        Assertions.assertEquals(8, list.size());
        Assertions.assertEquals(11, list.get(7));
    }

    @Test
    void addAll_AddCollectionAtSpecifiedIndexThatEqualsSizeInNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        list.addAll(4, Arrays.asList(13, null, null, 11));
        Assertions.assertEquals(8, list.size());
        Assertions.assertEquals(11, list.get(7));
    }

    @Test
    void addAll_AddCollectionAtSpecifiedIndexInNotEmptyList_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, null, null, 11));
        list.addAll(3, Arrays.asList(13, null, null));
        Assertions.assertEquals(11, list.get(6));
        Assertions.assertEquals(null, list.get(5));
    }

    @Test
    void addAll_AddCollectionAtNotValidIndexInEmptyList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.addAll(1, Arrays.asList(13, null, null, 11)),
                "Expected addAll() to throw, but it didn't"
        );
    }

    @Test
    void addAll_AddCollectionAtNegativeIndexInNotEmptyList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        IndexOutOfBoundsException thrown = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.addAll(-1, Arrays.asList(13, null, null, 11)),
                "Expected addAll() to throw, but it didn't"
        );
    }

    @Test
    void addAll_AddNullAtValidIndex_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> list.addAll(1, null),
                "Expected addAll() to throw, but it didn't"
        );
    }

    @Test
    void removeAll_RemoveAllWithNullAsCollection_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        NullPointerException thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> list.removeAll(null),
                "Expected removeAll() to throw, but it didn't"
        );
    }

    @Test
    void removeAll_RemoveAllWithTheSameCollection_Success() {
        java.util.List<Integer> l = Arrays.asList(11, null, 13);
        util.ArrayList<Integer> list = new util.ArrayList<>(l);
        list.removeAll(l);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void removeAll_RemoveAllWithSeveralMatches_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        list.removeAll(Arrays.asList(null, 13));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(11, list.get(0));
    }

    @Test
    void removeIf_RemoveIf_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        list.removeIf(el -> el == null);
        Assertions.assertEquals(2, list.size());
        Assertions.assertFalse(list.contains(null));
    }

    @Test
    void removeIf_RemoveIfWithNullAsFilter_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> list.removeIf(null),
                "Expected removeIf() to throw, but it didn't"
        );
    }

    @Test
    void replaceAll_ReplaceAllWithNullAsOperator_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 13));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> list.replaceAll(null),
                "Expected replaceAll() to throw, but it didn't"
        );
    }

    @Test
    void replaceAll_ReplaceAllWithNotEmptyList_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>(Arrays.asList(11, 14));
        util.ArrayList<Integer> list2 = new util.ArrayList<>(Arrays.asList(null, null));
        list1.replaceAll(el -> null);
        Assertions.assertEquals(list1, list2);
    }

    @Test
    void replaceAll_ReplaceAllWithEmptyList_Success() {
        util.ArrayList<Integer> list1 = new util.ArrayList<>();
        util.ArrayList<Integer> list2 = new util.ArrayList<>();
        list1.replaceAll(el -> null);
        Assertions.assertEquals(list1, list2);
    }

    @Test
    void retainAll_RetainAllWithEmptyList_Success() {
        util.ArrayList<Integer> actual = new util.ArrayList<>();
        util.ArrayList<Integer> expected = new util.ArrayList<>();
        actual.retainAll(Arrays.asList(1, 2));
        Assertions.assertEquals(0, actual.size());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void retainAll_RetainAllWithNotEmptyList_Success() {
        util.ArrayList<Integer> actual = new util.ArrayList<>(Arrays.asList(1, 2, null, null, 1, 0, -1));
        util.ArrayList<Integer> expected = new util.ArrayList<>(Arrays.asList(1, null, null, 1));
        actual.retainAll(Arrays.asList(1, null));
        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void retainAll_RetainAllWithNullAsCollection_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(1, 2, null, null, 1, 0, -1));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> list.retainAll(null),
                "Expected retainAll() to throw, but it didn't"
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

    @Test
    void forEach_NullConsumer_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(1, 2, null, null, 1, 0, -1));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> list.forEach(null),
                "Expected forEach() to throw, but it didn't"
        );
    }

    @Test
    void forEach_ApplyToNotEmptyList_Success() {
        util.ArrayList<StringBuilder> actual = new util.ArrayList<>(Arrays.asList(
                new StringBuilder("a"),
                new StringBuilder("b"),
                new StringBuilder("c"),
                new StringBuilder("d")));
        util.ArrayList<StringBuilder> expected = new util.ArrayList<>(Arrays.asList(
                new StringBuilder("x"),
                new StringBuilder("x"),
                new StringBuilder("x"),
                new StringBuilder("x")));
        actual.forEach(el -> el.setCharAt(0, 'x'));
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void containsAll_WithNullCollectionPassed_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 13));
        Assertions.assertThrows(
                NullPointerException.class,
                () -> list.containsAll(null),
                "Expected containsAll() to throw, but it didn't"
        );
    }

    @Test
    void containsAll_WithEmptyListAndNotEmptyCollection_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertFalse(list.containsAll(Arrays.asList(13)));
    }

    @Test
    void containsAll_WithNulls_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        list.add(null);
        Assertions.assertTrue(list.containsAll(Arrays.asList(null, null)));
    }

    @Test
    void containsAll_WithEmptyListAndEmptyCollection_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertTrue(list.containsAll(Arrays.asList()));
    }

    @Test
    void containsAll_WithNotEmptyListAndNotEmptyCollection_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 0, 0, null));
        Assertions.assertTrue(list.containsAll(Arrays.asList(null, 0, 11, null)));
    }

    @Test
    void containsAll_WithNotEmptyListAndNotEmptyCollection_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, 0, 0, null));
        Assertions.assertFalse(list.containsAll(Arrays.asList(null, 0, 11, null, -1)));
    }

    @Test
    void iteratorHasNext_WithNoElements_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertFalse(list.iterator().hasNext());
    }

    @Test
    void iteratorHasNext_WithOneElement_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13));
        util.Iterator<Integer> it = list.iterator();
        Assertions.assertTrue(it.hasNext());
    }

    @Test
    void iteratorHasNext_WithOneElementAfterNextCalled_False() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13));
        util.Iterator<Integer> it = list.iterator();
        it.next();
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    void iteratorHasNext_WithSeveralElements_True() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(null, 0, 0, 11));
        Assertions.assertTrue(list.iterator().hasNext());
    }

    @Test
    void iteratorNext_WithNoElements_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> list.iterator().next(),
                "Expected iterator().next() to throw, but it didn't"
        );
    }

    @Test
    void iteratorNext_WithOneElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(0));
        Assertions.assertEquals(0, list.iterator().next());
    }

    @Test
    void iteratorNext_CallNextTwoTimesWithOneElement_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(0));
        util.Iterator<Integer> it = list.iterator();
        Assertions.assertEquals(0, it.next());
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> it.next(),
                "Expected it.next() to throw, but it didn't"
        );
    }

    @Test
    void iteratorNext_WithSeveralElements_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(null, 11, 0));
        util.Iterator<Integer> it = list.iterator();
        Assertions.assertEquals(null, it.next());
        Assertions.assertEquals(11, it.next());
        Assertions.assertEquals(0, it.next());
    }

    @Test
    void iteratorRemove_WithNoNextCalledAndNoElements_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> list.iterator().remove(),
                "Expected iterator().remove() to throw, but it didn't"
        );
    }

    @Test
    void iteratorRemove_WithNoNextCalledAndSeveralElementsInList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11, null, 0, -1));
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> list.iterator().remove(),
                "Expected iterator().remove() to throw, but it didn't"
        );
    }

    @Test
    void iteratorRemove_WithNextCalledAndOneElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        util.Iterator<Integer> it = list.iterator();
        it.next();
        it.remove();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void iteratorRemove_TwoTimesAfterNextCalledAndOneElement_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        util.Iterator<Integer> it = list.iterator();
        it.next();
        it.remove();
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> it.remove(),
                "Expected iterator().remove() to throw, but it didn't"
        );
    }

    @Test
    void iteratorForEachRemaining_NoElements_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        util.Iterator<Integer> it = list.iterator();
        it.forEachRemaining(el -> Assertions.assertFalse(false));
    }

    @Test
    void iteratorForEachRemaining_OneElement_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(11));
        util.Iterator<Integer> it = list.iterator();
        it.forEachRemaining(el -> Assertions.assertEquals(11, el));
    }

    @Test
    void iteratorForEachRemaining_SeveralElements_Success() {
        util.ArrayList<StringBuilder> actual = new util.ArrayList<>(Arrays.asList(
                new StringBuilder("a"),
                new StringBuilder("b"),
                new StringBuilder("c"),
                new StringBuilder("d")));
        util.ArrayList<StringBuilder> expected = new util.ArrayList<>(Arrays.asList(
                new StringBuilder("b"),
                new StringBuilder("c"),
                new StringBuilder("d"),
                new StringBuilder("e")));
        util.Iterator<StringBuilder> it = actual.iterator();
        it.forEachRemaining(el -> el.setCharAt(0, (char)(el.charAt(0) + 1)));
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void modCount_WhenAddElement_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        util.Iterator<Integer> it = list.iterator();
        list.add(13);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.add() to throw, but it didn't"
        );
    }

    @Test
    void modCount_WhenRemoveElement_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.remove(0);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.remove() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterIncreasingCapacity_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.ensureCapacity(20);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.ensureCapacity() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterSettingLowCapacityWithDefaultList_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>();
        util.Iterator<Integer> it = list.iterator();
        list.ensureCapacity(1);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.ensureCapacity() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterSettingLowCapacity_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.ensureCapacity(1);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.ensureCapacity() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterEnsureCapacityWithZero_ExceptionNotThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.ensureCapacity(0);
        Assertions.assertDoesNotThrow(
                () -> it.next(),
                "Expected iterator().next() after list.ensureCapacity() not throw, but it throws"
        );
    }

    @Test
    void modCount_AfterAddAll_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.addAll(Arrays.asList(null, -1));
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.addAll() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterRemoveAllOneMatch_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.removeAll(Arrays.asList(null, 0));
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.removeAll() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterRemoveAllNoMatch_Success() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.removeAll(Arrays.asList(null, 11));
        Assertions.assertEquals(13, it.next());
    }

    @Test
    void modCount_AfterSort_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0));
        util.Iterator<Integer> it = list.iterator();
        list.sort(null);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.sort() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterRemoveIf_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0, null));
        util.Iterator<Integer> it = list.iterator();
        list.removeIf(el -> el == null);
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.removeIf() to throw, but it didn't"
        );
    }

    @Test
    void modCount_AfterTrimToSize_ExceptionThrown() {
        util.ArrayList<Integer> list = new util.ArrayList<>(Arrays.asList(13, 0, null));
        util.Iterator<Integer> it = list.iterator();
        list.trimToSize();
        Assertions.assertThrows(
                ConcurrentModificationException.class,
                () -> it.next(),
                "Expected iterator().next() after list.trimToSize() to throw, but it didn't"
        );
    }
}