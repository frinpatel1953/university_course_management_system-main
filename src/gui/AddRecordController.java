package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AddRecordController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void btnStudentOnClick(ActionEvent event) {
        System.out.println("Add Student Record Clicked");
        // Load Student Record Form
    }

    @FXML
    void btnInstructorOnClick(ActionEvent event) {
        System.out.println("Add Instructor Record Clicked");
        // Load Instructor Record Form
    }

    @FXML
    void btnCourseOnClick(ActionEvent event) {
        System.out.println("Add Course Record Clicked");
        // Load Course Record Form
    }

    @FXML
    void btnBackToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
