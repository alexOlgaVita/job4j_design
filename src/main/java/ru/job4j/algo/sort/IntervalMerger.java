package ru.job4j.algo.sort;

import java.util.Arrays;

public class IntervalMerger {

    /**
     * Метод выполняет объединение перекрывающиеся интервалов заданного массива из интервалов.
     *
     * @param intervals массив интервалов
     * @return массив, в котором перекрывающиеся интервалы объединены
     */
    public int[][] merge(int[][] intervals) {
        java.util.Arrays.sort(intervals, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        int[][] result = new int[1][1];
        int j = -1;
        int[] n = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if ((intervals[i][0] <= n[1]) && (intervals[i][1] >= n[1])) {
                n[1] = intervals[i][1];
            } else {
                j++;
                result = Arrays.copyOfRange(result, 0, j + 1);
                result[j] = n;
                n = intervals[i];
            }
        }
        j++;
        result = Arrays.copyOfRange(result, 0, j + 1);
        result[j] = n;
        return result;
    }
}
