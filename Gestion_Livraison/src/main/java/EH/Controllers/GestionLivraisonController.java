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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionLivraisonController implements Initializable {
    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_delete;
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
    private TextField TFSearch;
    @FXML
    private Button Statistique1;
    @FXML
    private TextField TfQuantity;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Integer> cb_Produit;
    ObservableList<Integer> optionsProduit=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> cb_Client;
    ObservableList<Integer> optionsClient=FXCollections.observableArrayList();
    @FXML
    private TextField TfMontant;

    /**
     * Initializes the controller class.
     */

    ServiceLivraison sl =new ServiceLivraison();
    int id;
    Livraison l;
    ObservableList<Livraison> data=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
        fillcomboProduit();
        fillcomboClient();
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillcomboClient(){
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = " select * from client";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsClient.add(rs.getInt("ID_Client"));
            }
            cb_Client.setItems(optionsClient);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillcomboProduit(){
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = " select * from produits";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsProduit.add(rs.getInt("ID_Produit"));
            }
            cb_Produit.setItems(optionsProduit);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sl.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDLivraisonTab.setVisible(false);
        IDLivraisonTab.setCellValueFactory(new PropertyValueFactory<>("ID_Livraison"));
        ProduitTab.setCellValueFactory(new PropertyValueFactory<>("ID_Produit"));
        ClientTab.setCellValueFactory(new PropertyValueFactory<>("ID_Client"));
        QuantityTab.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MontantTab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("Date"));
        LivraisonTab.setItems(data);
    }

    public void recherche_avance() throws SQLException {

        data = FXCollections.observableArrayList(sl.afficher());
        //System.out.println(data);
        FilteredList<Livraison> filteredData = new FilteredList<>(data, b -> true);
        TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(p.getID_Livraison()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                if (String.valueOf(p.getID_Produit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if(String.valueOf(p.getID_Client()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if(String.valueOf(p.getQuantity()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if(String.valueOf(p.getMontant()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if(String.valueOf(p.getDate()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }

                else
                    return false; // Does not match.
            });

        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Livraison> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(LivraisonTab.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        LivraisonTab.setItems(sortedData);

    }

    @FXML
    private void fillforum(MouseEvent event) {
        Livraison livraison=LivraisonTab.getSelectionModel().getSelectedItem();
        id=livraison.getID_Livraison();
        cb_Produit.setValue(Livraison.getID_Produit());
        cb_Client.setValue(livraison.getID_Client());
        TfQuantity.setText(Integer.toString(livraison.getQuantity()));
        TfMontant.setText(Float.toString(livraison.getMontant()));
        date.setValue(livraison.getDate().toLocalDate());
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("EH/Gui/Menu.fxml"));
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
        int id;
        id=LivraisonTab.getSelectionModel().getSelectedItem().getID_Livraison();
        try {
            sl.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    public void updateReclamation() throws SQLException{

        Livraison livraison = new Livraison(cb_Produit.getValue(), cb_Client.getValue(), Integer.parseInt(TfQuantity.getText()), Float.parseFloat(TfMontant.getText()),
                date.getValue().atStartOfDay());
        sl.modifier(id, new Livraison());
        refreshlist();
    }
    @FXML
    private void EditLivraison(ActionEvent event) {
        try {
            updateReclamation();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

