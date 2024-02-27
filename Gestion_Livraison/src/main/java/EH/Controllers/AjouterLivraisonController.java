package EH.Controllers;

import EH.models.Livraison;
import EH.services.ServiceLivraison;
import EH.utils.MyDataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class AjouterLivraisonController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Integer> cb_Produit;
    ObservableList<Integer> optionsProduit=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> cb_Client;
    ObservableList<Integer> optionsClient=FXCollections.observableArrayList();
    @FXML
    private TextField TfQuantity;
    @FXML
    private TextField TfMontant;

    /**
     * Initializes the controller class.
     */
    ServiceLivraison sl =new ServiceLivraison();
    int id;
    Livraison l;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillcomboProduit();
        fillcomboClient();
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
    @FXML
    private void Acceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("EH/Gui/GestionLivraison.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregister(ActionEvent event) {
        String erreurs="";
        if(TfQuantity.getText().trim().isEmpty()){
            erreurs+="Quantity vide\n";
        }
        if(TfMontant.getText().trim().isEmpty()){
            erreurs+="Montant vide\n";
        }
        if(  date.getValue() != null
                &&   date.getValue().isBefore(LocalDate.now())
        ){
            erreurs+="date must be after\n";
        }
        if(date.getValue() == null){
            erreurs+="date vide\n";
        }

        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
            Livraison livraison = new Livraison(cb_Produit.getValue(),
                    cb_Client.getValue(),
                    Integer.parseInt(TfQuantity.getText()),
                    Float.parseFloat(TfMontant.getText()),
                    date.getValue().atStartOfDay());
            sl.ajouter(livraison);

        }
    }

}

