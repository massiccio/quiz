package data_structures;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * Reverses a list in O(n/2) time where n is the number of elements.
 * 
 * @author Michele Mazzucco
 */
public class ReverseList {

    private ReverseList() {
        //
    }


    /**
     * Reverses the given list, in O(n/2) time.
     * <p>
     * If the input is <i>null</i> nothing happens.
     * 
     * @param list The list to reverse;
     */
    public static <T> void reverse(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        final int size = list.size();
        if (list instanceof RandomAccess) {
            int stop = (size >> 1); // from 0 to (len/2)-1

            for (int i = 0; i < stop; i++) {
                // this works fine with array list, not with a linked list!
                Collections.swap(list, i, (size - 1 - i));
            }
        } else {
            ListIterator<T> fwd = list.listIterator();
            ListIterator<T> rev = list.listIterator(size);
            for (int i=0, mid=list.size()>>1; i<mid; i++) {
                T tmp = fwd.next();
                fwd.set(rev.previous());
                rev.set(tmp);
            }
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
