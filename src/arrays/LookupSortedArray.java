package arrays;

/**
 * Write a lookup function taking two arguments: a target array and the searched
 * integer. The target array is sorted and contains integers(possibly with
 * duplicates). The lookup function should return the index of the left-most
 * occurrence of the searched integer in the input array or -1 if the integer is
 * not present in the input. Please also discuss the complexity of your
 * implementation.
 * For example, the lookup function invoked on target array [1,2,2,3,4] and
 * searching for 2, should return 1.
 * 
 * @author Michele Mazzucco
 */
public class LookupSortedArray {

    /**
     * Recursive binary search.
     * 
     * @param array The array.
     * @param value The value to look for.
     * @param low The low index.
     * @param high The high index.
     * @return The index of the left-most
     * occurrence of the searched integer in the input array or -1 if
     * the integer is not present in the input.
     */
    private static int lookup(int[] array, int value, int low, int high) {
	if (low == high) {
	    if (value == array[high]) {
		return high;
	    }
	    return -1;
	}

	// binary search
	int middle = (high - low) / 2;
	if (value < array[middle]) {
	    return lookup(array, value, low, middle);
	} else if (value == array[middle]) {
	    int res = lookup(array, value, low, middle - 1);
	    if (res == -1) {
		return middle;
	    }
	    return res;
	} else {
	    return lookup(array, value, middle + 1, high);
	}
    }

    /**
     * Looks up the specified value in array, if it exists, in O(log2(n)) time,
     * where n is the number of elements in array.
     * 
     * @param array The sorted array.
     * @param value The value to look for.
     * @return The index of the left-most occurrence of the searched integer in
     * the input array or -1 if the integer is not present in array.
     */
    public static int lookup(int[] array, int value) {
	if (array == null || array.length == 0) {
	    return -1;
	}
	return lookup(array, value, 0, array.length - 1);
    }

    public static void main(String[] args) {
	int[] array = { 1, 1, 1, 2, 2, 2, 2, 4, 5, 6 };
	System.out.println(lookup(array, 2));
    }

}
