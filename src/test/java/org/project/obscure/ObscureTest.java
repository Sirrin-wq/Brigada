package org.project.obscure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ObscureTest {

  static @NotNull Stream<Arguments> ofProviderArguments() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of("Test", "Test"),
        Arguments.of(1, 1),
        Arguments.of(true, true),
        Arguments.of(1.5, 1.5),
        Arguments.of('t', 't'),
        Arguments.of("Another String", "Another String"),
        Arguments.of(0, 0),
        Arguments.of("", ""),
        Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE),
        Arguments.of(Long.MIN_VALUE, Long.MIN_VALUE));
  }

  @DisplayName("This method creates an instance of Obscure with the provided object.")
  @ParameterizedTest(
      name =
          "The object {0} to be stored in the Obscure instance. "
              + "An Obscure {1} instance containing the provided object.")
  @MethodSource("ofProviderArguments")
  void of(Object object, Object expected) {
    Obscure<?> actual = Obscure.of(object);

    assertEquals(expected, actual.get());
  }

  @DisplayName("This method creates an empty instance of Obscure.")
  @Test
  void empty() {
    Obscure<?> actual = Obscure.empty();

    assertNull(actual.get());
  }

  static @NotNull Stream<Arguments> getProviderArguments() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of("Test", "Test"),
        Arguments.of(1, 1),
        Arguments.of(true, true),
        Arguments.of(1.5, 1.5),
        Arguments.of('t', 't'),
        Arguments.of("Another String", "Another String"),
        Arguments.of(0, 0),
        Arguments.of("", ""),
        Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE),
        Arguments.of(Long.MIN_VALUE, Long.MIN_VALUE));
  }

  @DisplayName("This method retrieves the object stored in the Obscure instance.")
  @ParameterizedTest(
      name = "The object {0} to be retrieved. The expected {1} object to be retrieved.")
  @MethodSource("getProviderArguments")
  void get(Object object, Object expected) {
    Obscure<?> actual = new Obscure<>(object);

    assertEquals(expected, actual.get());
  }

  static @NotNull Stream<Arguments> isPresentProviderArguments() {
    return Stream.of(
        Arguments.of(null, false),
        Arguments.of("Test", true),
        Arguments.of(1, true),
        Arguments.of(true, true),
        Arguments.of(1.5, true),
        Arguments.of('t', true),
        Arguments.of("", true),
        Arguments.of(Double.MIN_VALUE, true),
        Arguments.of("NonEmpty", true),
        Arguments.of(false, true));
  }

  @DisplayName("This method checks if the object stored in the Obscure instance is not null.")
  @ParameterizedTest(
      name = "The object {0} to be checked. The expected {1} object to be retrieved.")
  @MethodSource("isPresentProviderArguments")
  void isPresent(Object object, boolean expected) {
    Obscure<?> obscure = new Obscure<>(object);

    boolean actual = obscure.isPresent();

    assertEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> isEmptyProviderArguments() {
    return Stream.of(
        Arguments.of(null, true),
        Arguments.of("Test", false),
        Arguments.of(1, false),
        Arguments.of(true, false),
        Arguments.of(1.5, false),
        Arguments.of('t', false),
        Arguments.of("", false),
        Arguments.of(Double.MIN_VALUE, false),
        Arguments.of("NonEmpty", false),
        Arguments.of(false, false));
  }

  @DisplayName("This method checks if the object stored in the Obscure instance is null.")
  @ParameterizedTest(
      name =
          "The object {0} to be checked. The expected {1} boolean value indicating whether the object is null or not")
  @MethodSource("isEmptyProviderArguments")
  void isEmpty(Object object, boolean expected) {
    Obscure<?> obscure = new Obscure<>(object);

    boolean actual = obscure.isEmpty();

    assertEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> orElseProviderArguments() {
    return Stream.of(
        Arguments.of(null, "Test", "Test"),
        Arguments.of("Test", "Code", "Test"),
        Arguments.of(1, 2, 1),
        Arguments.of(true, false, true),
        Arguments.of(1.5, 10.5, 1.5),
        Arguments.of('t', 'j', 't'),
        Arguments.of(null, "", ""),
        Arguments.of("", "Default", ""),
        Arguments.of(null, 100L, 100L),
        Arguments.of(null, 0.0, 0.0));
  }

  @DisplayName(
      "This method returns the value of the object stored in the Obscure instance,"
          + " or the specified default value if the object is null.")
  @ParameterizedTest(
      name =
          "The object {0} stored in the Obscure instance. The default value {1} to be returned if the object is null. "
              + "The value of the object {2}  stored in the Obscure instance, or the specified default value.")
  @MethodSource("orElseProviderArguments")
  void orElse(Object object, Object defaultObject, Object expected) {
    Obscure<Object> obscure = new Obscure<>(object);

    Object actual = obscure.orElse(defaultObject);

    assertEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> orElseThrowNullProviderArguments() {
    return Stream.of(Arguments.of((Object) null));
  }

  @DisplayName(
      "This method throws the specified exception if the object stored in the Obscure instance is null.")
  @ParameterizedTest(name = "The exception to be thrown if the object is {0}.")
  @MethodSource("orElseThrowNullProviderArguments")
  void orElseThrowNull(Object object) {
    Obscure<Object> obscure = new Obscure<>(object);

    assertThrows(NullPointerException.class, () -> obscure.orElseThrow(NullPointerException::new));
  }

  static @NotNull Stream<Arguments> orElseThrowProviderArguments() {
    return Stream.of(
        Arguments.of("Test", "Test"),
        Arguments.of(1, 1),
        Arguments.of(true, true),
        Arguments.of(1.5, 1.5),
        Arguments.of('t', 't'),
        Arguments.of("Another String", "Another String"),
        Arguments.of(0, 0),
        Arguments.of("", ""),
        Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE),
        Arguments.of(Long.MIN_VALUE, Long.MIN_VALUE));
  }

  @DisplayName(
      "This method returns the value of the object stored in the Obscure instance, "
          + "or throws the specified exception if the object is null.")
  @ParameterizedTest(
      name =
          "The object {0} stored in the Obscure instance. "
              + "The expected {1} value to be returned if the object is not null.")
  @MethodSource("orElseThrowProviderArguments")
  void orElseThrow(Object object, Object expected) {
    Obscure<Object> obscure = new Obscure<>(object);

    Object actual = obscure.orElseThrow(IllegalStateException::new);

    assertEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> equalsProviderArguments() {
    return Stream.of(
        Arguments.of(new Obscure<>("Test"), new Obscure<>("Test"), true),
        Arguments.of(new Obscure<>("Test"), new Obscure<>(null), false),
        Arguments.of(new Obscure<>(null), new Obscure<>(null), true),
        Arguments.of(new Obscure<>(null), new Obscure<>("Test"), false),
        Arguments.of(new Obscure<>(123), new Obscure<>(123), true),
        Arguments.of(new Obscure<>(true), new Obscure<>(true), true),
        Arguments.of(new Obscure<>(1.5), new Obscure<>(1.5), true),
        Arguments.of(new Obscure<>("Test"), new Obscure<>("Different"), false),
        Arguments.of(new Obscure<>(1L), new Obscure<>(1L), true));
  }

  @DisplayName("This method tests the equality of two Obscure instances.")
  @ParameterizedTest(
      name =
          "The first Obscure {0} instance to compare. The second Obscure {1} instance to compare. "
              + "The expected {2} boolean result of the equality check.")
  @MethodSource("equalsProviderArguments")
  void testEquals(@NotNull Obscure<String> obscure1, Obscure<String> obscure2, boolean expected) {
    assertEquals(expected, obscure1.equals(obscure2));
  }

  static @NotNull Stream<Arguments> hashCodeProviderArguments() {
    return Stream.of(
        Arguments.of(new Obscure<>("Test"), new Obscure<>("Test"), true),
        Arguments.of(new Obscure<>("Test"), new Obscure<>(null), false),
        Arguments.of(new Obscure<>(null), new Obscure<>(null), true),
        Arguments.of(new Obscure<>(null), new Obscure<>("Test"), false),
        Arguments.of(new Obscure<>(123), new Obscure<>(123), true),
        Arguments.of(new Obscure<>(true), new Obscure<>(true), true),
        Arguments.of(new Obscure<>(1.5), new Obscure<>(1.5), true),
        Arguments.of(new Obscure<>("Test"), new Obscure<>("Different"), false),
        Arguments.of(new Obscure<>(1L), new Obscure<>(1L), true));
  }

  @DisplayName(
      "This method tests the equality of two Obscure instances by comparing their hash codes.")
  @ParameterizedTest(
      name =
          "The first Obscure {0} instance to compare. The second Obscure {1} instance to compare. "
              + "The expected {2} boolean result of the equality check.")
  @MethodSource("hashCodeProviderArguments")
  void testHashCode(
      @NotNull Obscure<String> obscure1, @NotNull Obscure<String> obscure2, boolean expected) {
    assertEquals(expected, obscure1.hashCode() == obscure2.hashCode());
  }

  static @NotNull Stream<Arguments> getObjectProviderArguments() {
    return Stream.of(
        Arguments.of(new Obscure<>("Test"), "Test"),
        Arguments.of(new Obscure<>(null), null),
        Arguments.of(new Obscure<>("Another String"), "Another String"),
        Arguments.of(new Obscure<>(""), ""),
        Arguments.of(new Obscure<>("12345"), "12345"),
        Arguments.of(new Obscure<>("SpecialChars!@#$"), "SpecialChars!@#$"));
  }

  @DisplayName("This method retrieves the object stored in the Obscure instance.")
  @ParameterizedTest(
      name =
          "An instance of Obscure {0} containing the object to be retrieved. The expected {1} object to be retrieved.")
  @MethodSource("getObjectProviderArguments")
  void getObject(@NotNull Obscure<String> actual, String expected) {
    assertEquals(expected, actual.get());
  }

  static @NotNull Stream<Arguments> setObjectProviderArguments() {
    return Stream.of(
        Arguments.of("New Value", "New Value"),
        Arguments.of("Another New Value", "Another New Value"),
        Arguments.of("", ""),
        Arguments.of("12345", "12345"),
        Arguments.of("SpecialChars!@#$", "SpecialChars!@#$"));
  }

  @DisplayName("Sets a new value for the object stored in the Obscure instance.")
  @ParameterizedTest(
      name =
          "The new value {0} to be stored in the Obscure instance. "
              + "The updated Obscure {1} instance with the new value.")
  @MethodSource("setObjectProviderArguments")
  void setObject(String newValue, String expected) {
    Obscure<String> obscure = Obscure.of("!");

    obscure.setObject(newValue);

    assertEquals(expected, obscure.get());
  }
}
