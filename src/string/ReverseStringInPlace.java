package string;
import java.util.Arrays;

/**
 * Reverse a string "in place" by swapping the characters in O(n/2) time, where
 * n is the number of characters of the string.
 * 
 * @author Michele Mazzucco
 * @see {@linkplain http://www.joelonsoftware.com/articles/fog0000000073.html}
 */
public class ReverseStringInPlace {

    public static void reverse(char[] string) {
	final int len = string.length;
	// if len is even, swap them all (1st and last, 2nd and 2nd from last,
	// etc.)
	// if len is odd, the char in the middle does not move
	int stop = (len >>> 1) - 1; // from 0 to (len/2)-1
	for (int i = 0; i <= stop; i++) {
	    char tmp = string[i];
	    string[i] = string[len - 1 - i];
	    string[len - 1 - i] = tmp;
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	String s = "abcdefg";
	
	char[] string = s.toCharArray();
	reverse(string);

	System.out.println(Arrays.toString(string));

    }

}
