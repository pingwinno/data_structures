package com.study.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static com.study.datastructures.tools.CollectionUtil.populateList;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_1;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_2;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_20;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_3;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_4;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_5;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_0;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_1;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_2;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_3;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_5;
import static com.study.datastructures.tools.Constants.INDEX_0;
import static com.study.datastructures.tools.Constants.INDEX_1;
import static com.study.datastructures.tools.Constants.INDEX_2;
import static com.study.datastructures.tools.Constants.INDEX_3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTests {
    List<Integer> list;

    public ListTests(List<Integer> list) {
        this.list = list;
    }

    @Test
    void should_returnOneElementSizeIsOne_when_addOneElement() {
        populateList(EXPECTED_SIZE_1, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_1, list.size());
    }

    @Test
    void should_returnTwoElementAndSizeIsTwo_when_addTwoElement() {
        populateList(EXPECTED_SIZE_2, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, list.size());
    }

    @Test
    void should_returnTwoElementAndSizeIsTwo_when_addOneElementToPopulatedList() {
        populateList(EXPECTED_SIZE_1, list);
        list.add(EXPECTED_INTEGER_2, INDEX_1);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, list.size());
    }

    @Test
    void should_returnThreeElementInRightOrderAndSizeIsThree_when_addOneElementInMiddleOfPopulatedList() {
        populateList(EXPECTED_SIZE_2, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_1));
        list.add(EXPECTED_SIZE_3, INDEX_1);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_3, list.get(INDEX_1));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_2));
        assertEquals(EXPECTED_SIZE_3, list.size());
    }

    @Test
    void should_throwIndexOutOfBoundsException_when_addElementToWrongIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }

    @Test
    void should_removeAndReturnRemovedElement_when_removeElement() {
        populateList(EXPECTED_SIZE_1, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_1, list.remove(INDEX_0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_0, list.size());
    }

    @Test
    void should_removeAndReturnNextToRemovedElementFromBeginningOfList_when_removeElementFromStartOfList() {
        populateList(EXPECTED_SIZE_3, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_1, list.remove(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_2, list.size());
    }

    @Test
    void should_removeAndReturnPreviousToRemovedElementFromBeginningOfList_when_removeElementFromEndOfList() {
        populateList(EXPECTED_SIZE_3, list);
        assertEquals(EXPECTED_INTEGER_3, list.get(INDEX_2));
        assertEquals(EXPECTED_INTEGER_3, list.remove(INDEX_2));
        assertEquals(EXPECTED_INTEGER_2, list.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, list.size());
    }

    @Test
    void should_removeAndReturnRemainElementsInRightOrder_when_removeElementFromMiddleOfList() {
        populateList(EXPECTED_SIZE_3, list);
        assertEquals(EXPECTED_INTEGER_2, list.get(EXPECTED_SIZE_1));
        assertEquals(EXPECTED_INTEGER_2, list.remove(EXPECTED_SIZE_1));
        assertEquals(EXPECTED_SIZE_2, list.size());
        assertEquals(EXPECTED_INTEGER_1, list.get(EXPECTED_SIZE_0));
        assertEquals(EXPECTED_INTEGER_3, list.get(EXPECTED_SIZE_1));
    }

    @Test
    void should_throwIndexOutOfBoundsException_when_removeElementByWrongIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(INDEX_0));
    }

    @Test
    void should_throwIndexOutOfBoundsException_when_getElementByWrongIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(INDEX_0));
    }

    @Test
    void should_replaceAndReturnOldValue_when_setNewElementByIndex() {
        var oldObject = EXPECTED_INTEGER_1;
        list.add(oldObject);
        assertEquals(oldObject, list.get(INDEX_0));
        var newObject = EXPECTED_INTEGER_2;
        list.set(newObject, INDEX_0);
        assertEquals(newObject, list.get(INDEX_0));
    }

    @Test
    void should_throwIndexOutOfBoundsException_when_clearList() {
        populateList(EXPECTED_SIZE_2, list);
        assertEquals(EXPECTED_INTEGER_1, list.get(0));
        assertEquals(EXPECTED_INTEGER_2, list.get(1));
        assertEquals(EXPECTED_SIZE_2, list.size());
        list.clear();
        assertEquals(EXPECTED_SIZE_0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void should_returnTrue_when_listIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void should_returnFalse_when_listIsNotEmpty() {
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void should_returnTrue_when_listContainsTheElement() {
        populateList(EXPECTED_SIZE_1, list);
        assertTrue(list.contains(EXPECTED_INTEGER_1));
    }

    @Test
    void should_returnIndexOfElementCountingFromStart_when_listContainsTheElement() {
        populateList(EXPECTED_SIZE_5, list);
        list.add(EXPECTED_INTEGER_20, INDEX_1);
        list.add(EXPECTED_INTEGER_20, INDEX_3);
        assertEquals(INDEX_1, list.indexOf(EXPECTED_INTEGER_20));
    }

    @Test
    void should_returnIndexOfElement_when_listContainsTheElementAndAndHasSizeOne() {
        populateList(EXPECTED_SIZE_1, list);
        assertEquals(INDEX_0, list.indexOf(EXPECTED_SIZE_1));
    }

    @Test
    void should_returnIndexOfElementCountingFromEnd_when_listContainsTheElement() {
        populateList(EXPECTED_SIZE_5, list);
        list.add(EXPECTED_INTEGER_20, INDEX_1);
        list.add(EXPECTED_INTEGER_20, INDEX_3);
        assertEquals(INDEX_3, list.lastIndexOf(EXPECTED_INTEGER_20));
    }

    @Test
    void should_returnObjects_when_callNext() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        assertEquals(EXPECTED_INTEGER_1, iterator.next());
        assertEquals(EXPECTED_INTEGER_2, iterator.next());
        assertEquals(EXPECTED_INTEGER_3, iterator.next());
        assertEquals(EXPECTED_INTEGER_4, iterator.next());
        assertEquals(EXPECTED_INTEGER_5, iterator.next());
    }

    @Test
    void should_returnTrue_when_callHasNext() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void should_returnFalse_when_callHasNextAfterLastElement() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void should_throwNoSuchElementException_when_callNextAfterLastElement() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void should_removeAllObjectsFromList_when_callRemove() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(INDEX_0, list.size());
    }

    @Test
    void should_throwIllegalStateException_when_callRemoveBeforeNext() {
        populateList(EXPECTED_SIZE_5, list);
        var iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

}
