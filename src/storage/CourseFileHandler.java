package storage;

import models.Course;
import models.Instructor; // Import Instructor class
import exceptions.RecordNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseFileHandler {
  private static final String FILE_NAME = "src\\data\\courses.dat"; // Ensure the file is a binary .dat file

  /**
   * Adds a new course to the binary file.
   * @param course the course to be added
   * @throws IOException if file format is invalid or on other I/O errors
   */
  public static void addCourse(Course course) throws IOException, RecordNotFoundException {

    // Check if a course with the same ID already exists
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
        while (file.getFilePointer() < file.length()) {
            Course existingCourse = Course.readFromFile(file);
            if (existingCourse.getCourseID() == course.getCourseID()) {
                throw new RecordNotFoundException("Course with ID " + course.getCourseID() + " already exists.");
            }
        }
    }

    // If no duplicate is found, append the new course
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
        file.seek(file.length()); // Move to the end of the file to append
        course.writeToFile(file); // Write course details to the file
    }
  }

  /**
   * Retrieves a course by its ID from the binary file.
   * @param courseID the ID of the course
   * @return the course found
   * @throws IOException if file format is invalid or on other I/O errors
   * @throws RecordNotFoundException if the course is not found
   */
  public static Course getCourse(int courseID) throws IOException, RecordNotFoundException {
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
        while (file.getFilePointer() < file.length()) {
            Course course = Course.readFromFile(file); // Read a course from the file
            if (course.getCourseID() == courseID) {
                return course; // Return the course if it matches the ID
            }
        }
    }
    throw new RecordNotFoundException("Course with ID " + courseID + " not found.");
}

  /**
   * Updates a course in the binary file based on the course ID.
   * @param courseID the ID of the course to be updated
   * @param newName the new name of the course
   * @param newCredits the new credit value of the course
   * @param instructor the updated instructor
   * @throws IOException if file format is invalid or on other I/O errors
   * @throws RecordNotFoundException if the course is not found
   */
  public static void updateCourse(int courseID, String newName, int newCredits, Instructor instructor)
      throws IOException, RecordNotFoundException {
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
      while (file.getFilePointer() < file.length()) {
        long position = file.getFilePointer(); // Save the position to update later
        Course course = Course.readFromFile(file);
        if (course.getCourseID() == courseID) {
          file.seek(position); // Seek to the position to update
          newName = newName.length() > Course.NAME_SIZE ? newName.substring(0, Course.NAME_SIZE) : newName;
          course = new Course(courseID, newName, newCredits); // Create updated course
          course.assignInstructor(instructor); // Assign updated instructor
          course.writeToFile(file); // Write updated course to file
          return;
        }
      }
    }
    throw new RecordNotFoundException("Course with ID " + courseID + " not found.");
  }

  /**
   * Deletes a course by its ID from the binary file.
   * @param courseID the ID of the course to be deleted
   * @throws IOException if file format is invalid or on other I/O errors
   * @throws RecordNotFoundException if the course is not found
   */
  public static void deleteCourse(int courseID) throws IOException, RecordNotFoundException {
    File tempFile = new File("src\\data\\temp.dat");
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw")) {
      boolean found = false;
      while (file.getFilePointer() < file.length()) {
        Course course = Course.readFromFile(file);
        if (course.getCourseID() != courseID) {
          course.writeToFile(temp); // Copy non-matching course to temp file
        } else {
          found = true;
        }
      }
      if (!found)
        throw new RecordNotFoundException("Course ID not found.");
    }
    new File(FILE_NAME).delete(); // Delete the original file
    tempFile.renameTo(new File(FILE_NAME)); // Rename temp file to original file name
  }

  /**
   * Retrieves all courses from the binary file.
   * @return a list of all courses
   * @throws IOException if file format is invalid or on other I/O errors
   */
  public static List<Course> getAllCourses() throws IOException {
    List<Course> courses = new ArrayList<>();
    try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
      while (file.getFilePointer() < file.length()) {
        courses.add(Course.readFromFile(file)); // Read and add each course to the list
      }
    }
    return courses;
  }

  /**
     * Retrieves a course by its ID from the binary file.
     * @param courseID the ID of the course
     * @return the course found
     * @throws IOException if file format is invalid or on other I/O errors
     * @throws RecordNotFoundException if the course is not found
     */
    
}
