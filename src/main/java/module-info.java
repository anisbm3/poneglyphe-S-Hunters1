module tn.esprit.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports tn.esprit.test;
    exports tn.esprit.controlls;

    opens tn.esprit.controlls to javafx.fxml;
    opens tn.esprit.models to javafx.base;
}
