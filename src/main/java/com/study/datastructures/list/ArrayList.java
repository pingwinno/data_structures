package com.study.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T>, Iterable<T> {

    private static final int INITIAL_CAPACITY = 100;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int size) {
        array = (T[]) new Object[size];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Can't add new element. Index: " + index + " is bigger than size: " + size);
        }
        if (isExpansionNeeded()) {
            expandArray();
        }
        if (size != index) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        var temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        array[size] = null;
        return temp;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        var temp = array[index];
        array[index] = value;
        return temp;
    }

    @Override
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
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
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExpansionNeeded() {
        return array.length == size;
    }

    private void expandArray() {
        var newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(newArray, 0, array, 0, size);
        array = newArray;

    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
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
