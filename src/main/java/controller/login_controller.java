package controller;
import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

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
                    case "Utilisateur":
                        clientFrontController.setLoggedInUser(utilisateur);
                        ps.changeScreen(event, "/clientFront.fxml", "client");
                        break;
                    case "Livreur":
                        livreurFrontController.setLoggedInUser(utilisateur);
                        ps.changeScreen(event, "/livreurFront.fxml", "livreur");
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
    private String app_Id = "780207970644067";
    private String app_Secret = "a843fe172c5db9c5bbe8a001957fc11d";
    private String redirect_Url = "http://localhost:8181/";
    private String redirect_url_encoded = "http%3A%2F%2Flocalhost%3A8181%2F";
    private String state = "9812" ;
    private String code = "";
    private String authentication = "https://www.facebook.com/v19.0/dialog/oauth?client_id="+app_Id+"&redirect_uri="+redirect_url_encoded+"&state="+state;

    @FXML
    private void connectWithFacebook(ActionEvent event) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(authentication);

        Pane webViewPane = new Pane();
        webViewPane.getChildren().add(webView);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(webViewPane));
        stage.show();

        webEngine.locationProperty().addListener((obs, oldLocation, newLocation) -> {
            if (newLocation != null && newLocation.startsWith("http://localhost")) {
                URI uri = URI.create(newLocation);
                String code = null;
                for (String queryParam : uri.getQuery().split("&")) {
                    String[] keyValue = queryParam.split("=");
                    if (keyValue.length == 2 && keyValue[0].equals("code")) {
                        code = keyValue[1];
                        break;
                    }
                }
                if (code != null) {
                    try {
                        DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_19_0);
                        AccessToken accessToken = facebookClient.obtainUserAccessToken(
                                app_Id, app_Secret, redirect_Url, code);

                        String access_token = accessToken.getAccessToken();

                        FacebookClient fbClient = new DefaultFacebookClient(access_token, Version.VERSION_19_0);

                        // Récupération des informations du profil de l'utilisateur
                        JsonObject userProfileJson = fbClient.fetchObject("me", JsonObject.class, Parameter.with("fields", "id,name,email,picture"));
                        String userId = userProfileJson.getString("id", String.valueOf(String.class));
                        String userName = userProfileJson.getString("name", String.valueOf(String.class));
                        String userEmail = userProfileJson.getString("email", String.valueOf(String.class));

                        // Création de l'objet Utilisateur et définition des attributs
                        user user = new user();
                        user.setPseudo(userId);
                        user.setNom(userName);
                        user.setEmail(userEmail);
                        user.setRole("Utilisateur");
                        clientFrontController.setLoggedInUser(user);

                        // Fermeture de la fenêtre de connexion et changement d'écran
                        JOptionPane.showMessageDialog(null,"Vous avez connecté avec succès! Si vous souhaitez garder votre compte persistant, allez à modifier et mettez vos autres informations.");
                        stage.close();
                        ps.changeScreen(event, "/clientFront.fxml", "XTRATIME");

                    } catch (FacebookOAuthException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid redirect URI or code not found in the URL");
                }
            }
        });
    }
}
