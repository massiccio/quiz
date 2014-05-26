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

    /**
     * Rounds log2(n) to the next integer.
     * 
     * @throws IllegalArgumentException If n &le; 0.
     */
    public static int ceilingLog2(int n) throws IllegalArgumentException {
        checkArg(n);
        int counter;
        for (counter = 0; n > 1; n >>= 1, counter++) {
            ;
        }
        return counter;
    }


    /**
     * Computes the the smallest power of 2 larger than <i>n</i>
     * 
     * @throws IllegalArgumentException If n &le; 0.
     */
    public static int ceilingPow2(int n) throws IllegalArgumentException {
        checkArg(n);
        while (!isPowerOf2(n)) {
            n++;
        }
        return n;
    }


    private static final void checkArg(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be > 0");
        }
    }


    /**
     * Checks if <i>n</i> is a power of 2.
     * 
     * @return <code>true</code> if <i>n</i> is a power of 2, <code>false</code>
     *         otherwise.
     * @throws IllegalArgumentException If n &le; 0.
     */
    public static boolean isPowerOf2(int n) throws IllegalArgumentException {
        checkArg(n);
        return (n & (n - 1)) == 0;
    }


    /**
     * Computes pow(num, exp) without using the pow function in O(log2(exp))
     * time (recursive algorithm).
     * 
     * @param base The base.
     * @param exp The exponent (>= 0).
     * @return num ^ pow.
     * @throws IllegalArgumentException If exp is negative.
     */
    public static long power(long base, long exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("pow should be positive!");
        }
        if (exp == 1 || base == 1) {
            return base;
        }
        if (exp == 0) {
            return 1;
        }
        if ((exp & (exp - 1)) == 0) { // power of 2
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

        System.out.println(ceilingLog2(0));
    }

}
