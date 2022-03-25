/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: Discussion example tester code
 * 
 * 
 * A JUnit unit tester file for MyArrayList.
 * The cases implemented here are specified in the PA2 instructions file.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;
import org.junit.*;

public class MyArrayListHiddenTester {


    static final int DEFAULT_CAPACITY = 5;
    Integer[] arrInts = { 1, 2, 3 };
    
    private MyArrayList listDefaultCap, listWithNull, listWithInt;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test */
    @Before
    public void setUp() throws Exception {
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listWithNull = new MyArrayList(null);
        listWithInt = new MyArrayList<Integer>(arrInts);
    }

    /**
     * Aims to test the capacity argument constructor when the input
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        int invalidCapacity = -1;
        try {
            new MyArrayList(invalidCapacity);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
    }

    /**
     * Aims to test the Array argument constructor when the input
     * is null
     */
    @Test
    public void testConstructorNullArg(){
        assertEquals("Check that size is 0", 0, listWithNull.size);
        assertEquals("Check that capacity is 0", 5, listWithNull.data.length);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(4);

        assertEquals("Check that append increments size", 4, listWithInt.size);
        assertEquals("Check that append doubles capacity",
         6, listWithInt.data.length);
    }

    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listWithInt.prepend(null);

        assertEquals("Check that append increments size", 4, listWithInt.size);
        assertEquals("Check that append doubles capacity",
         6, listWithInt.data.length);
        assertEquals("check the correct element", null, listWithInt.data[0]);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBound(){
       int invalidIndex = -1;

        try {
            listWithInt.insert(invalidIndex, 0);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for(int i = 0; i< 100; i++) {
            listDefaultCap.insert(0, i);
        }
        assertEquals("Check array size", 100, listDefaultCap.size);
        assertEquals("Check array capacity ", 160, listDefaultCap.data.length);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        int invalidIndex = -1;

        try {
            listWithInt.get(invalidIndex);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        int invalidIndex = -1;

        try {
            listWithInt.set(invalidIndex, 0);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
    }


    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        int invalidIndex = -1;

        try {
            listWithInt.remove(invalidIndex);
        }
        catch (IndexOutOfBoundsException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
        int requiredCapacity = 2;
        try {
            listWithInt.expandCapacity(requiredCapacity);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Check that message is empty", null, e.getMessage());
            throw e;
        }
        fail("Exception not catched");
        
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than double(2x) the current capacity
     */
    @Test
    public void testExpandCapacityExplode(){
        int requiredCapacity = 11;
        listWithInt.expandCapacity(requiredCapacity);
        assertEquals("Check array capacity ", 11, listWithInt.data.length);
    }

}
