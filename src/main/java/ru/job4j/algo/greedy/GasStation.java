package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /* для хранения общего количества бензина. */
        int totalGas = 0;
        /* для хранения общей стоимости перемещения. */
        int totalCost = 0;
        /* для хранения текущего запаса бензина в баке. */
        int tank = 0;
        /* для хранения индекса стартовой станции. */
        int start = 0;
        if (cost.length != gas.length) {
            throw new IllegalArgumentException("Некорректные входные данные");
        }
        boolean found = false;
        int i = 0;
        int j = 0;
        while ((!found) && (i < gas.length) && (start < gas.length)) {
            totalGas += gas[j];
            totalCost += cost[j];
            tank += gas[j] - cost[j];
            if ((i == gas.length - 1) && (totalGas >= totalCost)) {
                found = true;
            } else if ((tank < 0)) {
                tank = 0;
                start++;
                j = start;
                i = 0;
                totalGas = 0;
                totalCost = 0;
            } else {
                i++;
                if (j == gas.length - 1) {
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return found ? start : -1;
    }
}
