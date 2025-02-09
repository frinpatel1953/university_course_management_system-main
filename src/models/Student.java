package models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import exceptions.RecordNotFoundException;
import storage.CourseFileHandler;

/**
 * Represents a Student with an ID, name, email, and enrolled courses.
 * Supports binary file read/write operations.
 */
public class Student {
    private int studentID;
    private String name;
    private String email;
    private List<Course> enrolledCourses; // Courses the student is enrolled in

    public static final int NAME_SIZE = 50;
    public static final int EMAIL_SIZE = 50;
    public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + (2 * EMAIL_SIZE);

    public Student(int id, String name, String email) {
        this.studentID = id;
        this.name = formatString(name, NAME_SIZE);
        this.email = formatString(email, EMAIL_SIZE);
        this.enrolledCourses = new ArrayList<>();
    }

    private String formatString(String str, int length) {
        return String.format("%-" + length + "s", str).substring(0, length);
    }

    /**
     * Enrolls the student in a course.
     */
    public void addCourse(Course course) throws RecordNotFoundException {
        if (course == null) {
            throw new RecordNotFoundException("Course is null.");
        }
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    /**
     * Writes student data to a binary file.
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeInt(studentID);
        file.writeChars(name);
        file.writeChars(email);

        file.writeInt(enrolledCourses.size());
        for (Course course : enrolledCourses) {
            file.writeInt(course.getCourseID());
        }
    }

    /**
     * Reads a Student record from a binary file.
     */
    public static Student readFromFile(RandomAccessFile file) throws IOException {
        int id = file.readInt();
    
        char[] nameChars = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) nameChars[i] = file.readChar();
        String name = new String(nameChars).trim();
    
        char[] emailChars = new char[EMAIL_SIZE];
        for (int i = 0; i < EMAIL_SIZE; i++) emailChars[i] = file.readChar();
        String email = new String(emailChars).trim();
    
        Student student = new Student(id, name, email);
    
        // Read enrolled courses
        int numCourses = file.readInt();
        for (int i = 0; i < numCourses; i++) {
            int courseId = file.readInt();
            try {
                Course course = CourseFileHandler.getCourse(courseId);
                student.addCourse(course);
            } catch (RecordNotFoundException e) {
                // System.out.println("Warning: Course with ID " + courseId + " not found. Skipping.");
            }
        }
    
        return student;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name.trim();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentID)
          .append(", Name: ").append(name.trim())
          .append(", Email: ").append(email.trim())
          .append("\nEnrolled Courses:\n");
        if (enrolledCourses.isEmpty()) {
            sb.append("None");
        } else {
            for (Course course : enrolledCourses) {
                sb.append(course).append("\n");
            }
        }
        return sb.toString();
    }
}