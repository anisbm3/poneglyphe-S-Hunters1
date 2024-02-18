package EH.Controllers;

import EH.models.Livraison;
import EH.services.ServiceLivraison;
import EH.utils.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionLivraisonController implements Initializable {
    @FXML
    private TextField Adresse;

    @FXML
    private TableColumn<?, ?> ClientTab;

    @FXML
    private TableColumn<?, ?> DateTab;

    @FXML
    private TableColumn<?, ?> AdresseTab;

    @FXML
    private TableColumn<?, ?> IDLivraisonTab;

    @FXML
    private TableView<Livraison> Livraisontab;

    @FXML
    private TableColumn<?, ?> MontantTab;

    @FXML
    private TableColumn<?, ?> ProduitTab;

    @FXML
    private TableColumn<?, ?> QuantityTab;

    @FXML
    private Button Statistique1;

    @FXML
    private TextField TFSearch;

    @FXML
    private TextField TfMontant;

    @FXML
    private TextField TfQuantity;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private ComboBox<?> cb_Produit;

    @FXML
    private TextField clien;

    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane left_main;

    ServiceLivraison sl = new ServiceLivraison();
    int id;
    Livraison l;
    ObservableList<Livraison> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
        fillcomboProduit();
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void fillcomboProduit() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = " select * from produits";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while (rs.next()) {
                optionsProduit.add(rs.getInt("idP"));
            }
            cb_Produit.setItems(optionsProduit);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
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
        ProduitTab.setCellValueFactory(new PropertyValueFactory<>("ID_Preduit"));
        ClientTab.setCellValueFactory(new PropertyValueFactory<>("Nom_Client"));
        QuantityTab.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MontantTab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("Date"));
        AdresseTab.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        Livraisontab.setItems(data);
    }

    public void recherche_avance() throws SQLException {

        data = FXCollections.observableArrayList(sl.afficher());
        FilteredList<Livraison> filteredData = new FilteredList<>(data, b -> true);
        TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(p.getID_Livraison()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(p.getID_Produit()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(p.getNom_Client()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(p.getQuantity()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(p.getMontant()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(p.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });

        });
        SortedList<Livraison> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Livraisontab.comparatorProperty());
        Livraisontab.setItems(sortedData);

    }

    @FXML
    private void fillforum(MouseEvent event) {
        Livraison livraison = Livraisontab.getSelectionModel().getSelectedItem();
        id = livraison.getID_Livraison();
        cb_Produit.setValue(livraison.getID_Produit());
        clien.setText(livraison.getNom_Client());
        TfQuantity.setText(Integer.toString(livraison.getQuantity()));
        TfMontant.setText(Float.toString(livraison.getMontant()));
        date.setValue(livraison.getDate().toLocalDate());
        Adresse.setText(livraison.getAdresse());
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestioncommande/Gui/Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteLivraison(ActionEvent event) {
        int Id;
        Id = Livraisontab.getSelectionModel().getSelectedItem().getID_Livraison();
        try {
            sl.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    public void updateLivraison() throws SQLException {

        Livraison livraison = new Livraison(cb_Produit.getValue(), NomClient(), Integer.parseInt(TfQuantity.getText()), Float.parseFloat(TfMontant.getText()), date.getValue().atStartOfDay());

        sl.modifier(id, livraison);
        refreshlist();
    }

    @FXML
    private void EditLivraison(ActionEvent event) {
        try {
            updateLivraison();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
