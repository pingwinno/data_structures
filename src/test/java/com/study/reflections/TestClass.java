package com.study.reflections;

public class TestClass {

    private int field = 5;
    private Object field2 = 2;
    private boolean field3 = true;

    public boolean isField3() {
        return field3;
    }

    public int getField() {
        return field;
    }

    public Object getField2() {
        return field2;
    }

    public final void firstMethod() {

    }

    public final void secondMethod() {

    }

    private void thirdMethod(Object o) {

    }

    public final Object fourthMethod() {
        return new Object();
    }
}
