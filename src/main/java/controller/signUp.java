package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import service.personneService;
import entities.user;

import javax.swing.*;

public class signUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ageSignup;

    @FXML
    private Label ageSignupError;

    @FXML
    private TextField cinSignup;

    @FXML
    private Label cinSignupError;

    @FXML
    private PasswordField confirmMdpSignup;

    @FXML
    private Label confirmMdpSignupError;

    @FXML
    private TextField emailSignup;

    @FXML
    private Label emailSignupError;

    @FXML
    private PasswordField mdpSignup;

    @FXML
    private Label mdpSignupError;

    @FXML
    private TextField nomSignup;

    @FXML
    private Label nomSignupError;

    @FXML
    private TextField numtelSignup;

    @FXML
    private Label numtelSignupError;

    @FXML
    private TextField prenomSignup;

    @FXML
    private Label prenomSignupError;

    @FXML
    private TextField pseudoSignup;

    @FXML
    private Label pseudoSignupError;
    @FXML
    private Button connectionButton;

    personneService ps = new personneService();

    @FXML
    void initialize() {
    }

    public boolean getErrors(){
        pseudoSignupError.setText("");
        cinSignupError.setText("");
        nomSignupError.setText("");
        prenomSignupError.setText("");
        ageSignupError.setText("");
        numtelSignupError.setText("");
        emailSignupError.setText("");
        mdpSignupError.setText("");
        confirmMdpSignupError.setText("");
        if(pseudoSignup.getText().isBlank()){
            pseudoSignupError.setTextFill(Color.RED);
            pseudoSignupError.setText("Le Pseudo est invalide");
            return true;
        }
        if(cinSignup.getText().isBlank() || !cinSignup.getText().matches("\\d{1,9}")){
            cinSignupError.setTextFill(Color.RED);
            cinSignupError.setText("Le CIN est invalide");
            return true;
        }
        if(nomSignup.getText().isBlank() || !nomSignup.getText().matches("[a-zA-Z ]+")){
            nomSignupError.setTextFill(Color.RED);
            nomSignupError.setText("Le nom est invalide");
            return true;
        }
        if(prenomSignup.getText().isBlank() || !prenomSignup.getText().matches("[a-zA-Z ]+")){
            prenomSignupError.setTextFill(Color.RED);
            prenomSignupError.setText("Le prénom est invalide");
            return true;
        }
        if(ageSignup.getText().isBlank() || !ageSignup.getText().matches("\\d+") || Integer.parseInt(ageSignup.getText()) < 18){
            ageSignupError.setTextFill(Color.RED);
            ageSignupError.setText("L'âge doit être un nombre valide et être supérieur à 18");
            return true;
        }
        if(numtelSignup.getText().isBlank() || !numtelSignup.getText().matches("\\d{1,12}")){
            numtelSignupError.setTextFill(Color.RED);
            numtelSignupError.setText("Le numéro de téléphone est invalide");
            return true;
        }
        if(emailSignup.getText().isBlank() || !emailSignup.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            emailSignupError.setTextFill(Color.RED);
            emailSignupError.setText("L'email est invalide");
            return true;
        }
        if(mdpSignup.getText().isBlank()|| mdpSignup.getText().length() < 8 || mdpSignup.getText().matches("[^a-zA-Z0-9]")){
            mdpSignupError.setTextFill(Color.RED);
            mdpSignupError.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmMdpSignup.getText().isBlank()){
            confirmMdpSignupError.setTextFill(Color.RED);
            confirmMdpSignupError.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmMdpSignup.getText(), mdpSignup.getText())){
            confirmMdpSignupError.setTextFill(Color.RED);
            confirmMdpSignupError.setText("Le mot de passe doit etre le meme");
            return true;
        }
        try {
            if (ps.pseudoExiste(pseudoSignup.getText())){
                pseudoSignupError.setTextFill(Color.RED);
                pseudoSignupError.setText("Ce pseudo est déjà utilisé, veuillez en choisir un autre");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @FXML
    public void signUpButtonButtonOnClick(ActionEvent event){
        if (!getErrors()) {
            user newUser = new user(pseudoSignup.getText(), Integer.parseInt(cinSignup.getText()), nomSignup.getText(),
                    prenomSignup.getText(), Integer.parseInt(ageSignup.getText()), Integer.parseInt(numtelSignup.getText()), emailSignup.getText(),
                    mdpSignup.getText(), "Utilisateur");
            try {
                ps.add(newUser);
                JOptionPane.showMessageDialog(null,"Sign up effectuée! Connectez-vous Maintenant ");
                ps.changeScreen(event, "/login.fxml", "Login");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            }

        }
    }
    @FXML
    private void connectionButtonOnClick(ActionEvent event){
        ps.changeScreen(event, "/login.fxml", "Login");
    }

}
