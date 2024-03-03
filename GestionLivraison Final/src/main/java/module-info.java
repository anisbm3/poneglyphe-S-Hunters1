module eh.gestionlivraison {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires java.sql;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires com.google.zxing.javase; // Ajouter cette ligne pour lire le module com.google.zxing.javase

    opens eh.gestionlivraison to javafx.fxml;
    exports eh.gestionlivraison;
    exports eh.gestionlivraison.test;
    opens eh.gestionlivraison.test to javafx.fxml;
}
