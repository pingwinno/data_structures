package com.study.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T>, Iterable<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
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
        var newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            newNode.nextNode = head;
            head = newNode;
        } else if (index == size) {
            newNode.previousNode = tail;
            tail.nextNode = newNode;
            tail = newNode;
        } else {
            var targetNode = getNodeByIndex(index);
            newNode.previousNode = targetNode.previousNode;
            newNode.nextNode = targetNode;
            targetNode.previousNode.nextNode = newNode;
            targetNode.previousNode = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        var node = getNodeByIndex(index);
        removeNode(node);
        return node.value;
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        if (index == 0) {
            return head.value;
        }
        return getNodeByIndex(index).value;
    }

    @Override
    public T set(T value, int index) {
        isIndexValid(index);
        var node = getNodeByIndex(index);
        var oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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
        var node = head;
        int currentIndex = 0;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return currentIndex;
            }
            node = node.nextNode;
            currentIndex++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        var node = tail;
        int currentIndex = size - 1;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return currentIndex;
            }
            node = node.previousNode;
            currentIndex--;
        }
        return -1;
    }

    private void removeNode(Node<T> node) {
        var nextNode = node.nextNode;
        var previousNode = node.previousNode;
        if (nextNode != null) {
            nextNode.previousNode = previousNode;
        }
        if (previousNode != null) {
            previousNode.nextNode = nextNode;
        }
        if (node == head) {
            head = nextNode;
        }
        if (node == tail) {
            tail = previousNode;
        }
        node.nextNode = node.previousNode = null;
        size--;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = null;
        if (index <= size / 2) {
            node = head;
            int currentIndex = 0;
            while (node.nextNode != null && currentIndex < index) {
                node = node.nextNode;
                currentIndex++;
            }
        } else if (index >= size / 2) {
            node = tail;
            int currentIndex = size - 1;
            while (node.previousNode != null && currentIndex > index) {
                node = node.previousNode;
                currentIndex--;
            }
        }
        return node;
    }

    private void isIndexValid(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + " out of list size:" + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private static class Node<T> {
        private Node<T> previousNode;
        private Node<T> nextNode;
        private T value;

        private Node(T value) {
            this.value = value;
        }
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> currentNode = head;
        private Node<T> previousNode = null;
        private boolean isNextInvoked = false;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                isNextInvoked = true;
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
                return previousNode.value;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (!isNextInvoked) {
                throw new IllegalStateException("Next hasn't been called on iterator yet");
            }
            removeNode(previousNode);
            isNextInvoked = false;
        }
    }
}
