module eh.gestionlivraison {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens eh.gestionlivraison to javafx.fxml;
    exports eh.gestionlivraison.test;
    opens eh.gestionlivraison.test to javafx.fxml;
}
