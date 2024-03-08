
package tn.esprit.controlls;

import tn.esprit.services.ServiceLivraison;
import tn.esprit.models.Livraison;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LivreurController implements Initializable {

    @FXML
    private Button Statistique1;
    private FXMLLoader livreurCardLoader;
    @FXML
    private FlowPane flowPaneLivreur;
    private Livraison selectedLivraison;
    ServiceLivraison sl = new ServiceLivraison();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Chargement des données de livraison...");
        loadLivreurData();
    }
    private List<Livraison> getLivraisons() {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        return serviceLivraison.getAll();
    }
    private void loadLivreurData() {
        List<Livraison> livraisons = sl.getAll();

        flowPaneLivreur.getChildren().clear();
        for (Livraison livraison : livraisons) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/LivraisonCard.fxml"));
                AnchorPane card = loader.load();
                LivraisonCardController controller = loader.getController();
                controller.initialize(livraison); // Initialise la carte avec les données de la livraison
                card.setUserData(livraison); // Définit l'objet Livraison comme userData de la carte
                card.getProperties().put("controller", controller); // Définit le contrôleur comme propriété de la carte
                flowPaneLivreur.getChildren().add(card);

                // Mettre en place l'événement de sélection de la carte de livraison
                card.setOnMouseClicked(event -> {
                  /*  onLivraisonSelected(event);
                    updateLivraisonCards(); // Mettre à jour l'apparence des cartes de livraison*/
                });
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des données de livraison.");
                e.printStackTrace();
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
