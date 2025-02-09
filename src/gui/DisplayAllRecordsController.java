package gui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DisplayAllRecordsController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnBackToMainMenu;

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnInstructor;

    @FXML
    private Button btnStudent;

    @FXML
    void btnBackToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnCourseOnClick(ActionEvent event) {

    }

    @FXML
    void btnInstructorOnClick(ActionEvent event) {

    }

    @FXML
    void btnStudentOnClick(ActionEvent event) {

    }

}
