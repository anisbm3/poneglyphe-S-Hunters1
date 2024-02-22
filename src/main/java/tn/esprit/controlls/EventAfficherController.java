package tn.esprit.controlls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventAfficherController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    private final ServiceEvenement SE = new ServiceEvenement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshEvents();
    }

    // Method to refresh the events displayed in the ScrollPane
    private void refreshEvents() {
        // Retrieve data from the database
        List<Evenement> events = SE.afficher();

        // Create a VBox to hold all the event VBoxes
        VBox eventVBox = new VBox();
        eventVBox.setSpacing(10); // Add spacing between items

        // Iterate over the list of events
        for (Evenement event : events) {
            // Create a VBox for each line of data
            VBox lineVBox = new VBox();
            lineVBox.setSpacing(10); // Add spacing between items

            // Create Labels to represent each data element
            Label nameLabel = new Label(event.getNom_Event());
            Label presenterLabel = new Label(event.getDescription_Event());
            Label descriptionLabel = new Label(event.getLieu_Event());
            Label dateLabel = new Label(event.getDate_Event().toString());

            // Apply styling to the Labels
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            presenterLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            descriptionLabel.setFont(Font.font("Arial", 12));
            dateLabel.setFont(Font.font("Arial", 12));

            // Create a button for deletion
            Button deleteButton = new Button("Supprimer");
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // Supprimer l'événement de la base de données
                    SE.supprimer(event);
                    // Supprimer l'affichage de l'événement de l'interface utilisateur
                    eventVBox.getChildren().remove(lineVBox);
                }
            });

            // Create a button for modification
            Button modifyButton = new Button("Modifier");
            modifyButton.setOnAction((ActionEvent actionEvent) -> {
                Evenement selectedEvent = event; // Récupérer l'Evenement associé au bouton "Modifier"
                // Load the modification scene
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ModificationScene.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(loader.load());

                    // Get the controller of the modification scene
                    ModificationSceneController controller = loader.getController();

                    // Pass the Evenement object to the modification scene
                    controller.initData(selectedEvent);

                    // Show the modification scene
                    stage.setScene(scene);
                    stage.showAndWait();

                    // Refresh the events after modification
                    refreshEvents();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Add padding to each Label and the delete button
            nameLabel.setPadding(new Insets(5, 0, 0, 0));
            presenterLabel.setPadding(new Insets(0, 0, 0, 10));
            descriptionLabel.setPadding(new Insets(0, 0, 5, 10));
            dateLabel.setPadding(new Insets(0, 0, 5, 10));
            deleteButton.setPadding(new Insets(0, 0, 5, 10));
            modifyButton.setPadding(new Insets(0, 0, 5, 10));

            // Add Labels and buttons to the VBox
            lineVBox.getChildren().addAll(nameLabel, presenterLabel, dateLabel, descriptionLabel, deleteButton, modifyButton);

            // Add the VBox to the main VBox
            eventVBox.getChildren().add(lineVBox);
        }

        // Set the VBox containing all events as the content of the ScrollPane
        scrollPane.setContent(eventVBox);
    }

    // Method to handle refresh button action
    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        refreshEvents();
    }
}
