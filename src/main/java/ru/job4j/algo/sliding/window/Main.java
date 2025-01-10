package ru.job4j.algo.sliding.window;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", start, end);
    }
}

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }
        PriorityQueue<Interval> activeIntervals = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));
        var activeResultIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
        var activeResultIntervalsComm = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));

        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        int maxOverlapCurr = 0;
        int maxStartCurr = -1;
        int maxEndCurr = -1;

        int start = -1;
        int end = -1;

        Interval prevInterval = new Interval(-1, -1);
        activeIntervals.addAll(intervals);
        while (!activeIntervals.isEmpty()) {
            Interval activeInterval = activeIntervals.poll();
            if ((maxStartCurr == -1) && (maxEndCurr == -1)) {
                maxStartCurr = activeInterval.start;
                maxEndCurr = activeInterval.end;
                if (prevInterval.end == -1 || (activeInterval.start < prevInterval.end)) {
                    start = Math.max(start, activeInterval.start);
                    end = activeInterval.end;
                } else {
                    start = activeInterval.start;
                    end = activeInterval.end;
                }
                prevInterval = activeInterval;
            } else {
                /* если есть предыдущий элемент у текущего элемента (у самого первого его не может быть) */
                if (maxOverlapCurr == 0 && activeInterval.start < prevInterval.end) {
                    activeResultIntervals.add(prevInterval);
                    maxOverlapCurr++;
                    start = Math.max(start, activeInterval.start);
                    end = maxEndCurr;
                    activeResultIntervals.add(activeInterval);
                } else if (maxOverlapCurr == 0 && activeInterval.start >= prevInterval.end) {
                    /* если есть предыдущий элемент и текущий элемент с ним не связан, т.е. является первым в новой цепочке */
                    start = Math.max(maxStartCurr, start);
                    end = prevInterval.end;
                } else if (maxOverlapCurr != 0 && activeInterval.start < maxEndCurr) {
                    activeResultIntervals.add(activeInterval);
                    maxOverlapCurr++;
                } else {
                    if (maxOverlapCurr > maxOverlap) {
                        maxOverlap = maxOverlapCurr;
                        maxStart = start;
                        maxEnd = end;
                        activeResultIntervalsComm.clear();
                        activeResultIntervalsComm.addAll(activeResultIntervals);
                        activeResultIntervals.clear();
                    } else {
                        maxOverlapCurr = 0;
                        var tmp = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
                        while (activeResultIntervals.size() < 2) {
                            activeResultIntervals.poll();
                        }
                        while (!activeResultIntervals.isEmpty()) {
                            prevInterval = activeResultIntervals.poll();
                            tmp.add(prevInterval);
                        }
                        activeResultIntervals.addAll(tmp);
                        maxOverlapCurr++;
                    }
                    if (activeInterval.start < prevInterval.end) {
                        activeResultIntervals.add(activeInterval);
                        maxOverlapCurr++;
                        maxOverlapCurr++;
                        start = prevInterval.start;
                        end = prevInterval.end;
                    } else {
                        start = activeInterval.end;
                    }
                    maxStartCurr = -1;
                    maxEndCurr = -1;
                }
            }
            /* обработка результатов последней итерации */
            if (maxOverlapCurr > maxOverlap || intervals.size() == 1) {
                maxOverlap = maxOverlapCurr;
                maxStart = start;
                maxEnd = end;
                activeResultIntervalsComm.clear();
                activeResultIntervalsComm.addAll(activeResultIntervals);
            }
            System.out.println("maxOverlap = " + maxOverlap);
            System.out.println("activeResultIntervalsComm = " + Arrays.toString(activeResultIntervalsComm.toArray()));
        }

        return new int[]{
                maxStart, maxEnd
        };
    }

    public static void main(String[] args) {
        List intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
