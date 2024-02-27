module workshopJDBC {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
    requires java.datatransfer;

    exports test;


        opens controllers to javafx.fxml;
        opens entities to javafx.base; }