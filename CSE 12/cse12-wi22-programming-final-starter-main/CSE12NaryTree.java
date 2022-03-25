/**
 * TODO: Add file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * File description: 
 * An implementation of an N-nary tree, 
 * where each node can have up to N children.
 * This file stores generic comparable elements.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * An implementation of an N-nary tree,
 * where each node can have up to N children.
 * The class has an internal Node class,
 * and it stores the root note, the size, 
 * and the max number of children per node.
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Adds a node containing an element to the tree.
     * @param element The element to add to the tree
     */
    public void add(E element) {
        // Edge Cases
        if (element == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(element);
            return;
        }

        List<Node> levelOrderList = levelOrderList();

        // Find parent node and add element as child
        int addIndex = levelOrderList.size();
        int parentIndex = (addIndex-1) / N;
        Node parentNode = levelOrderList.get(parentIndex);
        parentNode.addChild(new Node(element));
    }

    /**
     * Returns a list of the nodes in order
     * @return In order list of nodes
     */
    private List<Node> levelOrderList() {
        // Initialize list of nodes and queue
        List<Node> levelOrderList = new ArrayList<>();
        Queue<Node> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);

        // Add nodes in order
        while(!levelOrderQueue.isEmpty()) {
            Node currentNode = levelOrderQueue.remove();
            for (Node child: currentNode.getChildren()) {
                levelOrderQueue.add(child);
            }
            levelOrderList.add(currentNode);
        }

        return levelOrderList;
    }

    /**
     * Returns if the tree contains the element
     * @param element The element to search for
     * @return Whether the tree contains the element
     */
    public boolean contains(E element) {
        return contains(root, element);
    }
    
    /**
     * Returns if the subtree with node as root contains the element
     * @param node The root of the subtree
     * @param element The element to search for
     * @return Whether the tree contains the element.
     */
    private boolean contains(Node node, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        // Base cases
        if (node == null) {
            return false;
        }
        if (node.getData().equals(element)) {
            return true;
        }

        // Recursively search children for element
        for (Node child: node.getChildren()) {
            if (contains(child, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a heap-sorted ArrayList of the tree elements.
     * @return Sorted array of tree elements
     */
    public ArrayList<E> sortTree(){
        // Empty tree
        if (root == null) {
            return new ArrayList<>();
        }
        // Add nodes to priority queue
        Queue<E> priorityQueue = new PriorityQueue<>();
        for (Node n : levelOrderList()) {
            priorityQueue.add(n.getData());
        }

        // Remove elements from the priority queue to sorted Array
        ArrayList<E> sortedArray = new ArrayList<>();
        while(!priorityQueue.isEmpty()) {
            sortedArray.add(priorityQueue.remove());
        }

        return sortedArray;
    }
}
