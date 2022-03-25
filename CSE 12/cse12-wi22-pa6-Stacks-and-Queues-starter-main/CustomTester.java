/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * A custom tester for MyDeque, MyStack, and MyQueue.
 * It implements tests not in the public tester
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 * 
 * A class containing JUnit tests for MyDeque, MyStack, and MyQueue.
 * It uses assert statements to test the methods run correctly.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initialized with negative capacity
     */
    @Test
    public void testMyDequeConstructor() {
        try {
            new MyDeque<>(-1);
            fail();
        } catch(IllegalArgumentException e) {
            // passed
        }
    }

    /**
     * Test the expandCapacity method when front > rear
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(8);
        Integer[] data = { 3, 4, 5, 6, null, null, 1, 2 };
        deque.data = data;
        deque.size = 6;
        deque.front = 6;
        deque.rear = 3;
        Integer[] newData = { 1, 2, 3, 4, 5, 6 , null, null, null, null, null, 
            null, null, null, null, null};

        deque.expandCapacity();

        assertEquals(16, deque.data.length);
        assertEquals(6, deque.size);
        assertEquals(0, deque.front);
        assertEquals(5, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    /**
     * Test the addFirst method when elements start from beginning
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(8);
        Integer[] data = { 1, 2, 3, 4, 5, 6, null, null};
        deque.data = data;
        deque.size = 6;
        deque.front = 0;
        deque.rear = 5;      
        Integer[] newData = { 1, 2, 3, 4, 5, 6 , null, 0};

        deque.addFirst(0);

        assertEquals(8, deque.data.length);
        assertEquals(7, deque.size);
        assertEquals(7, deque.front);
        assertEquals(5, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    /**
     * Test the addLast method when rear = data.length-1
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(8);
        Integer[] data = {null, null , 1, 2, 3, 4, 5, 6};
        deque.data = data;
        deque.size = 6;
        deque.front = 2;
        deque.rear = 7;      
        Integer[] newData = {7, null , 1, 2, 3, 4, 5, 6};

        deque.addLast(7);

        assertEquals(8, deque.data.length);
        assertEquals(7, deque.size);
        assertEquals(2, deque.front);
        assertEquals(0, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    /**
     * Test the removeFirst method when first element is at end of array
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(8);
        Integer[] data = {2, 3, 4, 5, 6, null, null, 1};
        deque.data = data;
        deque.size = 6;
        deque.front = 7;
        deque.rear = 5;      
        Integer[] newData = {2, 3, 4, 5, 6, null, null, null};

        assertEquals(Integer.valueOf(1), deque.removeFirst());

        assertEquals(8, deque.data.length);
        assertEquals(5, deque.size);
        assertEquals(0, deque.front);
        assertEquals(5, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    /**
     * Test the removeLast method when last element is at the beginning of array
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(8);
        Integer[] data = {7, null, 1, 2, 3, 4, 5, 6};
        deque.data = data;
        deque.size = 7;
        deque.front = 2;
        deque.rear = 0;      
        Integer[] newData = {null, null, 1, 2, 3, 4, 5, 6};

        assertEquals(Integer.valueOf(7), deque.removeLast());

        assertEquals(8, deque.data.length);
        assertEquals(6, deque.size);
        assertEquals(2, deque.front);
        assertEquals(7, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    /**
     * Test the peekFirst method when array is empty
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        assertEquals(null, deque.peekFirst());
    }

    /**
     * Test the peekLast method when array is empty
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        assertEquals(null, deque.peekLast());
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when popping from and empty stack
     * and pushing to a full stack
     */
    @Test
    public void testMyStack(){
        MyStack<Integer> stack = new MyStack<>(3);
        assertEquals(null, stack.pop());
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        MyDeque<Integer> deque = stack.theStack;
        Integer[] newData = {0, 1, 2, 3, null, null};
        assertEquals(6, deque.data.length);
        assertEquals(4, deque.size);
        assertEquals(0, deque.front);
        assertEquals(3, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when dequeueing from empty Queue 
     * and enqueueing to a full queue
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(3);
        assertEquals(null, queue.dequeue());
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(Integer.valueOf(0), queue.dequeue());

        MyDeque<Integer> deque = queue.theQueue;
        Integer[] newData = {null, 1, 2, 3, null, null};
        assertEquals(6, deque.data.length);
        assertEquals(3, deque.size);
        assertEquals(1, deque.front);
        assertEquals(3, deque.rear);
        for (int i = 0; i < newData.length; i++) {
            assertEquals(newData[i], deque.data[i]);
        }
    }
}
