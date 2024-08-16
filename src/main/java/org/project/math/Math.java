package org.project.math;

import org.jetbrains.annotations.NotNull;

/**
 * This is an abstract class representing mathematical operations on a collection of numbers. It
 * provides methods for finding the maximum, minimum, average, sorting, and binary searching.
 *
 * @param <T> The type of numbers to be used in the mathematical operations. It must extend Number
 *     and Comparable.
 */
public abstract class Math<T extends Number & Comparable<T>> {
  /** A constant string indicating that the array mustn't be empty or contain only null values. */
  public static final String EMPTY_OR_CONTAIN_ONLY_NULL_VALUES =
      "Array must not be empty or contain only null values.";

  /** The array of numbers on which mathematical operations will be performed. */
  protected T[] numbers;

  /**
   * Constructor for the Math class.
   *
   * @param numbers The array of numbers to be used in the mathematical operations. It mustn't be
   *     empty or contain only null values. If the array is empty or contains only null values, an
   *     IllegalArgumentException will be thrown.
   */
  protected Math(T @NotNull [] numbers) {
    if (numbers.length == 0) {
      throw new IllegalArgumentException(EMPTY_OR_CONTAIN_ONLY_NULL_VALUES);
    }

    this.numbers = numbers;
  }

  /**
   * Abstract method to find the maximum value in the array of numbers.
   *
   * @return The maximum value in the array.
   */
  public abstract T getMaximum();

  /**
   * Abstract method to find the minimum value in the array of numbers.
   *
   * @return The minimum value in the array.
   */
  public abstract T getMinimum();

  /**
   * Abstract method calculates the average number of numbers in an array.
   *
   * @return The average value of the array.
   */
  public abstract double getAverage();

  /** Abstract method to sort the array of numbers in ascending order. */
  public abstract T[] sort();

  /**
   * Abstract method to perform a binary search for a given value in the array of numbers.
   *
   * @param value The value to search for in the array.
   * @return The index of the value in the array if found, or -1 if not found.
   */
  public abstract int binarySearch(T value);
}
