package tn.poneglyphe.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import tn.poneglyphe.Models.entities.Materiaux;

import java.net.URL;
import java.util.ResourceBundle;
public class ViewmateriauxController {

    @FXML
    private Label labelDispo;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelType;
    @FXML

private FlowPane flowPaneMat;
    private boolean selected =false;
    Materiaux materiaux;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @FXML
    void onMateriauxSelected(MouseEvent event) {
        // Récupérer la carte de matri cliquée
        AnchorPane Viewmateriaux = (AnchorPane) event.getSource();

        // Mettre à jour l'état de sélection de la carte
        setSelected(!isSelected());

        // Mettre à jour l'apparence de la carte en fonction de l'état de sélection
        Viewmateriaux.setStyle(isSelected() ? "-fx-background-color: lightblue;" : "");

        // Désélectionner les autres cartes
        for (int i = 0; i < flowPaneMat.getChildren().size(); i++) {
            AnchorPane node = (AnchorPane) flowPaneMat.getChildren().get(i);
            if (node !=  Viewmateriaux) {
                ViewmateriauxController otherController = ( ViewmateriauxController) node.getProperties().get("controller");
                otherController.setSelected(false);
                node.setStyle("");
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation de LivraisonCardController...");
    }

    public void initialize(Materiaux materiaux) {
        if (materiaux == null) {
            System.err.println("Erreur: instance de Mat est nulle");
            return;
        }

        this.materiaux = materiaux;

        labelNom.setText("NomMateriaux: " + materiaux.getNomMa());
        labelType.setText("TypeMat: " + materiaux.getTypeMa());
        labelDispo.setText("Disponiblité: " + materiaux.getDisponibilite());

    }

    public Materiaux getMateriaux() {
        return  materiaux;
    }
}
