/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements

import org.junit.*;
import static org.junit.Assert.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseArrayListTester {

    /**
     * Run before every test
     */

    private Integer[] small, numbers;
    private MyArrayList<Integer> smallArray, numberArray;
    // private MyLinkedList<Integer> smallList, numberList;
    @Before
    public void setUp(){
        Integer[] small = {1};
        smallArray = new MyArrayList<>(small);
        // smallList = new MyLinkedList<>(small);

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};
        numberArray = new MyArrayList<>(numbers);
        // numberList = new MyLinkedList<>(numbers);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        try {
            numberArray.reverseRegion(-1, 3);
            fail();
        } catch (IndexOutOfBoundsException e){
            // Success
        }
        try {
            numberArray.reverseRegion(0, 7);
            fail();
        } catch (IndexOutOfBoundsException e){
            // Success
        }
        try {
            numberArray.reverseRegion(-2, 7);
            fail();
        } catch (IndexOutOfBoundsException e){
            // Success
        }

        // try {
        //     numberList.reverseRegion(-1, 3);
        //     fail();
        // } catch (IndexOutOfBoundsException e){
        //     // Success
        // }
        // try {
        //     numberList.reverseRegion(0, 7);
        //     fail();
        // } catch (IndexOutOfBoundsException e){
        //     // Success
        // }
        // try {
        //     numberList.reverseRegion(-2, 7);
        //     fail();
        // } catch (IndexOutOfBoundsException e){
        //     // Success
        // }
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        
        numberArray.reverseRegion(4, 3);
        // numberList.reverseRegion(4, 3);
        
        for(int i = 0; i<expected.length; i++) {
            assertEquals(expected[i], numberArray.get(i)); 
            // assertEquals(expected[i], numberList.get(i));
        }
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){
        Integer[] expected = {1, 2, 5, 4, 3, 6, 7};
        
        numberArray.reverseRegion(2, 4);
        // numberList.reverseRegion(2, 4);
        
        for(int i = 0; i<expected.length; i++) {
            assertEquals(expected[i], numberArray.get(i)); 
            // assertEquals(expected[i], numberList.get(i));
        }   
    }

    /**
     * Custom test
     * Reverses a list of size 1. 
     * A list of size one has fromIndex and toIndex be the same.
     * It's also an edge case in terms of size.
    */
    @Test
    public void testReverseCustom(){
        smallArray.reverseRegion(0, 0);
        // smallList.reverseRegion(0, 0);
        
        assertEquals(Integer.valueOf(1), smallArray.get(0)); 
        // assertEquals(Integer.valueOf(1), smallList.get(0));
    }


}
