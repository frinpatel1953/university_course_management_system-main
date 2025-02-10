package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCourseController {


    @FXML
    private Button btnBackToMainMenu;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtCourseCredits;

    @FXML
    private TextField txtCourseID;

    @FXML
    private TextField txtCourseName;

    // Submit button logic: Validate inputs and add the course
    @FXML
    void btnSubmit(ActionEvent event) throws IOException {
        String courseID = txtCourseID.getText().trim();
        String courseName = txtCourseName.getText().trim();
        String courseCredits = txtCourseCredits.getText().trim();

        // Validation
        if (courseID.isEmpty() || courseName.isEmpty() || courseCredits.isEmpty()) {
            System.out.println("Please fill in all course details.");
            return;
        }

        try {
            int credits = Integer.parseInt(courseCredits);
            if (credits <= 0) {
                System.out.println("Course credits must be a positive number.");
                return;
            }

            System.out.println("Course Added Successfully: ID=" + courseID + ", Name=" + courseName + ", Credits=" + credits);

            // Redirect back to Add Record screen
            navigateToScreen(event, "AddRecordView.fxml");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for course credits.");
        }
    }

    // Cancel button logic: Return to Add Record screen
    @FXML
    void btnCancel(ActionEvent event) {
        try {
            navigateToScreen(event, "AddRecordView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Back to Main Menu button logic
    @FXML
    void btnBackToMainMenu(ActionEvent event) {
        try {
            navigateToScreen(event, "MainView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle screen navigation
    private void navigateToScreen(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
