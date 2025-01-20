package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        boolean result;
        Stack<Character> stack = new Stack<>();
        var components = s.toCharArray();
        for (char component : components) {
            if (stack.empty() || stack.peek() != findOpeningBracket(component)) {
                stack.push(component);
            } else {
                stack.pop();
            }
        }
        result = stack.empty();
        return result;
    }

    private char findOpeningBracket(char closingBracket) {
        return switch (closingBracket) {
            case (')') -> '(';
            case ('}') -> '{';
            case (']') -> '[';
            default -> ' ';
        };
    }
}
