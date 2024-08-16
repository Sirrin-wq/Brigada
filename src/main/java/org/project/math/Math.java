package org.project.math;

public abstract class Math<T extends Number> {
    protected T[] numbers;

    public Math(T[] numbers) {
        this.numbers = numbers;
    }

    // 1
    public static <T extends Number & Comparable<T>> T max(T num1, T num2, T num3) {
        T max = num1;
        if (max.compareTo(num2) < 0) max = num2;
        if (max.compareTo(num3) < 0) max = num3;
        return max;
    }

    // 2
    public static <T extends Number & Comparable<T>> T min(T num1, T num2, T num3, T num4, T num5) {
        T min = num1;
        if (min.compareTo(num2) > 0) min = num2;
        if (min.compareTo(num3) > 0) min = num3;
        if (min.compareTo(num4) > 0) min = num4;
        if (min.compareTo(num5) > 0) min = num5;
        return min;
    }

    // 3
    public static <T extends Number> double average(T[] arr) {
        double sum = 0;
        for (T element : arr) {
            sum += element.doubleValue();
        }
        return sum / arr.length;
    }

    //4
    public static <T extends Number & Comparable<T>> T maxElem(T[] arr) {
        T max = arr[0];
        for (T elem : arr) {
            if (max.compareTo(elem) < 0) {
                max = elem;
            }
        }
        return max;
    }

    //5
    public static <T extends Number & Comparable<T>> T minElem(T[] arr) {
        T min = arr[0];
        for (T elem : arr) {
            if (min.compareTo(elem) > 0) {
                min = elem;
            }
        }
        return min;
    }

    //6.1
    public static <T extends Number & Comparable<T>> void selectionSort(T[] arr) {
        int smallestIndex = 0;
        T temp;

        for (int i = 0; i < arr.length; i++) {
            smallestIndex = i;
            // finding index of the smallest element
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[smallestIndex].compareTo(arr[j]) > 0) {
                    smallestIndex = j;
                }
            }
            // swapping the smallest element(if it's not already in the right place)
            // with the ith element so all the smallest elements go in ascending order
            if (smallestIndex != i) {
                temp = arr[i];
                arr[i] = arr[smallestIndex];
                arr[smallestIndex] = temp;
            }
        }
    }

    //6.2
    public static <T extends Number & Comparable<T>> void bubbleSort(T[] arr) {
        T temp;
        boolean isSorted = false;

        for (int i = arr.length - 1; i > 0; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
    }

    //7
    public static <T extends Number & Comparable<T>> int binarySearch(T[] arr, T target) {
        int left = 0;
        int right = arr.length-1;
        int middle;

        while (left <= right) {
            middle = (left + right) / 2;

            if (target.compareTo(arr[middle]) > 0) {
                left = middle + 1;
            }else if (target.compareTo(arr[middle]) < 0) {
                right = middle - 1;
            } else {
                return middle; //returns index of the target element
            }
        }
        return -1;
    }
}
