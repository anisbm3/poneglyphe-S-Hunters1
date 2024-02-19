/**
 *
 */
module tn.esprit.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens tn.esprit to javafx.fxml;

    exports tn.esprit.test;
    opens tn.esprit.test to javafx.fxml;
}