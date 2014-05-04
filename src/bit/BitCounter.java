package bit;

/**
 * Counts the number of bits set to 1 in a positive long.
 * 
 * @author Michele Mazzucco
 */
public class BitCounter {

    /**
     * Counts the number of bit in the specified positive input.
     * 
     * @param num The number.
     * @return The number of bits set to 1 in num, if num is positive, 0
     * otherwise.
     */
    public static int bitCounter(long num) {
	int count = 0;
	while (num > 0) {
	    if ((num & 1) == 1) {
		count++;
	    }
	    num >>>= 1;
	}

	return count;
    }

    public static void main(String[] args) {
	// count the number of bits in one byte
	int num = 5; // 101
	int count = bitCounter(num);

	System.out.println(count);

    }

}
