
package EH.Controllers;

import EH.models.Facture;
import EH.models.Livraison;
import EH.services.ServiceFacture;
import EH.services.ServiceLivraison;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterFactureController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Livraison> LivraisonTab;
    @FXML
    private TableColumn<Livraison, Integer> IDLivraisonTab;
    @FXML
    private TableColumn<Livraison, Integer> ProduitTab;
    @FXML
    private TableColumn<Livraison, Integer> ClientTab;
    @FXML
    private TableColumn<Livraison, Integer> QuantityTab;
    @FXML
    private TableColumn<Livraison, Float> MontantTab;
    @FXML
    private TableColumn<Livraison, LocalDateTime> DateTab;
    @FXML
    private DatePicker date;
    @FXML
    private TextField TfRemise;

    ServiceLivraison sl = new ServiceLivraison();
    ServiceFacture sf = new ServiceFacture();
    int id;
    Livraison l;
    ObservableList<Livraison> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Livraison livraison = LivraisonTab.getSelectionModel().getSelectedItem();
        id = livraison.getID_Livraison();
    }

    @FXML
    private void AjouterFacture(ActionEvent event) throws SQLException {
        String erreurs = "";
        if (TfRemise.getText().trim().isEmpty()) {
            erreurs += "Remise vide\n";
        }
        if (date.getValue() != null && date.getValue().isBefore(LocalDate.now())) {
            erreurs += "date must be after\n";
        }
        if (date.getValue() == null) {
            erreurs += "date vide\n";
        }

        if (erreurs.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else {
            Facture facture = new Facture(id, date.getValue().atStartOfDay(), Integer.parseInt(TfRemise.getText()));
            sf.AjouterFacture(id, facture);
        }
    }

    public void refreshlist() {
        data.clear();
        try {
            data = FXCollections.observableArrayList(sl.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDLivraisonTab.setVisible(false);
        IDLivraisonTab.setCellValueFactory(new PropertyValueFactory<>("ID_Livraison"));
        ProduitTab.setCellValueFactory(new PropertyValueFactory<>("ID_Pannier"));
        ClientTab.setCellValueFactory(new PropertyValueFactory<>("Nom_Client"));
        QuantityTab.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MontantTab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("Date"));
        LivraisonTab.setItems(data);
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestionLivraison/Gui/Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
