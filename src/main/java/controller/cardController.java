package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
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


public class cardController {
    @FXML
    private Label pseudoSignup ;
    @FXML
    private Label nomSignup;
    @FXML
    private Label prenomSignup;
    @FXML
    private Label cinSignup;
    @FXML
    private Label num_telSignup;
    @FXML
    private Label emailSignup;
    @FXML
    private Label ageSignup;
    @FXML
    private Label RoleSignup ;
    @FXML
    Pane Card;
    private static user user;
    userService userService = new userService();
    dashboard d;
    public void initialize() {}
    public void setData(user user) throws SQLException {
        userService utilisateurService = new userService();
        List<user> users = utilisateurService.Readall();

        if (!users.isEmpty()) {
            this.user = user;
            pseudoSignup.setText(String.valueOf(user.getPseudo()));
            nomSignup.setText(String.valueOf(user.getNom()));
            prenomSignup.setText(String.valueOf(user.getPrenom()));
            cinSignup.setText(String.valueOf(user.getCin()));
            num_telSignup.setText(String.valueOf(user.getNumTel()));
            emailSignup.setText(user.getEmail());
            ageSignup.setText(String.valueOf(user.getAge()));
            RoleSignup.setText(user.getRole());
        }
    }
    @FXML
    private void SupprimerButtonOnClick(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation :");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet utilisateur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            userService.delete(user);
            userService.changeScreen(event, "/dashboard.fxml", "dashboard");
        }
    }
    @FXML
    private void modiferOnClick(ActionEvent event){
        modifierUtilisateur.setLoggedInuser(user);
        userService.changeScreen(event, "/modifierUtilisateur.fxml", "MODIFIER");
    }
}
