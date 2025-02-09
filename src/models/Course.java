package models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import exceptions.RecordNotFoundException;

/**
 * Represents a Course with an ID, name, credit hours, instructor, and enrolled students.
 */
public class Course {
    private int courseID;
    private String courseName;
    private int credits;
    private Instructor assignedInstructor;  // Instructor assigned to the course
    private List<Student> enrolledStudents; // Students enrolled in the course

    public static final int NAME_SIZE = 50;
    public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + 4; // Fixed-size record

    public Course(int id, String name, int credits) {
        this.courseID = id;
        this.courseName = formatString(name, NAME_SIZE);
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
    }

    private String formatString(String str, int length) {
        return String.format("%-" + length + "s", str).substring(0, length);
    }

    /**
     * Assigns an instructor to this course.
     */
    public void assignInstructor(Instructor instructor) {
        this.assignedInstructor = instructor;
    }

    /**
     * Returns the assigned instructor.
     */
    public Instructor getInstructor() {
        return assignedInstructor;
    }

    /**
     * Enrolls a student in this course.
     * @throws RecordNotFoundException 
     */
    public void enrollStudent(Student student) throws RecordNotFoundException {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.addCourse(this); // Also update student's course list
        }
    }

    /**
     * Returns the list of students enrolled in this course.
     */
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Writes course data to a binary file.
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeInt(courseID);
        file.writeChars(courseName);
        file.writeInt(credits);
        // Write assigned instructor (if any)
    if (assignedInstructor != null) {
        file.writeInt(assignedInstructor.getInstructorID()); // Assuming Instructor has an ID
    } else {
        file.writeInt(-1); // Use -1 to indicate no instructor is assigned
    }

    // Write the number of enrolled students
    file.writeInt(enrolledStudents.size());

    // Write each enrolled student's ID (assuming Student has an ID)
    for (Student student : enrolledStudents) {
        file.writeInt(student.getStudentID());
    }
    }

    /**
     * Reads a Course record from a binary file.
     */
    public static Course readFromFile(RandomAccessFile file) throws IOException {
        int id = file.readInt();
        char[] nameChars = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) {
            nameChars[i] = file.readChar();
        }
        String name = new String(nameChars).trim();
        int credits = file.readInt();
        return new Course(id, name, credits);
    }

    public int getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Name: " + courseName.trim() + ", Credits: " + credits;
    }
}