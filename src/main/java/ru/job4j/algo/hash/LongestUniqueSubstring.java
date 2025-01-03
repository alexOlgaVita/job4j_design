package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {

    public static Set<Character> toSet(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    public static String longestUniqueSubstring(String str) {
        String result = "";
        int j = str.length();
        while ((j > 0) && (result.isEmpty())) {
            int windowSize = j;
            int i = 0;
            while ((i <= str.length() - windowSize) && (result.isEmpty())) {
                String window = str.substring(i, i + windowSize);
                if (toSet(window).size() == window.length()) {
                    result = window;
                }
                i++;
            }
            j--;
        }
        System.out.println("result = " + result);
        return result;
    }

    public static void main(String[] args) {
        String str = "abcde";
        String result = longestUniqueSubstring(str);
        System.out.println("result = " + result);
    }
}
