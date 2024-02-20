package tn.esprit.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;

import java.sql.SQLException;

public class ModifierEventController {

    @FXML
    private TextField IDevent;

    @FXML
    private DatePicker dateEvent;

    @FXML
    private TextField descEvent;

    @FXML
    private TextField lieuEvent;

    @FXML
    private Button modfierbtn;

    @FXML
    private TextField nomEvent;

   // ServiceProduit sp = new ServiceProduit();
    private final ServiceEvenement SE = new ServiceEvenement();
    @FXML
    void onModifierClicked(ActionEvent event) {

            int id = Integer.parseInt(IDevent.getText());
            Evenement evenement = new Evenement(id, nomEvent.getText(),descEvent.getText(),lieuEvent.getText(), dateEvent.getValue().atStartOfDay());
          //  Evenement evenement  = new Produit(id, .getText(), .getText(), Integer.parseInt(.getText()), IDDescription.getText());
            SE.modifier(evenement);


    }

}
