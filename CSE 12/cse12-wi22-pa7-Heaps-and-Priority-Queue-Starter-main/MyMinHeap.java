/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * 2-4 sentence file description here
 * Implementation of a Minimum Heap.
 * It allows insertion of elements as well as quick removal of 
 * the minimum element.
 */

// Your import statements
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * TODO: Add class header
 * A Minimum Heap class that implements the MinHeapInterface.
 * It stores the heap as an ArrayList.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{

    public ArrayList<E> data;

    /**
     * The default constructor
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }

    /**
     * Constructor initialized from a collection
     * @param collection Collection that MinHeap should begin as
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for (int i = data.size()-1; i>=0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Swaps the items in two indexces
     * @param from Index to swap from
     * @param to Index to swap to
     */
    protected void swap(int from, int to) {
        Collections.swap(data, from, to);
    }

    /**
     * Returns the parent index of the given index using 0-based indexing
     * @param index Child index
     * @return Parent index
     */
    protected int getParentIdx(int index) {
        return (index-1)/2;
    }

    /**
     * Returns the left child index
     * @param index Parent index
     * @return Left child index
     */
    protected int getLeftChildIdx(int index) {
        return 2*index + 1;
    }

    /**
     * Returns the right child index
     * @param index Parent index
     * @return Right child index
     */
    protected int getRightChildIdx(int index) {
        return 2*index + 2;
    }

    /**
     * Returns the index of the smallest child, or -1 if it is a leaf node
     * @param index Parent index
     * @return Minimum child index
     */
    protected int getMinChildIdx(int index) {
        // Handle 0 or 1 children nodes
        int leftChildIdx = getLeftChildIdx(index);
        int rightChildIdx = getRightChildIdx(index);
        if (leftChildIdx >= data.size()) {
            return -1;
        } else if (rightChildIdx >= data.size()) {
            return leftChildIdx;
        }

        // Compare both children
        E leftChild = data.get(leftChildIdx);
        E rightChild = data.get(rightChildIdx);

        if (leftChild.compareTo(rightChild) <= 0) {
            return leftChildIdx;
        }
        return rightChildIdx;
    }

    /**
     * Percolates the element up so it doesn't violate heap properties
     * @param index The index of the element to percolate up
     */
    protected void percolateUp(int index) {
        // Don't do anything if greater than parent
        // Since (0-1)/2 == 0, I do not need to
        // independently handle the case when index == 0
        if (data.get(index).compareTo(data.get(getParentIdx(index))) >= 0) {
            return;
        }

        swap(index, getParentIdx(index));
        percolateUp(getParentIdx(index));
        
    }

    /**
     * Percolates the element down so it doesn't violate heap properties
     * @param index The index of the element to percolate down
     */
    protected void percolateDown(int index) {
        // Don't do anything if leaf or smaller than children
        int minChildIdx = getMinChildIdx(index);
        if (minChildIdx == -1) {
            return;
        } else if (data.get(index).compareTo(data.get(minChildIdx)) <= 0) {
            return;
        }

        swap(index, minChildIdx);
        percolateDown(minChildIdx);
    }

    /**
     * Deletes and returns the element at an index 
     * while preserving heap properties.
     * @param index The index of the element to delete
     * @return The deleted element
     */
    protected E deleteIndex(int index) {
        // Simply return the element if it is the last one
        if (index == data.size()-1) {
            return data.remove(index);
        }

        // Otherwise, swap with last and percolate the index
        swap(index, data.size()-1);
        E removedElement = data.remove(data.size()-1);

        percolateDown(index);
        percolateUp(index);
        return removedElement;
    }

    /**
     * Inserts an element to the end of the heap, then percolates it up.
     * @param element The element to insert
     */
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size()-1);
    }
    
    /**
     * Returns the root element
     * @return The root element
     */
    public E getMin() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }
    
    /**
     * Deletes and returns the root element
     * @return The root element
     */
    public E remove() {
        return deleteIndex(0);
    }

    /**
     * Returns the size of the heap
     * @return The size of the heap
     */
    public int size() {
        return data.size();
    }

    /**
     * Clears the heap of all elements
     */
    public void clear() {
        data.clear();
    }
}