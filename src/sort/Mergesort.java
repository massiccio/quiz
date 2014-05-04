package sort;

import java.util.Arrays;

/**
 * Mergesort
 * 
 * @author Michele Mazzucco
 * @see <a href="http://en.wikipedia.org/wiki/Merge_sort">Merge sort</a>
 */
public class Mergesort {

    public static void mergesort(int[] s, int low, int high) {
	if (low < high) {
	    int middle = (low + high) >>> 1;
	    mergesort(s, low, middle);
	    mergesort(s, middle + 1, high);
	    merge(s, low, middle, high);
	}
    }

    public static void merge(int[] s, int low, int middle, int high) {
	int[] left = new int[middle - low + 1];
	int[] right = new int[high - middle];
	if (left.length == 0 && right.length == 0) {
	    return;
	}
	System.arraycopy(s, low, left, 0, left.length);
	System.arraycopy(s, middle + 1, right, 0, right.length);

	int i = 0; // index left
	int j = 0; // index right
	int k = low;
	while ((i < left.length) && (j < right.length)) {
	    if (left[i] < right[j]) {
		s[k++] = left[i++];
	    } else {
		s[k++] = right[j++];
	    }
	}
	while (i < left.length) {
	    s[k++] = left[i++];
	}
	while (j < right.length) {
	    s[k++] = right[j++];
	}
    }

    public static void main(String[] args) {
	int[] s = { 1, 10, 5, 34, -1, 54, 17 };
	mergesort(s, 0, s.length - 1);
	System.out.println(Arrays.toString(s));
    }

}
