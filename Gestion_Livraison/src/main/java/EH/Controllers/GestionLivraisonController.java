
package EH.Controllers;

import EH.models.Livraison;
import EH.services.ServiceLivraison;
import com.sun.javafx.logging.PlatformLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionLivraisonController {

    @FXML
    private Label Iid__client;

    @FXML
    private Button Statistique1;

    @FXML
    private TextField TFSearch;

    @FXML
    private TextField TfMontant;

    @FXML
    private TextField TfQuantity;

    @FXML
    private Button btn_delete1;

    @FXML
    private Button btn_delete2;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_refresh;

    @FXML
    private ComboBox<?> cb_Client;

    @FXML
    private ComboBox<?> cb_Produit;

    @FXML
    private DatePicker date;

    @FXML
    private Label id_pannier;

    @FXML
    private AnchorPane left_main;

    @FXML
    private Label montant;
    @FXML
    private HBox LivraisonCard;

    @FXML
    private Label quantity;

    @FXML
    private ScrollPane scroll;
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    void DeleteAllLivraison(ActionEvent event) {

    }

    @FXML
    void DeleteSelectedLivraison(ActionEvent event) {
    }

    @FXML
    void EditLivraison(ActionEvent event) {

    }

    @FXML
    void RefreshLivraison(ActionEvent event) {

    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Livraison livraison = LivraisonTab.getSelectionModel().getSelectedItem();
        if (livraison != null) {
            id=livraison.getID_Livraison();
            cb_Produit.setValue(Livraison.getID_Produit());
            cb_Client.setValue(livraison.getID_Client());
            TfQuantity.setText(Integer.toString(livraison.getQuantity()));
            TfMontant.setText(Float.toString(livraison.getMontant()));
            date.setValue(livraison.getDate().toLocalDate());
        }
    }



}
