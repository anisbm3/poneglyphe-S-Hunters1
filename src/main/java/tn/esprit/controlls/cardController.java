package tn.esprit.controlls;

import tn.esprit.models.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.esprit.services.userService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


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
    private user userr;
    userService userService = new userService();
    dashboard d;
    public void initialize() {}
    public void setData(user user) throws SQLException {
        userService utilisateurService = new userService();
        List<user> users = utilisateurService.Readall();
        userr = user;
        if (!users.isEmpty()) {
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
        if (userr != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation :");
            alert.setHeaderText("Voulez-vous vraiment supprimer "+ userr.getNom() +"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                userService.delete(userr);
                userService.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");
            }
        }else {
            System.out.println("MAFAMMECH");
        }

    }
    @FXML
    private void modiferOnClick(ActionEvent event){
        modifierUtilisateur.setLoggedInuser(userr);
        userService.changeScreen(event, "/tn/esprit/modifierUtilisateur.fxml", "MODIFIER");
    }
}
