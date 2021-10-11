package com.study.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.study.tools.TestTools.getPrivateField;
import static com.study.tools.TestTools.setPrivateField;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListTest {

    private static final String ARRAY_FIELD_NAME = "array";
    private static final String SIZE_FIELD_NAME = "size";

    private ArrayList arrayList;

    @BeforeEach
    void init() {
        arrayList = new ArrayList();
    }

    @Test
    void addOneElement() {
        var expectedObject = new Object();
        var expectedSize = 1;
        arrayList.add(expectedObject);
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = (int) getPrivateField(arrayList, SIZE_FIELD_NAME);
        assertEquals(expectedSize, arraySize);
        assertEquals(expectedObject, array[0]);
        assertNull(array[1]);
    }

    @Test
    void addTwoElements() {
        var firstExpectedObject = new Object();
        var secondExpectedObject = new Object();
        var expectedSize = 2;
        arrayList.add(firstExpectedObject);
        arrayList.add(secondExpectedObject);
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = (int) getPrivateField(arrayList, SIZE_FIELD_NAME);
        assertEquals(expectedSize, arraySize);
        assertEquals(firstExpectedObject, array[0]);
        assertEquals(firstExpectedObject, array[1]);
        assertNull(array[2]);
    }

    @Test
    void addByIndex() {
        var lastObject = 4;
        var indexOfNewObject = 3;
        for (int i = 0; i <= lastObject; i++) {
            arrayList.add(i);
        }
        var expectedObject = 20;
        var expectedSize = 6;
        arrayList.add(expectedObject, indexOfNewObject);
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = (int) getPrivateField(arrayList, SIZE_FIELD_NAME);
        assertEquals(expectedSize, arraySize);
        assertEquals(expectedObject, array[indexOfNewObject]);
        assertEquals(lastObject, array[lastObject]);
    }

    @Test
    void addByIndexThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(new Object(), 3));
    }

    @Test
    void remove() {
        var expectedObject = new Object();

        var removedObject = arrayList.remove(0);
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = (int) getPrivateField(arrayList, SIZE_FIELD_NAME);
        assertEquals(expectedObject, removedObject);
        assertNull(array[0]);
        assertEquals(0, arraySize);
    }

    @Test
    void removeThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
    }

    @Test
    void get() {
        var expectedObject = new Object();
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = 1;
        array[0] = expectedObject;
        injectArray(array, arraySize);
        assertEquals(expectedObject, arrayList.get(0));
    }

    @Test
    void getThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0));
    }

    @Test
    void set() {
        var initialObject = new Object();
        var array = (Object[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = 1;
        array[0] = initialObject;
        var expectedObject = new Object();
        setPrivateField(arrayList, ARRAY_FIELD_NAME, array);
        setPrivateField(arrayList, SIZE_FIELD_NAME, arraySize);
        assertEquals(expectedObject, arrayList.get(3));
    }

    @Test
    void setThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(new Object(), 3));
    }

    @Test
    void clear() {
        Object[] injectedArray = {1, 2, 3, 4, 5, 6};
        injectArray(injectedArray);
        var emptyArray = new int[100];
        arrayList.clear();
        var array = (int[]) getPrivateField(arrayList, ARRAY_FIELD_NAME);
        var arraySize = (int) getPrivateField(arrayList, SIZE_FIELD_NAME);
        assertArrayEquals(emptyArray, array);
        assertEquals(0, arraySize);
    }

    @Test
    void size() {
        var expectedSize = 1;
        setPrivateField(arrayList, SIZE_FIELD_NAME, expectedSize);
        assertEquals(expectedSize, arrayList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void isNotEmpty() {
        var arraySize = 1;
        setPrivateField(arrayList, SIZE_FIELD_NAME, arraySize);
        assertFalse(arrayList.isEmpty());
    }

    @Test
    void contains() {
        Object[] injectedArray = {1, 2, 3, 4, 5, 6};
        injectArray(injectedArray);
        var targetElement = 4;
        assertTrue(arrayList.contains(targetElement));
    }

    @Test
    void notContains() {
        Object[] injectedArray = {1, 2, 3, 5, 5, 6};
        injectArray(injectedArray);
        var targetElement = 4;
        assertFalse(arrayList.contains(targetElement));
    }

    @Test
    void indexOfExistingElement() {
        Object[] injectedArray = {1, 2, 3, 4, 5, 6};
        injectArray(injectedArray);
        var targetElement = 4;
        var expectedIndex = 3;
        assertEquals(expectedIndex, arrayList.indexOf(targetElement));
    }

    @Test
    void indexOfNonExistingElement() {
        var targetElement = 4;
        var expectedIndex = -1;
        assertEquals(expectedIndex, arrayList.indexOf(targetElement));
    }

    @Test
    void lastIndexOf() {
        Object[] injectedArray = {1, 2, 3, 4, 5, 6};
        injectArray(injectedArray);
        var targetElement = 2;
        var expectedIndex = 4;
        assertEquals(expectedIndex, arrayList.indexOf(targetElement));
    }

    private void injectArray(Object[] injectedArray) {
        injectArray(injectedArray, injectedArray.length);
    }

    private void injectArray(Object[] injectedArray, int size) {
        setPrivateField(arrayList, ARRAY_FIELD_NAME, injectedArray);
        setPrivateField(arrayList, SIZE_FIELD_NAME, injectedArray.length);
    }

}