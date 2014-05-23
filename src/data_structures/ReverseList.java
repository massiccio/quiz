package data_structures;

import java.util.LinkedList;
import java.util.List;

/**
 * Reverses a list in O(n/2) time where n is the number of elements.
 * 
 * @author Michele Mazzucco
 */
public class ReverseList {

    public static <T> void reverse(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        final int len = list.size();
        int stop = (len >>> 1) - 1; // from 0 to (len/2)-1

        for (int i = 0; i <= stop; i++) {
            T tmp1 = list.get(i);
            list.set(i, list.get(len - 1 - i));
            list.set((len - 1 - i), tmp1);
        }
    }


    public static void main(String[] args) {
        List<Character> list = new LinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        list.add('e');
        list.add('f');

        List<Character> expected = new LinkedList<>();
        for (Character c : list) {
            expected.add(c);
        }

        reverse(list);
        reverse(list);

        if (list.size() != expected.size()) {
            throw new IllegalStateException("Unexecpected size!");
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(expected.get(i))) {
                throw new IllegalStateException("Unexpected element at index "
                        + i);
            }
        }
    }
}
