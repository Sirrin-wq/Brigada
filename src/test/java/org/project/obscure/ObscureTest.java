package org.project.obscure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObscureTest {

    static Stream<Arguments> ofProviderArguments() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("Test", "Test"),
                Arguments.of(1, 1),
                Arguments.of(true, true),
                Arguments.of(1.5, 1.5),
                Arguments.of('t', 't')
        );
    }

    @ParameterizedTest
    @MethodSource("ofProviderArguments")
    void of(Object object, Object expected) {
        Obscure<?> actual = Obscure.of(object);

        assertEquals(expected, actual.get());
    }

    @Test
    void empty() {
        Obscure<?> actual = Obscure.empty();

        assertNull(actual.get());
    }

    static Stream<Arguments> getProviderArguments() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("Test", "Test"),
                Arguments.of(1, 1),
                Arguments.of(true, true),
                Arguments.of(1.5, 1.5),
                Arguments.of('t', 't')
        );
    }

    @ParameterizedTest
    @MethodSource("getProviderArguments")
    void get(Object object, Object expected) {
        Obscure<?> actual = new Obscure<>(object);

        assertEquals(expected, actual.get());
    }

    static Stream<Arguments> isPresentProviderArguments() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("Test", true),
                Arguments.of(1, true),
                Arguments.of(true, true),
                Arguments.of(1.5, true),
                Arguments.of('t', true)
        );
    }

    @ParameterizedTest
    @MethodSource("isPresentProviderArguments")
    void isPresent(Object object, boolean expected) {
        Obscure<?> obscure = new Obscure<>(object);

        boolean actual = obscure.isPresent();

        assertEquals(expected, actual);
    }

    static Stream<Arguments> isEmptyProviderArguments() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("Test", false),
                Arguments.of(1, false),
                Arguments.of(true, false),
                Arguments.of(1.5, false),
                Arguments.of('t', false)
        );
    }

    @ParameterizedTest
    @MethodSource("isEmptyProviderArguments")
    void isEmpty(Object object, boolean expected) {
        Obscure<?> obscure = new Obscure<>(object);

        boolean actual = obscure.isEmpty();

        assertEquals(expected, actual);
    }

    static Stream<Arguments> orElseProviderArguments() {
        return Stream.of(
                Arguments.of(null, "Test", "Test"),
                Arguments.of("Test", "Code", "Test"),
                Arguments.of(1, 2, 1),
                Arguments.of(true, false, true),
                Arguments.of(1.5, 10.5, 1.5),
                Arguments.of('t', 'j', 't')
        );
    }

    @ParameterizedTest
    @MethodSource("orElseProviderArguments")
    void orElse(Object object, Object defaultObject, Object expected) {
        Obscure<Object> obscure = new Obscure<>(object);

        Object actual = obscure.orElse(defaultObject);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> orElseThrowNullProviderArguments() {
        return Stream.of(
                Arguments.of((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("orElseThrowNullProviderArguments")
    void orElseThrowNull(Object object) {
        Obscure<Object> obscure = new Obscure<>(object);
        Exception exception = new NullPointerException();

        assertThrows(NullPointerException.class, () -> obscure.orElseThrow(exception));
    }

    static Stream<Arguments> orElseThrowProviderArguments() {
        return Stream.of(
                Arguments.of("Test", "Test"),
                Arguments.of(1, 1),
                Arguments.of(true, true),
                Arguments.of(1.5, 1.5),
                Arguments.of('t', 't')
        );
    }

    @ParameterizedTest
    @MethodSource("orElseThrowProviderArguments")
    void orElseThrow(Object object, Object expected) throws Exception {
        Obscure<Object> obscure = new Obscure<>(object);
        Exception exception = new Exception();

        Object actual = obscure.orElseThrow(exception);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> equalsProviderArguments() {
        return Stream.of(
                Arguments.of(new Obscure<>("Test"), new Obscure<>("Test"), true),
                Arguments.of(new Obscure<>("Test"), new Obscure<>(null), false),
                Arguments.of(new Obscure<>(null), new Obscure<>(null), true),
                Arguments.of(new Obscure<>(null), new Obscure<>("Test"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("equalsProviderArguments")
    void testEquals(Obscure<String> obscure1, Obscure<String> obscure2, boolean expected) {
        assertEquals(expected, obscure1.equals(obscure2));
    }

    static Stream<Arguments> hashCodeProviderArguments() {
        return Stream.of(
                Arguments.of(new Obscure<>("Test"), new Obscure<>("Test"), true),
                Arguments.of(new Obscure<>("Test"), new Obscure<>(null), false),
                Arguments.of(new Obscure<>(null), new Obscure<>(null), true),
                Arguments.of(new Obscure<>(null), new Obscure<>("Test"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("hashCodeProviderArguments")
    void testHashCode(Obscure<String> obscure1, Obscure<String> obscure2, boolean expected) {
        assertEquals(expected, obscure1.hashCode() == obscure2.hashCode());
    }

    static Stream<Arguments> getObjectProviderArguments() {
        return Stream.of(
                Arguments.of(new Obscure<>("Test"), "Test"),
                Arguments.of(new Obscure<>(null), null)
        );
    }

    @ParameterizedTest
    @MethodSource("getObjectProviderArguments")
    void getObject(Obscure<String> actual, String expected) {
        assertEquals(expected, actual.get());
    }

    static Stream<Arguments> setObjectProviderArguments() {
        return Stream.of(
                Arguments.of("New Value", "New Value")
        );
    }

    @ParameterizedTest
    @MethodSource("setObjectProviderArguments")
    void setObject(String newValue, String expected) {
        Obscure<String> actual = Obscure.of(newValue);

        assertEquals(expected, actual.get());
    }
}
