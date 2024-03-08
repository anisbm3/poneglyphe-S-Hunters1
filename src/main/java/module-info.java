module tn.esprit.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client.json.gson; // Assurez-vous que cette ligne est correcte
   // requires com.fasterxml.jackson.core:jackson-databind;
    requires com.google.api.services.calendar;
    requires jdk.httpserver;
    requires com.google.api.client.json.jackson2;
    requires com.google.api.client;
    requires javafx.media;
    requires twilio;
    requires java.desktop;
    requires restfb;
    requires javafx.web;
    requires java.mail;
    requires poi;
    requires poi.ooxml;

    exports tn.esprit.test;
    exports tn.esprit.controlls;

    opens tn.esprit.controlls to javafx.fxml;
    opens tn.esprit.models to javafx.base;
}
