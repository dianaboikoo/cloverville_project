package com.example.cloverville_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager{

    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchTo(String fxml) {
        try {
            String path = "/com/example/cloverville_project/" + fxml;
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(path));

            Scene scene = new Scene(loader.load());
            mainStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
