package com.study.tools;

import java.lang.reflect.Field;

public class TestTools {

    public static Object getPrivateField(Object object, String fieldName) {
        try {
            Field privateStringField = object.getClass().getDeclaredField(fieldName);
            privateStringField.setAccessible(true);
            return privateStringField.get(object);
        } catch (NoSuchFieldException e) {
            throw new TestToolException("No such field: " + fieldName);
        } catch (IllegalAccessException e) {
            throw new TestToolException("Can't get access to field: " + fieldName);
        }
    }
}
