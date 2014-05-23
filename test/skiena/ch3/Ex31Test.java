package skiena.ch3;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex31Test {

    @Test
    public final void testIsBalancedNull() {
        assertTrue(Ex31.isBalanced(null));
    }


    @Test
    public final void testIsBalancedEmpty() {
        String s = "";
        assertTrue(Ex31.isBalanced(s));
    }


    @Test
    public final void testIsBalancedEmpty1() {
        String s = "  ";
        assertTrue(Ex31.isBalanced(s));
    }


    @Test
    public final void testUnbalanced() {
        String s = ")()(";
        assertFalse(Ex31.isBalanced(s));
    }


    @Test
    public final void testUnbalanced1() {
        String s = "())";
        assertFalse(Ex31.isBalanced(s));
    }


    @Test
    public final void testIsBalanced() {
        String s = "((())())()";
        assertTrue(Ex31.isBalanced(s));
    }


    /**
     * Long test.
     */
    @Test
    public final void testIsBalancedLong() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000000; i++) {
            if ((i & 1) == 1) {
                sb.append('(');
            } else {
                sb.append(')');
            }
        }
        assertTrue(Ex31.isBalanced(sb.toString()));
    }

}
