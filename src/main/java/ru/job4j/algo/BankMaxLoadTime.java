package ru.job4j.algo;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.algo.BankMaxLoadTime.EventType.ARRIVAL;
import static ru.job4j.algo.BankMaxLoadTime.EventType.DEPARTURE;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> intervals) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        List<Event> eventIntervals = new ArrayList<>();
        for (int[] interval : intervals) {
            eventIntervals.add(new Event(interval[0], ARRIVAL));
            eventIntervals.add(new Event(interval[1], DEPARTURE));
        }
        eventIntervals = eventIntervals.stream().sorted().toList();
        boolean found = false;
        boolean startFound = false;
        int i = -1;
        while (!found) {
            i++;
            if (EventType.DEPARTURE.equals(eventIntervals.get(i).type)) {
                if (maxLoadStartTime == eventIntervals.get(i).time) {
                    startFound = true;
                } else {
                    maxLoadEndTime = eventIntervals.get(i).time;
                    found = true;
                }
            } else if (!startFound) {
                maxLoadStartTime = eventIntervals.get(i).time;
            }
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}
