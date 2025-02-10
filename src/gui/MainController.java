package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML
    private Button aTitle, drTitle, dsrTitle, extTitle, rrTitle, urTitle;
    
    private Stage mainWindow;

     private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
void btnAROnClick(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRecordView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error loading AddRecordView.fxml. Make sure the file is in the correct directory.");
    }
}

@FXML
    void btnRROnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayRecordView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading DisplayRecordView.fxml. Make sure the file is in the correct directory.");
        }
    }

    @FXML
    void btnDsROnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayAllRecordsView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading DisplayAllRecordsView.fxml. Make sure the file is in the correct directory.");
        }
    }

    @FXML
    void btnUROnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateRecordView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading UpdateRecordView.fxml. Make sure the file is in the correct directory.");
        }
    }


    @FXML
    void btnDROnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecordView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading DeleteRecordView.fxml. Make sure the file is in the correct directory.");
        }
    }

    

    @FXML
    void btnExtOnClick(ActionEvent event) {
        System.out.println("Exit Clicked");
        System.exit(0);
    }

    

    

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }
}
