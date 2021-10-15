package com.study.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T>, Iterable<T> {

    private static final int INITIAL_CAPACITY = 100;
    private int currentCapacity;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int size) {
        array = (T[]) new Object[size];
        currentCapacity = array.length;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Can't add new element. Index: " + index + " is bigger than size: " + size);
        }
        if (isExpansionNeeded()) {
            expandArray();
        }
        if (index < size) {
            moveElementsForAdd(index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        var temp = array[index];
        array[index] = null;

        moveElementsForRemove(index);

        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        isIndexValid(index);
        var temp = array[index];
        array[index] = value;
        return temp;
    }

    @Override
    public void clear() {
        clearArray();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size; i > 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExpansionNeeded() {
        return currentCapacity <= size;
    }

    private void expandArray() {
        var newArray = (T[]) new Object[currentCapacity = currentCapacity * 2];
        System.arraycopy(newArray, 0, array, 0, size);
        array = newArray;

    }

    private void moveElementsForAdd(int index) {
        if (size - index >= 0) {
            System.arraycopy(array, index + 1, array, index + 2, size - index);
        }
    }

    private void moveElementsForRemove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
    }

    private void isIndexValid(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + " out of list size:" + size);
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    private void clearArray() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;
        private boolean isNextInvoked = false;

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                isNextInvoked = true;
                return array[currentIndex++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException("Next hasn't been called on iterator yet");
            }
            ArrayList.this.remove(--currentIndex);
            isNextInvoked = false;
        }
    }
}
