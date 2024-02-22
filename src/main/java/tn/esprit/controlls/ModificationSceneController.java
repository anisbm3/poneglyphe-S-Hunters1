package tn.esprit.controlls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificationSceneController implements Initializable {

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField lieuField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button saveButton;

    private Evenement event;
    private ServiceEvenement SE = new ServiceEvenement();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set event handler for the save button
        saveButton.setOnAction(event -> {
            // Implement logic to save modifications to the database
            // For example:
            if (this.event != null) { // Use "this.event" to refer to the instance variable
                this.event.setNom_Event(nomField.getText());
                this.event.setDescription_Event(descriptionField.getText());
                this.event.setLieu_Event(lieuField.getText());
                this.event.setDate_Event(dateField.getValue().atStartOfDay());

                SE.modifier(this.event);

            }
        });
    }

    // Method to initialize data of the event to be modified
    public void initData(Evenement event) {
        this.event = event;

        // Initialize modification scene with event data
        if (event != null) {
            nomField.setText(event.getNom_Event());
            descriptionField.setText(event.getDescription_Event());
            lieuField.setText(event.getLieu_Event());
            dateField.setValue(event.getDate_Event().toLocalDate());
        }
    }
}