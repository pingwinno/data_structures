package com.study.reflections;

import com.study.datastructures.list.LinkedList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ReflectionTest {


    @Test
    void should_returnInstanceOfLinkedList_when_callAndPassLinkedListClass() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var expectedInstance = Reflection.getInstance(LinkedList.class);

        assertNotNull(expectedInstance);
    }

    @Test
    @Disabled
    void should_invokeAllObjectsMethodsWithoutParams_when_callCallAllMethods() {
        var mockedObject = mock(TestClass.class);
        doNothing().when(mockedObject).firstMethod();
        doNothing().when(mockedObject).secondMethod();

        Reflection.callAllMethods(new TestClass());

        verify(mockedObject).firstMethod();
        verify(mockedObject).secondMethod();
    }

    @Test
    void should_displayAllObjectsFinalMethods_when_callShowFinalMethods() {
        Reflection.showAllFinalMethods(new TestClass());
    }

    @Test
    void should_displayAllClassNonPublicMethods_when_callShowNonPublicMethods() {
        Reflection.showAllNonPublicMethods(TestClass.class);
    }

    @Test
    void should_displayAllParentsAndInterfaces_when_callShowParentsAndInterfaces() {
        Reflection.showAllParentsAndInterfaces(int.class);
    }

    @Test
    void should_getDefaultValues_when_callSetAllPrivateFieldsToDefaultValues() {
        var testClass = new TestClass();
        assertEquals(5, testClass.getField());
        assertEquals(2, testClass.getField2());
        assertTrue(testClass.isField3());
        Reflection.setAllPrivateFieldsToDefaultValues(testClass);
        assertEquals(0, testClass.getField());
        assertNull(testClass.getField2());
        assertFalse(testClass.isField3());
    }


}