package models;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Represents an Instructor with an ID, name, email, and department.
 * Supports binary file read/write operations.
 */
public class Instructor {
  private int instructorID;
  private String name;
  private String email;
  private String department;

  public static final int NAME_SIZE = 50;
  public static final int EMAIL_SIZE = 50;
  public static final int DEPT_SIZE = 50;
  public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + (2 * EMAIL_SIZE) + (2 * DEPT_SIZE);

  public Instructor(int id, String name, String email, String department) {
    this.instructorID = id;
    this.name = formatString(name, NAME_SIZE);
    this.email = formatString(email, EMAIL_SIZE);
    this.department = formatString(department, DEPT_SIZE);
  }

  private String formatString(String str, int length) {
    return String.format("%-" + length + "s", str).substring(0, length);
  }

  /**
   * Writes instructor data to a binary file.
   */
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(instructorID);
    file.writeChars(name);
    file.writeChars(email);
    file.writeChars(department);
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

    return new Instructor(id, name, email, department);
  }

  public int getInstructorID() {
    return instructorID;
  }

  @Override
  public String toString() {
    return "Instructor ID: " + instructorID + ", Name: " + name.trim() +
        ", Email: " + email.trim() + ", Department: " + department.trim();
  }
}
