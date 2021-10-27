package com.study.reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;

public class Reflection {
    private static final String BYTE = "byte";
    private static final String SHORT = "short";
    private static final String INT = "int";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String CHAR = "char";
    private static final String BOOLEAN = "boolean";

    public static <T> T getInstance(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return tClass.getConstructor().newInstance();
    }

    public static void callAllMethods(Object object) {

    }

    public static void showAllFinalMethods(Object object) {
        Arrays.stream(object.getClass().getMethods())
                .filter(method -> Modifier.isFinal(method.getModifiers()))
                .forEach(method -> System.out.println("Method: " + method.getName()
                        + "\nParams types: "
                        + Arrays.toString(method.getParameterTypes())
                        + "\nReturn types: " + method.getReturnType() + "\n"));
    }

    public static void showAllNonPublicMethods(Class<?> tClass) {
        Arrays.stream(tClass.getDeclaredMethods())
                .filter(method -> Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers()))
                .forEach(method -> System.out.println("Private method: " + method.getName()));
    }

    public static void showAllParentsAndInterfaces(Class<?> tClass) {
        System.out.println("Interfaces of class " + tClass.getName() + ": " + Arrays.toString(tClass.getInterfaces()));
        var superClasses = new LinkedList<Class<?>>();
        Class<?> clazz = tClass.getSuperclass();
        while (clazz != null) {
            superClasses.add(clazz);
            clazz = clazz.getSuperclass();
        }
        System.out.println("Superclasses of class " + tClass.getName() + ": " + superClasses);
    }

    public static void setAllPrivateFieldsToDefaultValues(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> Modifier.isPrivate(field.getModifiers()))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(object, getInitializedValue(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new ReflectionException("Can't set field " + field.getName() + ". Error: " + e.getMessage());
                    }
                });
    }

    private static Object getInitializedValue(Class<?> clazz) {
        return switch (clazz.getName()) {
            case BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, CHAR -> 0;
            case BOOLEAN -> false;
            default -> null;
        };
    }
}
