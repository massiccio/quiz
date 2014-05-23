/**
 * 
 */
package skiena.ch3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A common problem for compilers and text editors is determining whether the
 * parentheses in a string are balanced and properly nested. For example, the
 * string
 * ((())())() contains properly nested pairs of parentheses, which the strings
 * )()( and
 * ()) do not. Give an algorithm that returns true if a string contains properly
 * nested
 * and balanced parentheses, and false if otherwise. For full credit, identify
 * the position
 * of the first offending parenthesis if the string is not properly nested and
 * balanced.
 * 
 * @author michelemazzucco
 */
public class Ex31 {

    /**
     * Check if the parentheses are balanaced or not.
     * 
     * @param parentheses The string to check
     * @return <code>true</code> if the parentheses are balanced, or if the
     *         string is empty or <code>null</code>, <code>false</code>
     *         otherwise.
     */
    public static boolean isBalanced(String parentheses) {
        if (null == parentheses || parentheses.trim().isEmpty()) { return true; }
        Queue<Character> l = new LinkedList<>();
        // it would have been possible to use arrays too, but the exercise does
        // not allow it
        for (int i = 0; i < parentheses.length(); i++) {
            l.add(parentheses.charAt(i));
        }

        int open = 0;
        int i = 0;
        while (!l.isEmpty()) {
            char c = l.poll();
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (--open < 0) {
                    System.err.printf("Unbalanced at index %d\n", i);
                    return false;
                }
            }
            i++;
        }
        return true;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        String a = "((())())()";
        System.out.println(isBalanced(a));
        String b = ")()(";
        System.out.println(isBalanced(b));
        String c = "())";
        System.out.println(isBalanced(c));
    }

}
