module tn.esprit.javafinallgestionevents {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens tn.esprit.javafinallgestionevents.controllers to javafx.fxml;
    opens tn.esprit.javafinallgestionevents to javafx.fxml;
    //exports tn.esprit.javafinallgestionevents;
    exports tn.esprit.javafinallgestionevents.controllers;
    exports tn.esprit.javafinallgestionevents.test;
    opens tn.esprit.javafinallgestionevents.test to javafx.fxml;
}