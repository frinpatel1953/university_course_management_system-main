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


public class DisplayRecordController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button btnCourse;

    @FXML
    private Button btnInstructor;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnStudentOnClick;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayStudentRecordView.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading DisplayStudentRecordView.fxml. Make sure the file is in the correct directory.");
        }

    }

}

