/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * A class that stores information for each student.
 * It stores the name and PID.
 */
import java.util.Objects;

/**
 * A comparable student class that stores the name and PID of the student.
 * It implements the hashCode function so that it can be added to a HashTable.
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Constructor for Student
     * @param firstName The first name of the student
     * @param lastName The last name of the student
     * @param PID The student ID number
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * Gets the last name
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the first name
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the student PID
     * @return The PID
     */
    public String getPID() {
        return PID;
    }
    
    /**
     * Checks if two students are equal
     * @param o The other student to be compared
     * @returns If the two students are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student s = (Student) o; // cast object so student methods can be called
        return this.firstName.equals(s.getFirstName()) && 
            this.lastName.equals(s.getLastName()) && 
            this.PID.equals(s.getPID());

    }

    /**
     * Calculates the hash using the firstname, lastname, and PID.
     * The field variables are put into Objects.hash()
     * @return The integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }
    /**
     * Compares two students alphanumerically in the order of last name,
     * firstname, then PID
     * @return A negative number if the current object is before, zero if equal, 
     *         and postive if the object is after.
     */
    @Override
    public int compareTo(Student o) {
        // Since comparason is alphanumeric, use the string compareTo method
        String thisString = lastName + firstName + PID;
        String thatString = o.getLastName() + o.getFirstName() + o.getPID();
        return thisString.compareTo(thatString);
    }
}
