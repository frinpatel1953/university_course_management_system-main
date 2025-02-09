package storage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import exceptions.RecordNotFoundException;
import models.Student;

public class StudentFileHandler {
    private static final String FILE_NAME = "src\\data\\students.dat";

    /**
     * Adds a student to the binary file.
     */
    public static void addStudent(Student student) throws IOException, RecordNotFoundException {
        File fileObj = new File(FILE_NAME);

        // If file doesn't exist or is empty, create it and write the student directly
        if (!fileObj.exists() || fileObj.length() == 0) {
            try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
                student.writeToFile(file);
            }
            return; // Exit after writing the first student
        }

        // Check if student already exists
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            while (file.getFilePointer() < file.length()) {
                Student existingStudent = Student.readFromFile(file);
                if (existingStudent.getStudentID() == student.getStudentID()) {
                    throw new RecordNotFoundException("Student with ID " + student.getStudentID() + " already exists.");
                }
            }
        }

        // Append new student if no duplicate is found
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length()); // Move to end of file
            student.writeToFile(file);
        }
    }

    /**
     * Reads all students from the file.
     */
    public static void readAllStudents() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            while (file.getFilePointer() < file.length()) {
                Student student = Student.readFromFile(file);
                System.out.println(student);
            }
        }
    }

    /**
     * Resets (deletes) the student file for testing purposes.
     */
    public static void resetFile() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists() && file.delete()) {
            System.out.println("File reset successfully.");
        }
    }

    /**
   * Retrieves a student by their ID from the binary file.
   * @param studentID the ID of the student to be retrieved
   * @return the student object found
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no student with the given ID is found
   */
  public static Student getStudent(int studentID) throws IOException, RecordNotFoundException {
    // if (!BinaryFileCheck.isBinaryFile(FILE_NAME)) {
    //   throw new IOException("Invalid file format. Only binary .dat files are allowed.");
    // }
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
      while (file.getFilePointer() < file.length()) {
        Student student = Student.readFromFile(file);
        if (student.getStudentID() == studentID) {
          return student; // Return student if ID matches
        }
      }
    }
    throw new RecordNotFoundException("Student with ID " + studentID + " not found.");
  }

  /**
   * Updates the details of a student.
   * @param studentID the ID of the student to be updated
   * @param newName the new name of the student
   * @param newEmail the new email of the student
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no student with the given ID is found
   */
  public static void updateStudent(int studentID, String newName, String newEmail)
      throws IOException, RecordNotFoundException {
    // if (!BinaryFileCheck.isBinaryFile(FILE_NAME)) {
    //   throw new IOException("Invalid file format. Only binary .dat files are allowed.");
    // }
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
      while (file.getFilePointer() < file.length()) {
        long position = file.getFilePointer();
        Student student = Student.readFromFile(file);
        if (student.getStudentID() == studentID) {
          file.seek(position); // Move to the position to overwrite
          newName = newName.length() > Student.NAME_SIZE ? newName.substring(0, Student.NAME_SIZE) : newName;
          newEmail = newEmail.length() > Student.EMAIL_SIZE ? newEmail.substring(0, Student.EMAIL_SIZE) : newEmail;
          student = new Student(studentID, newName, newEmail); // Create updated student
          student.writeToFile(file); // Write updated data to file
          return;
        }
      }
    }
    throw new RecordNotFoundException("Student with ID " + studentID + " not found.");
  }

  /**
   * Deletes a student record from the binary file.
   * @param studentID the ID of the student to be deleted
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no student with the given ID is found
   */
  public static void deleteStudent(int studentID) throws IOException, RecordNotFoundException {
    File tempFile = new File("src\\data\\temp_students.dat"); // Temporary file to hold valid records
    // if (!BinaryFileCheck.isBinaryFile(FILE_NAME)) {
    //   throw new IOException("Invalid file format. Only binary .dat files are allowed.");
    // }
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw")) {
      boolean found = false;
      while (file.getFilePointer() < file.length()) {
        Student student = Student.readFromFile(file);
        if (student.getStudentID() != studentID) {
          student.writeToFile(temp); // Copy valid records to temp file
        } else {
          found = true; // Mark as found if the record to be deleted is found
        }
      }
      if (!found)
        throw new RecordNotFoundException("Student ID not found.");
    }
    new File(FILE_NAME).delete(); // Delete the original file
    tempFile.renameTo(new File(FILE_NAME)); // Rename temp file to the original file name
  }

  /**
   * Displays all students present in the binary file.
   * @throws IOException if file format is invalid or other I/O errors
   */
  public static void displayAllStudents() throws IOException {
    // if (!BinaryFileCheck.isBinaryFile(FILE_NAME)) {
    //   throw new IOException("Invalid file format. Only binary .dat files are allowed.");
    // }
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
      System.out.println("\nAll Students:");
      while (file.getFilePointer() < file.length()) {
        Student student = Student.readFromFile(file);
        System.out.println(student); // Print student details
      }
    }
  }
}
