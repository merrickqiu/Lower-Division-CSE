/**
 * TODO: Add file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * File description: 
 * A tester file for the NaryTree file.
 * It uses Junit tests.
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * A tester class for the NaryTree class.
 * It unit tests each of the methods in the NaryTree class.
 */
public class CSE12NaryTreeTester {
    /**
     * Test add on 5-ary tree with a root and 5 children
     */
    @Test 
    public void testAdd() {
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(5);
        List<Integer> levelOrderArray = 
            List.of(0, 1, 2, 3, 4, 5);
        for (Integer n : levelOrderArray) {
            nTree.add(n);
        }

        // See if tree was constructed correctly
        assertEquals(Integer.valueOf(0), nTree.root.getData());
        assertEquals(Integer.valueOf(1), 
            nTree.root.getChildren().get(0).getData());
        assertEquals(Integer.valueOf(2), 
            nTree.root.getChildren().get(1).getData());
        assertEquals(Integer.valueOf(3), 
            nTree.root.getChildren().get(2).getData());
        assertEquals(Integer.valueOf(4), 
            nTree.root.getChildren().get(3).getData());
        assertEquals(Integer.valueOf(5), 
            nTree.root.getChildren().get(4).getData());

        // Add to the tree 
        nTree.add(6);
        assertEquals(Integer.valueOf(6), 
            nTree.root.getChildren().get(0).getChildren().get(0).getData());

    }
    /**
     * Tests adding nodes until the tree in the appendix is constructed.
     */
    @Test
    public void testAddAppendix(){
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(3);
        List<Integer> levelOrderArray = 
            List.of(4, 2, 6, 10, 3, 50, 25, 19, 1, 0);
        for (Integer n : levelOrderArray) {
            nTree.add(n);
        }

        // Create iterator and check tree against iterator
        Iterator<Integer> iterator = levelOrderArray.iterator();
        assertEquals(iterator.next(), nTree.root.getData()); // root
        //root children
        for (CSE12NaryTree<Integer>.Node child : nTree.root.getChildren()) {
            assertEquals(iterator.next(), child.getData());
        }
        //left subtree children
        for (CSE12NaryTree<Integer>.Node child : 
            nTree.root.getChildren().get(0).getChildren()) {

            assertEquals(iterator.next(), child.getData());
        }
        //middle subtree children
        for (CSE12NaryTree<Integer>.Node child : 
            nTree.root.getChildren().get(1).getChildren()) {
                
            assertEquals(iterator.next(), child.getData());
        }
        //right subtree has no children
        assertEquals(List.of(), nTree.root.getChildren().get(2).getChildren());

    }
    /**
     * Test contains on a 5-ary tree when the element is not present
     */
    @Test 
    public void testContains() {
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(5);
        List<Integer> levelOrderArray = 
            List.of(0, 1, 2, 3, 4, 5);
        for (Integer n : levelOrderArray) {
            nTree.add(n);
        }

        // Contains not present element?
        assertFalse(nTree.contains(6));
    }
    /**
     * Test contains on tree from Appendix for numbers 0-10
     */
    @Test
    public void testContainsAppendix(){
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(3);
        List<Integer> levelOrderArray = 
            List.of(4, 2, 6, 10, 3, 50, 25, 19, 1, 0);
        for (Integer n : levelOrderArray) {
            nTree.add(n);
        }

        //Asserts
        assertTrue(nTree.contains(0));
        assertTrue(nTree.contains(1));
        assertTrue(nTree.contains(2));
        assertTrue(nTree.contains(3));
        assertTrue(nTree.contains(4));
        assertFalse(nTree.contains(5));
        assertTrue(nTree.contains(6));
        assertFalse(nTree.contains(7));
        assertFalse(nTree.contains(8));
        assertFalse(nTree.contains(9));
        assertTrue(nTree.contains(10));

    }

    /**
     *  Sort 5-ary tree with only a root node
     */
    @Test 
    public void testSortTree() {
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(5);
        nTree.add(0);

        // sorted?
        assertEquals(List.of(0), nTree.sortTree());
    }
    /**
     * Sort the tree from the appendix
     */
    @Test
    public void testSortTreeAppendix(){
        // Add to tree
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(3);
        List<Integer> levelOrderArray = 
            List.of(4, 2, 6, 10, 3, 50, 25, 19, 1, 0);
        for (Integer n : levelOrderArray) {
            nTree.add(n);
        }

        // sorted?
        List<Integer> sorted = 
            List.of(0, 1, 2, 3, 4, 6, 10, 19, 25, 50);
        assertEquals(sorted, nTree.sortTree());
    }

    /**
     * Test above methods for null input
     */
    @Test
    public void testCustom(){
        CSE12NaryTree<Integer> nTree = new CSE12NaryTree<>(5);
        try {
            nTree.add(null);
            fail();
        } catch (NullPointerException e) {
            //sucess
        }
        try {
            nTree.contains(null);
            fail();
        } catch (NullPointerException e) {
            //sucess
        }
        assertEquals(List.of(), nTree.sortTree());
    }
}
