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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src\\gui\\views\\AddRecordView.fxml"));
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
    void btnDROnClick(ActionEvent event) {
        System.out.println("Delete a Record Clicked");
    }

    @FXML
    void btnDsROnClick(ActionEvent event) {
        System.out.println("Display All Records Clicked");
    }

    @FXML
    void btnExtOnClick(ActionEvent event) {
        System.out.println("Exit Clicked");
        System.exit(0);
    }

    @FXML
    void btnRROnClick(ActionEvent event) {
        System.out.println("Retrieve a Record Clicked");
    }

    @FXML
    void btnUROnClick(ActionEvent event) {
        System.out.println("Update a Record Clicked");
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }
}
