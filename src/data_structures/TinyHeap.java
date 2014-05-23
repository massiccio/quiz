package data_structures;

import java.util.Arrays;

/**
 * Write a class that represents a minimal heap. The heap class should at a
 * minimum support the following methods:
 * <ul>
 * <li>AllocTinyHeap() which should initialize the heap with a given amount of
 * bytes
 * <li>DeleteTinyHeap() which frees all memory associated with the heap
 * <li>TinyAlloc() which allocates a given number of bytes on the heap if there
 * is room
 * <li>TinyFree() which frees a specific location on the heap
 * </ul>
 * You may define whatever parameters are necessary for the above methods as
 * well as write any additional methods. Overall consideration will be given to
 * correctness, design, code readability as well as any unit testing done. As
 * part of a final solution please submit test cases you used to verify
 * correctness in addition to any unit tests done.
 * 
 * @author Michele Mazzucco
 */
public class TinyHeap {

    /** Default array capacity. */
    private static final int DEFAULT_CAPACITY = 10;

    private static final byte[] ZERO_ARRAY = {};

    /** The array containing the data. */
    private byte[] array;

    /** Number of elements. */
    private int size;

    /**
     * Creates a new tiny heap with the given capacity.
     * 
     * @param bytes The number of bytes.
     * @throws IllegalArgumentException If bytes is not positive.
     */
    private TinyHeap(int bytes) throws IllegalArgumentException {
	if (bytes <= 0) {
	    throw new IllegalArgumentException("Capacity should be > 0");
	}
	try {
	    array = new byte[bytes];
	} catch (OutOfMemoryError e) {
	    System.err.println(e.getMessage() + ", setting array size to "
		    + DEFAULT_CAPACITY);
	    array = new byte[DEFAULT_CAPACITY];
	}
	size = 0;
    }

    /**
     * Frees all memory associated with this heap (deletes the array).
     * <p>
     * Invoking this method multiple times has no effect.
     * <p>
     * <strong>All the other methods will throw a {@link NullPointerException}
     * if invoked after this method</strong>.
     */
    public void deleteTinyHeap() {
	// if the heap contained object we should set each element to null,
	// however since it contains a primitive type this is OK.
	this.array = null;
	this.size = 0;
    }

    /**
     * Initialize a heap with a given amount of bytes (factory
     * method).
     * <p>
     * If <i>bytes</i> is too big, i.e., if the memory can not be allocated, the
     * buffer size is set to {@value #DEFAULT_CAPACITY}.
     * 
     * @param bytes The number of bytes in the {@link TinyHeap}.
     * @return A new {@link TinyHeap}.
     * @throws IllegalArgumentException If bytes is not positive.
     */
    public static TinyHeap allocTinyHeap(int bytes)
	    throws IllegalArgumentException {
	return new TinyHeap(bytes);
    }

    private final void checkNotNull() throws NullPointerException {
	if (this.array == null) {
	    throw new NullPointerException(
		    "The memory has been deallocated, create a new object");
	}
    }

    /**
     * Ensures that there is space for at least <i>minCapacity</i> elements.
     * 
     * @param minCapacity The minimum number of elements.
     * @throws IllegalArgumentException If minCapacity is negative.
     */
    private final void ensureCapacity(int minCapacity)
	    throws IllegalArgumentException {
	if (minCapacity < 0) {
	    throw new IllegalArgumentException("minCapacity should be >= 0");
	}
	if (minCapacity == 0) { // nothing to do
	    return;
	}

	final int remainingCapacity = this.array.length - this.size;
	if (minCapacity > remainingCapacity) {
	    grow(minCapacity - remainingCapacity);
	}
    }

    private final void grow(int minCapacity) {
	final int oldCapacity = this.array.length;
	int newCapacity = oldCapacity + (oldCapacity >> 1);
	newCapacity = newCapacity > minCapacity ? newCapacity : minCapacity;
	try {
	    this.array = Arrays.copyOf(this.array, this.array.length
		    + newCapacity);
	} catch (OutOfMemoryError e) {
	    throw new IllegalArgumentException("Array is too large", e);
	}
    }

    /**
     * Allocate a given number of bytes on the heap.
     * <p>
     * If <i>bytes</i> is too big, i.e., if the memory can not be allocated,
     * this method does not change the state of this object.
     * 
     * @param bytes The number of bytes to allocate.
     * @throws IllegalArgumentException If bytes is negative (as an alternative,
     *             negative values could indicate a free operation...).
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    public void tinyAlloc(int bytes) throws IllegalArgumentException {
	checkNotNull();
	if (bytes < 0) {
	    throw new IllegalArgumentException("Expecting positive value");
	}
	if (bytes == 0) { // nothing to do
	    return;
	}
	// extend the array: length increases by bytes, size does not change
	try {
	    this.array = Arrays.copyOf(this.array, bytes + array.length);
	} catch (OutOfMemoryError e) {
	    System.err.println(e.getMessage()
		    + ", unable to increase the buffer size");
	}
    }

    /**
     * Frees a specific location on the heap.
     * 
     * @param index The index of the element to remove.
     * @throws IndexOutOfBoundsException If index is negative or larger than the
     *             number of elements stored into this heap.
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    public void tinyFree(int index) throws IndexOutOfBoundsException {
	checkNotNull();
	if ((index < 0) || (index > this.size - 1)) {
	    throw new IndexOutOfBoundsException("Illegal index");
	}
	int numMoved = --size - index;
	if (numMoved > 0) {
	    // copies data in order to free memory at index "index"
	    byte[] newArray = new byte[this.size];
	    System.arraycopy(this.array, 0, newArray, 0, index);
	    System.arraycopy(this.array, index + 1, newArray, index, numMoved);
	    this.array = newArray;
	} else {
	    this.array = Arrays.copyOf(this.array, this.size);
	}
    }

    /**
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    @Override
    public String toString() {
	checkNotNull();
	return Arrays.toString(this.array);
    }

    // ==================== Sorting related methods ===========================

    /**
     * Gets the index of the left child.
     */
    private static final int left(int index) {
	return (2 * index) + 1;
    }

    /**
     * Swaps the two elements at index <i>i</i> and <i>j</i>.
     */
    private final void swap(int i, int j) {
	this.array[i] ^= this.array[j];
	this.array[j] ^= this.array[i];
	this.array[i] ^= this.array[j];
    }

    /**
     * Heapify in "Introduction to algorithms".
     * 
     * @param i The index of the value to bubble down.
     */
    private final void bubbleDown(final int i) {
	int left = left(i);
	int right = left + 1;
	int minIndex = i;
	if (left < size && array[left] < array[i]) {
	    minIndex = left;
	}
	if (right < size && array[right] < array[minIndex]) {
	    minIndex = right;
	}

	if (minIndex != i) {
	    swap(i, minIndex);
	    bubbleDown(minIndex);
	}
    }

    /**
     * Gets the number of elements in this heap.
     * 
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    public int size() throws NullPointerException {
	checkNotNull();
	return this.size;
    }

    /**
     * Gets a copy of the data contained into this heap.
     * <p>
     * If no data is stored into this heap, a new array of size 0 is returned.
     * 
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    public byte[] getArray() {
	checkNotNull();
	if (this.size == 0) {
	    return ZERO_ARRAY;
	}
	byte[] array = new byte[this.size]; // copy only the data!!!
	System.arraycopy(this.array, 0, array, 0, this.size);
	return array;
    }

    /**
     * Adds the specified array.
     * 
     * @param data The array to add.
     * @throws NullPointerException If array is <code>null</code>.
     * @throws IllegalArgumentException If cannot allocate memory (the array is too big).
     */
    public void add(byte[] data) throws NullPointerException, IllegalArgumentException {
	if (data == null) {
	    throw new NullPointerException();
	}
	if (data.length > 0) {
	    // an error might occur if memory can not be allocated
	    ensureCapacity(data.length);
	    if (this.array.length - this.size - data.length < 0) {
		int tmp = this.array.length - this.size - data.length;
		throw new IllegalStateException("Expected > 0, was " + tmp);
	    }
	    System.arraycopy(data, 0, this.array, this.size, data.length);
	    this.size += data.length;

	    // fix the heap
	    for (int i = (this.array.length - 1) / 2; i >= 0; i--) {
		bubbleDown(i);
	    }
	}
    }

    /**
     * Builds the heap.
     * 
     * @param array The unsorted array.
     * @return The heap.
     * @throws NullPointerException if array is <code>null</code>.
     * @throws IllegalArgumentException If len(array) is not > 0.
     */
    public static TinyHeap makeHeap(byte[] array) throws NullPointerException,
	    IllegalArgumentException {
	if (array == null) {
	    throw new NullPointerException("Null array");
	}
	TinyHeap heap = allocTinyHeap(array.length);
	heap.add(array);

	// last parent at floor((size-1)/2)
	for (int i = (array.length - 1) / 2; i >= 0; i--) {
	    heap.bubbleDown(i);
	}
	return heap;
    }

    /**
     * Extracts the minimum
     * 
     * @return The minimum element.
     * @throws IllegalStateException If there are no elements.
     * @throws NullPointerException If this method is invoked after
     *             {@link #deleteTinyHeap()}.
     */
    public byte extractMin() throws IllegalStateException {
	checkNotNull();
	if (this.size <= 0) {
	    throw new IllegalStateException("No elements");
	}
	byte min = this.array[0]; // first element is the min
	// set the root to be the last element
	this.array[0] = this.array[this.size - 1];
	tinyFree(this.size - 1);

	// now push down the root in order to maintain the heap
	bubbleDown(0);
	return min;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	byte[] array = { 13, -50, 1, 5, 11, 10, 7, 115, 18, 20, 9 };
	System.out.printf("%s\n", Arrays.toString(array));

	// run the code as follows to cause an OOME:
	// java -client -Xms2m -Xmx2m -cp . TinyHeap
	TinyHeap heap = allocTinyHeap(5 * 1024 * 1024);

	heap.add(array);

	// last parent at floor((size-1)/2)
	for (int i = (array.length - 1) / 2; i >= 0; i--) {
	    heap.bubbleDown(i);
	}

	int counter = 0;
	while (heap.size() > 0) {
	    array[counter++] = heap.extractMin();
	}

	System.out.printf("%s\n", Arrays.toString(array));
    }

}
