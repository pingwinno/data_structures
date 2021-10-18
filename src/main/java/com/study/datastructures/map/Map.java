package com.study.datastructures.map;

public interface Map<K,V> {
    V put(K key, V value);

    V get(K key);

    int size();

    boolean containsKey(K key);

    V remove(K key);

}