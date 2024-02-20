package tn.esprit.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;

public class AjouterEventController {

    @FXML
    private Button ajouterbtn;

    @FXML
    private DatePicker dateEv;

    @FXML
    private TextField descEv;

    @FXML
    private TextField idEv;

    @FXML
    private TextField lieuEv;

    @FXML
    private TextField nomEv;
    private final ServiceEvenement SE = new ServiceEvenement();
    @FXML
    void OnClickAjouterEv(ActionEvent event) {
 SE.ajouter(new Evenement(nomEv.getText(),descEv.getText(), lieuEv.getText(),dateEv.getValue().atStartOfDay()));
    }


}
