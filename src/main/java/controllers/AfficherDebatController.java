//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controllers;

import entities.Debat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.ServiceDebat;
import utils.MyDB;
import java.sql.*;

public class AfficherDebatController {
    @FXML
    private TextField DESCDEBAT;
    @FXML
    private TextField IDDEBAT;
    @FXML
    private TextField NOMANIME;
    @FXML
    private TextField SUJETDEBAT;
    @FXML
    private Button afficherbtn;


    private  ServiceDebat SD = new ServiceDebat();

    public Debat chercherParId(int id) {
        String req = "SELECT * FROM debat WHERE ID_Debat = ?";
        try {

            Connection connection = MyDB.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                // Récupérer les données de la ligne trouvée
                String sujetDebat = rs.getString("Sujet_Debat");
                String descriptionDebat = rs.getString("Description_Debat");
                String nomAnime = rs.getString("Nom_Anime");

                // Créer et retourner un objet Debat
                return new Debat(id, sujetDebat, descriptionDebat, nomAnime);
            } else {
                // Aucune ligne correspondante trouvée
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de base de données
            return null;
        }
    }
    @FXML
    void onClickedAfficher(ActionEvent event) {
        // Récupérer l'ID du débat à afficher depuis l'interface utilisateur
        int idDebat = Integer.parseInt(this.IDDEBAT.getText());
        // Utiliser la méthode chercherParId pour rechercher le débat par son ID
        System.out.println(idDebat);
        Debat debat = chercherParId(idDebat);

        // Vérifier si le débat a été trouvé
        if (debat != null) {
            // Afficher les informations du débat dans l'interface utilisateur
            // Vous pouvez utiliser des composants JavaFX comme des labels pour afficher les informations
            // Par exemple :

            SUJETDEBAT.setText(debat.getSujet_Debat());
            DESCDEBAT.setText(debat.getDescription_Debat());
            NOMANIME.setText(debat.getNom_Anime());
        } else {
            // Afficher un message d'erreur si le débat n'a pas été trouvé
            // Vous pouvez utiliser une boîte de dialogue ou tout autre méthode pour informer l'utilisateur

            afficherAlerte("Erreur","Le débat avec l'ID " + idDebat + " n'a pas été trouvé.");

        }

    }
    @FXML
    void onClickedModifier(ActionEvent event) throws SQLException {
        int idDebat = Integer.parseInt(this.IDDEBAT.getText());
        Debat debat = new Debat(idDebat ,NOMANIME.getText() , DESCDEBAT.getText() , SUJETDEBAT.getText());

        if (NOMANIME.getText().isEmpty() || DESCDEBAT.getText().isEmpty()  || SUJETDEBAT.getText().isEmpty() )
            afficherAlerte("Error","veuillez remplir tous les champs");
        else if (isNumeric(NOMANIME.getText()) || isNumeric(DESCDEBAT.getText()) || isNumeric(SUJETDEBAT.getText()))
            afficherAlerte("Error","veuillez saisir des chaines de characteres");

        else {
            SD.modifier(debat);
            afficherAlerte("Sucees","Modification reussi");
        }


    }

    @FXML
    void onClickedSupprimer(ActionEvent event) throws SQLException {
        int idDebat = Integer.parseInt(this.IDDEBAT.getText());
       if (chercherParId(idDebat)!=null){
        SD.supprimerParId(idDebat);
           afficherAlerte("succes","suppression reussi");}
       else
           afficherAlerte("Erreur","Le débat avec l'ID " + idDebat + " n'a pas été trouvé.");


    }

    private void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}




