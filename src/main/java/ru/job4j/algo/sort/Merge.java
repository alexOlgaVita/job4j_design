package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[0];
        int indexLeft = 0;
        int indexRight = 0;
        int j = -1;
        while ((indexLeft != left.length) && (indexRight != right.length)) {
            j++;
            if (left[indexLeft] <= right[indexRight]) {
                result = Arrays.copyOfRange(result, 0, j + 1);
                result[j] = left[indexLeft];
                indexLeft++;
            } else {
                result = Arrays.copyOfRange(result, 0, j + 1);
                result[j] = right[indexRight];
                indexRight++;
            }
        }
        int[] res = new int[0];
        if (indexLeft != left.length) {
            int[] ost = Arrays.copyOfRange(left, indexLeft, left.length);
            res = Arrays.copyOf(result, j + 1 + ost.length);
            System.arraycopy(ost, 0, res, res.length - ost.length, ost.length);
        } else if (indexRight != right.length) {
            int[] ost = Arrays.copyOfRange(right, indexRight, right.length);
            res = Arrays.copyOf(result, j + 1 + ost.length);
            System.arraycopy(ost, 0, res, res.length - ost.length, ost.length);
        }
        res = j == -1 ? null : res;
        return res;
    }
}
