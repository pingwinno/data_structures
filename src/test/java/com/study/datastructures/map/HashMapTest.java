package com.study.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Random;

import static com.study.datastructures.tools.CollectionUtil.populateHashMap;
import static com.study.datastructures.tools.CollectionUtil.populateList;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_1;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_2;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_20;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_3;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_4;
import static com.study.datastructures.tools.Constants.EXPECTED_INTEGER_5;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_1;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_4;
import static com.study.datastructures.tools.Constants.EXPECTED_SIZE_5;
import static com.study.datastructures.tools.Constants.INDEX_0;
import static com.study.datastructures.tools.Constants.KEY_1;
import static com.study.datastructures.tools.Constants.KEY_2;
import static com.study.datastructures.tools.Constants.KEY_3;
import static com.study.datastructures.tools.Constants.KEY_4;
import static com.study.datastructures.tools.Constants.KEY_5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void should_expandBucketsArray_when_reachThreshold() {
        for (int i = 0; i < 15; i++) {
            hashMap.put(String.valueOf(new Random().nextInt()), 1);
        }
        var expectedSize = 32;
        assertEquals(expectedSize, hashMap.bucketsSize());
    }

    @Test
    void should_returnObjects_when_callNext() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        assertEquals(KEY_1, iterator.next().key());
        assertEquals(KEY_2, iterator.next().key());
        assertEquals(KEY_3, iterator.next().key());
        assertEquals(KEY_4, iterator.next().key());
        assertEquals(KEY_5, iterator.next().key());
    }

    @Test
    void should_returnTrue_when_callHasNext() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void should_returnFalse_when_callHasNextAfterLastElement() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void should_throwNoSuchElementException_when_callNextAfterLastElement() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void should_removeAllObjectsFromList_when_callRemove() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(INDEX_0, hashMap.size());
    }

    @Test
    void should_throwIllegalStateException_when_callRemoveBeforeNext() {
        populateHashMap(EXPECTED_SIZE_5, hashMap);
        var iterator = hashMap.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }
}