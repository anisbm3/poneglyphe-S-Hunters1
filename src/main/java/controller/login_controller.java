package controller;
import util.Encryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import entities.user; // Assurez-vous que le nom de la classe est correctement orthographié et importé
import service.userService; // Assurez-vous que le nom de la classe est correctement orthographié et importé
import util.SendSMS;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    private userService ps = new userService();
    Encryptor encryptor = new Encryptor();

    @FXML
    public void connecterButtonOnClick(ActionEvent event) throws NoSuchAlgorithmException {
        if (!pseudoLogin.getText().isBlank()&& !mdpLogin.getText().isBlank()){
            loginCheck(event);
        }
        else {
            errorLogin.setTextFill(Color.RED);
            errorLogin.setText("Entrer correctement votre pseudo et mot de passe");
        }
    }

    private void loginCheck(ActionEvent event) throws NoSuchAlgorithmException {
        try {
            if (!ps.userLoggedIn(pseudoLogin.getText(), encryptor.encryptString(mdpLogin.getText()))) {
                errorLogin.setTextFill(Color.RED);
                errorLogin.setText("Pseudo ou mot de passe incorrect");
            } else {
                user utilisateur = ps.afficherParPseudo(pseudoLogin.getText());
                errorLogin.setTextFill(Color.GREEN);
                switch (utilisateur.getRole()){
                    case "Admin":
                        dashboard.setLoggedInuser(utilisateur);
                        ps.changeScreen(event, "/dashboard.fxml", "Admin");
                        break;
                    case "Client":
                        break;
                    case "Livreur":
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage());
        }
    }
    @FXML
    private void signinLoginButtonOnClick(ActionEvent event){
        ps.changeScreen(event, "/signUp.fxml", "Sign Up");
    }
    @FXML
    private void mdpOublieOnClick(ActionEvent event){
        Random rd = new Random();
        int Ra = rd.nextInt(1000000+1);
        motDePasseOublie.setRand(Ra);
        try {
            user newUser = ps.afficherParPseudo(pseudoLogin.getText());
            motDePasseOublie.setLoggedInUser(newUser);
            String message = "Bonjour " + newUser.getNom() + " Voici votre code de Confirmation de le mot de passe : " + String.valueOf(Ra);
            //System.out.println(message);
            String num = "+216"+String.valueOf(newUser.getNumTel());
            //System.out.println(num);
            SendSMS.SendSMS(message, num);
            motDePasseOublie.setLoggedInUser(ps.afficherParPseudo(newUser.getPseudo()));
            //System.out.println("mochkla");
            ps.changeScreen(event, "/motDePasseOublie.fxml", "Vérifier le code");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
