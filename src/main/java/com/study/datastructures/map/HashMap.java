package com.study.datastructures.map;

import com.study.datastructures.list.LinkedList;
import com.study.datastructures.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private List<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int bucketsCapacity) {
        initMap(bucketsCapacity);
    }

    @Override
    public V put(K key, V value) {
        if (size >= buckets.length * 0.75) {
            expandBucketArray();
        }
        var entry = findEntryByKey(key);
        if (entry == null) {
            getBucketByKey(key).add(new Entry<>(key, value));
            size++;
            return value;
        }
        var oldValue = entry.value;
        entry.value = value;
        return oldValue;
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
        var bucket = getBucketByKey(key);
        for (int i = 0; i < bucket.size(); i++) {
            var element = bucket.get(i);
            if (Objects.equals(key, element.key)) {
                return element;
            }
        }
        return null;
    }

    private Entry<K, V> removeEntryByKey(K key) {
        var bucket = getBucketByKey(key);
        for (int i = 0; i < bucket.size(); i++) {
            var element = bucket.get(i);
            if (Objects.equals(key, element.key)) {
                size--;
                return bucket.remove(i);
            }
        }
        return null;
    }

    private List<Entry<K, V>> getBucketByKey(K key) {
        return buckets[Math.abs(key.hashCode() % (buckets.length - 1))];
    }

    private void expandBucketArray() {
        var oldBuckets = buckets;
        initMap(oldBuckets.length * 2);
        for (List<Entry<K, V>> bucket : oldBuckets) {
            for (Entry<K, V> element : bucket) {
                put(element.key, element.value);
            }
        }
    }

    private void initMap(int capacity) {
        buckets = (List<Entry<K, V>>[]) new List[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MapIterator();
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
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
        private final Iterator<List<Entry<K, V>>> bucketIterator = Arrays.stream(buckets).iterator();
        private Iterator<Entry<K, V>> currentIterator;

        private MapIterator() {
            currentIterator = bucketIterator.next().iterator();
        }

        @Override
        public boolean hasNext() {
            while (!currentIterator.hasNext() && bucketIterator.hasNext()) {
                currentIterator = bucketIterator.next().iterator();
            }
            return bucketIterator.hasNext() || currentIterator.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            if (hasNext()) {
                return currentIterator.next();
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            currentIterator.remove();
            size--;
        }
    }
}
