/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * This file contains a MyDeque class, which is an implementation for the Deque
 * ADT. Elements can be added/removed from the deque at either end.
 * ordering.
 */

 /**
  * This class implements the Deque ADT using an Object array.
  * It also stores the size and the rear front of the array.
  */
public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * A constructor that initializes the Deque with an initial capacity
     * @param initialCapacity The initial capacity of the Object array
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        int size = 0;
        int rear = 0;
        int front = 0;
    }
    
    /**
     * A getter for the size
     * @return The size of the deque
     */
    public int size() {
        return size;
    }
    
    /**
     * Doubles the capacity of the array.
     * If the capacity is 0, set the capacity to 10.
     * Makes the elements continuous in the array.
     */
    public void expandCapacity() {
        // Determine the new capacity
        int newCapacity;
        if (data.length == 0) {
            newCapacity = 10;
        } else {
            newCapacity = data.length * 2;
        }

        // Move data over to expanded array
        Object[] newData = new Object[newCapacity];
        
        for(int i = 0; i < size; i++) {
            // Index of nth entry in old data array
            int oldDataIndex = (i+front)%data.length;
            newData[i] = data[oldDataIndex];
        }

        data = newData;
        front = 0;
        rear = size-1;
    }
    
    /**
     * Add an element to the front of the deque
     * @param element The element to add
     */
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }

        //Don't move pointer if size is zero
        if (size != 0) {
            // Move front one backwards
            front = front!=0 ? (front - 1) : data.length - 1; 
        }
        data[front] = element;
        size++;
    }

    /**
     * Add an element to the rear of the deque
     * @param element The element to add
     */
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }

        //Dont move pointer if size is zero
        if (size != 0) {
            rear = (rear + 1) % data.length;
        }
        data[rear] = element;
        size++;
    }

    /**
     * Removes and returns the element at the front
     * @return The element at front or null if the deque is empty
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        E first = (E) data[front];
        data[front] = null;
        // Don't move pointer if removing last element
        if (size != 1) {
            front = (front + 1) % data.length;
        }
        size--;

        return first;
    }
    
    /**
     * Removes and returns the element at the rear
     * @return The element at the rear or null if the deque is empty
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        E last = (E) data[rear];
        data[rear] = null;

        //Dont move pointer if removing last element
        if (size != 1) {
            // Move rear one backwards
            rear = rear!=0 ? (rear - 1) : data.length - 1; 
        }
        size--;

        return last;
    }
    
    /**
     * Return the element at the front
     * @return The element at the front or null if the deque is empty
     */
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return (E) data[front];
    }

    /**
     * Return the element at the rear
     * @return The element at the rear or null if the deque is empty
     */
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return (E) data[rear];
    }
}
