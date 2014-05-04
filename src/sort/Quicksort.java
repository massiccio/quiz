package sort;

import java.util.Arrays;

/**
 * Quicksort
 * 
 * @author Michele Mazzucco
 * @see <a href="http://en.wikipedia.org/wiki/Quicksort">Quicksort</a>
 */
public class Quicksort {

    public static void quicksort(int[] s, int low, int high) {
	if (low < high) {
	    int p = partition(s, low, high);
	    quicksort(s, low, p - 1);
	    quicksort(s, p + 1, high);
	}
    }

    private static final void swap(int[] s, int i, int j) {
	s[i] ^= s[j];
	s[j] ^= s[i];
	s[i] ^= s[j];
    }

    private static final int partition(int[] s, int low, int high) {
	final int pivot = high; // pivot
	int firsthigh = low; // divider position for pivot
	for (int i = low; i < high; i++) {
	    if (s[i] < s[pivot]) {
		if (i != firsthigh) {
		    swap(s, i, firsthigh);
		}
		firsthigh++;
	    }
	}
	swap(s, pivot, firsthigh);
	return firsthigh;
    }

    public static void main(String[] args) {
	int[] s = { 1, 10, 5, 34, -1, 54, 17 };
	quicksort(s, 0, s.length - 1);
	System.out.println(Arrays.toString(s));

    }

}
