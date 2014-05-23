/**
 * 
 */
package data_structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import static data_structures.ReverseList.reverse;

/**
 * @author michelemazzucco
 *
 */
public class ReverseListTest {
    

    @Test
    public final void testNull() {
        reverse(null);
    }
    
    @Test
    public final void test1() {
        ArrayList<Character> list = new ArrayList<>(Collections.singletonList('A'));
        @SuppressWarnings("unchecked")
        ArrayList<Character> clone = (ArrayList<Character>) list.clone();
        assertEquals(clone, list);
        reverse(list);
        assertEquals(clone, list);
    }
    
    @Test
    public final void test2() {
        ArrayList<Character> list = new ArrayList<>();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random();
        for (int i = 0; i < 2_000_001; i++) {
            char c = chars[random.nextInt(chars.length)];
            list.add(c);
        }
        
        @SuppressWarnings("unchecked")
        ArrayList<Character> clone = (ArrayList<Character>) list.clone();
        assertEquals(clone.size(), list.size());
        reverse(list);
        assertEquals(clone.size(), list.size());
        reverse(list);
        assertEquals(clone.size(), list.size());
        System.out.println("Checking");
        for (int i = 0; i < list.size(); i++) {
            assertEquals(clone.get(i), list.get(i));
        }
    }

}
