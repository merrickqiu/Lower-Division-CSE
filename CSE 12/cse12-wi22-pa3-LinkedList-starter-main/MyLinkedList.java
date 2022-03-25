import java.util.AbstractList;

/** 
 * A class which inherits from abstractlist, 
 * and it implements a generic doubly-linked linkedlist.
 * The class stores both the head and tail nodes along with the size.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
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
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
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

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		// Instantiate null head and tail nodes that point to each other
		head = new Node(null);
		tail = new Node(null);
		head.setNext(tail);
		tail.setPrev(head);

		size = 0;
	}

	/**
	 * A getter for the size of the List
	 * @return The size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Gets the element at the index
	 * @param index The index to get the element from
	 * @return The element at index
	 */
	@Override
	public E get(int index) {
		return getNth(index).getElement();  
	}

	/**
	 * Adds the an element at the index
	 * @param index The index to add at
	 * @param data the data to add at the index
	 */
	@Override
	public void add(int index, E data) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
		if (data == null) {
			throw new NullPointerException();
		}
		
		Node oldNode;
		if (size == index) {
			oldNode = tail;
		}
		else {
			oldNode = getNth(index);
		}
		Node newNode = new Node(data);
		// Set new node pointers  
		newNode.setNext(oldNode);
		newNode.setPrev(oldNode.getPrev());
		// Then have other nodes point to new node
		oldNode.setPrev(newNode);
		newNode.getPrev().setNext(newNode);

		size++;
	}

	/**
	 * Adds the an element at the end of the array
	 * @param data the data to add 
	 * @return always returns true
	 */
	public boolean add(E data) {
		add(size, data);
		return true; 
	}
	/**
	 * Set the value at the index to data
	 * @param index the index to change
	 * @param data the new data
	 * @return the old data at index
	 */
	public E set(int index, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		Node currentNode = getNth(index);
		E oldData = currentNode.getElement(); // Remember old data
		currentNode.setElement(data);
		return oldData; 
	}

	/**
	 * Removes the value at the index
	 * @param index the index to remove
	 * @return the removed data
	 */
	public E remove(int index) {
		Node oldNode = getNth(index);
		// Change the pointers for the nodes next to the index
		oldNode.getPrev().setNext(oldNode.getNext());
		oldNode.getNext().setPrev(oldNode.getPrev());
		size--;
		return oldNode.getElement(); 
	}
	/**
	 * Changes the list to an empty list
	 */
	public void clear() {
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}

	/**
	 * Checks if the list is empty
	 * @return if the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;  
	}

	/**
	 * Returns the node at index
	 * @param index the index to get the node
	 * @return the node at index
	 */
	protected Node getNth(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} 
		Node currentNode = head.getNext();
		// go forward "index" times
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}
}