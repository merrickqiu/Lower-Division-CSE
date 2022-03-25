
/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * This is a tester file for MyLinkedList.
 * The tester specifically tests the methods of the iterator of MyLinkedList
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * TODO: Add your class header
 * The class of the tester file.
 * Each method of this class tests a separate feature of each iterator method.
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    private MyLinkedList list0, list1, list2;
    private MyLinkedList.MyListIterator listIter0, listIter1, listIter2;

    @Before
    public void setUp() throws Exception {
        list0 = new MyLinkedList();
        listIter0 = list0.new MyListIterator();

        list1 = new MyLinkedList();
        list1.add(1);
        listIter1 = list1.new MyListIterator();

        list2 = new MyLinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        listIter2 = list2.new MyListIterator();
    }

    /**
     * TODO: test the hasNext method when list is empty
     */
    @Test
    public void testHasNext() {
        assertEquals(false, listIter0.hasNext());
    }

    /**
     * TODO: test the next method when list is empty
     */
    @Test
    public void testNext() {
        try {
            listIter0.next();
            fail();
        }
        catch (NoSuchElementException e){
            // Correct exception thrown
        }
    }

    /**
     * TODO: test the hasPrevious method when iterator is at beginning
     */
    @Test
    public void testHasPrevious() {
        assertEquals(false, listIter2.hasPrevious());
    }

    /**
     * TODO: test the previous method when iterator is at beginning
     */
    @Test
    public void testPrevious() {
        try {
            listIter2.previous();
            fail();
        }
        catch (NoSuchElementException e){
            // Correct exception thrown
        }
    }

    /**
     * TODO: test the nextIndex method when iterator is on the first node
     */
    @Test
    public void testNextIndex() {
        assertEquals(0, listIter2.nextIndex());
    }

    /**
     * TODO: test the previousIndex method when iterator is on the last node
     */
    @Test
    public void testPreviousIndex() {
        listIter2.next();
        listIter2.next();
        listIter2.next();
        assertEquals(2, listIter2.previousIndex());
    }

    /**
     * TODO: test the set method when list is empty
     */
    @Test
    public void testSet() {
        int newElement = 0;
        try {
            listIter0.set(newElement);
            fail();
        }
        catch (IllegalStateException e){
            // Correct exception thrown
        }
    }

    /**
     * TODO: test the remove method after next then add has been called
     */
    @Test
    public void testRemoveTestOne() {
        int newElement = 0;
        listIter1.next();
        listIter1.add(newElement);
        try {
            listIter1.remove();
            fail();
        }
        catch (IllegalStateException e){
            // Correct exception thrown
        }
    }

    /**
     * TODO: test the remove method when removing from empty list
     */
    @Test
    public void testRemoveTestTwo() {
        try {
            listIter0.remove();
            fail();
        }
        catch (IllegalStateException e){
            // Correct exception thrown
        }
    }

    /**
     * TODO: test the add method when adding on the last index
     */
    @Test
    public void testAdd() {
        int newElement = 0;
        listIter2.next();
        listIter2.next();
        listIter2.next();
        listIter2.add(newElement);
        assertEquals(newElement, listIter2.left.getElement());
        assertEquals(null, listIter2.right.getElement());
    }

}