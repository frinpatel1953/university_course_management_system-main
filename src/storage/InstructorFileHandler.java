package storage;

import models.Instructor;
import models.Course;
import exceptions.RecordNotFoundException;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class InstructorFileHandler {
  private static final String FILE_NAME = "src\\data\\instructors.dat"; // Path to the binary file
  private static Scanner scanner = new Scanner(System.in);

   /**
   * Adds a new instructor record to the binary file.
   * @param instructor the instructor to be added
   * @throws IOException if file format is invalid or other I/O errors
   */
  public static void addInstructor(Instructor instructor) throws IOException, RecordNotFoundException {
    // Check if an instructor with the same ID already exists
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
        while (file.getFilePointer() < file.length()) {
            Instructor existingInstructor = Instructor.readFromFile(file);
            if (existingInstructor.getInstructorID() == instructor.getInstructorID()) {
                throw new RecordNotFoundException("Instructor with ID " + instructor.getInstructorID() + " already exists.");
            }
        }
    }

    // Ask if the user wants to assign a course
    System.out.print("Do you want to assign a course to this instructor? (y/n): ");
    String response = scanner.nextLine();

    if (response.equalsIgnoreCase("y")) {
        // Loop until user says no
        while (true) {
            System.out.print("Enter Course ID to assign: ");
            int courseId = Integer.parseInt(scanner.nextLine());

            try {
                // Assume CourseFileHandler has a method to get course by ID
                Course course = CourseFileHandler.getCourse(courseId);
                instructor.addCourse(course);
                System.out.println("Course assigned successfully!");

                // Ask if the user wants to assign another course
                System.out.print("Do you want to assign another course? (y/n): ");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    break;
                }
            } catch (RecordNotFoundException e) {
                System.out.println("Course with ID " + courseId + " not found.");
            }
        }
    }

    // Append the new instructor to the file
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
        file.seek(file.length()); // Move to the end of the file for appending
        instructor.writeToFile(file); // Write instructor data to the file
    }
  }

  /**
   * Retrieves an instructor by their ID from the binary file.
   * @param instructorID the ID of the instructor to be retrieved
   * @return the instructor object found
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no instructor with the given ID is found
   */
  public static Instructor getInstructor(int instructorID) throws IOException, RecordNotFoundException {
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
      while (file.getFilePointer() < file.length()) {
        Instructor instructor = Instructor.readFromFile(file);
        if (instructor.getInstructorID() == instructorID) {
          return instructor; // Return instructor if ID matches
        }
      }
    }
    throw new RecordNotFoundException("Instructor with ID " + instructorID + " not found.");
  }

  /**
   * Updates the details of an instructor.
   * @param instructorID the ID of the instructor to be updated
   * @param newName the new name of the instructor
   * @param newEmail the new email of the instructor
   * @param newDepartment the new department of the instructor
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no instructor with the given ID is found
   */
  public static void updateInstructor(int instructorID, String newName, String newEmail, String newDepartment)
      throws IOException, RecordNotFoundException {
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
      while (file.getFilePointer() < file.length()) {
        long position = file.getFilePointer();
        Instructor instructor = Instructor.readFromFile(file);
        if (instructor.getInstructorID() == instructorID) {
          // Preserve assigned courses
          List<Course> assignedCourses = instructor.getAssignedCourses();

          file.seek(position); // Move to the position to overwrite
          newName = newName.length() > Instructor.NAME_SIZE ? newName.substring(0, Instructor.NAME_SIZE) : newName;
          newEmail = newEmail.length() > Instructor.EMAIL_SIZE ? newEmail.substring(0, Instructor.EMAIL_SIZE) : newEmail;
          newDepartment = newDepartment.length() > Instructor.DEPT_SIZE ? newDepartment.substring(0, Instructor.DEPT_SIZE) : newDepartment;

          instructor = new Instructor(instructorID, newName, newEmail, newDepartment); // Create updated instructor
          for (Course course : assignedCourses) {
              instructor.addCourse(course);
          }
          instructor.writeToFile(file); // Write updated data to file
          return;
        }
      }
    }
    throw new RecordNotFoundException("Instructor with ID " + instructorID + " not found.");
  }

  /**
   * Deletes an instructor record from the binary file.
   * @param instructorID the ID of the instructor to be deleted
   * @throws IOException if file format is invalid or other I/O errors
   * @throws RecordNotFoundException if no instructor with the given ID is found
   */
  public static void deleteInstructor(int instructorID) throws IOException, RecordNotFoundException {
    File tempFile = new File("src\\data\\temp_instructors.dat"); // Temporary file to hold valid records
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw")) {
      boolean found = false;
      while (file.getFilePointer() < file.length()) {
        Instructor instructor = Instructor.readFromFile(file);
        if (instructor.getInstructorID() != instructorID) {
          instructor.writeToFile(temp); // Copy valid records to temp file
        } else {
          found = true; // Mark as found if the record to be deleted is found
        }
      }
      if (!found)
        throw new RecordNotFoundException("Instructor ID not found.");
    }
    new File(FILE_NAME).delete(); // Delete the original file
    tempFile.renameTo(new File(FILE_NAME)); // Rename temp file to the original file name
  }

  /**
   * Displays all instructors present in the binary file.
   * @throws IOException if file format is invalid or other I/O errors
   */
  public static void displayAllInstructors() throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
      System.out.println("\nAll Instructors:");
      while (file.getFilePointer() < file.length()) {
        Instructor instructor = Instructor.readFromFile(file);
        System.out.println(instructor); // Print instructor details
      }
    }
  }
}
