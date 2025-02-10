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
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class AddStudentController {
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
    private ToggleButton enrollToggleGroup;

    @FXML
    private Label lblCourseID;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbYes;

    @FXML
    private TextField txtCourseID;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentID;

    @FXML
    private TextField txtStudentName;

    @FXML
    void btnBackToAddRecordMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onEnrollSelection(ActionEvent event) {
        boolean isEnrolled = rbYes.isSelected();
        txtCourseID.setVisible(isEnrolled);
        lblCourseID.setVisible(isEnrolled);

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddRecordView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    void btnSubmit(ActionEvent event) {
        String studentID = txtStudentID.getText().trim();
        String studentName = txtStudentName.getText().trim();
        String studentEmail = txtStudentEmail.getText().trim();
        boolean isEnrolled = rbYes.isSelected();
        String courseID = txtCourseID.getText().trim();

        if (studentID.isEmpty() || studentName.isEmpty() || studentEmail.isEmpty()) {
            System.out.println("Please fill in all student details.");
            return;
        }

        if (isEnrolled && courseID.isEmpty()) {
            System.out.println("Please enter a Course ID.");
            return;
        }

        if (isEnrolled) {
            System.out.println("Student Registered Successfully & Enrolled in Course ID: " + courseID);
        } else {
            System.out.println("Student Registered Successfully!");
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


}
