/**
 * 
 */
package skiena.ch3;

/**
 * Design a dictionary data structure in which search, insertion, and deletion
 * can
 * all be processed in O(1) time in the worst case. You may assume the set
 * elements
 * are integers drawn from a finite set 1, 2, .., n, and initialization can take
 * O(n) time
 * 
 * @author michelemazzucco
 */
public class Ex34 {

    // an array of size n suffices
    
    
    private int[] array;
    
    public Ex34(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be > 0");
        }
        array = new int[n];
    }
    
    
    public int search(int key) throws IllegalArgumentException {
        checkBounds(key);
        return this.array[key];
    }
    
    public void insert(int value) throws IllegalArgumentException {
        checkBounds(value);
        this.array[value] = value;
    }
    
    
    public void delete(int value) throws IllegalArgumentException {
        checkBounds(value);
        this.array[value] = 0;
    }
    
    

    private final void checkBounds(int index) throws IllegalArgumentException {
        if (index < 0 || index > array.length - 1) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 10;
        Ex34 ex = new Ex34(n);
        int value = ex.search(11);
        System.out.println(value);
    }

}
