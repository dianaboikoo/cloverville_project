package com.example.cloverville_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchTo(String fxml) {
        try {
            String path = "/com/example/cloverville_project/" + fxml;

            // Check if resource exists
            if (SceneManager.class.getResource(path) == null) {
                System.err.println("FXML NOT FOUND: " + path);
                return;
            }

            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(path));
            Parent root = loader.load();

            mainStage.setScene(new Scene(root));

        } catch (Exception e) {
            System.err.println("Error loading FXML: " + fxml);
            e.printStackTrace();
        }
    }
}
