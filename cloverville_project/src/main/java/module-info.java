module com.example.cloverville_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cloverville_project to javafx.fxml;
    exports com.example.cloverville_project;
}