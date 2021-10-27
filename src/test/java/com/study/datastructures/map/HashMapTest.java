package com.study.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.study.datastructures.tools.CollectionUtil.populateHashMap;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_1;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_2;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_20;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_3;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_4;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_1;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_4;
import static com.study.datastructures.tools.Constants.KEY_1;
import static com.study.datastructures.tools.Constants.KEY_2;
import static com.study.datastructures.tools.Constants.KEY_3;
import static com.study.datastructures.tools.Constants.KEY_4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashMapTest {

    HashMap<String, Integer> hashMap;

    @BeforeEach
    void init() {
        hashMap = new HashMap<>();
    }

    @Test
    void should_returnValueByKey_when_putOnePair() {
        populateHashMap(1, hashMap);
        assertEquals(EXPECTED_INTEGER_1, hashMap.get(KEY_1));
    }

    @Test
    void should_returnFourValuesByKey_when_putFourPairs() {
        populateHashMap(4, hashMap);
        assertEquals(EXPECTED_INTEGER_1, hashMap.get(KEY_1));
        assertEquals(EXPECTED_INTEGER_2, hashMap.get(KEY_2));
        assertEquals(EXPECTED_INTEGER_3, hashMap.get(KEY_3));
        assertEquals(EXPECTED_INTEGER_4, hashMap.get(KEY_4));
    }


    @Test
    void should_returnReplaceOldValue_when_putNewValueWithSameKey() {
        var key = KEY_1;
        var oldValue = EXPECTED_INTEGER_1;
        hashMap.put(key, oldValue);
        assertEquals(oldValue, hashMap.get(key));
        var newValue = EXPECTED_INTEGER_20;
        hashMap.put(key, newValue);
        assertEquals(newValue, hashMap.get(key));
    }


    @Test
    void should_sizeReturn1_when_putOnePair() {
        populateHashMap(1, hashMap);

        assertEquals(EXPECTED_SIZE_1, hashMap.size());
    }

    @Test
    void should_sizeReturn4_when_putFourPairs() {
        populateHashMap(EXPECTED_SIZE_4, hashMap);

        assertEquals(EXPECTED_SIZE_4, hashMap.size());
    }

    @Test
    void should_removeOnePair_when_callRemove() {
        populateHashMap(EXPECTED_SIZE_4, hashMap);

        assertEquals(EXPECTED_INTEGER_1, hashMap.remove(KEY_1));

        assertNull(hashMap.get(KEY_1));
    }

    @Test
    void should_returnNull_when_callRemove() {
        assertNull(hashMap.remove(KEY_1));
    }

    @Test
    void should_removeOnePair_when_putFourPairs() {
        populateHashMap(EXPECTED_SIZE_4, hashMap);

        assertEquals(EXPECTED_INTEGER_1, hashMap.get(KEY_1));
        assertEquals(EXPECTED_INTEGER_2, hashMap.remove(KEY_2));
        assertEquals(EXPECTED_INTEGER_3, hashMap.get(KEY_3));
        assertEquals(EXPECTED_INTEGER_4, hashMap.get(KEY_4));

        assertNull(hashMap.get(KEY_2));
    }


    @Test
    void should_returnTrue_when_putOnePair() {
        populateHashMap(1, hashMap);

        assertTrue(hashMap.containsKey(KEY_1));
    }

    @Test
    void should_returnFalse_when_putOnePair() {
        populateHashMap(1, hashMap);

        assertFalse(hashMap.containsKey(KEY_2));
    }
}