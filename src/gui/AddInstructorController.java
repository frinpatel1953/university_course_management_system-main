package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddInstructorController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnBackToMainMenu;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label lblCourseID;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbYes;

    @FXML
    private TextField txtCourseID;

    @FXML
    private TextField txtInstructorDept;

    @FXML
    private TextField txtInstructorEmail;

    @FXML
    private TextField txtInstructorID;

    @FXML
    private TextField txtInstructorName;

    // Show or hide the Course ID field based on selection
    @FXML
    void onAssignSelection(ActionEvent event) {
        boolean isAssigning = rbYes.isSelected();
        lblCourseID.setVisible(isAssigning);
        txtCourseID.setVisible(isAssigning);
    }

    // Submit logic with validation
    @FXML
    void btnSubmit(ActionEvent event) {
        String instructorID = txtInstructorID.getText().trim();
        String instructorName = txtInstructorName.getText().trim();
        String instructorEmail = txtInstructorEmail.getText().trim();
        String instructorDept = txtInstructorDept.getText().trim();
        boolean isAssigning = rbYes.isSelected();
        String courseID = txtCourseID.getText().trim();

        // Validation
        if (instructorID.isEmpty() || instructorName.isEmpty() || instructorEmail.isEmpty() || instructorDept.isEmpty()) {
            System.out.println("Please fill in all instructor details.");
            return;
        }

        if (isAssigning && courseID.isEmpty()) {
            System.out.println("Please enter a Course ID.");
            return;
        }

        // Success message
        if (isAssigning) {
            System.out.println("Instructor Registered Successfully & Assigned to Course ID: " + courseID);
        } else {
            System.out.println("Instructor Registered Successfully!");
        }

        // Redirect back to Add Record screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddRecordView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cancel and return to Add Record screen
    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddRecordView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Back to Main Menu
    @FXML
    void btnBackToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
