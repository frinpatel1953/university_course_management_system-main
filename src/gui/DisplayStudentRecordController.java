package gui;

import java.io.IOException;

import exceptions.RecordNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Student;
import storage.StudentFileHandler;

public class DisplayStudentRecordController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button btnDisplay;

    @FXML
    private Label courseLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label idLable;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField txtStudentID;
    @FXML
    void btnBackToRetriveMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DisplayRecordView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
void btnDisplay(ActionEvent event) {
    // Get the student ID entered in the TextField
    String studentIdText = txtStudentID.getText();

    // Validate that the input is not empty
    if (studentIdText.isEmpty()) {
        idLable.setText("Please enter a student ID.");
        return;
    }

    try {
        // Convert the input to an integer (student ID)
        int studentId = Integer.parseInt(studentIdText);

        // Call the method to get the student record by ID
        Student student = StudentFileHandler.getStudent(studentId);

        // Display the student details in the labels
        idLable.setText("ID: " + student.getStudentID());
        nameLabel.setText("Name: " + student.getName());
        courseLabel.setText("Course: " + student.getCourse());
        emailLabel.setText("Email: " + student.getEmail());

    } catch (NumberFormatException e) {
        // Handle case where the entered ID is not a valid number
        idLable.setText("Invalid student ID.");
    } catch (RecordNotFoundException e) {
        // Handle case where the student is not found
        idLable.setText("Student not found.");
        nameLabel.setText("");
        courseLabel.setText("");
        emailLabel.setText("");
    } catch (IOException e) {
        // Handle any I/O errors
        idLable.setText("Error reading student records.");
    }
}


}
