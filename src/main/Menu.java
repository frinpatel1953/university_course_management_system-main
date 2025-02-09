package main;

import models.Course;
import models.Instructor;
import models.Student;
import storage.CourseFileHandler;
import storage.InstructorFileHandler;
import storage.StudentFileHandler;
import exceptions.RecordNotFoundException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
  private static final Scanner scanner = new Scanner(System.in);

  // Main method that runs the menu loop
  public static void main(String[] args) {
    while (true) {
      // Display the main menu
      System.out.println("\n--- University Course Management System ---");
      System.out.println("1. Add a new record");
      System.out.println("2. Retrieve a record by ID");
      System.out.println("3. Display all records");
      System.out.println("4. Update a record");
      System.out.println("5. Delete a record");
      System.out.println("6. Exit");
      System.out.print("Enter your choice: ");

      // Get the user's choice
      int choice = getIntInput();
      switch (choice) {
        case 1 -> addRecord();          // Handle adding a new record
        case 2 -> retrieveRecord();     // Handle retrieving a record by ID
        case 3 -> displayAllRecords();  // Handle displaying all records
        case 4 -> updateRecord();       // Handle updating a record
        case 5 -> deleteRecord();       //Handle deleteing a record
        case 6 -> {                    // Exit the program
          System.out.println("Exiting program...");
          scanner.close();
          return;
        }
        default -> System.out.println("Invalid choice! Please try again.");
      }
    }
  }

  // Method to add a new record based on user input (Course, Instructor, or Student)
  private static void addRecord() {
    System.out.println("\nAdd Record to");
    System.out.println("1: Course");
    System.out.println("2: Instructor");
    System.out.println("3: Student");
    System.out.println("4: Return to Main Menu");
    System.out.print("Enter your choice: ");
    int type = getIntInput();  // Get the record type from the user

    try {
      // Switch based on record type
      switch (type) {
        case 1 -> {
          // Get Course details from the user and add the record
          int id = getIntInput("Enter Course ID: ");
          String name = getStringInput("Enter Course Name: ");
          int credits = getIntInput("Enter Credits: ");
          CourseFileHandler.addCourse(new Course(id, name, credits));
          System.out.println("Course added successfully.");
        }
        case 2 -> {
          // Get Instructor details from the user and add the record
          int id = getIntInput("Enter Instructor ID: ");
          String name = getStringInput("Enter Name: ");
          String email = getStringInput("Enter Email: ");
          String dept = getStringInput("Enter Department: ");
          InstructorFileHandler.addInstructor(new Instructor(id, name, email, dept));
          System.out.println("Instructor added successfully.");
        }
        case 3 -> {
          // Get Student details from the user and add the record
          int id = getIntInput("Enter Student ID: ");
          String name = getStringInput("Enter Name: ");
          String email = getStringInput("Enter Email: ");
          Student student = new Student(id, name, email);
          
          // Add courses to the student if necessary (can be extended)
          while (true) {
            System.out.print("Do you want to enroll in a course? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) break;
            int courseId = getIntInput("Enter Course ID to enroll in: ");
            // Retrieve course from the file and add it to the student (assumes CourseFileHandler has getCourse)
            try {
              Course course = CourseFileHandler.getCourse(courseId);
              student.addCourse(course);
              System.out.println("Course added successfully.");
            } catch (RecordNotFoundException e) {
              System.out.println("Course not found.");
            }
          }
          
          StudentFileHandler.addStudent(student);
          System.out.println("Student added successfully.");
        }
        case 4 -> {
          System.out.println("Returning to the main menu...");
          return;
        }
        default -> System.out.println("Invalid type!");  // Handle invalid input
      }
    } catch (Exception e) {
      // Handle any errors that occur during the addition of records
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  // Method to retrieve a record by its ID based on user input (Course, Instructor, or Student)
  private static void retrieveRecord() {
    System.out.println("\nRetrieve Record from:");
    System.out.println("1: Course");
    System.out.println("2: Instructor");
    System.out.println("3: Student");
    System.out.println("4: Return to Main Menu");
    System.out.print("Enter your choice: ");
    int type = getIntInput();  // Get the record type from the user
    int id = getIntInput("Enter ID: ");  // Get the record ID from the user

    try {
      // Switch based on record type to retrieve the corresponding record
      switch (type) {
        case 1 -> System.out.println(CourseFileHandler.getCourse(id));
        case 2 -> System.out.println(InstructorFileHandler.getInstructor(id));
        case 3 -> System.out.println(StudentFileHandler.getStudent(id));
        default -> System.out.println("Invalid type!");  // Handle invalid input
      }
    } catch (Exception e) {
      // Handle any errors that occur during record retrieval
      System.out.println("Error to get a record:" + e.getMessage());
    }
  }

  // Method to display all records of a selected type (Course, Instructor, or Student)
  private static void displayAllRecords() {
    System.out.println("\nDisplay Records of:");
    System.out.println("1: Course");
    System.out.println("2: Instructor");
    System.out.println("3: Student");
    System.out.println("4: Return to Main Menu");
    System.out.print("Enter your choice: ");
    int type = getIntInput();  // Get the record type from the user

    if(type == 4){
      System.out.println("Returning to the main menu...");
          return;
    }

    try {
      // Switch based on record type to display all records
      switch (type) {
        case 1 -> CourseFileHandler.getAllCourses().forEach(System.out::println);
        case 2 -> InstructorFileHandler.displayAllInstructors();
        case 3 -> StudentFileHandler.displayAllStudents();
        case 4 -> {
          System.out.println("Returning to the main menu...");
          return;
        }
        default -> System.out.println("Invalid type!");  // Handle invalid input
      }
    } catch (IOException e) {
      // Handle any errors that occur during data retrieval
      System.out.println("Error reading data: " + e.getMessage());
    }
  }

  // Method to update an existing record by ID and new values
  private static void updateRecord() {
    System.out.println("\nUpdate Record to:");
    System.out.println("1: Course");
    System.out.println("2: Instructor");
    System.out.println("3: Student");
    System.out.println("4: Return to Main Menu");
    System.out.print("Enter your choice: ");
    int type = getIntInput();  // Get the record type from the user
    if(type == 4){
      System.out.println("Returning to the main menu...");
          return;
    }
    int id = getIntInput("Enter ID: ");  // Get the record ID from the user
   


    try {
      // Switch based on record type to update the corresponding record
      switch (type) {
        case 1 -> {
          // Get new Course details from the user and update the record
          String name = getStringInput("Enter new Course Name: ");
          int credits = getIntInput("Enter new Credits: ");
          CourseFileHandler.updateCourse(id, name, credits, null);
          System.out.println("Course updated successfully.");
        }
        case 2 -> {
          // Get new Instructor details from the user and update the record
          String name = getStringInput("Enter new Name: ");
          String email = getStringInput("Enter new Email: ");
          String dept = getStringInput("Enter new Department: ");
          InstructorFileHandler.updateInstructor(id, name, email, dept);
          System.out.println("Instructor updated successfully.");
        }
        case 3 -> {
          // Get new Student details from the user and update the record
          String name = getStringInput("Enter new Name: ");
          String email = getStringInput("Enter new Email: ");
          StudentFileHandler.updateStudent(id, name, email);
          System.out.println("Student updated successfully.");
        }
        case 4 -> {
          System.out.println("Returning to the main menu...");
          return;
        }
        default -> System.out.println("Invalid type!");  // Handle invalid input
      }
    } catch (Exception e) {
      // Handle any errors that occur during record update
      System.out.println("Error: " + e.getMessage());
    }
  }

  // Method to delete an existing record by ID
  private static void deleteRecord() {
    System.out.println("\nDelete Record of:");
    System.out.println("1: Course");
    System.out.println("2: Instructor");
    System.out.println("3: Student");
    System.out.println("4: Return to Main Menu");
    System.out.print("Enter your choice: ");
    int type = getIntInput();  // Get the record type from the user
    if(type == 4){
      System.out.println("Returning to the main menu...");
          return;
    }
    int id = getIntInput("Enter ID: ");  // Get the record ID from the user
    


    try {
      // Switch based on record type to delete the corresponding record
      switch (type) {
        case 1 -> CourseFileHandler.deleteCourse(id);
        case 2 -> InstructorFileHandler.deleteInstructor(id);
        case 3 -> StudentFileHandler.deleteStudent(id);
        case 4 -> {
          System.out.println("Returning to the main menu...");
          return;
        }
        default -> System.out.println("Invalid type!");  // Handle invalid input
      }
      System.out.println("Record deleted successfully.");
    } catch (IOException | RecordNotFoundException e) {
      // Handle any errors that occur during record deletion
      System.out.println("Error:" + e.getMessage());
    }
  }

  // Helper method to safely get an integer input
  private static int getIntInput() {
    while (true) {
      try {
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
      } catch (InputMismatchException e) {
        System.out.print("Invalid input!\nPlease enter a number input mentioned above: ");
        scanner.nextLine(); // Clear invalid input
      }
    }
  }

  // Helper method to get an integer input with a message
  private static int getIntInput(String message) {
    System.out.print(message);
    return getIntInput();
  }

  // Helper method to get a string input with a message
  private static String getStringInput(String message) {
    System.out.print(message);
    return scanner.nextLine();
  }
}
