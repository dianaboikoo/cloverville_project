module com.example.cloverville_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.cloverville_project to javafx.fxml;
    exports com.example.cloverville_project;
}