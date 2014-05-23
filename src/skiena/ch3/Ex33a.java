/**
 * 
 */
package skiena.ch3;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

/**
 * We have seen how dynamic arrays enable arrays to grow while still achieving
 * constant-time amortized performance. This problem concerns extending dynamic
 * arrays to let them both grow and shrink on demand.
 * <p>
 * Consider an underflow strategy that cuts the array size in half whenever the
 * array falls below half full. Give an example sequence of insertions and
 * deletions where this strategy gives a bad amortized cost.
 * 
 * @author michelemazzucco
 */
public class Ex33a {

    protected int[] array;

    protected int size;

    /** Number of times the array has been expanded. */
    protected int expansions;

    /** Number of times the size of the array has been reduced. */
    protected int contractions;


    /**
     * Creates a new list with initial capacity of <i>1</i>.
     */
    public Ex33a() {
        this(1);
    }


    /**
     * Creates a new list with the specified initial capacity.
     * 
     * @param capacity The initial capacity.
     * @throws IllegalArgumentException If <i>capacity</i> &lt; 1.
     */
    public Ex33a(int capacity) throws IllegalArgumentException {
        if (capacity < 1) { throw new IllegalArgumentException(); }
        array = new int[capacity];
        size = 0;
        expansions = 0;
        contractions = 0;
    }


    public void add(int value) {
        if (this.array.length == this.size) { // double size
            this.array = Arrays.copyOf(this.array, this.array.length << 1);
            this.expansions++;
        }
        this.array[size++] = value;
    }


    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) { throw new IndexOutOfBoundsException(); }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        this.array[--size] = 0;
        if (this.size < (this.array.length >>> 1)) { // half size
            this.array = Arrays.copyOf(this.array, this.array.length >>> 1);
            this.contractions++;
        }
    }


    public int getContractions() {
        return contractions;
    }


    public int getExpansions() {
        return expansions;
    }


    public int size() {
        return this.size;
    }


    /**
     * To see the garbage collection effects:
     * <p>
     * java -client -Xmx50m -verbose:gc -cp . skiena.ch3.Ex33a
     * <p>
     * extra arguments
     * <p>
     * -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        List<GarbageCollectorMXBean> gc = ManagementFactory
                .getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean i : gc) {
            System.out.println(Arrays.toString(i.getMemoryPoolNames()));
            System.out.printf("%s, %d %d\n", i.getName(),
                    i.getCollectionCount(), i.getCollectionTime());
        }

        Thread.sleep(20000L);
        System.out.println("Starting");

        Ex33a list = new Ex33a();
        final int fstThreshold = 2 << 20; // ~ 1M values, ~8MB memory
        final int sndThreshold = fstThreshold + 10000; // 2 << 21;
        for (int i = 0; i <= fstThreshold >>> 1; i++) {
            list.add(i + 1);
        }
        System.out.println(list.size);
        for (int i = fstThreshold; i < sndThreshold; i++) {
            for (int j = 0; j < 2; j++) {
                list.remove(0); // causes contraction
            }
            for (int j = 0; j < 2; j++) {
                list.add(i + 1); // expansion
            }
        }
        System.out.printf("expansions %d, contractions %d\n",
                list.getExpansions(), list.getContractions());

    }

}
