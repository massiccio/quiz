package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * java -client -Xms3m -Xmx3m -cp
 * ../lib/junit-4.11.jar:../lib/hamcrest-core-1.3.jar:.
 * org.junit.runner.JUnitCore ms.TinyHeapTest
 * 
 * @author Michele Mazzucco
 */
@RunWith(JUnit4.class)
public class TinyHeapTest {

    private static final int HEAP_SIZE = 10;

    private static final byte[] DATA = { 13, -50, 1, 5, 11, 10, 7, 115, 18, 20,
            9 };

    private static final byte[] HEAP_DATA = { -50, 5, 1, 13, 9, 10, 7, 115, 18,
            20, 11 };

    private TinyHeap heap;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        heap = TinyHeap.allocTinyHeap(HEAP_SIZE);
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.heap = null;
    }


    /**
     * Test method for {@link ms.TinyHeap#deleteTinyHeap(ms.TinyHeap)}.
     */
    @Test
    public final void testDeleteTinyHeap() {
        this.heap.deleteTinyHeap();
        try {
            this.heap.getArray(); // throws NPE
            throw new AssertionError("Expecting NPE");
        } catch (NullPointerException ignore) {
            //
        }
        this.heap.deleteTinyHeap();
    }


    /**
     * Test method for {@link ms.TinyHeap#allocTinyHeap(int)}.
     * <p>
     * Testing huge memory allocation causing OOME.
     */
    @Test
    public final void testHugeAllocTinyHeap() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        TinyHeap heap = TinyHeap.allocTinyHeap((int) (maxMemory + 1));
        assertEquals(0, heap.size());
        assertEquals(0, heap.getArray().length);

        byte[] data = { 1, 2, 3 };
        heap.add(data);
        assertEquals(data.length, heap.size());
        assertEquals(data.length, heap.getArray().length);
        assertArrayEquals(data, heap.getArray());

    }


    /**
     * Test method for allocation, adda and free.
     */
    @Test
    public final void testAllocAddFreeTinyHeap() {
        assertEquals(0, this.heap.size());
        assertEquals(0, this.heap.getArray().length);

        this.heap.add(DATA);
        assertEquals(DATA.length, this.heap.size());
        this.heap.tinyFree(0);
        assertEquals(DATA.length - 1, this.heap.size());
    }


    /**
     * Test method for {@link ms.TinyHeap#allocTinyHeap(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testAllocTinyHeapNegative() {
        TinyHeap.allocTinyHeap(-10);
    }


    /**
     * Test method for {@link ms.TinyHeap#allocTinyHeap(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testAllocTinyHeapZero() {
        TinyHeap.allocTinyHeap(0);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyAlloc(int)}.
     */
    @Test
    public final void testTinyAlloc() {
        assertEquals(0, this.heap.size());
        assertEquals(0, this.heap.getArray().length);

        final int newAlloc = 3;
        this.heap.tinyAlloc(newAlloc); // allocate more space
        assertEquals(0, this.heap.size());
        assertEquals(0, this.heap.getArray().length);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyAlloc(int)}.
     */
    @Test
    public final void testTinyAlloc1() {
        TinyHeap heap = TinyHeap.makeHeap(DATA);
        assertEquals(DATA.length, heap.size());
        byte[] array = heap.getArray();

        int newAlloc = 5;
        heap.tinyAlloc(newAlloc);
        assertArrayEquals(array, heap.getArray());
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyAlloc(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testTinyAllocNegative() {
        this.heap.tinyAlloc(-1);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyAlloc(int)}.
     */
    @Test
    public final void testTinyAllocZero() {
        // nothing happens
        this.heap.tinyAlloc(0);
        assertEquals(0, this.heap.size());
        assertEquals(0, this.heap.getArray().length);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyFree(int)}.
     */
    @Test
    public final void testTinyFree() {
        byte[] array = { 1, 2, 3, 45 };
        TinyHeap heap = TinyHeap.makeHeap(array);
        heap.tinyFree(0);
        byte[] expected = { 2, 3, 45 };
        assertArrayEquals(expected, heap.getArray());
        assertEquals(3, heap.size());
        heap.tinyFree(1);
        byte[] expected1 = { 2, 45 };
        assertArrayEquals(expected1, heap.getArray());
        assertEquals(2, heap.size());
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyFree(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testTinyFreeWrongIndexLow() {
        this.heap.tinyFree(-1);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyFree(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testTinyFreeWrongIndexHigh() {
        byte[] array = { 1, 2, 45 };
        this.heap.add(array);
        this.heap.tinyFree(array.length + 1);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyFree(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testTinyFreeEmpty1() {
        this.heap.tinyFree(0);
    }


    /**
     * Test method for {@link ms.TinyHeap#tinyFree(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testTinyFreeEmpty() {
        byte[] array = { 1, 2, 45 };
        TinyHeap heap = TinyHeap.makeHeap(array);
        for (int i = array.length - 1; i >= -1; i--) {
            try {
                heap.tinyFree(0);
            } catch (IndexOutOfBoundsException e) {
                if (i == -1) {
                    throw e;
                }
                throw new AssertionError("Wrong index " + i, e);
            }
        }
    }


    /**
     * Test method for {@link ms.TinyHeap#makeHeap(int[])}.
     */
    @Test
    public final void testMakeHeap() {
        byte[] array = { 1, 2, 45 };
        TinyHeap heap = TinyHeap.makeHeap(array);
        assertNotNull(heap);
        assertEquals(3, heap.size());
    }


    /**
     * Test method for {@link ms.TinyHeap#makeHeap(int[])}.
     */
    @Test
    public final void testMakeHeapGetArray() {
        TinyHeap heap = TinyHeap.makeHeap(DATA);
        assertArrayEquals(HEAP_DATA, heap.getArray());
    }


    /**
     * Test add and getArray
     */
    @Test
    public final void testAddGetArray() {
        this.heap.add(DATA);
        assertArrayEquals(HEAP_DATA, heap.getArray());
        assertEquals(DATA.length, heap.size());
    }


    /**
     * Test getArray when no data is stored into the heap.
     */
    @Test
    public final void testGetArray() {
        byte[] expected = new byte[0];
        assertArrayEquals(expected, this.heap.getArray());
        assertEquals(0, heap.size());
    }


    /**
     * Test method for {@link ms.TinyHeap#makeHeap(int[])}.
     */
    @Test(expected = NullPointerException.class)
    public final void testMakeHeapNull() {
        TinyHeap.makeHeap(null);
    }


    /**
     * Test method for {@link ms.TinyHeap#makeHeap(int[])}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testMakeHeapZero() {
        byte[] array = new byte[0];
        TinyHeap.makeHeap(array);
    }


    /**
     * Test method for {@link ms.TinyHeap#extractMin()}. No elements, throws
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public final void testExtractMinNoValues() {
        this.heap.extractMin();
    }


    /**
     * Test method for {@link ms.TinyHeap#extractMin()}.
     */
    @Test
    public final void testExtractMin() {
        TinyHeap heap = TinyHeap.makeHeap(DATA);
        assertEquals(-50, heap.extractMin());
        assertEquals(1, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertEquals(9, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(11, heap.extractMin());
        assertEquals(13, heap.extractMin());
        assertEquals(18, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(115, heap.extractMin());

        assertEquals(0, heap.size());

        try {
            heap.extractMin();
            throw new AssertionError("Should not occur");
        } catch (IllegalStateException ignore) {
            //
        }
    }

}
