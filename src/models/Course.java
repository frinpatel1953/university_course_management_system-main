package models;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Represents a Course with an ID, name, and credit hours.
 * Supports binary file read/write operations.
 */
public class Course {
  private int courseID;
  private String courseName;
  private int credits;

  public static final int NAME_SIZE = 50;
  public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + 4; // Fixed-size record

  public Course(int id, String name, int credits) {
    this.courseID = id;
    this.courseName = formatString(name, NAME_SIZE);
    this.credits = credits;
  }

  private String formatString(String str, int length) {
    return String.format("%-" + length + "s", str).substring(0, length);
  }

  /**
   * Writes course data to a binary file.
   */
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(courseID);
    file.writeChars(courseName);
    file.writeInt(credits);
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
