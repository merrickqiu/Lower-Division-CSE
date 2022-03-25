/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email:myqiu@ucsd.edu
 * Sources used: None
 * 
 * Implementation of MyList using arrays to make my own ArrayList.
 * The ArrayList is dynamic and has a capacity that gets resized.
 */

 /**
 * TODO: Add your implementation of MyArrayList here
 */

/** 
 *  ArrayList implementation of List with Object array and size
 */
public class MyArrayList<E> implements MyList<E> {
    static final int DEFAULT_CAPACITY = 5;

    Object[] data;
    int size;

    /**
     * Default Constructor that creates ArrayList of capacity 5
     */
    public MyArrayList() {
        size = 0;
        data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates ArrayList of capacity of initialCapacity
     * @param initialCapacity - Starting Capacity of ArrayList
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        size = 0;
        data = new Object[initialCapacity];
    }

    /**
     * Creates ArrayList with elements of arr
     * @param arr - Array for ArrayList to contain
     */
    public MyArrayList(E[] arr) {
        // If arr is null, use default constructor
        if (arr == null) {
            size = 0;
            data = new Object[DEFAULT_CAPACITY];
            return;
        }
        size = arr.length;
        data = arr;
    }

    /**
     * Resizes data to have a size of at least requiredCapacity
     * @param requiredCapacity - Capacity that ArrayList must have
     */
    public void expandCapacity(int requiredCapacity) {
        int capacity = data.length;
    
        if (requiredCapacity < capacity) {
            throw new IllegalArgumentException();
        }
        // Expand Capacity
        else if (capacity == 0) {
            capacity = DEFAULT_CAPACITY;
        }
        else {
            capacity *= 2;
        }

        // Explode Capacity
        if (requiredCapacity > capacity) {
            capacity = requiredCapacity;
        }

        // Copy Elements
        Object[] newData = new Object[capacity];
        for (int i = 0; i<data.length; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Returns the capacity of the ArrayList
     * @returns The capacity of the ArrayList
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Add an element at the specified index
     * @param index - position in the array to insert the element
     * @param element - the element to be inserted 
     */
    public void insert(int index, E element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        // Expand capacity if needed
        if (size == getCapacity()) {
            expandCapacity(size+1);
        }

        // Move elements past index forward
        for (int i = size-1; i > index; i--) {
            data[i+1] = data[i];
        }

        // Insert element
        data[index] = element;
        size++;
    }

    /**
     * Add an element to the end of the ArrayList
     * @param element - the element to be appended
     */
    public void append(E element) {
        insert(size, element);
    }

    /**
     * Add an element to the beginning of the ArrayList
     * @param element - the element to be prepended
     */
    public void prepend(E element) {
        insert(0, element);
    }

    /**
     * Return the element at the index
     * @param index - the index to get element from
     * @returns the element at the index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    /**
     * Sets the element at index to the given element
     * @param index - the index to change
     * @param element - the element to be set
     * @returns - the overwritten element at index
     */
    public E set(int index, E element) {
        E overwrittenElement = get(index);
        data[index] = element;
        return overwrittenElement;
    }

    /**
     * Remove the element at index
     * @param index - the element to be removed
     * @returns - the removed elements
     */
    public E remove(int index) {
        E removedElement = get(index);
        // Move elements past index backwards
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        data[size-1] = null;
        size--;
        return removedElement;
    }

    /**
     *  Return the size of the ArrayList
     * @returns size of ArrayList
     */
    public int size() {
        return size;
    }
}
