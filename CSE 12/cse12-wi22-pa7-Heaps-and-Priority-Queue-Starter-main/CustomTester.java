/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * A JUnit tester for MyMinHeap.
 * It tests for cases not covered by the public tester.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 * 
 * The class that tests MyMinHeap.
 * It does unit tests on the methods by having one or more tester methods 
 * for each method to be tested.
 */
public class CustomTester {
    
    /**
     * Test the constructor when passed an empty constructor.
     */
    @Test
    public void testMyMinHeapConstructor() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>(List.of());
        assertEquals(0, minHeap.data.size());
    }

    /**
     * Test the getMinChildIdx method when heap is not empty
     */
    @Test
    public void testGetMinChildIdx() {
        MyMinHeap<Integer> heap = new MyMinHeap<>(List.of(0, 1, 2, 4, 3, 5));
        assertEquals(1, heap.getMinChildIdx(0));
        assertEquals(4, heap.getMinChildIdx(1));
        assertEquals(5, heap.getMinChildIdx(2));
        assertEquals(-1, heap.getMinChildIdx(3));
    }

    /**
     * Test the percolateUp method when at root
     */
    @Test
    public void testPercolateUp() {
        MyMinHeap<Integer> heap = new MyMinHeap<>(List.of(0, 1, 2, 4, 3, 5));
        heap.percolateUp(0);

        // Nothing should have changed
        Integer[] expected = {0, 1, 2, 4, 3, 5};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the percolateDown method when starting from middle.
     */
    @Test
    public void testPercolateDown() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.data = new ArrayList<>(Arrays.asList(
            new Integer[] {3,8,2,5,9,6,8,9,2}));
        heap.percolateDown(1);
        Integer[] expected = {3,5,2,2,9,6,8,9,8};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when at the middle of the heap
     */
    @Test
    public void testDeleteIndex() {
        MyMinHeap<Integer> heap = new MyMinHeap<>(List.of(0, 1, 2, 3, 4, 5));
        heap.deleteIndex(1);

        Integer[] expected = {0, 3, 2, 5, 4};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when deleting the last element
     */
    @Test
    public void testDeleteIndex2() {
        MyMinHeap<Integer> heap = new MyMinHeap<>(List.of(0, 1, 2, 3, 4, 5));
        heap.deleteIndex(5);

        Integer[] expected = {0, 1, 2, 3, 4};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting into an empty heap
     */
    @Test
    public void testInsert(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.insert(Integer.valueOf(0));

        Integer[] expected = {0};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting a null element
     */
    @Test
    public void testInsert2(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        try {
            heap.insert(null);
            fail();
        } catch (NullPointerException e) {
            // catched
        }
    }

   
    /**
     * Test remove when removing a single element
     */
    @Test
    public void testRemove(){
        MyMinHeap<Integer> heap = new MyMinHeap<>(List.of(0));
        assertEquals(Integer.valueOf(0), heap.remove());

        assertEquals(0, heap.data.size());
    }

  
    /**
     * Test getMin when heap is empty
     */
    @Test
    public void testGetMin(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        assertEquals(null, heap.getMin());
    }
}