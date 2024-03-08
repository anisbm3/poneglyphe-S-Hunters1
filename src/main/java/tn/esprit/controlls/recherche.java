package tn.esprit.controlls;

import tn.esprit.models.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tn.esprit.services.userService;

import java.sql.SQLException;
import java.util.Optional;


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
        RoleSignup.setText(loggedInUser.getRole());
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
            userService.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
        }
    }
    @FXML
    private void modiferOnClick(ActionEvent event){
        modifierUtilisateur.setLoggedInuser(loggedInUser);
        userService.changeScreen(event, "/tn/esprit/modifierUtilisateur.fxml", "MODIFIER");
    }
    @FXML
    private void retourOnClick(ActionEvent event){
        userService.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
    }
}
