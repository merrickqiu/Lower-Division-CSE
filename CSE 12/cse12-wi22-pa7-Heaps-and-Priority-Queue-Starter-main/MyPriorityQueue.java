/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email:myqiu@ucsd.edu
 * Sources used: None
 * 
 * A priority queue to demonstrate an application of the heap.
 * It stores generic comparable elements.
 */

import java.util.Collection;

/**
 * TODO: Add Class Header
 * A priority queue that has a single MyMinHeap instance variable.
 * This is an example of the adapter pattern used on the heap.
 */
public class MyPriorityQueue<E extends Comparable<E>>
{

    //TODO: Add a public instance variable called "heap"
    //"heap" is of a generic MyMinHeap type
    public MyMinHeap<E> heap;
    
    /**
     * Constructor that creates an empty priority queue
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Constructor that creates a priority queue from a collection
     * @param collection The collection used to intialize priority queue
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Adds an element to the priority queue
     * @param element the element to be added
     */
    public void push(E element){
        heap.insert(element);
    }

    /**
     * Removes the element with the highest priority from the priority queue 
     * @return the element with the highest priority
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * Sees the element with the highest priority from the priority queue
     * @return the element with the highest priority
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Finds the number of elements in the priority queue
     * @return the number of elements in the priority queue
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Remove all the elements from the priority queue.
     */
    public void clear(){
        heap.clear();
    }
}