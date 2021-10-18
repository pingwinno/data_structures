package com.study.datastructures.tools;

import com.study.datastructures.list.List;
import com.study.datastructures.map.Map;

public class CollectionUtil {

    public static void populateList(int numberOfElements, List<Integer> list) {
        for (int i = 1; i <= numberOfElements; i++) {
            list.add(i);
        }
    }

    public static Integer[] createArray(int numberOfElements) {
        var array = new Integer[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static void populateHashMap(int numberOfElements, Map<String, Integer> map) {
        for (int i = 1; i <= numberOfElements; i++) {
            map.put(String.valueOf(i), i);
        }
    }
}
