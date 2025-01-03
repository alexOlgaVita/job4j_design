package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    /**
     * Метод должен возвращать массив из двух целых чисел, представляющих наименьший диапазон с k различными
     * элементами в массиве nums. Если такой диапазон найти невозможно, вернуть null.
     *
     * @param nums упорядоченный массив
     * @param k    целочисленное значение
     */
    public static int[] findSmallestRange(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int first = 0;
        int last = 0;
        int[] result = new int[k];
        int[] res = new int[2];
        result[0] = nums[0];
        boolean exit = false;
        while (!exit) {
            i++;
            j++;
            result[j] = nums[i];
            if (nums[i] == nums[i - 1]) {
                first = i;
                j = 0;
                result = Arrays.copyOfRange(result, k - 1, k + k - 1);
            }
            if ((i == nums.length - 1) || (j == k - 1)) {
                last = i;
                exit = true;
            }
        }
        if (j != k - 1) {
            result = null;
            res = null;
        } else {
            res[0] = first;
            res[1] = last;
        }
        System.out.println("Получился массив: " + Arrays.toString(result));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}