package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import entities.user;
import service.userService;
import util.datasource;

import javax.management.relation.RoleStatus;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class recherche {
    @FXML
    private TextField pseudoSignup ;
    @FXML
    private TextField nomSignup;
    @FXML
    private TextField prenomSignup;
    @FXML
    private TextField cinSignup;
    @FXML
    private TextField num_telSignup;
    @FXML
    private TextField emailSignup;
    @FXML
    private TextField ageSignup;
    @FXML
    private TextField RoleSignup ;
    @FXML
    Pane Card;
    private static user loggedInUser;
    public static void setLoggedInuser(user user) {
        loggedInUser = user;
    }
    userService userService = new userService();
    dashboard d;
    public void initialize(){
        pseudoSignup.setText(loggedInUser.getPseudo());
        cinSignup.setText(String.valueOf(loggedInUser.getCin()));
        nomSignup.setText(loggedInUser.getNom());
        prenomSignup.setText(loggedInUser.getPrenom());
        ageSignup.setText(String.valueOf(loggedInUser.getAge()));
        num_telSignup.setText(String.valueOf(loggedInUser.getNumTel()));
        emailSignup.setText(loggedInUser.getEmail());
        RoleSignup.setText(loggedInUser.getEmail());
    }


    @FXML
    private void SupprimerButtonOnClick(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation :");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet utilisateur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            userService.delete(loggedInUser);
            userService.changeScreen(event, "/dashboard.fxml", "dashboard");
        }
    }
    @FXML
    private void modiferOnClick(ActionEvent event){
        modifierUtilisateur.setLoggedInuser(loggedInUser);
        userService.changeScreen(event, "/modifierUtilisateur.fxml", "MODIFIER");
    }
    @FXML
    private void retourOnClick(ActionEvent event){
        userService.changeScreen(event, "/dashboard.fxml", "dashboard");
    }
}
