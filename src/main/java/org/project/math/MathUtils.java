package org.project.math;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for performing mathematical operations on arrays of numbers.
 *
 * @param <T> The type of numbers in the array, which must extend Number and be Comparable.
 */
public class MathUtils<T extends Number & Comparable<T>> extends Math<T> {
  private boolean isSorted = false;

  /**
   * Constructs a new instance of MathUtils with the given array of numbers.
   *
   * @param numbers An array of numbers, which must extend Number and be Comparable. The array
   *     mustn't be empty.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  public MathUtils(final T @NotNull [] numbers) {
    super(numbers);
  }

  /**
   * Finds the maximum of three numbers.
   *
   * <p>This method takes three numbers of type T (which must extend Number and be Comparable) and
   * returns the maximum value among them. If any of the numbers is null, an {@link
   * NoSuchElementException} will be thrown.
   *
   * @param a The first number to compare.
   * @param b The second number to compare.
   * @param c The third number to compare.
   * @return The maximum value among a, b, and c.
   * @throws NoSuchElementException If any of the numbers is null.
   */
  public static <T extends Number & Comparable<T>> T maxOfThree(final T a, final T b, final T c) {
    return Stream.of(a, b, c).max(Comparable::compareTo).orElseThrow();
  }

  /**
   * Finds the minimum of five numbers.
   *
   * <p>This method takes five numbers of type T (which must extend Number and be Comparable) and
   * returns the minimum value among them. If any of the numbers is null, an {@link
   * NoSuchElementException} will be thrown.
   *
   * @param a The first number to compare.
   * @param b The second number to compare.
   * @param c The third number to compare.
   * @param d The fourth number to compare.
   * @param e The fifth number to compare.
   * @return The minimum value among a, b, c, d, and e.
   * @throws NoSuchElementException If any of the numbers is null.
   */
  public static <T extends Number & Comparable<T>> T minOfFive(
      final T a, final T b, final T c, final T d, final T e) {
    return Stream.of(a, b, c, d, e).min(Comparable::compareTo).orElseThrow();
  }

  /**
   * Calculates and returns the average of the numbers in the array.
   *
   * <p>This method first validates the array to ensure it's not empty or contains only null values.
   * Then, it uses Java 8's Stream API to convert the array of numbers to a Stream of Doubles,
   * calculates the average of these Doubles, and returns the result. If the array is empty or
   * contains only null values, an {@link IllegalArgumentException} is thrown.
   *
   * @return The average of the numbers in the array.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  @Override
  public double getAverage() {
    return Arrays.stream(numbers)
        .mapToDouble(Number::doubleValue)
        .average()
        .orElseThrow(() -> new IllegalArgumentException(EMPTY_OR_CONTAIN_ONLY_NULL_VALUES));
  }

  /**
   * Returns the maximum value from the array of numbers.
   *
   * <p>This method uses the {@link Comparable#compareTo(Object)} method to compare the numbers in
   * the array. It calls the {@link #findExtreme(Comparator)} method with a comparator that sorts in
   * ascending order. If the array is empty or contains only null values, an {@link
   * IllegalArgumentException} is thrown.
   *
   * @return The maximum value from the array of numbers.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  @Override
  public T getMaximum() {
    return findExtreme(Comparable::compareTo);
  }

  /**
   * Returns the minimum value from the array of numbers.
   *
   * <p>This method uses the {@link Comparator#reverseOrder()} comparator to compare the numbers in
   * the array. It calls the {@link #findExtreme(Comparator)} method with this comparator. If the
   * array is empty or contains only null values, an {@link IllegalArgumentException} is thrown.
   *
   * @return The minimum value from the array of numbers.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  @Override
  public T getMinimum() {
    return findExtreme(Comparator.reverseOrder());
  }

  /**
   * Sorts the array of numbers in ascending order.
   *
   * <p>This method checks if the array is already sorted. If not, it uses an iterative version of
   * the quicksort algorithm to sort the array. The sorted array is marked as sorted by setting the
   * {@link #isSorted} flag to true.
   *
   * <p>The time complexity of this method is O(n log n) in the average and best cases, and O(n^2)
   * in the worst case. The space complexity is O(log n) due to the use of a stack for the iterative
   * quicksort algorithm.
   *
   * <p>This method doesn't return any value, but it modifies the original array.
   */
  public T[] sort() {
    if (!isSorted) {
      T[] sortedArray = Arrays.copyOf(numbers, numbers.length);

      quickSortIterative(sortedArray, sortedArray.length - 1);
      isSorted = true;

      return sortedArray;
    } else {
      return numbers;
    }
  }

  /**
   * Performs a binary search on the sorted array of numbers.
   *
   * <p>This method first ensures the array is sorted by calling the {@link #sort()} method. Then,
   * it performs a binary search on the sorted array to find the index of the specified value. If
   * the value is found, the method returns its index. If the value isn't found, the method returns
   * -1.
   *
   * @param value The value to search for in the array.
   * @return The index is the first one to occur, or -1 if the value isn't found.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  @Override
  public int binarySearch(final T value) {
    sort();

    int low = 0;
    int high = numbers.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      T midVal = numbers[mid];
      int cmp = midVal.compareTo(value);

      if (cmp == 0) {
        return mid;
      } else if (cmp > 0) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }

  /**
   * Finds the extreme value (maximum or minimum) from the array of numbers based on the provided
   * comparator.
   *
   * <p>This method first validates the array to ensure it's not empty or contains only null values.
   * Then, it uses Java 8's Stream API to convert the array of numbers to a Stream, applies the
   * provided comparator to find the extreme value, and returns the result. If the array is empty or
   * contains only null values, an {@link IllegalArgumentException} is thrown.
   *
   * @param comparator The comparator to determine the extreme value. It should be either {@link
   *     Comparable#compareTo(Object)} for finding the maximum or {@link Comparator#reverseOrder()}
   *     for finding the minimum.
   * @return The extreme value from the array of numbers based on the provided comparator.
   * @throws IllegalArgumentException If the array is empty or contains only null values.
   */
  private T findExtreme(final Comparator<T> comparator) {
    return Arrays.stream(numbers)
        .max(comparator)
        .orElseThrow(() -> new IllegalArgumentException(EMPTY_OR_CONTAIN_ONLY_NULL_VALUES));
  }

  /**
   * Performs an iterative version of the quicksort algorithm on the given array.
   *
   * <p>This method uses a stack to perform the quicksort algorithm iteratively. It starts by
   * pushing the initial range [0, high] onto the stack. Then, it repeatedly pops a range from the
   * stack, partitions the array around a pivot element, and pushes the two resulting ranges onto
   * the stack. The process continues until the stack is empty.
   *
   * @param array The array to be sorted.
   * @param high The ending index of the range to be sorted.
   */
  private void quickSortIterative(final T[] array, int high) {
    Deque<int[]> stack = new ArrayDeque<>();

    stack.push(new int[] {0, high});

    while (!stack.isEmpty()) {
      int[] range = stack.pop();
      int low = range[0];
      high = range[1];

      if (low < high) {
        int pi = partition(array, low, high);

        stack.push(new int[] {low, pi - 1});
        stack.push(new int[] {pi + 1, high});
      }
    }
  }

  /**
   * Partitions the given array around a pivot element using the Lomuto partition scheme.
   *
   * <p>This method selects the last element as the pivot and rearranges the array such that all
   * elements with values less than or equal to the pivot are placed before it, and all elements
   * with values greater than the pivot are placed after it. The method returns the index of the
   * pivot element after partitioning.
   *
   * @param array The array to be partitioned.
   * @param low The starting index of the range to be partitioned.
   * @param high The ending index of the range to be partitioned.
   * @return The index of the pivot element after partitioning.
   */
  private int partition(final T @NotNull [] array, final int low, final int high) {
    T pivot = array[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (array[j].compareTo(pivot) <= 0) {
        i++;

        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }

    T temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;

    return i + 1;
  }
}
