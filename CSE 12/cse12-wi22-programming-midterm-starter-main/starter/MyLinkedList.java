/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * File description: 
 * A custom LinkedList implementation that is modified from my PA3 MyLinkedLIst.
 * The LinkedList has less methods.
 */

/**
 * TODO: Add class header
 * MyLinkedList stores generic elements in doubly linked nodes.
 * It implements the MyReverseList interface.
 */
public class MyLinkedList<E> implements MyReverseList<E>{

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes
     * This class is used for both MyLinkedList and MyListIterator.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            //Initialise the elements
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the previous node in the list
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //Set the node p on the previous position
            prev = p;
        }

        /** 
         * Set the next node in the list
         * @param n new next node
         */
        public void setNext(Node n) {
            //Set the node n on the next position
            next = n;
        }

        /** 
         * Set the element 
         * @param e new element 
         */
        public void setElement(E e) {
            this.data = e;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!
    /**
     * Constructor to create a doubly linked list 
     * with the argument array's elements
     * @param arr - array of elements to be used to construct the LinkedList
     */
    public MyLinkedList(E[] arr) {

        //Create dummy nodes
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;	

        if(arr != null){
            //create list by inserting each element
            Node currNode = head;
            for(int i=0; i<arr.length; i++){
                Node newNode = new Node(arr[i]);
                currNode.next.prev = newNode;
                newNode.next = currNode.next;
                newNode.prev = currNode;
                currNode.next = newNode;

                //move pointer to the next node
                currNode = currNode.next;
                //increase size of list
                this.size++;
            }
        }
    }


    /**
	 * TODO: Method header comment here
     * Reverses the order of the region from toIndex to fromIndex
     * @param fromIndex The inclusive index to begin reversing
     * @param toIndex The inclusive index to end reversing
	 */
    public void reverseRegion(int fromIndex, int toIndex){
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (fromIndex >= toIndex) {
            return;
        }
        
        Node firstNode = getNth(fromIndex);
        Node lastNode = getNth(toIndex);
        Node regionPreviousNode = firstNode.getPrev();
        Node regionNextNode = lastNode.getNext();

        Node currentNode = firstNode;
        for(int i = 0; i<toIndex-fromIndex+1; i++) {
            //Switch next and previous
            Node nextNode = currentNode.getNext();
            currentNode.setNext(currentNode.getPrev());
            currentNode.setPrev(nextNode);
            // Move to the next node, which is now the previous
            currentNode = currentNode.getPrev();
        }

        //Have the first and last nodes point correctly
        firstNode.setNext(regionNextNode);
        lastNode.setPrev(regionPreviousNode);
        // Have previous node and next node point correctly
        regionPreviousNode.setNext(lastNode);
        regionNextNode.getNext().setPrev(firstNode);
        
    }

    @Override
    /** 
     * Returns the number of elements stored
     * @return - number of elements in the linkedlist
    */
    public int size() {
        //Return the number of nodes in the linkedlist
        return this.size;
    }

    @Override
    /** 
     * Get contents at position i
     * @param index - The index position to obtain the data
     * @return the Element at the specified index
     */
    public E get(int index)	{

        Node currNode = this.getNth(index);

        //Get the value of data at the position
        E element = currNode.getElement();

        return element;	
    }


    /** A method that returns the node at a specified index,
     *  not the content
     *  @param index - position to return the node
     * @return - Node at 'index'
     */
    // Helper method to get the Node at the Nth index
    protected Node getNth(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();

        Node currNode = this.head;

        //Loop through the linked list and stop at the position
        for (int i = 0; i <= index; i++) {
            currNode = currNode.getNext();
        }

        //return the node	
        return currNode; 
    }

}
