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
     *         otherwise.
     * @see http://graphics.stanford.edu/~seander/bithacks.html#CountBitsSetKernighan
     */
    public static int bitCounter(long num) {
        int count;
        for (count = 0; num > 0; count++) {
            num &= num - 1;
        }
        return count;
    }


    public static void main(String[] args) {
        // count the number of bits in one byte
        int num = 1234545; // 0001 0010 1101 0110 0111 0001
        int count = bitCounter(num);
        System.out.println(count);
    }

}
