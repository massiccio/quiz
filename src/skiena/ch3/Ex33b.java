/**
 * 
 */
package skiena.ch3;

import java.util.Arrays;

/**
 * We have seen how dynamic arrays enable arrays to grow while still achieving
 * constant-time amortized performance. This problem concerns extending dynamic
 * arrays to let them both grow and shrink on demand.
 * <p>
 * (b) Then, give a better underflow strategy than that suggested above (see
 * {@link Ex33a}), one that achieves constant amortized cost per deletion.
 * 
 * @author michelemazzucco
 * @see Ex33a
 */
public class Ex33b extends Ex33a {

    /**
     * 
     */
    public Ex33b() {
        super();
    }

    
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) { throw new IndexOutOfBoundsException(); }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        this.array[--size] = 0;
        if (this.size < (this.array.length >>> 2)) { // 1/4 size
            // free only 1/2 of the array
            this.array = Arrays.copyOf(this.array, this.array.length >>> 1);
            this.contractions++;
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
