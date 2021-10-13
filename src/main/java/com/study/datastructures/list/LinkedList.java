package com.study.datastructures.list;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {

    private int size;
    private Node head;
    private Node tail;

    public LinkedList() {
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Can't add new element. Index: " + index + " is bigger than size: " + size);
        }
        var newNode = new Node(value);
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
    public Object remove(int index) {
        isIndexValid(index);
        var node = getNodeByIndex(index);
        var nextNode = node.nextNode;
        var previousNode = node.previousNode;
        if (nextNode != null) {
            nextNode.previousNode = previousNode;
        }
        if (previousNode != null) {
            previousNode.nextNode = nextNode;
        }
        node.nextNode = node.previousNode = null;
        size--;
        return node.value;
    }

    @Override
    public Object get(int index) {
        isIndexValid(index);
        if (index == 0) {
            return head.value;
        }
        return getNodeByIndex(index).value;
    }

    @Override
    public Object set(Object value, int index) {
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
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
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
    public int lastIndexOf(Object value) {
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

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        var node = head;
        while (head != null) {
            stringJoiner.add(String.valueOf(head.value));
            head = head.nextNode;
        }
        return stringJoiner.toString();
    }

    private Node getNodeByIndex(int index) {
        Node node = null;
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

    private static class Node {
        private Node previousNode;
        private Node nextNode;
        private Object value;

        public Node(Object value) {
            this.value = value;
        }
    }
}
