package org.example.base.helper.alg;

import java.util.Stack;

/**
 * @author panfudong
 * @description
 *
 * 当我们说“有效的括号”时，通常是指一个包含以下字符的字符串：'('，')'，'{'，'}'，'['，']'。对于一个字符串，如果括号的使用是正确匹配的，
 * 即每个左括号都有一个正确的右括号与之对应，并且括号之间的嵌套关系也是正确的，那么这个字符串就是有效的括号字符串。
 *
 * 举个例子，下面的字符串是有效的括号字符串：
 * ()
 * ()[]{}
 * {[()]}
 * 而下面的字符串则是无效的括号字符串：
 * (]
 * ([)]
 * {}
 */
public class StackTest {

    /**
     * 我写的
     * @param args
     */
    public static void main(String[] args) {
        Character left = '(';
        Character right = ')';
        Character left2 = '[';
        Character right2 = ']';
        String s = "";
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == left) {
                stack.push(c);
            }
            if (c == left2) {
                stack.push(left2);
            }
            if (c == right) {
                if (stack.peek() == left) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            if (c == right2) {
                if (stack.peek() == left2) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        System.out.println(stack.empty());
    }


    /**
     * GPT写的
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // 遇到无效括号直接返回 false
            }
        }

        return stack.isEmpty(); // 栈为空表示所有括号都匹配成功
    }
}
