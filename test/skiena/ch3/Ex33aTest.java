/**
 * 
 */
package skiena.ch3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Test class for {@link Ex33a} and {@link Ex33b}.
 * 
 * @author michelemazzucco
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ex33aTest {

    // ~ 1M values (2^20), ~8MB of memory
    private static final int fstThreshold = 2 << 19;
    private static final int sndThreshold = fstThreshold + 10000;

    private static long time1 = 0L;

    private Ex33a list1;

    private Ex33b list2;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        list1 = new Ex33a();
        list2 = new Ex33b();
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        list1 = null;
        list2 = null;
    }


    private static final void doWork(Ex33a list) {
        for (int i = 0; i <= fstThreshold >>> 1; i++) {
            list.add(i + 1);
        }
        for (int i = fstThreshold; i < sndThreshold; i++) {
            for (int j = 0; j < 2; j++) {
                list.remove(0); // causes contraction
            }
            for (int j = 0; j < 2; j++) {
                list.add(i + 1); // expansion
            }
        }
    }


    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        doWork(list1);
        long stop = System.currentTimeMillis();
        time1 = stop - start;

        assertTrue(list1.getExpansions() > list1.getContractions());
        assertEquals(1 + (fstThreshold >>> 1), list1.size);

    }


    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        doWork(list2);
        long stop = System.currentTimeMillis();
        long time2 = stop - start;
        assertTrue(list2.getExpansions() > list2.getContractions());

        // log2(list size) expansions
        int expansions = -1; // number of expansions
        int x = fstThreshold;
        while (x > 0) {
            expansions++;
            x >>>= 1;
        }
        assertEquals(expansions, list2.getExpansions());
        assertEquals(0, list2.getContractions());

        assertTrue(time1 > time2);

        System.out.printf("First took %.1f sec., second took %.1f sec\n",
                (time1 / 1000.0), (time2 / 1000.0));

    }
}
