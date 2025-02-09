package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            Parent root = fxmlLoader.load();
            
            MainController controller = fxmlLoader.getController(); // Corrected controller reference
            controller.setMainWindow(primaryStage); // Pass primaryStage if needed

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Course Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
