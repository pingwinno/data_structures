package com.study.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
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

//TODO: Переименовать методы и объединить тесты
class ArrayListTest {

    private ArrayList<Integer> arrayList;

    @BeforeEach
    void init() {
        arrayList = new ArrayList<>();
    }

    @Test
    void addOneElement() {
        populateList(EXPECTED_SIZE_1, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_1, arrayList.size());
    }

    @Test
    void addMultipleElements() {
        populateList(EXPECTED_SIZE_2, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
    }

    @Test
    void addMultipleElementsByIndexToListTail() {
        populateList(EXPECTED_SIZE_1, arrayList);
        arrayList.add(EXPECTED_INTEGER_2, INDEX_1);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
    }

    @Test
    void addMultipleElementsByIndex() {
        populateList(EXPECTED_SIZE_2, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_1));
        arrayList.add(EXPECTED_SIZE_3, INDEX_1);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_3, arrayList.get(INDEX_1));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_2));
        assertEquals(EXPECTED_SIZE_3, arrayList.size());
    }

    @Test
    void addElementsByIndexWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(1, 10));
    }

    @Test
    void remove() {
        populateList(EXPECTED_SIZE_1, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_1, arrayList.remove(INDEX_0));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_0, arrayList.size());
    }

    @Test
    void removeHead() {
        populateList(EXPECTED_SIZE_3, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_INTEGER_1, arrayList.remove(INDEX_0));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_0));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
    }

    @Test
    void removeTail() {
        populateList(EXPECTED_SIZE_3, arrayList);
        assertEquals(EXPECTED_INTEGER_3, arrayList.get(INDEX_2));
        assertEquals(EXPECTED_INTEGER_3, arrayList.remove(INDEX_2));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(INDEX_1));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
    }

    @Test
    void removeWithMultipleElements() {
        populateList(EXPECTED_SIZE_3, arrayList);
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(EXPECTED_SIZE_1));
        assertEquals(EXPECTED_INTEGER_2, arrayList.remove(EXPECTED_SIZE_1));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(EXPECTED_SIZE_0));
        assertEquals(EXPECTED_INTEGER_3, arrayList.get(EXPECTED_SIZE_1));
    }

    @Test
    void removeWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(INDEX_0));
    }

    @Test
    void getWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(INDEX_0));
    }

    @Test
    void set() {
        var oldObject = EXPECTED_INTEGER_1;
        arrayList.add(oldObject);
        assertEquals(oldObject, arrayList.get(INDEX_0));
        var newObject = EXPECTED_INTEGER_2;
        arrayList.set(newObject, INDEX_0);
        assertEquals(newObject, arrayList.get(INDEX_0));
    }

    @Test
    void clear() {
        populateList(EXPECTED_SIZE_2, arrayList);
        assertEquals(EXPECTED_INTEGER_1, arrayList.get(0));
        assertEquals(EXPECTED_INTEGER_2, arrayList.get(1));
        assertEquals(EXPECTED_SIZE_2, arrayList.size());
        arrayList.clear();
        assertEquals(EXPECTED_SIZE_0, arrayList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(1));
    }

    @Test
    void isEmpty() {
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void isNotEmpty() {
        arrayList.add(1);
        assertFalse(arrayList.isEmpty());
    }

    @Test
    void contains() {
        populateList(EXPECTED_SIZE_1, arrayList);
        assertTrue(arrayList.contains(EXPECTED_INTEGER_1));
    }

    @Test
    void indexOf() {
        populateList(EXPECTED_SIZE_5, arrayList);
        arrayList.add(EXPECTED_INTEGER_20, INDEX_1);
        arrayList.add(EXPECTED_INTEGER_20, INDEX_3);
        assertEquals(INDEX_1, arrayList.indexOf(EXPECTED_INTEGER_20));
    }

    @Test
    void indexOfWithOneElement() {
        populateList(EXPECTED_SIZE_1, arrayList);
        assertEquals(INDEX_0, arrayList.indexOf(EXPECTED_SIZE_1));
    }

    @Test
    void lastIndexOf() {
        populateList(EXPECTED_SIZE_5, arrayList);
        arrayList.add(EXPECTED_INTEGER_20, INDEX_1);
        arrayList.add(EXPECTED_INTEGER_20, INDEX_3);
        assertEquals(INDEX_3, arrayList.lastIndexOf(EXPECTED_INTEGER_20));
    }

    @Test
    void should_returnObjects_when_callNext() {
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
        assertEquals(EXPECTED_INTEGER_1, iterator.next());
        assertEquals(EXPECTED_INTEGER_2, iterator.next());
        assertEquals(EXPECTED_INTEGER_3, iterator.next());
        assertEquals(EXPECTED_INTEGER_4, iterator.next());
        assertEquals(EXPECTED_INTEGER_5, iterator.next());
    }

    @Test
    void should_returnTrue_when_callHasNext() {
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
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
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void should_throwNoSuchElementException_when_callNextAfterLastElement() {
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void should_removeAllObjectsFromList_when_callRemove() {
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
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
        assertEquals(INDEX_0, arrayList.size());
    }

    @Test
    void should_throwIllegalStateException_when_callRemoveBeforeNext() {
        populateList(EXPECTED_SIZE_5, arrayList);
        var iterator = arrayList.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }
}