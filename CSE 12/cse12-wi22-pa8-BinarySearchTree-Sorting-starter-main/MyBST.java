/**
 * Name: Merrick Qiu
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * An implementation of a binary search tree.
 * The left subtree will have keys smaller than the node, and
 * The right subtree will have keys greater than the node.
 */

import java.util.ArrayList;

/**
 * A binary search tree that takes in comparable key values and
 * returns the value associated with it.
 * The tree is not self balancing.
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * Return the size of the tree
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Inserts a key value pair
     * @param key The key to insert
     * @param value The value to insert
     * @return The replaced value or null if new node created
     */
    public V insert(K key, V value){
        // Handle Edge Cases
        if (key == null) {
            throw new NullPointerException();
        } else if (root == null) {
            root = new MyBSTNode<>(key, value, null);
            return null;
        }

        // Loop until element inserted
        size++;
        MyBSTNode<K, V> current = root;
        while(true) {
            K nodeKey = current.getKey();

            // Go to left subtree
            if (key.compareTo(nodeKey) < 0) {
                // Insert if subtree null
                if (current.getLeft() == null) {
                    current.setLeft(new MyBSTNode<>(key, value, current));
                    return null;
                }
                current = current.getLeft(); // Go to left subtree otherwise
            } 
            // Go to right subtree
            else if (key.compareTo(nodeKey) > 0) {
                // Insert if subtree null
                if (current.getRight() == null) {
                    current.setRight(new MyBSTNode<>(key, value, current));
                    return null;
                }
                current = current.getRight(); // Go to right subtree otherwise
            } 
            // Replace and return the old value
            else {
                V previousValue = current.getValue();
                current.setValue(value);
                return previousValue;
            } 
        }
    }

    /**
     * Searches for the value of the given key
     * @param key The key to search
     * @return The value associated with the key, or null if not found
     */
    public V search(K key){
        MyBSTNode<K, V> current = root; 
        while(true) {
            // Key not found
            if (current == null) {
                return null;
            }       

            // Search left or right subtree
            K nodeKey = current.getKey();
            if (key.compareTo(nodeKey) < 0) {
                current = current.getLeft();
            } 
            else if (key.compareTo(nodeKey) > 0) {
                current = current.getRight();
            }

            // Found value!
            else {
                return current.getValue();
            }
        }
    }

    /**
     * Removes the key-value pair
     * @param key The key to remove
     * @return The value of the removed node or null if nothing is removed.
     */
    public V remove(K key){
        MyBSTNode<K, V> current = root; 
        while(true) {
            // Key not found
            if (current == null) {
                return null;
            }       

            // Search left or right subtree
            K nodeKey = current.getKey();
            if (key.compareTo(nodeKey) < 0) {
                current = current.getLeft();
            } 
            else if (key.compareTo(nodeKey) > 0) {
                current = current.getRight();
            }

            // Found value!
            else {
                V previousValue = current.getValue();

                // Delete if leaf node
                if (current.getLeft() == null && current.getRight() == null) {
                    MyBSTNode<K, V> parent = current.getParent(); 
                    if (current == parent.getLeft()) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } 
                //Replace with successor if two children
                else if (current.getLeft() != null && 
                         current.getRight() != null) {
                    MyBSTNode<K, V> successor =  current.successor();
                    current.setKey(successor.getKey());
                    current.setValue(successor.getValue());

                    // Delete successor
                    MyBSTNode<K, V> succParent = successor.getParent(); 
                    if (successor == succParent.getLeft()) {
                        succParent.setLeft(null);
                    } else {
                        succParent.setRight(null);
                    }
                }
                // Move Child Up if one child
                else {
                    MyBSTNode<K, V> child =   current.getRight() == null 
                            ? current.getLeft() : current.getRight();

                    MyBSTNode<K, V> parent = current.getParent();
                    if (current == parent.getLeft()) {
                        parent.setLeft(child);
                    } else {
                        parent.setRight(child);
                    }
                }

                size--;
                return previousValue;
            }
        }
    }
    
    /**
     * Returns an inorder array of the BST nodes
     * @return The inorder array
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> array = new ArrayList<>();
        if (root == null) {
            return array;
        }

        // Find smallest node
        MyBSTNode<K, V> current = root;
        while(current.getLeft() != null) {current = current.getLeft(); }

        // Continuously add successors
        for (int i = 0; i < size; i++) {
            array.add(current);
            current = current.successor();
        }

        return array;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){  // If right node exists
                MyBSTNode<K,V> curr = this.getRight(); // The right subtree
                while(curr.getLeft() != null){ //while not smallest
                    curr = curr.getLeft(); // go left
                }
                return curr; // return the smallest node in the right subtree
            }
            else{
                MyBSTNode<K,V> parent = this.getParent(); // the parent
                MyBSTNode<K,V> curr = this; // current node
                // Find the "leftmost" ancestor
                // While not the "leftmost" ancestor...
                while(parent != null && curr == parent.getRight()){ 
                    curr = parent; // move curr up
                    parent = parent.getParent(); // move parent up
                }

                // The parent of the leftmost ancestor is the sucessor
                // For the largest node, this is the parent of the root,
                // which is null
                return parent;
            }
        }
        /**
         * The method returns the in order precessor of the current node.
         * The code is the same of successor but left and right are switched.
         * @return The predecessor node
         */
        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null){
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}