package eh.gestionlivraison;

import eh.gestionlivraison.models.Facture;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FactureCardController {

    @FXML
    private VBox V1;

    @FXML
    private AnchorPane V2;

    @FXML
    private Label labelAdresse;

    @FXML
    private Label labelDate_Facture;

    @FXML
    private Label labelDate_Livraison;

    @FXML
    private Label labelMontantSansRemise;

    @FXML
    private Label labelMontantavecremise;

    @FXML
    private Label labelNomPrenimClient;


    @FXML
    private Label labelRemise;

    @FXML
    private FlowPane flowPaneFacture;

    private Facture facture;
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @FXML
    public void onFactureSelected(MouseEvent event) {
        // Récupérer la carte de livraison cliquée
        AnchorPane FactureCard = (AnchorPane) event.getSource();

        // Mettre à jour l'état de sélection de la carte
        setSelected(!isSelected());

        // Mettre à jour l'apparence de la carte en fonction de l'état de sélection
        FactureCard.setStyle(isSelected() ? "-fx-background-color: lightblue;" : "");

        // Désélectionner les autres cartes
        for (int i = 0; i < flowPaneFacture.getChildren().size(); i++) {
            AnchorPane node = (AnchorPane) flowPaneFacture.getChildren().get(i);
            if (node != FactureCard ){
                FactureCardController otherController = (FactureCardController)node.getProperties().get("controller");
                otherController.setSelected(false);
                node.setStyle("");
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation de FactureCardController...");
    }

    public void initialize(Facture facture) {
        if (facture == null) {
            System.err.println("Erreur: instance de Facture est nulle");
            return;
        }

        this.facture = facture;
        labelDate_Facture.setText("Date_Facture:" + facture.getDateFacture());
        labelNomPrenimClient.setText("NomPrenomClient: " + facture.getNomPrenomClient());
        labelAdresse.setText("Adresse: " + facture.getAdresse());
        labelDate_Livraison.setText("Date_Livraison:" + facture.getDate());
        labelMontantSansRemise.setText("Montant sans remise: " + facture.getMontant());
        labelRemise.setText("Remise: " + facture.getRemise());
        labelMontantavecremise.setText("Montant avec remise: " + facture.getMontantAvecRemise());
    }

    public Facture getFacture() {
        return facture;
    }
}
