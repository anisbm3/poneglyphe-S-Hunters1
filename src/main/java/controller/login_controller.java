package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import entities.user; // Assurez-vous que le nom de la classe est correctement orthographié et importé
import service.personneService; // Assurez-vous que le nom de la classe est correctement orthographié et importé

import java.sql.SQLException;

public class login_controller {

    @FXML
    private Button connecterLogin;
    @FXML
    private Button signinlogin;

    @FXML
    private PasswordField mdpLogin;

    @FXML
    private TextField pseudoLogin;

    @FXML
    private Label errorLogin;

    private personneService ps = new personneService();

    @FXML
    public void connecterButtonOnClick(ActionEvent event) {
        if (!pseudoLogin.getText().isBlank()&& !mdpLogin.getText().isBlank()){
            loginCheck(event);
        }
        else {
            errorLogin.setTextFill(Color.RED);
            errorLogin.setText("Entrer correctement votre pseudo et mot de passe");
        }
    }

    private void loginCheck(ActionEvent event) {
        try {
            if (!ps.utilisateurLoggedIn(pseudoLogin.getText(), mdpLogin.getText())) {
                errorLogin.setTextFill(Color.RED);
                errorLogin.setText("Pseudo ou mot de passe incorrect");
            } else {
                user utilisateur = ps.afficherParPseudo(pseudoLogin.getText());
                errorLogin.setTextFill(Color.GREEN);
                errorLogin.setText("Bienvenue, " + utilisateur.getNom());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage());
        }
    }
    @FXML
    private void signinLoginButtonOnClick(ActionEvent event){
        ps.changeScreen(event, "/signUp.fxml", "Sign Up");
    }
}
