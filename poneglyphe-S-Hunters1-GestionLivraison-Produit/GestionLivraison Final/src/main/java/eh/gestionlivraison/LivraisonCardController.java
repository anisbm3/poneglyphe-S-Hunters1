package eh.gestionlivraison;

import eh.gestionlivraison.models.Livraison;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LivraisonCardController {

    @FXML
    private VBox V;
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

    private Livraison livraison;
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

        // Mettre à jour l'état de sélection de la carte
        setSelected(!isSelected());

        // Mettre à jour l'apparence de la carte en fonction de l'état de sélection
        livraisonCard.setStyle(isSelected() ? "-fx-background-color: lightblue;" : "");

        // Désélectionner les autres cartes
        for (int i = 0; i < flowPaneLivraison.getChildren().size(); i++) {
            AnchorPane node = (AnchorPane) flowPaneLivraison.getChildren().get(i);
            if (node != livraisonCard) {
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

        this.livraison = livraison;

        labelNomPrenom.setText("NomPrenomClient: " + livraison.getNomPrenomClient());
        labelAdresse.setText("Adresse: " + livraison.getAdresse());
        labelProduits.setText("Produits: " + livraison.getProd_name());
        labelQuantity.setText("Quantity: " + livraison.getQuantity());
        labelMontant.setText("Montant: " + livraison.getMontant());

        if (livraison.getDate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(livraison.getDate());
            labelDate.setText("Date: " + formattedDate);
        } else {
            labelDate.setText("Date: N/A");
        }
    }

    public Livraison getLivraison() {
        return livraison;
    }
}
