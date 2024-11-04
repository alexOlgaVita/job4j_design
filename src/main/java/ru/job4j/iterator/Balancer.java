package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int nodeNum = 0;
        while (source.hasNext()) {
            nodeNum = (nodeNum == nodes.size()) ? 0 : nodeNum;
            nodes.get(nodeNum).add(source.next());
            nodeNum++;
        }
    }
}