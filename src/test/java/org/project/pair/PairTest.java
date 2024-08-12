package org.project.pair;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {
    static Stream<Arguments> swapProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), 2, 1),
                Arguments.of(new Pair<>("a", "b"), "b", "a"),
                Arguments.of(new Pair<>(1.5, 'c'), 'c', 1.5)
        );
    }

    @ParameterizedTest
    @MethodSource("swapProviderArguments")
    void testSwap(Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
        Pair<Object, Object> swapped = Pair.swap(pair);

        assertEquals(expectedKey, swapped.getK());
        assertEquals(expectedValue, swapped.getV());
    }

    static Stream<Arguments> getSwappedProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), 2, 1),
                Arguments.of(new Pair<>("a", "b"), "b", "a"),
                Arguments.of(new Pair<>(1.5, 'c'), 'c', 1.5)
        );
    }

    @ParameterizedTest
    @MethodSource("getSwappedProviderArguments")
    void testGetSwapped(Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
        Pair<Object, Object> swapped = pair.getSwapped();

        assertEquals(expectedKey, swapped.getK());
        assertEquals(expectedValue, swapped.getV());
    }

    static Stream<Arguments> equalsProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), new Pair<>(1, 2), true),
                Arguments.of(new Pair<>("a", "b"), new Pair<>("a", "b"), true),
                Arguments.of(new Pair<>(1, 2), new Pair<>(2, 1), false),
                Arguments.of(new Pair<>(null, 1), new Pair<>(null, 1), true),
                Arguments.of(new Pair<>(1, null), new Pair<>(1, null), true),
                Arguments.of(new Pair<>(1, 2), null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("equalsProviderArguments")
    void testEquals(Pair<Object, Object> pair1, Pair<Object, Object> pair2, boolean expected) {
        assertEquals(expected, pair1.equals(pair2));
    }

    static Stream<Arguments> hashCodeProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), Objects.hash(1, 2)),
                Arguments.of(new Pair<>("a", "b"), Objects.hash("a", "b")),
                Arguments.of(new Pair<>(1.5, 'c'), Objects.hash(1.5, 'c'))
        );
    }

    @ParameterizedTest
    @MethodSource("hashCodeProviderArguments")
    void testHashCode(Pair<Object, Object> pair, int expectedHashCode) {
        assertEquals(expectedHashCode, pair.hashCode());
    }

    static Stream<Arguments> getterProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), 1, 2),
                Arguments.of(new Pair<>("a", "b"), "a", "b"),
                Arguments.of(new Pair<>(1.5, 'c'), 1.5, 'c')
        );
    }

    @ParameterizedTest
    @MethodSource("getterProviderArguments")
    void testGetters(Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
        assertEquals(expectedKey, pair.getK());
        assertEquals(expectedValue, pair.getV());
    }

    static Stream<Arguments> setterProviderArguments() {
        return Stream.of(
                Arguments.of(new Pair<>(1, 2), 3, 4, 3, 4),
                Arguments.of(new Pair<>("a", "b"), "x", "y", "x", "y"),
                Arguments.of(new Pair<>(1.5, 'c'), 2.5, 'd', 2.5, 'd')
        );
    }

    @ParameterizedTest
    @MethodSource("setterProviderArguments")
    void testSetters(Pair<Object, Object> pair, Object newKey, Object newValue, Object expectedKey,
                     Object expectedValue) {
        pair.setK(newKey);
        pair.setV(newValue);

        assertEquals(expectedKey, pair.getK());
        assertEquals(expectedValue, pair.getV());
    }
}

