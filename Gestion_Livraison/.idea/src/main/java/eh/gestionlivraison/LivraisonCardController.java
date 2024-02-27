package eh.gestionlivraison;

import eh.gestionlivraison.models.Livraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LivraisonCardController {
    @FXML
    private Label labelNomPrenom;

    @FXML
    private Label labelAdresse;

    @FXML
    private Label labelProduits;

    @FXML
    private Label labelQuantity;

    @FXML
    private Label labelMontant;

    @FXML
    private Label labelDate;

    @FXML
    private FlowPane flowPaneLivraison;

    private Livraison selectedLivraison;

    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @FXML
    void onLivraisonSelected(MouseEvent event) {
        // Récupérer la carte de livraison cliquée
        AnchorPane livraisonCard = (AnchorPane) event.getSource();

        // Récupérer le contrôleur de la carte de livraison
        LivraisonCardController controller = (LivraisonCardController) livraisonCard.getProperties().get("controller");

        // Mettre à jour l'état de sélection de la carte
        controller.setSelected(!controller.isSelected());

        // Stocker la livraison sélectionnée dans la variable globale
        selectedLivraison = controller.getLivraison();

        // Mettre à jour l'apparence de la carte en fonction de l'état de sélection
        livraisonCard.setStyle(controller.isSelected() ? "-fx-background-color: lightblue;" : "");

        // Désélectionner les autres cartes
        for (Node node : flowPaneLivraison.getChildren()) {
            if (node instanceof AnchorPane && node != livraisonCard) {
                LivraisonCardController otherController = (LivraisonCardController) node.getProperties().get("controller");
                otherController.setSelected(false);
                node.setStyle("");
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation de LivraisonCardController...");
    }

    public void initialize(Livraison livraison) {
        if (livraison == null) {
            System.err.println("Erreur: instance de Livraison est nulle");
            return;
        }

        System.out.println("Initialisation de LivraisonCardController avec la livraison : " + livraison.getNomPrenomClient());
        // Vérifier si l'instance de Livraison est nulle
        System.out.println("Livraison instance: " + livraison);


        // Mise à jour de l'interface utilisateur avec les données de livraison
        labelNomPrenom.setText("NomPrenomClient: " + livraison.getNomPrenomClient());
        labelAdresse.setText("Adresse: " + livraison.getAdresse());
        labelProduits.setText("Produits: " + livraison.getProduits());
        labelQuantity.setText("Quantity: " + livraison.getQuantity());
        labelMontant.setText("Montant: " + livraison.getMontant());



        // Formatage de la date si elle n'est pas nulle
        if (livraison.getDate() != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = formatter.format(livraison.getDate()).toString();
                System.out.println(formattedDate);
                labelDate.setText("Date: " + formattedDate);
            } catch (Exception e) {
                System.err.println("Erreur lors du formatage de la date : " + e.getMessage());
                labelDate.setText("Date: Erreur de format");
            }
        } else {
            labelDate.setText("Date: N/A");
        }
    }

    public Livraison getLivraison() {
        return new Livraison();
    }
}
