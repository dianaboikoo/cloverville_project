package com.example.cloverville_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override public void start(Stage stage) throws IOException
    {
        ClovervilleFacade.getInstance().importAllData("cloverville_export.json");

        SceneManager.setStage(stage);
        SceneManager.switchTo("hello-view.fxml");
        stage.show();

    }
    public static void main(String[] args)
    {
        launch();
    }
}
