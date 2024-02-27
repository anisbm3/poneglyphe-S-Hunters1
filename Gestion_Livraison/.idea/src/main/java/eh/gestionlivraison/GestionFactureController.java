/*package eh.gestionlivraison;
import  eh.gestionlivraison.FactureCardController;
import eh.gestionlivraison.Services.ServiceFacture;
import eh.gestionlivraison.models.Facture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionFactureController {

    @FXML
    private TextField NomPrField;

    @FXML
    private Button Statistique1;

    @FXML
    private TextField TFSearch;

    @FXML
    private TextField TfMontantField;

    @FXML
    private TextField TfQuantityField;

    @FXML
    private Button brtn_DeleteAll;

    @FXML
    private Button btn_DeleteSelected;

    @FXML
    private Button btn_Edit;

    @FXML
    private Button btn_Refresh;

    @FXML
    private DatePicker dateField;

    @FXML
    private FlowPane flowPaneFacture;

    @FXML
    private AnchorPane left_main;

    @FXML
    private HBox recherche_avance;
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void DeleteAllLivraison(ActionEvent event) {

    }

    @FXML
    void DeleteLivraisonSelected(ActionEvent event) {

    }

    @FXML
    void EditLivraison(ActionEvent event) {

    }
    private void loadFactureData() {
        List<Facture> factures = sf.getAll();

        flowPaneFacture.getChildren().clear();
        for (Facture facture : factures) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureCard.fxml"));
                AnchorPane card = loader.load();
                LivraisonCardController controller = loader.getController();
                controller.initialize(facture);
                card.setUserData(facture); // Ajouter la livraison comme UserData pour la carte
                card.getProperties().put("controller", controller); // Définir le contrôleur comme propriété de la carte
                flowPaneFacture.getChildren().add(card);

                // Mettre en place l'événement de sélection de la carte de livraison
                card.setOnMouseClicked(event -> {
                    onFactureSelected(event);
                    updateFactureCards(); // Mettre à jour l'apparence des cartes de livraison
                });
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des données de facture.");
                e.printStackTrace();
            }
        }
    }


    @FXML
    void RefrecheListe(ActionEvent event) {
        loadFactureData();
    }


    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void recherche_avance(ActionEvent event) {

    }

}*/
