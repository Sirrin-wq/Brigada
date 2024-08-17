package org.project.math;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * This is an abstract class representing mathematical operations on a collection of numbers. It
 * provides methods for finding the maximum, minimum, average, sorting, and binary searching.
 *
 * @param <T> The type of numbers to be used in the mathematical operations. It must extend Number
 *     and Comparable.
 */
@Data
public abstract class Math<T extends Number & Comparable<T>> {
  /** A constant string indicating that the array mustn't be empty or contain only null values. */
  public static final String EMPTY_OR_CONTAIN_ONLY_NULL_VALUES =
      "Array must not be empty or contain only null values.";

  /** The array of numbers on which mathematical operations will be performed. */
  protected T[] numbers;

  /**
   * Constructor for the Math class. Initializes the Math object with an array of numbers for
   * performing mathematical operations.
   *
   * @param numbers The array of numbers to be used in the mathematical operations. It mustn't be
   *     empty or contain only null values. If the array is empty or contains only null values, an
   *     IllegalArgumentException will be thrown.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
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
   * <p>This method iterates through the array of numbers and compares each number with the current
   * maximum value. It updates the maximum value whenever a larger number is found.
   *
   * @return The maximum value in the array. If the array is empty or contains only null values, an
   *     IllegalArgumentException will be thrown.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  public abstract T getMaximum();

  /**
   * Abstract method to find the minimum value in the array of numbers.
   *
   * <p>This method iterates through the array of numbers and compares each number with the current
   * minimum value. It updates the minimum value whenever a smaller number is found.
   *
   * @return The minimum value in the array. If the array is empty or contains only null values, an
   *     IllegalArgumentException will be thrown.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  public abstract T getMinimum();

  /**
   * Abstract method to calculate the average value of numbers in the array.
   *
   * <p>This method iterates through the array of numbers, sums up all the numbers, and divides the
   * sum by the total count of numbers to calculate the average.
   *
   * @return The average value of the array. If the array is empty or contains only null values, an
   *     IllegalArgumentException will be thrown.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  public abstract double getAverage();

  /**
   * Abstract method to sort the array of numbers in ascending order.
   *
   * <p>This method should implement a sorting algorithm to arrange the numbers in the array in
   * ascending order. The sorted array should be returned by the method.
   *
   * @return The sorted array of numbers. The original array shouldn't be modified.
   * @throws UnsupportedOperationException If the sorting algorithm isn't implemented in the
   *     subclass.
   */
  public abstract T[] sort();

  /**
   * Abstract method to perform a binary search for a given value in the array of numbers.
   *
   * @param value The value to search for in the array.
   * @return The index of the value in the array if found, or -1 if not found.
   */
  public abstract int binarySearch(T value);
}
