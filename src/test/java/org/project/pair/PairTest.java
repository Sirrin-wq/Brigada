package org.project.pair;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PairTest {
  static @NotNull Stream<Arguments> swapProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), 2, 1),
        Arguments.of(new Pair<>("a", "b"), "b", "a"),
        Arguments.of(new Pair<>(1.5, 'c'), 'c', 1.5),
        Arguments.of(new Pair<>(null, "value"), "value", null),
        Arguments.of(new Pair<>("key", null), null, "key"),
        Arguments.of(new Pair<>(null, null), null, null));
  }

  @DisplayName("This method tests the Pair method.")
  @ParameterizedTest(
      name =
          "The pair {0} to be swapped. The expected key {1} after swapping. The expected value {2} after swapping.")
  @MethodSource("swapProviderArguments")
  void testSwap(Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
    Pair<Object, Object> swapped = Pair.swap(pair);

    assertAll(
        () -> assertEquals(expectedKey, swapped.getK()),
        () -> assertEquals(expectedValue, swapped.getV()));
  }

  static @NotNull Stream<Arguments> getSwappedProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), 2, 1),
        Arguments.of(new Pair<>("a", "b"), "b", "a"),
        Arguments.of(new Pair<>(1.5, 'c'), 'c', 1.5),
        Arguments.of(new Pair<>(null, "value"), "value", null),
        Arguments.of(new Pair<>("key", null), null, "key"),
        Arguments.of(new Pair<>(null, null), null, null));
  }

  @DisplayName(
      "This method returns a new Pair object with the values of the current Pair object swapped.")
  @ParameterizedTest(
      name =
          "The type of the first element {0} in the Pair. The type of the second element {1} in the Pair. "
              + "A new Pair object {2} with the values of the current Pair object swapped.")
  @MethodSource("getSwappedProviderArguments")
  void testGetSwapped(
      @NotNull Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
    Pair<Object, Object> swapped = pair.getSwapped();

    assertAll(
        () -> assertEquals(expectedKey, swapped.getK()),
        () -> assertEquals(expectedKey, swapped.getK()));
  }

  static @NotNull Stream<Arguments> equalsProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), new Pair<>(1, 2), true),
        Arguments.of(new Pair<>("a", "b"), new Pair<>("a", "b"), true),
        Arguments.of(new Pair<>(1, 2), new Pair<>(2, 1), false),
        Arguments.of(new Pair<>(null, 1), new Pair<>(null, 1), true),
        Arguments.of(new Pair<>(1, null), new Pair<>(1, null), true),
        Arguments.of(new Pair<>(1, 2), new Pair<>(1, 3), false),
        Arguments.of(new Pair<>(null, null), new Pair<>(null, null), true),
        Arguments.of(new Pair<>(1, 2), null, false));
  }

  @DisplayName("This method tests the equality of two Pair objects.")
  @ParameterizedTest(
      name =
          "The first Pair object {0} to compare. The second Pair object {1} to compare. "
              + "The expected {2} boolean result of the equality comparison.")
  @MethodSource("equalsProviderArguments")
  void testEquals(
      @NotNull Pair<Object, Object> pair1, Pair<Object, Object> pair2, boolean expected) {
    assertEquals(expected, pair1.equals(pair2));
  }

  static @NotNull Stream<Arguments> hashCodeProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), Objects.hash(1, 2)),
        Arguments.of(new Pair<>("a", "b"), Objects.hash("a", "b")),
        Arguments.of(new Pair<>(1.5, 'c'), Objects.hash(1.5, 'c')),
        Arguments.of(new Pair<>(null, "value"), Objects.hash(null, "value")),
        Arguments.of(new Pair<>("key", null), Objects.hash("key", null)),
        Arguments.of(new Pair<>(null, null), Objects.hash(null, null)));
  }

  @DisplayName("This method tests the hashCode of a Pair object.")
  @ParameterizedTest(
      name = "The Pair object {0}  to calculate the hashCode for. The expected {1} hashCode value.")
  @MethodSource("hashCodeProviderArguments")
  void testHashCode(@NotNull Pair<Object, Object> pair, int expectedHashCode) {
    assertEquals(expectedHashCode, pair.hashCode());
  }

  static @NotNull Stream<Arguments> getterProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), 1, 2),
        Arguments.of(new Pair<>("a", "b"), "a", "b"),
        Arguments.of(new Pair<>(1.5, 'c'), 1.5, 'c'),
        Arguments.of(new Pair<>(null, "value"), null, "value"),
        Arguments.of(new Pair<>("key", null), "key", null),
        Arguments.of(new Pair<>(null, null), null, null));
  }

  @DisplayName("This method tests the getter methods of the Pair class.")
  @ParameterizedTest(
      name =
          "The Pair object {0} to test the getter methods on. "
              + "The expected {1} value of the key after calling the getter method. "
              + "The expected {2} value of the value after calling the getter method.")
  @MethodSource("getterProviderArguments")
  void testGetters(@NotNull Pair<Object, Object> pair, Object expectedKey, Object expectedValue) {
    assertAll(
        () -> assertEquals(expectedKey, pair.getK()),
        () -> assertEquals(expectedValue, pair.getV()));
  }

  static @NotNull Stream<Arguments> setterProviderArguments() {
    return Stream.of(
        Arguments.of(new Pair<>(1, 2), 3, 4, 3, 4),
        Arguments.of(new Pair<>("a", "b"), "x", "y", "x", "y"),
        Arguments.of(new Pair<>(1.5, 'c'), 2.5, 'd', 2.5, 'd'),
        Arguments.of(new Pair<>(null, "value"), "newKey", "newValue", "newKey", "newValue"),
        Arguments.of(new Pair<>("key", null), "newKey", "newValue", "newKey", "newValue"),
        Arguments.of(new Pair<>(null, null), "key", "value", "key", "value"));
  }

  @DisplayName("This method tests the setter methods of the Pair class.")
  @ParameterizedTest(
      name =
          "The Pair object {0} to test the setter methods on. The new value {1} to set for the key. "
              + "The new value {2} to set for the value. "
              + "The expected value {2} of the key after calling the setter method. "
              + "The expected value {3} of the value after calling the setter method.")
  @MethodSource("setterProviderArguments")
  void testSetters(
      @NotNull Pair<Object, Object> pair,
      Object newKey,
      Object newValue,
      Object expectedKey,
      Object expectedValue) {
    pair.setK(newKey);
    pair.setV(newValue);

    assertAll(
        () -> assertEquals(expectedKey, pair.getK()),
        () -> assertEquals(expectedValue, pair.getV()));
  }
}
