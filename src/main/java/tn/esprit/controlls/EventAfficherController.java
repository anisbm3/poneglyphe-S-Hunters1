package tn.esprit.controlls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static java.io.File.separator;

public class EventAfficherController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button triBtn;

    @FXML
    private Button reserverBtn;

    private String tri="ASC";
    private int i = 0;

    private final ServiceEvenement SE = new ServiceEvenement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshEvents();
        triBtn.setOnAction(this::OnClickedTri);
    }


    @FXML
    void OnClickedTri(ActionEvent event) {
if(i % 2 == 0){
    tri = "ASC";
}else{
    tri = "DESC";

}
        i++;
        // Rafraîchir l'affichage des événements triés
        refreshEvents();
    }
    // Method to refresh the events displayed in the ScrollPane

    private void refreshEvents() {
        // Retrieve data from the database
        List<Evenement> events = SE.afficherbyNOM(tri);
        System.out.println();


        // Create a VBox to hold all the event VBoxes
        VBox eventVBox = new VBox();
        eventVBox.setStyle("-fx-background-image: url('/tn/esprit/1.jpg'); -fx-background-size: cover;");
        eventVBox.setSpacing(10); // Add spacing between items C:\Users\Lenovo\Desktop

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
            nameLabel.setFont(Font.font("Cooper black", FontWeight.BOLD, 20));
            presenterLabel.setFont(Font.font("Kdam Thmor Pro", FontWeight.NORMAL, 15));
            descriptionLabel.setFont(Font.font("Kdam Thmor Pro", 15));
            dateLabel.setFont(Font.font("Kdam Thmor Pro", 15));

            // Create a button for deletion
            //Button deleteButton = new Button("Supprimer");
            Button deleteButton = new Button("Supprimer");
          deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Kdam Thmor Pro';  -fx-alignment: center;");

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
            //Button modifyButton = new Button("Modifier");
            Button modifyButton = new Button("Modifier");
           modifyButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Kdam Thmor Pro';");

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


            Separator separator = new Separator(Orientation.HORIZONTAL);
            separator.setStyle("-fx-background-color: #000000; -fx-margin: 2 0; -fx-max-width: 1000px;"); // Ajouter le style ici
            eventVBox.getChildren().addAll(separator);

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


    @FXML
    void onReserverClicked(ActionEvent event) {
     try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ajouterResevation.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        // Show the ajout scene
        stage.setScene(scene);
        stage.showAndWait();

        // Refresh the events after ajouter
        refreshEvents();

    } catch (IOException e) {
        e.printStackTrace();
    }


    }


}

