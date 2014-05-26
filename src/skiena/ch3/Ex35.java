/**
 * 
 */
package skiena.ch3;

/**
 * Find the overhead fraction (the ratio of data space over total space) for
 * each
 * of the following binary tree implementations on n nodes:
 * <p>
 * (a) All nodes store data, two child pointers, and a parent pointer. The data
 * field requires four bytes and each pointer requires four bytes.
 * <p>
 * (b) Only leaf nodes store data; internal nodes store two child pointers. The
 * data field requires four bytes and each pointer requires two bytes.
 * 
 * @author michelemazzucco
 */
public class Ex35 {

    public static float a(int n) throws IllegalArgumentException {
        checkArg(n);
        // size of each node
        byte dataSize = 4;
        byte pointerSize = 12;
        byte nodeSize = (byte) (dataSize + pointerSize); // 4 bytes for data, 4
                                                         // * 3 = 12 bytes for
                                                         // pointers

        float overhead = (dataSize * n) / ((float) (nodeSize * n));
        return overhead;
    }


    public static float b(int n) throws IllegalArgumentException {
        checkArg(n);
        /*
         * Only leafs store data
         * 1
         * / \
         * 2 3
         * / \ / \
         * 4 5 6 7
         * / \ / \
         * 8 9 10 11
         */
        int internalNodes = n >> 1;
        int leafs = n - internalNodes;

        // size of each node
        byte leafSize = 4;
        byte pointerSize = 4; // 2 pointers of 2 bytes each

        int tmp = leafSize * leafs;
        float overhead = tmp / ((float) (tmp + internalNodes * pointerSize));
        return overhead;
    }


    private static final void checkArg(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be > 0");
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 15;
        System.out.println(a(n));
        System.out.println(b(n));
    }

}
