package tn.esprit.controlls;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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

    @FXML
    private Button triBtn;

    @FXML
    private Button reserverBtn;
    @FXML
    private VBox EventVBox;

    @FXML
    private TextField Descfield;
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField nomfield;
    @FXML
    private TextField lieufield;
    @FXML
    private Button suppbtn;

    @FXML
    private Button modfierbtn;
    private Evenement selectedEvenement;

        @FXML
        void onClicekdSupp(ActionEvent event) {
            if (selectedEvenement != null) {
                int res = selectedEvenement.getID_Event();
                //SE.supprimer(selectedEvenement.getID_Event());
                SE.supprimer(new Evenement(res, nomfield.getText(),Descfield.getText(), lieufield.getText() , dateField.getValue().atStartOfDay()));
                refreshEvents();
            }

        }

        @FXML
        void onClickedModifier(ActionEvent event) {

            if (selectedEvenement != null) {
                selectedEvenement.setNom_Event(nomfield.getText());

                selectedEvenement.setDescription_Event(Descfield.getText());
                selectedEvenement.setLieu_Event(lieufield.getText());

                selectedEvenement.setDate_Event(dateField.getValue().atStartOfDay());


                SE.modifier(selectedEvenement);
                refreshEvents();
            }



        }

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
        refreshEvents();
    }

   /* private void refreshEvents() {
        // Retrieve data from the database
        List<Evenement> events = SE.afficherbyNOM(tri);

        // Clear the existing content of EventHBox
        EventVBox.getChildren().clear();

        // Iterate over the list of events
        for (Evenement event : events) {
            // Create Labels to represent each data element
        Label EVENTLabel = new Label( "nom"  +event.getNom_Event()+
            "Description"+ event.getDescription_Event()+
           "LIEU"+ event.getLieu_Event()+
           "Date" +event.getDate_Event().toString()) ;


            EVENTLabel.setOnMouseClicked(event -> {
                selectedEvenement = event;

            });




            // Create a separator between events
            Separator separator = new Separator(Orientation.HORIZONTAL);

            // Add Labels and buttons to the EventHBox

        }

        // Set the EventHBox as the content of the ScrollPane
        scrollPane.setContent(EventVBox);
    }*/


    private void refreshEvents() {
        // Retrieve data from the database or service
        List<Evenement> events = SE.afficherbyNOM(tri);

        // Clear the existing content of EventVBox
        EventVBox.getChildren().clear();

        // Iterate over the list of events
        for (Evenement evenement : events) {
            // Create Labels to represent each event
            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_Event() +
                            ", Description: " + evenement.getDescription_Event() +
                            ", Lieu: " + evenement.getLieu_Event() +
                            ", Date: " + evenement.getDate_Event().toString()
            );

            // Apply styling to the Labels

            // Set action for the label to select the event
            eventLabel.setOnMouseClicked(event -> {
                selectedEvenement = evenement; // Assuming selectedEvent is a class-level variable
               nomfield.setText(selectedEvenement.getNom_Event());
                Descfield.setText(selectedEvenement.getDescription_Event());
               lieufield.setText(selectedEvenement.getLieu_Event());
               dateField.setValue(selectedEvenement.getDate_Event().toLocalDate());
            });

            // Create a separator between events
            Separator separator = new Separator(Orientation.HORIZONTAL);

            // Add Labels and separator to the EventVBox
            EventVBox.getChildren().addAll(eventLabel, separator);
        }
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


