package com.study.datastructures.map;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    int size();

    boolean containsKey(K key);

    V remove(K key);

    interface Entry<K, V> {
        K key();
        V value();
    }

}