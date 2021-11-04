package com.study.datastructures.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private int size;

    private Entry<K, V>[] buckets;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int bucketsCapacity) {
        initMap(bucketsCapacity);
    }

    @Override
    public V put(K key, V value) {
        if (size == buckets.length * 0.75) {
            expandBucketArray();
        }
        var bucketIndex = getBucketIndexByHash(key.hashCode());
        var entry = buckets[bucketIndex];
        Entry<K, V> previousEntry = null;
        while (entry != null) {
            if (Objects.equals(key, entry.key)) {
                var pldValue = entry.value;
                entry.value = value;
                return pldValue;
            }
            previousEntry = entry;
            entry = entry.next;
        }
        if (previousEntry != null) {
            previousEntry.next = new Entry<>(key, key.hashCode(), value);
            size++;
            return value;
        }
        entry = new Entry<>(key, key.hashCode(), value);
        buckets[bucketIndex] = entry;
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        var entry = findEntryByKey(key);
        return entry == null ? null : entry.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return findEntryByKey(key) != null;
    }

    @Override
    public V remove(K key) {
        var entry = removeEntryByKey(key);
        return entry == null ? null : entry.value;
    }

    int bucketsSize() {
        return buckets.length;
    }

    private Entry<K, V> findEntryByKey(K key) {
        var bucketIndex = getBucketIndexByHash(key.hashCode());
        var entry = buckets[bucketIndex];
        while (entry != null) {
            if (Objects.equals(key, entry.key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    private Entry<K, V> removeEntryByKey(K key) {
        var bucketIndex = getBucketIndexByHash(key.hashCode());
        var entry = buckets[bucketIndex];
        Entry<K, V> previousEntry = null;
        while (entry != null) {
            if (Objects.equals(key, entry.key)) {
                size--;
                if (previousEntry != null) {
                    previousEntry.next = entry.next;
                    entry.next = null;
                    return entry;
                }
                buckets[bucketIndex] = entry.next;
                entry.next = null;
                return entry;
            }
            previousEntry = entry;
            entry = entry.next;
        }
        return null;
    }

    private int getBucketIndexByHash(int hash) {
        return Math.abs(hash % buckets.length);
    }

    private void expandBucketArray() {
        var newBuckets = (Entry<K, V>[]) new Entry[buckets.length * 2];
        var iterator = new MapIterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            var bucketIndex = getBucketIndexByHash(entry.hash);
            if (newBuckets[bucketIndex] != null) {
                newBuckets[bucketIndex].next = entry;
            }
            newBuckets[bucketIndex] = entry;
        }
        buckets = newBuckets;
    }

    private void initMap(int capacity) {
        buckets = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MapIterator();
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private final int hash;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, int hash, V value) {
            this.key = key;
            this.hash = hash;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }
    }

    private class MapIterator implements Iterator<Map.Entry<K, V>> {
        private int currentIndex;
        private Entry<K, V> currentEntry;
        private boolean isNextCalled;

        private MapIterator() {
        }

        @Override
        public boolean hasNext() {
            while (currentEntry == null && currentIndex != buckets.length) {
                currentEntry = buckets[currentIndex];
                currentIndex++;
            }
            return currentEntry != null;
        }

        @Override
        public Entry<K, V> next() {
            if (hasNext()) {
                var entry = currentEntry;
                currentEntry = currentEntry.next;
                isNextCalled = true;
                return entry;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (isNextCalled) {
                Entry<K, V> entryToRemove = buckets[currentIndex - 1];
                Entry<K, V> previousEntry = null;
                while (entryToRemove.next != currentEntry) {
                    previousEntry = entryToRemove;
                    entryToRemove = entryToRemove.next;
                }
                size--;
                if (previousEntry != null) {
                    previousEntry.next = entryToRemove.next;
                    return;
                }
                buckets[currentIndex - 1] = null;
                return;
            }
            throw new IllegalStateException("Remove call without next");
        }
    }
}
