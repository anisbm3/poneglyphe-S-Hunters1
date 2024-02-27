package eh.gestionlivraison;
import eh.gestionlivraison.Services.ServiceFacture;
import eh.gestionlivraison.models.Facture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterFactureController {

    @FXML
    private Button Acceuil;

    @FXML
    private TextField NomProduit;

    @FXML
    private HBox chosenhotelCard;

    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane left_main;

    @FXML
    private TextField montant;

    @FXML
    private TextField remise;

    @FXML
    private ScrollPane scroll;

    @FXML
    void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GestionFacture.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregister(ActionEvent event) {
        String erreurs = "";
        if (NomProduit.getText().trim().isEmpty()) {
            erreurs += "Nom vide\n";
        }
        if (remise.getText().trim().isEmpty()) {
            erreurs += "remise vide\n";
        } else if (!remise.getText().matches("\\d+")) {
            erreurs += "remise doit être un nombre entier\n";
        }
        if (montant.getText().trim().isEmpty()) {
            erreurs += "Montant vide\n";
        } else if (!montant.getText().matches("\\d+(\\.\\d+)?")) {
            erreurs += "Montant doit être un nombre\n";
        }
        if (date.getValue() == null) {
            erreurs += "date vide\n";
        } else if (date.getValue().isBefore(LocalDate.now())) {
            erreurs += "date doit être après la date actuelle\n";
        }

        if (erreurs.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Facture");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else {
            try {
                ServiceFacture sf = new ServiceFacture();
                Facture facture = new Facture(NomProduit.getText(),
                        Integer.parseInt(remise.getText()),
                        Float.parseFloat(montant.getText()),
                        date.getValue().atStartOfDay());
                sf.add(facture);
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Facture ajoutée avec succès !");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir des valeurs numériques valides pour la remise et le montant.");
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

