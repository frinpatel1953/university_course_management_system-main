package models;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Represents a Student with an ID, name, and email.
 * Supports binary file read/write operations.
 */
public class Student {
  private int studentID;
  private String name;
  private String email;

  public static final int NAME_SIZE = 50;
  public static final int EMAIL_SIZE = 50;
  public static final int RECORD_SIZE = 4 + (2 * NAME_SIZE) + (2 * EMAIL_SIZE);

  public Student(int id, String name, String email) {
    this.studentID = id;
    this.name = formatString(name, NAME_SIZE);
    this.email = formatString(email, EMAIL_SIZE);
  }

  private String formatString(String str, int length) {
    return String.format("%-" + length + "s", str).substring(0, length);
  }

  /**
   * Writes student data to a binary file.
   */
  public void writeToFile(RandomAccessFile file) throws IOException {
    file.writeInt(studentID);
    file.writeChars(name);
    file.writeChars(email);
  }

  /**
   * Reads a Student record from a binary file.
   */
  public static Student readFromFile(RandomAccessFile file) throws IOException {
    int id = file.readInt();
    char[] nameChars = new char[NAME_SIZE];
    for (int i = 0; i < NAME_SIZE; i++)
      nameChars[i] = file.readChar();
    String name = new String(nameChars).trim();

    char[] emailChars = new char[EMAIL_SIZE];
    for (int i = 0; i < EMAIL_SIZE; i++)
      emailChars[i] = file.readChar();
    String email = new String(emailChars).trim();

    return new Student(id, name, email);
  }

  public int getStudentID() {
    return studentID;
  }

  @Override
  public String toString() {
    return "Student ID: " + studentID + ", Name: " + name.trim() + ", Email: " + email.trim();
  }
}
