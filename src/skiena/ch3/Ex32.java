package skiena.ch3;


/**
 * Write a program to reverse the direction of a given singly-linked list. In
 * other
 * words, after the reversal all pointers should now point backwards. Your
 * algorithm
 * should take linear time.
 * 
 * @author michelemazzucco
 */
public class Ex32 implements Cloneable {

    private Node first;

    private int size;


    public Ex32() {
        first = null;
        size = 0;
    }


    /**
     * Add a value at the end of the list in O(n) time.
     * 
     * @param value The value to add.
     */
    public void add(int value) {
        Node last = new Node(value);
        if (null == this.first) {
            this.first = last;
        } else {
            Node cur = first;
            while (null != cur.next) {
                cur = cur.next;
            }
            cur.next = last;
        }
        this.size++;
    }


    public int size() {
        return this.size;
    }


    @Override
    public String toString() {
        if (0 == this.size) { return "Empty"; }
        StringBuilder sb = new StringBuilder();
        Node cur = this.first;
        while (null != cur.next) {
            sb.append("[").append(cur.value).append("] ");
            cur = cur.next;
        }
        // last value
        sb.append("[").append(cur.value).append("]");
        return sb.toString();
    }

    private static class Node {

        int value;

        Node next;


        Node(int value) {
            this.value = value;
            next = null;
        }


        @Override
        public String toString() {
            return Integer.toString(this.value);
        }
    }


    /**
     * Reverse the list in place, using O(n) time.
     */
    public void reverse() {
        if (this.size > 0) {
            Node prev = null;
            Node cur = this.first;
            while (null != cur.next) {
                Node next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            // link last value
            cur.next = prev;
            // first element is the last one
            this.first = cur;
        }
    }


    /**
     * Gets the value at index <i>index</i>. This operation costs O(n).
     * 
     * @param i
     * @return The value at index <i>index</i>.
     * @throws IndexOutOfBoundsException If <i>index</i> & lt; 0 or <i>index</i>
     *             & ge; <i>size</i>.
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) { throw new IndexOutOfBoundsException(); }
        Node x = this.first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.value;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Ex32 clone = new Ex32();

        for (Node x = first; x != null; x = x.next) {
            clone.add(x.value);
        }
        return clone;
    }


    public static void main(String[] args) {
        Ex32 list = new Ex32();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        String s = list.toString();
        System.out.println(s);
        list.reverse();
        s = list.toString();
        System.out.println(s);
    }

}
