package math;

/**
 * 
 */

/**
 * Power function in O(log2) time. Assumes pow is positive
 * 
 * @author Michele Mazzucco
 */
public class Power {

    private static int counter = 0;

    /**
     * Computes pow(num, exp) without using the pow function in O(log2(exp))
     * time (recursive algorithm).
     * 
     * @param base The base.
     * @param exp The exponent (>= 0).
     * @return num ^ pow.
     * @throws IllegalArgumentException If pow is negative.
     */
    public static long power(long base, long exp) {
	if (exp < 0) {
	    throw new IllegalArgumentException("pow should be positive!");
	}
	counter++;
	if (exp == 1) {
	    return base;
	}
	if (exp == 0) {
	    return 1;
	}
	if (exp % 2 == 0) {
	    final long val = power(base, exp >>> 1);
	    return val * val;
	}
	final long val = power(base, exp >>> 1);
	return base * val * val;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	int base = 2;
	int pow = -3;

	try {
	    power(base, pow);
	    throw new IllegalStateException("expecting expection here");
	} catch (IllegalArgumentException expected) {
	    // expected
	}
	
	
	pow = 3;
	long res = power(base, pow);
	
	System.out.println(res);
	System.out.println(counter);
    }

}
