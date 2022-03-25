import static org.junit.Assert.*;
import org.junit.*;


/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded 
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	private MyLinkedList<Integer> emptyIntegerList;
	private MyLinkedList<Integer> tenIntegerList;

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		emptyIntegerList = new MyLinkedList<Integer>();
		tenIntegerList = new MyLinkedList<Integer>();
		for (int i=0; i<10; i++) {
			tenIntegerList.add(i);
		}
	}

	/**
	 * TODO: test the add method when adding a null element
	 */
	@Test
	public void testAdd() {
		try {
			emptyIntegerList.add(null);
			fail();
		} catch (NullPointerException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the add with index method when index > size
	 */
	@Test
	public void testAddWithIndexTestOne() {
		int newElement = 10;
		int outOfBoundsIndex = 11;
		try {
			emptyIntegerList.add(outOfBoundsIndex, newElement);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the add with index method when index < 0
	 */	
	@Test
	public void testAddWithIndexTestTwo() {
		int newElement = 10;
		try {
			emptyIntegerList.add(-1, newElement);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the get method when index < 0
	 */
	@Test
	public void testGet() {
		try {
			emptyIntegerList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the getNth method when index < 0
	 */
	@Test
	public void testGetNth() {
		try {
			emptyIntegerList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the set method when index < 0
	 */
	@Test
	public void testSet() {
		int newElement = 10;
		try {
			emptyIntegerList.set(-1, newElement);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the remove method when element is in middle
	 */
	@Test
	public void testRemoveTestOne() {
		int middleIndex = 5;
		int removedElement = tenIntegerList.remove(middleIndex);
		assertEquals("Check correct removed element", 5, removedElement);
		assertEquals("Check correct element before",
			Integer.valueOf(4), tenIntegerList.get(4));
		assertEquals("Check correct element replacement",
			Integer.valueOf(6), tenIntegerList.get(5));
	}
	
	/**
	 * TODO: test the remove method when index < -1
	 */
	@Test
	public void testRemoveTestTwo() {
		try {
			emptyIntegerList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Threw correct exception
		}
	}

	/**
	 * TODO: test the clear method when list is already empty
	 */
	@Test
	public void testClear() {
		emptyIntegerList.clear();
		assertEquals("Check correct size", 0, emptyIntegerList.size());
		assertEquals("Check list is empty", true, emptyIntegerList.isEmpty());
	}

	/**
	 * TODO: test the size method when list is empty
	 */
	@Test
	public void testSize() {
		assertEquals("Check correct size", 0, emptyIntegerList.size());
	}
}