import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class CustomTester {
    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> emptyTree;
    List<MyBST.MyBSTNode<Integer, Integer>> inOrderExpected;

    @Before
    public void setup(){
        emptyTree = new MyBST();

        MyBST.MyBSTNode<Integer, Integer> eight = 
            new MyBST.MyBSTNode<>(8, 80, null);
        MyBST.MyBSTNode<Integer, Integer> four = 
            new MyBST.MyBSTNode<>(4, 40, eight);
        MyBST.MyBSTNode<Integer, Integer> twelve = 
            new MyBST.MyBSTNode<>(12, 120, eight);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode<>(2, 20, four);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode<>(6, 60, four);
        MyBST.MyBSTNode<Integer, Integer> ten = 
            new MyBST.MyBSTNode<>(10, 100, twelve);
        MyBST.MyBSTNode<Integer, Integer> fourteen = 
            new MyBST.MyBSTNode<>(14, 140, twelve);
        MyBST.MyBSTNode<Integer, Integer> zero = 
            new MyBST.MyBSTNode<>(0, 0, two);

        inOrderExpected = List.of(zero, two, four, six,
             eight, ten, twelve, fourteen);
        tree = new MyBST();
        tree.root = eight;
        eight.setLeft(four);
        eight.setRight(twelve);
        four.setLeft(two);
        four.setRight(six);
        twelve.setLeft(ten);
        twelve.setRight(fourteen);
        two.setLeft(zero);
        tree.size = 8;
    }
    // 
    /**
     *           8
     *         /   \
     *        4     12
     *      /  \   /  \
     *     2    6 10    14
     *    /
     *   0
     */

    // ====== MyBSTNode class ======

    // Test predecessor() smallest node
    @Test
    public void testNodePredecessorSmallestNode() {
        MyBST.MyBSTNode<Integer, Integer> smallest = 
            tree.root.getLeft().getLeft().getLeft();
        assertSame(null, smallest.predecessor());

    }

    // ====== MyBST class ======
    // Test Insert multiple times into tree
    @Test
    public void testInsert(){
        MyBST.MyBSTNode<Integer, Integer> root = tree.root; 
        tree.insert(1, 10);
        assertEquals((Integer)1, 
            root.getLeft().getLeft().getLeft().getRight().getKey());
        tree.insert(3, 30);
        assertEquals((Integer)3, 
            root.getLeft().getLeft().getRight().getKey());
        tree.insert(5, 50);
        assertEquals((Integer)5, 
            root.getLeft().getRight().getLeft().getKey());
        tree.insert(7, 70);
        assertEquals((Integer)7, 
            root.getLeft().getRight().getRight().getKey());
        tree.insert(9, 90);
        assertEquals((Integer)9, 
            root.getRight().getLeft().getLeft().getKey());   
        tree.insert(11, 110);
        assertEquals((Integer)11, 
            root.getRight().getLeft().getRight().getKey());
        tree.insert(13, 130);
        assertEquals((Integer)13, 
            root.getRight().getRight().getLeft().getKey());
        tree.insert(15, 150);
        assertEquals((Integer)15, 
            root.getRight().getRight().getRight().getKey());
    }

    // Insert into empty tree
    @Test
    public void testInsertEmpty() {
        emptyTree.insert(1, 10);
        assertEquals((Integer)1, emptyTree.root.getKey());
    }

    // Test Search many times
    @Test
    public void testSearch(){
        assertEquals((Integer)0, tree.search(0));
        assertEquals((Integer)20, tree.search(2));
        assertEquals((Integer)40, tree.search(4));
        assertEquals((Integer)60, tree.search(6));
        assertEquals((Integer)80, tree.search(8));
        assertEquals((Integer)100, tree.search(10));
        assertEquals((Integer)120, tree.search(12));
        assertEquals((Integer)140, tree.search(14));
        assertEquals(null, tree.search(-1));
        assertEquals(null, tree.search(7));
        assertEquals(null, tree.search(15));
    }

    // Test Remove for leaf, 1 child, 2 children, and not in BST
    @Test
    public void testRemove(){
        MyBST.MyBSTNode<Integer, Integer> root = tree.root; 

        // Leaf
        assertEquals((Integer)0, tree.remove(0));
        assertNull(root.getLeft().getLeft().getLeft());
        assertEquals((Integer)60, tree.remove(6));
        assertNull(root.getLeft().getRight());

        // One child
        assertEquals((Integer)40, tree.remove(4));
        assertNull(root.getLeft().getLeft());
        assertEquals((Integer)2, root.getLeft().getKey());

        // Two children
        assertEquals((Integer)120, tree.remove(12));
        assertNull(root.getRight().getRight());
        assertEquals((Integer)14, root.getRight().getKey());

        // Not in tree
        assertNull(tree.remove(-1));
    }
    // Test inorder for empty array
    @Test
    public void testInorderEmpty(){
        assertEquals(0, emptyTree.inorder().size());
    }
    // Test inorder method for current array
    @Test
    public void testInorder(){
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> inOrderResults
            = tree.inorder();
        for (int i=0; i<inOrderResults.size(); i++){
            assertSame(inOrderExpected.get(i), inOrderResults.get(i));
        }
    }

    // Test next for the whole BST
    @Test 
    public void testIterator() {
        MyBSTIterator<Integer, Integer> treeIter = new MyBSTIterator();
        treeIter.root = tree.root;

        MyBSTIterator<Integer, Integer>.MyBSTKeyIterator keyIter =
            treeIter.new MyBSTKeyIterator(
                tree.root.getLeft().getLeft().getLeft());

        for (int i=0; i<inOrderExpected.size(); i++){
            assertSame(inOrderExpected.get(i).getKey(), keyIter.next());
        }
    }

    // Test next on empty BST
        // Test next for the whole BST
        @Test 
        public void testEmptyIterator() {
            MyBSTIterator<Integer, Integer> treeIter = new MyBSTIterator();
            MyBSTIterator<Integer, Integer>.MyBSTKeyIterator keyIter =
                treeIter.new MyBSTKeyIterator(null);
    
            try {
                keyIter.next();
                fail();
            } catch (NoSuchElementException e) {
                //caught
            }

        }

    // ====== Calender class ======
    // Test the calendar some more
    @Test
    public void testCalender(){
        MyCalendar calendar = new MyCalendar();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(5, 10));
        assertFalse(calendar.book(0, 20));
    }
}
