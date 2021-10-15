package datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var key = "1";
        var value = 1;

        hashMap.put(key, value);

        assertEquals(value, hashMap.get(key));
    }

    @Test
    void should_returnFourValuesByKey_when_putFourPairs() {
        var firstKey = "1";
        var firstValue = 1;
        var secondKey = "2";
        var secondValue = 2;
        var thirdKey = "3";
        var thirdValue = 3;
        var fourthKey = "4";
        var fourthValue = 4;

        hashMap.put(firstKey, firstValue);
        hashMap.put(secondKey, secondValue);
        hashMap.put(thirdKey, thirdValue);
        hashMap.put(fourthKey, fourthValue);

        assertEquals(firstValue, hashMap.get(firstKey));
        assertEquals(secondValue, hashMap.get(secondKey));
        assertEquals(thirdValue, hashMap.get(thirdKey));
        assertEquals(fourthValue, hashMap.get(fourthKey));
    }


    @Test
    void should_returnReplaceOldValue_when_putNewValueWithSameKey() {
        var key = "1";
        var oldValue = 1;
        hashMap.put(key, oldValue);
        assertEquals(oldValue, hashMap.get(key));
        var newValue = 2;
        hashMap.put(key, newValue);
        assertEquals(newValue, hashMap.get(key));
    }


    @Test
    void should_sizeReturn1_when_putOnePair() {
        var expectedSize = 1;
        var key = "1";
        var value = 1;

        hashMap.put(key, value);

        assertEquals(expectedSize, hashMap.size());
    }

    @Test
    void should_sizeReturn4_when_putFourPairs() {
        var expectedSize = 4;

        var firstKey = "1";
        var firstValue = 1;
        var secondKey = "2";
        var secondValue = 2;
        var thirdKey = "3";
        var thirdValue = 3;
        var fourthKey = "4";
        var fourthValue = 4;

        hashMap.put(firstKey, firstValue);
        hashMap.put(secondKey, secondValue);
        hashMap.put(thirdKey, thirdValue);
        hashMap.put(fourthKey, fourthValue);

        assertEquals(expectedSize, hashMap.size());
    }

    @Test
    void should_removeOnePair_when_putOnePair() {
        var key = "1";
        var value = 1;

        hashMap.put(key, value);

        assertEquals(value, hashMap.remove(key));

        assertNull(hashMap.get(key));
    }

    @Test
    void should_removeOnePair_when_putFourPairs() {
        var firstKey = "1";
        var firstValue = 1;
        var secondKey = "2";
        var secondValue = 2;
        var thirdKey = "3";
        var thirdValue = 3;
        var fourthKey = "4";
        var fourthValue = 4;

        hashMap.put(firstKey, firstValue);
        hashMap.put(secondKey, secondValue);
        hashMap.put(thirdKey, thirdValue);
        hashMap.put(fourthKey, fourthValue);

        assertEquals(firstValue, hashMap.get(firstKey));
        assertEquals(secondValue, hashMap.remove(secondKey));
        assertEquals(thirdValue, hashMap.get(thirdKey));
        assertEquals(fourthValue, hashMap.get(fourthKey));

        assertNull(hashMap.get(secondKey));
    }


    @Test
    void should_returnTrue_when_putOnePair() {
        var key = "1";
        var value = 1;

        hashMap.put(key, value);

        assertTrue(hashMap.containsKey(key));
    }
}