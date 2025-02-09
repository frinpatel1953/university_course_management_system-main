package models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import exceptions.RecordNotFoundException;
import storage.CourseFileHandler;

/**
 * Represents an Instructor with an ID, name, email, and department.
 * Supports binary file read/write operations.
 */
public class Instructor {
  private int instructorID;
  private String name;
  private String email;
  private String department;
  private List<Course> assignedCourses;

  public static final int NAME_SIZE = 50;
  public static final int EMAIL_SIZE = 50;
  public static final int DEPT_SIZE = 50;
  public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + (2 * EMAIL_SIZE) + (2 * DEPT_SIZE);

  public Instructor(int id, String name, String email, String department) {
    this.instructorID = id;
    this.name = formatString(name, NAME_SIZE);
    this.email = formatString(email, EMAIL_SIZE);
    this.department = formatString(department, DEPT_SIZE);
    this.assignedCourses = new ArrayList<>();
  }

  private String formatString(String str, int length) {
    return String.format("%-" + length + "s", str).substring(0, length);
  }

    /**
     * Assigns the Instructor in a course.
     */
    public void addCourse(Course course) throws RecordNotFoundException {
        if (course == null) {
            throw new RecordNotFoundException("Course is null.");
        }
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

  /**
   * Writes instructor data to a binary file.
   */
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(instructorID);
    file.writeChars(name);
    file.writeChars(email);
    file.writeChars(department);

    file.writeInt(assignedCourses.size());
    for (Course course : assignedCourses) {
        file.writeInt(course.getCourseID());
    }
  }

  /**
   * Reads an Instructor record from a binary file.
   */
  public static Instructor readFromFile(RandomAccessFile file) throws IOException {
    int id = file.readInt();

    char[] nameChars = new char[NAME_SIZE];
    for (int i = 0; i < NAME_SIZE; i++)
      nameChars[i] = file.readChar();
    String name = new String(nameChars).trim();

    char[] emailChars = new char[EMAIL_SIZE];
    for (int i = 0; i < EMAIL_SIZE; i++)
      emailChars[i] = file.readChar();
    String email = new String(emailChars).trim();

    char[] deptChars = new char[DEPT_SIZE];
    for (int i = 0; i < DEPT_SIZE; i++)
      deptChars[i] = file.readChar();
    String department = new String(deptChars).trim();

    // return new Instructor(id, name, email, department);
    Instructor instructor = new Instructor(id, name, email, department);

    // Read enrolled courses
    int numCourses = file.readInt();
    for (int i = 0; i < numCourses; i++) {
        int courseId = file.readInt();
        try {
            Course course = CourseFileHandler.getCourse(courseId);
            instructor.addCourse(course);
        } catch (RecordNotFoundException e) {
            // Ignore if course is not found
        }
    }

    return instructor;
    }

  public int getInstructorID() {
    return instructorID;
  }

  public List<Course> getAssignedCourses() {
    return assignedCourses;
  }

  @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Instructor ID: ").append(instructorID)
      .append(", Name: ").append(name.trim())
      .append(", Email: ").append(email.trim())
      .append(", Department: ").append(department.trim())
      .append("\nAssigned Courses:\n");
    
    if (assignedCourses.isEmpty()) {
        sb.append("None");
    } else {
        for (Course course : assignedCourses) {
            sb.append(course).append("\n");
        }
    }
    return sb.toString();
}

public Object getName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
}

}
