package org.project.math;

import static java.util.Arrays.copyOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.project.math.MathUtils.maxOfThree;
import static org.project.math.MathUtils.minOfFive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MathUtilsTest {
  private static final String UNSUPPORTED_NUMBER_TYPE = "Unsupported number type ";
  private static final String INTEGER = "Integer";
  private static final String DOUBLE = "Double";
  private static final String FLOAT = "Float";
  private static final String LONG = "Long";
  private static final String SHORT = "Short";
  private static final String BYTE = "Byte";
  public static final String BIG_INTEGER = "BigInteger";
  public static final String BIG_DECIMAL = "BigDecimal";

  private static @NotNull MathUtils<? extends Number> getMathUtils(
      Number @NotNull [] numbers, @NotNull Class<? extends Number> type) {
    return switch (type.getSimpleName()) {
      case INTEGER -> new MathUtils<>(copyOf(numbers, numbers.length, Integer[].class));
      case DOUBLE -> new MathUtils<>(copyOf(numbers, numbers.length, Double[].class));
      case FLOAT -> new MathUtils<>(copyOf(numbers, numbers.length, Float[].class));
      case LONG -> new MathUtils<>(copyOf(numbers, numbers.length, Long[].class));
      case SHORT -> new MathUtils<>(copyOf(numbers, numbers.length, Short[].class));
      case BYTE -> new MathUtils<>(copyOf(numbers, numbers.length, Byte[].class));
      default -> throw new IllegalArgumentException(UNSUPPORTED_NUMBER_TYPE + type.getSimpleName());
    };
  }

  private static @NotNull Stream<Arguments> maxOfThreeArgumentsProvider() {
    return Stream.of(
        Arguments.of(1, 2, 3, 3),
        Arguments.of(5, 5, 5, 5),
        Arguments.of(5, 3, 5, 5),
        Arguments.of(10, 10, 10, 10),
        Arguments.of(-1, -2, -3, -1),
        Arguments.of(0, 0, 0, 0),
        Arguments.of(1.5, 2.5, 1.5, 2.5),
        Arguments.of(-1.0, -1.0, -2.0, -1.0),
        Arguments.of(1L, 2L, 1L, 2L),
        Arguments.of(1.5f, 2.5f, 1.5f, 2.5f),
        Arguments.of((short) 1, (short) 2, (short) 1, (short) 2),
        Arguments.of(
            new BigInteger("123456789"),
            new BigInteger("987654321"),
            new BigInteger("123456789"),
            new BigInteger("987654321")),
        Arguments.of(
            new BigDecimal("1.2345"),
            new BigDecimal("2.3456"),
            new BigDecimal("1.2345"),
            new BigDecimal("2.3456")),
        Arguments.of((byte) 1, (byte) 2, (byte) 1, (byte) 2));
  }

  @DisplayName("This method tests the maxOfThree method with different types of numbers.")
  @ParameterizedTest(
      name =
          "The first number {0} to compare. The second number {1} to compare. "
              + "The third number {2} to compare. The expected {3} maximum number.")
  @MethodSource("maxOfThreeArgumentsProvider")
  void testMaxOfThree(
      @NotNull Number firstNumber, Number secondNumber, Number thirdNumber, Number expected) {
    Class<? extends Number> type = firstNumber.getClass();

    Number actual =
        switch (type.getSimpleName()) {
          case INTEGER ->
              maxOfThree((Integer) firstNumber, (Integer) secondNumber, (Integer) thirdNumber);
          case DOUBLE ->
              maxOfThree((Double) firstNumber, (Double) secondNumber, (Double) thirdNumber);
          case FLOAT -> maxOfThree((Float) firstNumber, (Float) secondNumber, (Float) thirdNumber);
          case LONG -> maxOfThree((Long) firstNumber, (Long) secondNumber, (Long) thirdNumber);
          case SHORT -> maxOfThree((Short) firstNumber, (Short) secondNumber, (Short) thirdNumber);
          case BYTE -> maxOfThree((Byte) firstNumber, (Byte) secondNumber, (Byte) thirdNumber);
          case BIG_INTEGER ->
              maxOfThree(
                  (BigInteger) firstNumber, (BigInteger) secondNumber, (BigInteger) thirdNumber);
          case BIG_DECIMAL ->
              maxOfThree(
                  (BigDecimal) firstNumber, (BigDecimal) secondNumber, (BigDecimal) thirdNumber);
          default ->
              throw new IllegalArgumentException(UNSUPPORTED_NUMBER_TYPE + type.getSimpleName());
        };

    assertEquals(expected, actual);
  }

  private static @NotNull Stream<Arguments> minOfFiveArgumentsProvider() {
    return Stream.of(
        Arguments.of(5, 4, 3, 2, 1, 1),
        Arguments.of(1, 2, 3, 4, 5, 1),
        Arguments.of(3, 3, 3, 3, 3, 3),
        Arguments.of(10, 9, 8, 7, 6, 6),
        Arguments.of(-1, -2, -3, -4, -5, -5),
        Arguments.of(0, 0, 0, 0, 0, 0),
        Arguments.of(1.5, 2.5, 3.5, 4.5, 0.5, 0.5),
        Arguments.of(-1.0, -2.0, -3.0, -4.0, -5.0, -5.0),
        Arguments.of(5L, 4L, 3L, 2L, 1L, 1L),
        Arguments.of(1.5f, 2.5f, 3.5f, 4.5f, 0.5f, 0.5f),
        Arguments.of((short) 5, (short) 4, (short) 3, (short) 2, (short) 1, (short) 1),
        Arguments.of((byte) 5, (byte) 4, (byte) 3, (byte) 2, (byte) 1, (byte) 1));
  }

  @DisplayName("This method tests the minOfFive method with different types of numbers.")
  @ParameterizedTest(
      name =
          "The first number {0} to compare. The second number {1} to compare. "
              + "The third number {2} to compare. The fourth number {3} to compare. The fifth number {4} to compare. "
              + "The expected {5} minimum number.")
  @MethodSource("minOfFiveArgumentsProvider")
  void testMinOfFive(@NotNull Number a, Number b, Number c, Number d, Number e, Number expected) {
    Class<? extends Number> type = a.getClass();

    Number actual =
        switch (type.getSimpleName()) {
          case INTEGER ->
              minOfFive((Integer) a, (Integer) b, (Integer) c, (Integer) d, (Integer) e);
          case DOUBLE -> minOfFive((Double) a, (Double) b, (Double) c, (Double) d, (Double) e);
          case FLOAT -> minOfFive((Float) a, (Float) b, (Float) c, (Float) d, (Float) e);
          case LONG -> minOfFive((Long) a, (Long) b, (Long) c, (Long) d, (Long) e);
          case SHORT -> minOfFive((Short) a, (Short) b, (Short) c, (Short) d, (Short) e);
          case BYTE -> minOfFive((Byte) a, (Byte) b, (Byte) c, (Byte) d, (Byte) e);
          default ->
              throw new IllegalArgumentException(UNSUPPORTED_NUMBER_TYPE + type.getSimpleName());
        };

    assertEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> averageProviderArguments() {
    return Stream.of(
        Arguments.of(new Integer[] {1, 2, 3}, 2.0),
        Arguments.of(new Integer[] {-5, 0, 5}, 0.0),
        Arguments.of(new Integer[] {-10, -20, -30}, -20.0),
        Arguments.of(new Integer[] {2, 2, 2, 2, 2}, 2.0),
        Arguments.of(new Integer[] {7, 7, 7, 7}, 7.0),
        Arguments.of(new Double[] {1.1, 2.2, 3.3}, 2.2),
        Arguments.of(new Float[] {1.1f, 2.2f, 3.3f}, 2.2),
        Arguments.of(new Long[] {1L, 2L, 3L}, 2.0),
        Arguments.of(new Short[] {1, 2, 3}, 2.0),
        Arguments.of(new Byte[] {1, 2, 3}, 2.0),
        Arguments.of(new Integer[] {5, 5, 5}, 5.0),
        Arguments.of(new Double[] {1.0, 2.0, 3.0}, 2.0),
        Arguments.of(new Float[] {1.0f, 2.0f, 3.0f}, 2.0),
        Arguments.of(new Integer[] {-1, 0, 1}, 0.0),
        Arguments.of(new Double[] {-1.1, 0.0, 1.1}, 0.0));
  }

  @DisplayName("This method calculates the average of an array of numbers.")
  @ParameterizedTest(
      name =
          "An array of numbers {0} of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} average value.")
  @MethodSource("averageProviderArguments")
  void testGetAverage(Number @NotNull [] numbers, double expected) {
    Class<? extends Number> type = numbers[0].getClass();
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    double actual = mathUtils.getAverage();

    assertEquals(expected, actual, 0.0001);
  }

  private static @NotNull Stream<Arguments> findMaxExtremeArgumentsProvider() {
    return Stream.of(
        Arguments.of(new Integer[] {1, 2, 3}, 3),
        Arguments.of(new Integer[] {5, 3, 5}, 5),
        Arguments.of(new Integer[] {10, 10, 10}, 10),
        Arguments.of(new Integer[] {-1, -2, -3}, -1),
        Arguments.of(new Double[] {1.1, 2.2, 3.3}, 3.3),
        Arguments.of(new Double[] {5.5, 3.3, 5.5}, 5.5),
        Arguments.of(new Double[] {10.0, 10.0, 10.0}, 10.0),
        Arguments.of(new Double[] {-1.1, -2.2, -3.3}, -1.1),
        Arguments.of(new Float[] {1.1f, 2.2f, 3.3f}, 3.3f),
        Arguments.of(new Float[] {5.5f, 3.3f, 5.5f}, 5.5f),
        Arguments.of(new Float[] {10.0f, 10.0f, 10.0f}, 10.0f),
        Arguments.of(new Float[] {-1.1f, -2.2f, -3.3f}, -1.1f),
        Arguments.of(new Long[] {1L, 2L, 3L}, 3L),
        Arguments.of(new Long[] {5L, 3L, 5L}, 5L),
        Arguments.of(new Long[] {10L, 10L, 10L}, 10L),
        Arguments.of(new Long[] {-1L, -2L, -3L}, -1L),
        Arguments.of(new Short[] {1, 2, 3}, (short) 3),
        Arguments.of(new Short[] {5, 3, 5}, (short) 5),
        Arguments.of(new Short[] {10, 10, 10}, (short) 10),
        Arguments.of(new Short[] {-1, -2, -3}, (short) -1),
        Arguments.of(new Byte[] {1, 2, 3}, (byte) 3),
        Arguments.of(new Byte[] {5, 3, 5}, (byte) 5),
        Arguments.of(new Byte[] {10, 10, 10}, (byte) 10),
        Arguments.of(new Byte[] {-1, -2, -3}, (byte) -1));
  }

  @DisplayName(
      "This method tests the findExtreme method of the MathUtils class with different types of numbers.")
  @ParameterizedTest(
      name =
          "An array {0} of numbers of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} maximum number from the array.")
  @MethodSource("findMaxExtremeArgumentsProvider")
  void testFindExtreme(Number @NotNull [] numbers, Number expected)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class<? extends Number> type = numbers[0].getClass();
    Method findExtremeMethod = MathUtils.class.getDeclaredMethod("findExtreme", Comparator.class);
    findExtremeMethod.setAccessible(true);
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    Number actual = (Number) findExtremeMethod.invoke(mathUtils, Comparator.naturalOrder());

    assertEquals(expected, actual);
  }

  @DisplayName("This method retrieves the maximum number from an array of numbers.")
  @ParameterizedTest(
      name =
          "An array {0} of numbers of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} maximum number from the array.")
  @MethodSource("findMaxExtremeArgumentsProvider")
  void testGetMaximum(Number @NotNull [] numbers, Number expected) {
    Class<? extends Number> type = numbers[0].getClass();
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    Number actual = mathUtils.getMaximum();

    assertEquals(expected, actual);
  }

  private static @NotNull Stream<Arguments> findMinExtremeArgumentsProvider() {
    return Stream.of(
        Arguments.of(new Integer[] {1, 2, 3}, 1),
        Arguments.of(new Integer[] {5, 3, 5}, 3),
        Arguments.of(new Integer[] {10, 10, 10}, 10),
        Arguments.of(new Integer[] {-1, -2, -3}, -3),
        Arguments.of(new Double[] {1.1, 2.2, 3.3}, 1.1),
        Arguments.of(new Double[] {5.5, 3.3, 5.5}, 3.3),
        Arguments.of(new Double[] {10.0, 10.0, 10.0}, 10.0),
        Arguments.of(new Double[] {-1.1, -2.2, -3.3}, -3.3),
        Arguments.of(new Float[] {1.1f, 2.2f, 3.3f}, 1.1f),
        Arguments.of(new Float[] {5.5f, 3.3f, 5.5f}, 3.3f),
        Arguments.of(new Float[] {10.0f, 10.0f, 10.0f}, 10.0f),
        Arguments.of(new Float[] {-1.1f, -2.2f, -3.3f}, -3.3f),
        Arguments.of(new Long[] {1L, 2L, 3L}, 1L),
        Arguments.of(new Long[] {5L, 3L, 5L}, 3L),
        Arguments.of(new Long[] {10L, 10L, 10L}, 10L),
        Arguments.of(new Long[] {-1L, -2L, -3L}, -3L),
        Arguments.of(new Short[] {1, 2, 3}, (short) 1),
        Arguments.of(new Short[] {5, 3, 5}, (short) 3),
        Arguments.of(new Short[] {10, 10, 10}, (short) 10),
        Arguments.of(new Short[] {-1, -2, -3}, (short) -3),
        Arguments.of(new Byte[] {1, 2, 3}, (byte) 1),
        Arguments.of(new Byte[] {5, 3, 5}, (byte) 3),
        Arguments.of(new Byte[] {10, 10, 10}, (byte) 10),
        Arguments.of(new Byte[] {-1, -2, -3}, (byte) -3));
  }

  @DisplayName("This method retrieves the minimum number from an array of numbers.")
  @ParameterizedTest(
      name =
          "An array {0} of numbers of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} minimum number from the array.")
  @MethodSource("findMinExtremeArgumentsProvider")
  void testGetMinimum(Number @NotNull [] numbers, Number expected) {
    Class<? extends Number> type = numbers[0].getClass();
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    Number actual = mathUtils.getMinimum();

    assertEquals(expected, actual);
  }

  @DisplayName(
      "This method retrieves the minimum number from an array of numbers using reflection.")
  @ParameterizedTest(
      name =
          "An array {0} of numbers of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} minimum number from the array.")
  @MethodSource("findMinExtremeArgumentsProvider")
  void testGetMinimumWithReflection(Number @NotNull [] numbers, Number expected)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class<? extends Number> type = numbers[0].getClass();
    Method findExtremeMethod = MathUtils.class.getDeclaredMethod("findExtreme", Comparator.class);
    findExtremeMethod.setAccessible(true);
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    Number actual = (Number) findExtremeMethod.invoke(mathUtils, Comparator.reverseOrder());

    assertEquals(expected, actual);
  }

  private static @NotNull Stream<Arguments> sortArgumentsProvider() {
    return Stream.of(
        Arguments.of(new Integer[] {3, 1, 2}, new Integer[] {1, 2, 3}),
        Arguments.of(new Integer[] {5, 3, 5, 1, 2}, new Integer[] {1, 2, 3, 5, 5}),
        Arguments.of(new Integer[] {10, 9, 8, 7, 6}, new Integer[] {6, 7, 8, 9, 10}),
        Arguments.of(new Double[] {3.3, 1.1, 2.2}, new Double[] {1.1, 2.2, 3.3}),
        Arguments.of(
            new Double[] {5.5, 3.3, 5.5, 1.1, 2.2}, new Double[] {1.1, 2.2, 3.3, 5.5, 5.5}),
        Arguments.of(new Float[] {3.3f, 1.1f, 2.2f}, new Float[] {1.1f, 2.2f, 3.3f}),
        Arguments.of(new Long[] {5L, 3L, 5L, 1L, 2L}, new Long[] {1L, 2L, 3L, 5L, 5L}),
        Arguments.of(new Short[] {3, 1, 2}, new Short[] {1, 2, 3}),
        Arguments.of(new Byte[] {3, 1, 2}, new Byte[] {1, 2, 3}));
  }

  @DisplayName(
      "This method tests the sort method of the MathUtils class with different types of numbers.")
  @ParameterizedTest(
      name =
          "An array {0} of numbers of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected sorted array {1}.")
  @MethodSource("sortArgumentsProvider")
  void testSort(Number @NotNull [] inputArray, Number @NotNull [] expectedArray) {
    Class<? extends Number> type = inputArray[0].getClass();
    MathUtils<? extends Number> mathUtils = getMathUtils(inputArray, type);

    Number[] actual = mathUtils.sort();

    assertArrayEquals(expectedArray, actual);
  }

  @Test
  @DisplayName("Tests the binary search method of the MathUtils class with additional cases.")
  void testBinarySearch() {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    MathUtils<Integer> mathUtils = new MathUtils<>(numbers);

    int index = mathUtils.binarySearch(4);
    assertEquals(3, index);

    index = mathUtils.binarySearch(10);
    assertEquals(-1, index);

    index = mathUtils.binarySearch(1);
    assertEquals(0, index);

    index = mathUtils.binarySearch(9);
    assertEquals(8, index);

    index = mathUtils.binarySearch(5);
    assertEquals(4, index);
  }

  private static @NotNull Stream<Arguments> singleElementArraysProvider() {
    return Stream.of(
        Arguments.of(new Integer[] {1}, 1),
        Arguments.of(new Double[] {1.0}, 1.0),
        Arguments.of(new Float[] {1.0f}, 1.0f),
        Arguments.of(new Long[] {1L}, 1L),
        Arguments.of(new Short[] {1}, (short) 1),
        Arguments.of(new Byte[] {1}, (byte) 1));
  }

  @DisplayName("Tests the functionality of the MathUtils class with single-element arrays.")
  @ParameterizedTest(
      name =
          "An array of numbers {0} of any type (Integer, Double, Float, Long, Short, Byte). "
              + "The expected {1} maximum, minimum, and average values from the array.")
  @MethodSource("singleElementArraysProvider")
  void testWithSingleElement(Number @NotNull [] numbers, Number expected) {
    Class<? extends Number> type = numbers[0].getClass();
    MathUtils<? extends Number> mathUtils = getMathUtils(numbers, type);

    Object actualMaximum = mathUtils.getMaximum();
    Object actualMinimum = mathUtils.getMinimum();
    double actualAverage = mathUtils.getAverage();

    assertAll(
        () -> assertEquals(expected, actualMaximum),
        () -> assertEquals(expected, actualMinimum),
        () -> assertEquals(expected.doubleValue(), actualAverage, 0.0001));
  }

  @Test
  @DisplayName("Tests the sort method of the MathUtils class with a large array of numbers.")
  void testSortWithLargeArray() {
    Integer[] largeArray =
        IntStream.range(0, 1000)
            .mapToObj(i -> (int) (java.lang.Math.random() * 1000))
            .toArray(Integer[]::new);
    MathUtils<Integer> mathUtils = new MathUtils<>(largeArray);
    Integer[] expected = largeArray.clone();
    Arrays.sort(expected);

    Integer[] actual = mathUtils.sort();

    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("Constructs a new instance of MathUtils with a single-element array.")
  void testConstructorWithSingleElement() {
    MathUtils<Integer> utils = new MathUtils<>(new Integer[] {1});

    assertAll(
        () -> assertEquals(1, utils.getAverage(), 0.0001),
        () -> assertEquals(1, utils.getMaximum()),
        () -> assertEquals(1, utils.getMinimum()));
  }

  static @NotNull Stream<Arguments> provideExtremeValues() {
    return Stream.of(
        Arguments.of(
            Integer.class,
            new Integer[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0},
            Integer.MAX_VALUE,
            Integer.MIN_VALUE,
            0.0001),
        Arguments.of(
            Double.class,
            new Double[] {Double.MAX_VALUE, Double.MIN_VALUE, 0.0},
            Double.MAX_VALUE,
            Double.MIN_VALUE,
            0.0001),
        Arguments.of(
            Float.class,
            new Float[] {Float.MAX_VALUE, Float.MIN_VALUE, 0.0f},
            Float.MAX_VALUE,
            Float.MIN_VALUE,
            0.0001f),
        Arguments.of(
            Long.class,
            new Long[] {Long.MAX_VALUE, Long.MIN_VALUE, 0L},
            Long.MAX_VALUE,
            Long.MIN_VALUE,
            0.0001),
        Arguments.of(
            Short.class,
            new Short[] {Short.MAX_VALUE, Short.MIN_VALUE, (short) 0},
            Short.MAX_VALUE,
            Short.MIN_VALUE,
            0.0001),
        Arguments.of(
            Byte.class,
            new Byte[] {Byte.MAX_VALUE, Byte.MIN_VALUE, (byte) 0},
            Byte.MAX_VALUE,
            Byte.MIN_VALUE,
            0.0001));
  }

  @ParameterizedTest(
      name =
          "The class type of the numbers {0} in the array. The array of numbers {1}. "
              + "The expected {2} maximum value. The expected {3} minimum value. "
              + "The tolerance {4} for comparing the actual and expected values.")
  @MethodSource("provideExtremeValues")
  @DisplayName("This method tests the extreme values (maximum and minimum) of an array of numbers.")
  void testExtremeValues(
      Class<? extends Number> type,
      Number[] values,
      double expectedMax,
      double expectedMin,
      double tolerance) {
    MathUtils<? extends Number> mathUtils = getMathUtils(values, type);

    assertAll(
        () -> assertEquals(expectedMax, mathUtils.getMaximum().doubleValue(), tolerance),
        () -> assertEquals(expectedMin, mathUtils.getMinimum().doubleValue(), tolerance));
  }

  @Test
  @DisplayName(
      "Tests the sort method of the MathUtils class with an already sorted array of integers.")
  void testSortAlreadySortedArray() {
    Integer[] sortedArray = new Integer[] {1, 2, 3, 4, 5};
    MathUtils<Integer> mathUtils = new MathUtils<>(sortedArray);

    Integer[] actual = mathUtils.sort();

    assertArrayEquals(sortedArray, actual);
  }

  @Test
  @DisplayName(
      "Tests the sort method of the MathUtils class with arrays containing duplicate elements.")
  void testSortWithDuplicates() {
    Integer[] arrayWithDuplicates = new Integer[] {3, 1, 2, 3, 2, 1};
    Integer[] expected = new Integer[] {1, 1, 2, 2, 3, 3};
    MathUtils<Integer> mathUtils = new MathUtils<>(arrayWithDuplicates);

    Integer[] actual = mathUtils.sort();

    assertArrayEquals(expected, actual);
  }

  static @NotNull Stream<Arguments> provideBigIntegerAndBigDecimal() {
    return Stream.of(
        Arguments.of(
            new BigInteger[] {new BigInteger("123456789"), new BigInteger("987654321")},
            new BigDecimal[] {new BigDecimal("1.2345"), new BigDecimal("2.3456")},
            new BigInteger("987654321"),
            new BigInteger("123456789"),
            new BigDecimal("2.3456"),
            new BigDecimal("1.2345")));
  }

  @ParameterizedTest(
      name =
          "An array {0} of BigInteger numbers. An array {1} of BigDecimal numbers. "
              + "The expected maximum value {2} from the bigIntegers array. "
              + "The expected minimum value {3} from the bigIntegers array."
              + "The expected maximum value {4} from the bigDecimals array. "
              + "The expected minimum value {5} from the bigDecimals array.")
  @MethodSource("provideBigIntegerAndBigDecimal")
  @DisplayName(
      "This method tests the functionality of the MathUtils class with arrays of BigInteger and BigDecimal.")
  void testBigIntegerAndBigDecimal(
      BigInteger[] bigIntegers,
      BigDecimal[] bigDecimals,
      BigInteger expectedBigIntegerMax,
      BigInteger expectedBigIntegerMin,
      BigDecimal expectedBigDecimalMax,
      BigDecimal expectedBigDecimalMin) {

    MathUtils<BigInteger> bigIntegerMathUtils = new MathUtils<>(bigIntegers);
    MathUtils<BigDecimal> bigDecimalMathUtils = new MathUtils<>(bigDecimals);

    assertAll(
        () -> assertEquals(expectedBigIntegerMax, bigIntegerMathUtils.getMaximum()),
        () -> assertEquals(expectedBigIntegerMin, bigIntegerMathUtils.getMinimum()),
        () -> assertEquals(expectedBigDecimalMax, bigDecimalMathUtils.getMaximum()),
        () -> assertEquals(expectedBigDecimalMin, bigDecimalMathUtils.getMinimum()));
  }
}
