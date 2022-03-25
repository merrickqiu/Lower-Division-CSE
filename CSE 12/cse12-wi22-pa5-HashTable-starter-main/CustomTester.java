/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * JUnit custom tester for student, course, and sanctuary classes.
 * Tests are different from the public tester
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 * 
 * Custom tester that uses JUnit.
 * Tests edge cases not tested in the public tester.
 */
public class CustomTester {

    // ----------------Student class----------------
    /**
     * Test the equals method when two students are not the same
     */
    @Test
    public void testEquals() {
        Student student1 = new Student("Merrick", "Qiu", "A16839273");
        Student student2 = new Student("Hudson", "Ellis", "A12345678");
        assertFalse(student1.equals(student2));
        assertFalse(student1.equals("not a student object"));
        assertFalse(student1.equals(null));
    }
    

    /**
     * Test the compareTo method when two students are the same reference,
     * or when two students are not the same
     */
    @Test
    public void testCompareTo() {
        Student student1 = new Student("Merrick", "Qiu", "A16839273");
        Student student2 = new Student("Hudson", "Ellis", "A12345678");
        assertTrue(student1.compareTo(student2) > 0);
        assertTrue(student2.compareTo(student1) < 0);
        assertEquals(0, student1.compareTo(student1));
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when student is already in class or 
     * when class is at capacity
     */
    @Test
    public void testEnroll() {
        Student student1 = new Student("Merrick", "Qiu", "A16839273");
        Student student2 = new Student("Hudson", "Ellis", "A12345678");
        Student student3 = new Student("David", "Kim", "A66645678");
        Course math = new Course("MATH", "31BH", "Unmatched Quality", 2);
        assertTrue(math.enroll(student1));
        assertFalse(math.enroll(student1));
        assertTrue(math.enroll(student2));
        assertFalse(math.enroll(student3));

        assertTrue(math.getStudents().contains(student1));
        assertTrue(math.getStudents().contains(student2));
        assertFalse(math.getStudents().contains(student3));    
    }

    /**
     * Test the unenroll method when unenrolling from an empty class
     */
    @Test
    public void testUnenroll() {
        Student student1 = new Student("Merrick", "Qiu", "A16839273");
        Course math = new Course("MATH", "31BH", "Unmatched Quality", 2);
        assertFalse(math.unenroll(student1));
        assertFalse(math.getStudents().contains(student1));
    }

    /**
     * Test the getRoster method when the class is partially filled
     */
    @Test
    public void testGetRoster() {
        Student student1 = new Student("Merrick", "Qiu", "A16839273");
        Student student2 = new Student("Hudson", "Ellis", "A12345678");
        Student student3 = new Student("David", "Kim", "A66645678");
        Course math = new Course("MATH", "31BH", "Unmatched Quality", 7);
        math.enroll(student1);
        math.enroll(student2);
        math.enroll(student3);

        ArrayList<Student> roster = math.getRoster();
        assertEquals(3, roster.size());
        assertEquals(student2, roster.get(0));
        assertEquals(student3, roster.get(1));
        assertEquals(student1, roster.get(2));
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when maxAnimals < 0
     */
    @Test
    public void testSanctuaryConstructor() {
        try {
            new Sanctuary(-1, 3);
            fail();
        } catch (IllegalArgumentException e) {
            // Threw right exception
        }
    }

    /**
     * Test the rescue method when adding over the animal limit.
     * This is for a new species
     */
    @Test
    public void testRescueTestOne(){
        Sanctuary sanc = new Sanctuary(10, 2);
        assertEquals(20, sanc.rescue("zebra", 30));
        assertEquals(10, sanc.getNum("zebra"));
    }

    /**
     * Test the rescue method when adding over the animal limit.
     * This is for a old species
     */
    @Test
    public void testRescueTestTwo(){
        Sanctuary sanc = new Sanctuary(10, 2);
        assertEquals(0, sanc.rescue("zebra", 3));
        assertEquals(23, sanc.rescue("zebra", 30));
        assertEquals(10, sanc.getNum("zebra"));
    }

    /**
     * Test the rescue method when adding over the species limit.
     */
    @Test
    public void testRescueTestThree(){
        Sanctuary sanc = new Sanctuary(10, 3);
        assertEquals(0, sanc.rescue("zebra", 3));
        assertEquals(0, sanc.rescue("lion", 3));
        assertEquals(0, sanc.rescue("tiger", 3));
        assertEquals(3, sanc.rescue("rhino", 3));

        assertEquals(3, sanc.getNum("zebra"));
        assertEquals(3, sanc.getNum("lion"));
        assertEquals(3, sanc.getNum("tiger"));
        assertEquals(0, sanc.getNum("rhino"));
    }

    /**
     * Test the release method when releasing all the animals
     */
    @Test
    public void testReleaseTestOne(){
        Sanctuary sanc = new Sanctuary(10, 3);
        sanc.rescue("zebra", 3);
        sanc.release("zebra", 3);
        assertEquals(0, sanc.getTotalAnimals());
    }

    /**
     * Test the release method when releasing more animals than exist
     */
    @Test
    public void testReleaseTestTwo(){
        Sanctuary sanc = new Sanctuary(10, 3);
        sanc.rescue("zebra", 3);
        try {
            sanc.release("zebra", 4);
            fail();
        } catch (IllegalArgumentException e) {
            // threw right exception
        }
    }
}

