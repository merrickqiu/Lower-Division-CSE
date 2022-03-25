/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * A class that stores information for each course.
 * It stores the capacity, department, course number, and description.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * A Course class that stores the information about a course.
 * It uses a HashSet to store the students enrolled in it.
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * The constructor for the class. 
     * Throws an exception if any arguments are null.
     * @param department The course department
     * @param number The course number
     * @param description The course description
     * @param capacity The max capacity of the course.
     */
    public Course(String department, String number, String description, 
        int capacity){
            if (department == null || number == null || description == null 
                || capacity < 1) {
                    throw new IllegalArgumentException();
                }
            enrolled = new HashSet<>();
            this.capacity = capacity;
            this.department = department;
            this.number = number;
            this.description = description;
    }

    /**
     * Gets the department
     * @return The course department
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Gets the course number
     * @return The course number
     */
    public String getNumber(){
        return number;
    }

    /**
     * Gets the course description
     * @return The course description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Gets the course capacity
     * @return The course capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Enrolls the student in the current class
     * @param student The student in the current class
     * @return Whether the enrollement was successful
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (getEnrolledCount() < capacity && !enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        }
        return false;
    }

    /**
     * Unenrolls the student
     * @param student The student to be enrolled
     * @return If the unenrollment was successful
     */
    public boolean unenroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * Removes all students from the class
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Checks if the class if full
     * @return Whether the class is full
     */
    public boolean isFull() {
        return getEnrolledCount() == capacity;
    }

    /**
     * Gets the number of students enrolled
     * @return the number of students enrolled
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Gets the number of open seats
     * @return The available seats
     */
    public int getAvailableSeats() {
        return capacity - getEnrolledCount();
    }

    /**
     * Returns a shallow copy of the set of students enrolled
     * @return The set of students enrolled
     */
    public HashSet<Student> getStudents() {
        return enrolled;
    }

    /**
     * Returns an array of the students enrolled
     * @return The array of students enrolled
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> roster = new ArrayList<>();
        for (Student student: enrolled) {
            roster.add(student);
        }
        Collections.sort(roster);
        return roster;
    }

    /**
     * Returns a string with the information about the course
     * @return the course information
     */
    public String toString() {
        return String.format("%s %s [%d]\n%s", department, number, capacity, 
            description);
    }
}

