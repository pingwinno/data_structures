package com.study.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    private LinkedList linkedList;

    @BeforeEach
    void init() {
        linkedList = new LinkedList();
    }

    @Test
    void addOneElement() {
        var expectedObject = 1;
        var expectedSize = 1;
        linkedList.add(expectedObject);
        assertEquals(expectedObject, linkedList.get(0));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    void addMultipleElements() {
        var expectedSize = 2;
        var firstExpectedObject = 1;
        var secondExpectedObject = 2;
        linkedList.add(firstExpectedObject);
        linkedList.add(secondExpectedObject);
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(secondExpectedObject, linkedList.get(1));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    void addMultipleElementsByIndexToListTail() {
        var expectedSize = 2;
        var firstExpectedObject = 1;
        var secondExpectedObject = 2;
        linkedList.add(firstExpectedObject);
        linkedList.add(secondExpectedObject, 1);
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(secondExpectedObject, linkedList.get(1));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    void addMultipleElementsByIndex() {
        var expectedSize = 3;
        var firstExpectedObject = 1;
        var secondExpectedObject = 2;
        linkedList.add(firstExpectedObject);
        linkedList.add(secondExpectedObject);
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(secondExpectedObject, linkedList.get(1));
        var thirdExpectedObject = 3;
        linkedList.add(thirdExpectedObject,1);
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(thirdExpectedObject, linkedList.get(1));
        assertEquals(secondExpectedObject, linkedList.get(2));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    void addElementsByIndexWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(new Object(), 10));
    }

    @Test
    void remove() {
        var expectedSize = 0;
        var expectedObject = new Object();
        linkedList.add(expectedObject);
        assertEquals(expectedObject, linkedList.get(0));
        assertEquals(expectedObject, linkedList.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
        assertEquals(expectedSize, linkedList.size());
    }

    @Test
    void removeWithMultipleElements() {
        var expectedSize = 2;
        var firstExpectedObject = 1;
        var secondExpectedObject = 2;
        var thirdExpectedObject = 3;
        linkedList.add(firstExpectedObject);
        linkedList.add(secondExpectedObject);
        linkedList.add(thirdExpectedObject);
        assertEquals(secondExpectedObject, linkedList.get(1));
        assertEquals(secondExpectedObject, linkedList.remove(1));
        assertEquals(expectedSize, linkedList.size());
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(thirdExpectedObject, linkedList.get(1));
    }

    @Test
    void removeWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(0));
    }

    @Test
    void getWithOutOutBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }

    @Test
    void set() {
        var oldObject = new Object();
        linkedList.add(oldObject);
        assertEquals(oldObject, linkedList.get(0));
        var newObject = new Object();
        linkedList.set(newObject, 0);
        assertEquals(newObject, linkedList.get(0));
    }

    @Test
    void clear() {
        var expectedSize = 2;
        var firstExpectedObject = new Object();
        var secondExpectedObject = new Object();
        linkedList.add(firstExpectedObject);
        linkedList.add(secondExpectedObject);
        assertEquals(firstExpectedObject, linkedList.get(0));
        assertEquals(secondExpectedObject, linkedList.get(1));
        assertEquals(expectedSize, linkedList.size());
        linkedList.clear();
        assertEquals(0, linkedList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(1));
    }

    @Test
    void isEmpty() {
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void isNotEmpty() {
        linkedList.add(new Object());
        assertFalse(linkedList.isEmpty());
    }

    @Test
    void contains() {
        var expectedObject = new Object();
        linkedList.add(expectedObject);
        assertTrue(linkedList.contains(expectedObject));
    }

    @Test
    void indexOf() {
        var expectedIndex = 1;
        var expectedObject = new Object();
        linkedList.add(new Object());
        linkedList.add(expectedObject);
        linkedList.add(new Object());
        linkedList.add(expectedObject);
        linkedList.add(new Object());
        assertEquals(expectedIndex, linkedList.indexOf(expectedObject));
    }

    @Test
    void indexOfWithOneElement() {
        var expectedIndex = 0;
        var expectedObject = new Object();
        linkedList.add(expectedObject);
        assertEquals(expectedIndex, linkedList.indexOf(expectedObject));
    }

    @Test
    void lastIndexOf() {
        var expectedIndex = 3;
        var expectedObject = new Object();
        linkedList.add(new Object());
        linkedList.add(expectedObject);
        linkedList.add(new Object());
        linkedList.add(expectedObject);
        linkedList.add(new Object());
        assertEquals(expectedIndex, linkedList.lastIndexOf(expectedObject));
    }

    @Test
    void lastIndexOfWithOneElement() {
        var expectedIndex = 0;
        var expectedObject = new Object();
        linkedList.add(expectedObject);
        assertEquals(expectedIndex, linkedList.indexOf(expectedObject));
    }
}