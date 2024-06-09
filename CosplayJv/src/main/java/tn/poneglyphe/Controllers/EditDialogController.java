package tn.poneglyphe.Controllers;

import javafx.event.ActionEvent;
;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import tn.poneglyphe.Models.entities.Cosplay;
import javafx.stage.Stage;
import tn.poneglyphe.Services.CrudCosplay;

public class EditDialogController {
    @FXML
    private TextField captionField;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField nomField;

    @FXML
    private TextField personnageField;

    @FXML
    private TextField typematField;
    private Cosplay cosplay;
    private final CrudCosplay cs = new CrudCosplay();
    public void initData(Cosplay cosplay) {
        this.cosplay = cosplay;
        if (cosplay != null) {
            nomField.setText(cosplay.getNomCp()); // Populate the nom field
            captionField.setText(cosplay.getDescriptionCp()); // Populate the caption field
            personnageField.setText(cosplay.getPersonnage()); // Populate the personnage field
            typematField.setText(cosplay.getNomMa()); // Populate the typemat field
        }
    }

    // Method to retrieve the updated value of nom field
    public String getUpdatedNom() {
        return nomField.getText();
    }

    // Method to retrieve the updated value of caption field
    public String getUpdatedCaption() {
        return captionField.getText();
    }

    // Method to retrieve the updated value of personnage field
    public String getUpdatedPersonnage() {
        return personnageField.getText();
    }

    // Method to retrieve the updated value of typemat field
    public String getUpdatedTypemat() {
        return typematField.getText();
    }
    public void handleApplyAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
