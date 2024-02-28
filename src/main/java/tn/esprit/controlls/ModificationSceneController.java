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

        saveButton.setOnAction(event -> {

            if (this.event != null) {
                this.event.setNom_Event(nomField.getText());
                this.event.setDescription_Event(descriptionField.getText());
                this.event.setLieu_Event(lieuField.getText());
                this.event.setDate_Event(dateField.getValue().atStartOfDay());

                SE.modifier(this.event);

            }
        });
    }


    public void initData(Evenement event) {
        this.event = event;


        if (event != null) {
            nomField.setText(event.getNom_Event());
            descriptionField.setText(event.getDescription_Event());
            lieuField.setText(event.getLieu_Event());
            dateField.setValue(event.getDate_Event().toLocalDate());
        }
    }
}