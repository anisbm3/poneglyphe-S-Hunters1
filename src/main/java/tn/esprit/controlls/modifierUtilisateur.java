package tn.esprit.controlls;

import tn.esprit.models.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tn.esprit.services.userService;


import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.restfb.types.whatsapp.platform.status.CategoryType.service;




public class modifierUtilisateur {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ageDashboard;

    @FXML
    private Label ageDashboardError;

    @FXML
    private TextField cinDashboard;

    @FXML
    private Label cinDashboardError;

    @FXML
    private TextField emailDashboard;

    @FXML
    private Label emailDashboardError;

    @FXML
    private Button modifier;

    @FXML
    private TextField nomDashboard;

    @FXML
    private Label nomDashboardError;

    @FXML
    private TextField numtelDashboard;

    @FXML
    private Label numtelDashboardError;

    @FXML
    private TextField prenomDashboard;

    @FXML
    private Label prenomDashboardError;

    @FXML
    private TextField pseudoDashboard;

    @FXML
    private Label pseudoDashboardError;

    @FXML
    private Button retour;

    private static user loggedInuser;
    public static void setLoggedInuser(user user) {
        loggedInuser = user;
    }
    private user user;
    userService userService = new userService();


    public boolean getErrors(){
        pseudoDashboardError.setText("");
        cinDashboardError.setText("");
        nomDashboardError.setText("");
        prenomDashboardError.setText("");
        ageDashboardError.setText("");
        numtelDashboardError.setText("");
        emailDashboardError.setText("");
        if(pseudoDashboard.getText().isBlank()){
            pseudoDashboardError.setTextFill(Color.RED);
            pseudoDashboardError.setText("Le Pseudo est invalide");
            return true;
        }
        if(cinDashboard.getText().isBlank() || !cinDashboard.getText().matches("\\d{1,9}")){
            cinDashboardError.setTextFill(Color.RED);
            cinDashboardError.setText("Le CIN est invalide");
            return true;
        }
        if(nomDashboard.getText().isBlank() || !nomDashboard.getText().matches("[a-zA-Z ]+")){
            nomDashboardError.setTextFill(Color.RED);
            nomDashboardError.setText("Le nom est invalide");
            return true;
        }

        if(prenomDashboard.getText().isBlank() || !prenomDashboard.getText().matches("[a-zA-Z ]+")){
            prenomDashboardError.setTextFill(Color.RED);
            prenomDashboardError.setText("Le prénom est invalide");
            return true;
        }
        if(ageDashboard.getText().isBlank() || !ageDashboard.getText().matches("\\d+") || Integer.parseInt(ageDashboard.getText()) < 18){
            ageDashboardError.setTextFill(Color.RED);
            ageDashboardError.setText("L'âge doit être un nombre valide et être supérieur à 18");
            return true;
        }
        if(numtelDashboard.getText().isBlank() || !numtelDashboard.getText().matches("\\d{1,12}")){
            numtelDashboardError.setTextFill(Color.RED);
            numtelDashboardError.setText("Le numéro de téléphone est invalide");
            return true;
        }
        if(emailDashboard.getText().isBlank() || !emailDashboard.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            emailDashboardError.setTextFill(Color.RED);
            emailDashboardError.setText("L'email est invalide");
            return true;
        }
        return false;
    }
    @FXML
    void modifierOnClick(ActionEvent event) {
        if (!getErrors()) {
            try {
                user newuser1 = userService.afficherParPseudo(pseudoDashboard.getText());
                user newuser = new user(newuser1.getPseudo(), Integer.parseInt(cinDashboard.getText()), nomDashboard.getText(),
                        prenomDashboard.getText(), Integer.parseInt(ageDashboard.getText()), Integer.parseInt(numtelDashboard.getText()),
                        emailDashboard.getText(), newuser1.getMdp(), loggedInuser.getRole());
                userService.update(newuser);
                JOptionPane.showMessageDialog(null,"Modification effectuée! ");
                System.out.println("user modifié avec succès !");
                userService.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification de l'user : " + e.getMessage());
            }
        }
    }

    @FXML
    void retourOnClick(ActionEvent event) {
        userService.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
    }

    private void getFields(){
            pseudoDashboard.setText(loggedInuser.getPseudo());
            cinDashboard.setText(String.valueOf(loggedInuser.getCin()));
            nomDashboard.setText(loggedInuser.getNom());
            prenomDashboard.setText(loggedInuser.getPrenom());
            ageDashboard.setText(String.valueOf(loggedInuser.getAge()));
            numtelDashboard.setText(String.valueOf(loggedInuser.getNumTel()));
            emailDashboard.setText(loggedInuser.getEmail());
    }

    @FXML
    void initialize() {
        getFields();
    }

}
