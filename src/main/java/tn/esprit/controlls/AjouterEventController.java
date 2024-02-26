package tn.esprit.controlls;/*package tn.esprit.controlls;

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


}*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if (champsSontValides()) {
            SE.ajouter(new Evenement(nomEv.getText(), descEv.getText(), lieuEv.getText(), dateEv.getValue().atStartOfDay()));

            // Affichez une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Événement ajouté");
            alert.setHeaderText(null);
            alert.setContentText("L'événement a été ajouté avec succès!");
            alert.showAndWait();

            // Effacez les champs après l'ajout
            clearFields();
        } else {
            // Affichez une boîte de dialogue d'alerte pour informer l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }
    }

    // Vérifiez si les champs obligatoires sont vides
    private boolean champsSontValides() {
        return !nomEv.getText().isEmpty() && !descEv.getText().isEmpty() && !lieuEv.getText().isEmpty() && dateEv.getValue() != null;
    }

    // Méthode pour effacer les champs après l'ajout
    private void clearFields() {
        nomEv.clear();
        descEv.clear();
        lieuEv.clear();
        dateEv.setValue(null);
    }
}

