module eh.gestionlivraison {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires java.sql;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires com.google.zxing.javase;
    requires stripe.java;
    requires org.controlsfx.controls; // Ajouter cette ligne pour lire le module com.google.zxing.javase

    opens eh.gestionlivraison to javafx.fxml;
    exports eh.gestionlivraison;
    exports eh.gestionlivraison.test;
    opens eh.gestionlivraison.test to javafx.fxml;
    opens eh.gestionlivraison.models to javafx.base;

}
