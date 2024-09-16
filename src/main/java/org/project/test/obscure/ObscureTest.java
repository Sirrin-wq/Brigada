package org.project.test.obscure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.obscure.Obscure;

import static org.junit.jupiter.api.Assertions.*;

public class ObscureTest {
    @DisplayName("This test tests constructor and get method")
    @Test
    public void testConstructorAndGet() {
        Obscure<String> obscure = new Obscure<>("Weather");
        assertEquals("Weather", obscure.get());
    }

    @DisplayName("This test tests isPresent() method")
    @Test
    public void testIsPresent() {
        Obscure<String> obscureWithNonNull = new Obscure<>("Weather");
        assertTrue(obscureWithNonNull.isPresent());

        Obscure<String> obscureWithNull = new Obscure<>(null);
        assertFalse(obscureWithNull.isPresent());
    }

    @DisplayName("This test tests isEmpty() method")
    @Test
    public void testIsEmpty() {
        Obscure<String> obscureWithNonNull = new Obscure<>("Weather");
        assertFalse(obscureWithNonNull.isEmpty());

        Obscure<String> obscureWithNull = new Obscure<>(null);
        assertTrue(obscureWithNull.isEmpty());
    }

    @DisplayName("This test tests orElse() method")
    @Test
    public void testOrElse() {
        Obscure<String> obscureWithNonNull = new Obscure<>("Weather");
        assertEquals("Weather", obscureWithNonNull.orElse("Default"));

        Obscure<String> obscureWithNull = new Obscure<>(null);
        assertEquals("Default", obscureWithNull.orElse("Default"));
    }

    @DisplayName("This test tests orElseThrow() method")
    @Test
    public void testOrElseThrow(){
        Obscure<String> obscureWithNonNull = new Obscure<>("Weather");
        assertDoesNotThrow(() -> obscureWithNonNull.orElseThrow(new RuntimeException("Object is null")));
        assertEquals("Weather", obscureWithNonNull.orElseThrow(new RuntimeException("Object is null")));
    }

    @DisplayName("This test tests of() method")
    @Test
    public void testOf(){
        Obscure<String> obscure = Obscure.of("Weather");
        assertEquals("Weather", obscure.get());
    }

    @DisplayName("This test tests empty() method")
    @Test
    public void empty(){
        Obscure<String> obscure = Obscure.empty();
        assertNotNull(obscure);
        assertNull(obscure.get());
    }

}
