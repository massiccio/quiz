/**
 * 
 */
package math;

/**
 * Given a currency with denominations of 100, 50, 20, 10, 5 and 1 write a
 * method MakeChange that takes an integer amount representing the total change
 * to make and return an integer representing the smallest possible number of
 * bills to return. For example a call of MakeChange(135) would result in a
 * return value of 4 (1 one hundred bill, 1 twenty bill, 1 ten bill and 1 five
 * bill). As part of a final solution please provide unit tests done as well as
 * any test cases ran.
 * 
 * @author Michele Mazzucco
 */
public class MakeChange {
    // the greedy algorithm does the job in this case.

    private static int[] CURRENCY_DENOMINATIONS = { 100, 50, 20, 10, 5, 1 };

    /**
     * Given an integer amount representing the total change to make, returns an
     * integer representing the smallest possible number of bills to return
     * 
     * @param value The change to return.
     * @return The smallest possible number of bills to return.
     * @throws IllegalArgumentException If value is negative.
     */
    public static int makeChange(final int value)
	    throws IllegalArgumentException {
	if (value < 0) {
	    throw new IllegalArgumentException(
		    "Non negative value expected, got " + value);
	}
	if (value == 0) {
	    return 0;
	}

	int result = 0;
	int remainingValue = value;
	for (int i : CURRENCY_DENOMINATIONS) {
	    if (remainingValue >= i) {
		int notes = remainingValue / i;
		remainingValue = remainingValue % i;
		result += notes;
	    }
	}

	if (remainingValue > 0) {
	    throw new IllegalStateException("Left change %d\n" + remainingValue);
	}

	return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	int value = 135;
	System.out.printf("Number of bills %d\n", makeChange(value));

    }

}
