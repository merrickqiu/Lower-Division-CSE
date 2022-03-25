import java.util.Arrays;

/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * File description: 
 * A custom ArrayList implementation that is modified from my PA2 MyArrayList.
 * The ArrayList has less methods.
 */

/**
 * TODO: Add class header
 * The class MyArrayList stores generic elements in an object array.
 * It implements the MyReverseList interface.
 */

 
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
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
        Object[] newData = Arrays.copyOf(data, data.length);

        // Reverse the region in newData
        for (int i = 0; i < toIndex-fromIndex+1; i++) {
            newData[fromIndex+i] = data[toIndex-i]; 
        }
        data = newData;
    }
    

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
