package com.emazon.ApiReport.Application.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConstantsTest {

    @Test
    void testConstants() {
        assertEquals("Spring", AppConstants.SPRING);
        assertEquals("items", AppConstants.ITEMS);
        assertEquals(0, AppConstants.ZERO);
    }

    @Test
    void testConstructorThrowsException() {
        try {
            var constructor = AppConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true); // Permitir el acceso al constructor privado
            constructor.newInstance(); // Intentar instanciar la clase
            fail("Constructor should throw an exception");
        } catch (Exception e) {
            e.printStackTrace();

            assertTrue(e.getCause() instanceof UnsupportedOperationException, "Expected UnsupportedOperationException");

            UnsupportedOperationException uoe = (UnsupportedOperationException) e.getCause();
            assertEquals("This is a constants class and cannot be instantiated.", uoe.getMessage());
        }
    }
}
