/**
 * 
 */
package math;

import static org.junit.Assert.*;
import static math.Power.*;

import org.junit.Test;

/**
 * @author michelemazzucco
 */
public class PowerTest {

    /**
     * Test method for {@link math.Power#ceilingLog2(int)}.
     */
    @Test
    public final void testCeilingLog2() {
        int val = ceilingLog2(1);
        assertEquals(0, val);
        val = ceilingLog2(2);
        assertEquals(1, val);
    }


    /**
     * Test method for {@link math.Power#ceilingLog2(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testCeilingLog2Zero() {
        ceilingLog2(0);
    }


    /**
     * Test method for {@link math.Power#ceilingPow2(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testCeilingPow2Zero() {
        ceilingPow2(0);
    }


    /**
     * Test method for {@link math.Power#ceilingPow2(int)}.
     */
    @Test
    public final void testCeilingPow2() {
        int val = ceilingPow2(1);
        assertEquals(1, val);
    }


    /**
     * Test method for {@link math.Power#isPowerOf2(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIsPowerOf2Zero() {
        isPowerOf2(0);
    }


    /**
     * Test method for {@link math.Power#isPowerOf2(int)}.
     */
    @Test
    public final void testIsPowerOf2One() {
        assertTrue(isPowerOf2(1));
    }


    /**
     * Test method for {@link math.Power#isPowerOf2(int)}.
     */
    @Test
    public final void testIsPowerOf2() {
        assertTrue(isPowerOf2(2));
    }


    /**
     * Test method for {@link math.Power#isPowerOf2(int)}.
     */
    @Test
    public final void testIsPowerOf2False() {
        assertFalse(isPowerOf2(3));
    }
    
    /**
     * Test method for {@link math.Power#power(long, long)}.
     */
    @Test
    public final void testPowerOne() {
        assertEquals(1, power(1, 100));
    }
    
    /**
     * Test method for {@link math.Power#power(long, long)}.
     */
    @Test
    public final void testPowerZero() {
        assertEquals(1, power(100, 0));
    }


    /**
     * Test method for {@link math.Power#power(long, long)}.
     */
    @Test
    public final void testPower() {
        assertEquals(9, power(3, 2));
        assertEquals(9, power(-3, 2));
        assertEquals(8, power(2, 3));
        assertEquals(-8, power(-2, 3));
        
        assertEquals(729, power(3, 6));
        assertEquals(216, power(6, 3));
        assertEquals(10, power(10, 1));
        assertEquals(1, power(10, 0));
        assertEquals(10000000000L, power(10, 10));
        assertEquals(256, power(2, 8));
        assertEquals(27, power(3, 3));
    }

}
