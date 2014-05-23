/**
 * 
 */
package skiena.ch3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author michelemazzucco
 */
public class Ex32Test {
    
    private static final int DEF_SIZE = 20000;

    private Ex32 list;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        list = new Ex32();
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        list = null;
    }


    /**
     * Test method for {@link skiena.ch3.Ex32#add(int)}.
     */
    @Test
    public final void testAdd() {
        for (int i = 0; i < 10000; i++) {
            list.add(i);
            assertEquals(i+1, list.size());
            assertEquals(i, list.get(i));
        }
    }


    /**
     * Test method for {@link skiena.ch3.Ex32#toString()}.
     */
    @Test
    public final void testToString() {
        assertNotNull(list.toString());
    }
    
    /**
     * Test method for {@link skiena.ch3.Ex32#toString()}.
     */
    @Test
    public final void testToString1() {
        fill(5);
        String expected = "[0] [1] [2] [3] [4]";
        assertEquals(expected, list.toString());
    }


    @Test
    public final void testClone() throws CloneNotSupportedException {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Ex32 clone = (Ex32) list.clone();
        assertEquals(clone.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), clone.get(i));
        }
    }
    
    private final void fill() {
        fill(DEF_SIZE);
    }

    private final void fill(int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
    
    
    @Test
    public void testSize() {
        assertEquals(0, list.size());
    }
    
    
    @Test
    public void testSize1() {
        fill();
        assertEquals(DEF_SIZE, list.size());
    }
    

    /**
     * Test method for {@link skiena.ch3.Ex32#reverse()}.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public final void testReverse() throws CloneNotSupportedException {
        fill();

        Ex32 clone = (Ex32) list.clone();
        list.reverse();
        list.reverse();
        assertEquals(clone.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), clone.get(i));
        }
        
    }

}
