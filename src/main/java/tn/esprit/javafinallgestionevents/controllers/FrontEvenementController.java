package tn.esprit.javafinallgestionevents.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontEvenementController implements Initializable {

    @FXML
    private VBox EventVbox;

    @FXML
    private ScrollPane Scrollpane;

    private Evenement selectedEvenement;
    private final ServiceEvenement SE = new ServiceEvenement();
    private final String imageDirectory = "C:/Users/Lenovo/Desktop/test/gestionevent/public/uploads/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshEvents() throws SQLException {
        List<Evenement> events = SE.afficher();
        EventVbox.getChildren().clear();

        for (Evenement evenement : events) {
            HBox eventBox = new HBox(); // Créer un HBox pour chaque événement
            eventBox.setSpacing(10); // Définir l'espacement entre les éléments à l'intérieur de l'HBox

            // Créer et configurer le label pour le contenu texte
            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_event() + "\n" +
                            "Description: " + evenement.getDescription_event() + "\n" +
                            "Lieu: " + evenement.getLieu_event() + "\n" +
                            "Date: " + evenement.getDate_event().toString()
            );
            eventLabel.setStyle("-fx-alignment: center; -fx-font-size: 14pt;");

            //eventLabel.setStyle(" centered-label, -fx-font-size: 14pt;");

            // Créer et configurer l'imageView pour l'image
            ImageView imageView = new ImageView();
            try {
                File imageFile = new File(imageDirectory + evenement.getImage());
                Image image = new Image(imageFile.toURI().toString());
                imageView.setImage(image);
                imageView.setFitWidth(100); // Définir la largeur souhaitée de l'image
                imageView.setPreserveRatio(true); // Préserver le ratio de l'image
            } catch (Exception e) {
                // Gérer l'erreur de chargement de l'image
                e.printStackTrace();
            }

            // Ajouter le label et l'imageView à l'HBox
            eventBox.getChildren().addAll(imageView, eventLabel);

            // Ajouter l'HBox contenant l'image et le texte à l'EventVbox
            EventVbox.getChildren().add(eventBox);

            // Ajouter un séparateur horizontal entre chaque événement
            Separator separator = new Separator(Orientation.HORIZONTAL);
            EventVbox.getChildren().add(separator);
        }
    }
}

/*import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontEvenementController implements Initializable {

    @FXML
    private VBox EventVbox;

    @FXML
    private ScrollPane Scrollpane;

    private Evenement selectedEvenement;
    private final ServiceEvenement SE = new ServiceEvenement();
    private final String imageDirectory = "C:/Users/Lenovo/Desktop/test/gestionevent/public/uploads/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
          //  EventVbox.getStylesheets().add(getClass().getResource("/tn/esprit/javafinallgestionevents/controllers/style.css").toExternalForm());

            refreshEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshEvents() throws SQLException {
        List<Evenement> events = SE.afficher();
        EventVbox.getChildren().clear();

        for (Evenement evenement : events) {
            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_event() +
                            ", Description: " + evenement.getDescription_event() +
                            ", Lieu: " + evenement.getLieu_event() +
                            ", Date: " + evenement.getDate_event().toString()
            );
            eventLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");

            //eventLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt; -fx-text-fill: black;");

            ImageView imageView = new ImageView();
            try {
                File imageFile = new File(imageDirectory + evenement.getImage());
                Image image = new Image(imageFile.toURI().toString());
                imageView.setImage(image);
                imageView.setFitWidth(20); // Définir la largeur souhaitée de l'image
                imageView.setPreserveRatio(true); // Préserver le ratio de l'image
            } catch (Exception e) {
                // Gérer l'erreur de chargement de l'image
                e.printStackTrace();
            }

            Separator separator = new Separator(Orientation.HORIZONTAL);
            separator.setStyle("-fx-background-color: black;");

            // Appliquer le style au VBox (EventVbox)
            EventVbox.setStyle("-fx-background-color: lightgrey; -fx-padding: 10px;");

            EventVbox.getChildren().addAll(eventLabel, imageView, separator);
        }
    }
}
*/

