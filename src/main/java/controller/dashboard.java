package controller;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import entities.user;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.userService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.text.html.ImageView;

import util.Encryptor;
public class dashboard {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    //BORDER TOP
    @FXML
    private Button Menu;
    @FXML
    private Button MenuClose;
    @FXML
    private Button logo;
    @FXML
    private MenuItem deconnecterButton;
    //BORDER LEFT
    @FXML
    private Button usersButton;
    @FXML
    private AnchorPane slider;
    //BORDER CENTER
    //ANCHOR PANE userS
    @FXML
    private AnchorPane usersAnchorPane;
    //HBOX (RECHERCHE, AJOUTER ADMIN, MODIFIER, SUPPRIMER)
    @FXML
    private Button modifierButton, supprimerButton, rechercheButton, ajouterButton;
    @FXML
    private TextField rechercheDashboard;
    //GRIDPANE
    @FXML
    private TextField pseudoDashboard, cinDashboard, nomDashboard, prenomDashboard, ageDashboard, numtelDashboard, emailDashboard;
    @FXML
    private ToggleGroup roles;
    @FXML
    private RadioButton roleAdminSignup, roleLivreurSignup;
    @FXML
    private Label roleSignupError;
    //ANCHOR PANE ADD ADMIN, PROFILE
    @FXML
    private AnchorPane ajouterAnchorPane;
    @FXML
    private TextField pseudoAjout, cinAjout, nomAjouter, prenomAjout, ageAjout, numtelAjout, emailAjout, mdpAjout;
    @FXML
    private PasswordField confirmMdpAjout;
    @FXML
    private Label pseudoError, cinError, nomError, prenomError, ageError, numtelError, emailError, mdpError, confirmMdpError, mdpLabel, confirmMdpLabel;
    @FXML
    private Button annulerButton, ajoutAdminButton;
    //ANCHOR PANE MODIFIER MDP
    @FXML
    private AnchorPane anchorPaneModifierMdp;
    @FXML
    private PasswordField ancienText, nouveauMdp, confirmNouveauMdp;
    @FXML
    private Label ancienError, nouveauMdpError, confirmNouveauMdpError;
    @FXML
    private Button confirmerNouveauMdp;
    userService utilisateurService = new userService();

    private List<user> getlist() throws SQLException {
        return utilisateurService.Readall();
    }
    private Parent parent;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button back;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scrol;
    private List<user> u = new ArrayList<>();

    private List<user> getData() throws SQLException {

        List<user> Users = getlist();
        return Users;
    }
    userService userService = new userService();


    private static user loggedInuser;
    public static void setLoggedInuser(user user) {
        loggedInuser = user;
    }
    cardController card;
    Encryptor encryptor = new Encryptor();
    @FXML
    void initialize() {
        logo.setOnAction(event -> {
            usersAnchorPane.setVisible(false);
            ajouterAnchorPane.setVisible(false);
            anchorPaneModifierMdp.setVisible(false);
        });
        deconnecterButton.setOnAction(event -> {
            try {
                loggedInuser = null;
                Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
                ageAjout.getScene().setRoot(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        updateScroll();


    }

    public void updateScroll(){
        int column = 0;
        int row = 1;
        try {
            u.addAll(getData());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            for(int i=0;i<u.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                cardController cardController = fxmlLoader.getController();
                cardController.setData(u.get(i));
                if (column == 2){
                    column=0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void rechercheButtonOnClick(ActionEvent event){
        String data = rechercheDashboard.getText().toString();
        try {
            if(userService.pseudoExiste(data)){
                recherche.setLoggedInuser(userService.afficherParPseudo(data));
                userService.changeScreen(event, "/recherche.fxml", "CARD");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Utilisateur non trouvé !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void usersButtonOnClick(ActionEvent event){
        usersAnchorPane.setVisible(true);
        ajouterAnchorPane.setVisible(false);
        anchorPaneModifierMdp.setVisible(false);
    }
    @FXML
    private void ajouterButtonOnClick(ActionEvent event){
        usersAnchorPane.setVisible(false);
        ajouterAnchorPane.setVisible(true);
    }
    public boolean getErrors1(){
        pseudoError.setText("");
        cinError.setText("");
        nomError.setText("");
        prenomError.setText("");
        ageError.setText("");
        numtelError.setText("");
        emailError.setText("");
        mdpError.setText("");
        confirmMdpError.setText("");

        if(pseudoAjout.getText().isBlank()){
            pseudoError.setTextFill(Color.RED);
            pseudoError.setText("Le Pseudo est invalide");
            return true;
        }
        if(cinAjout.getText().isBlank() || !cinAjout.getText().matches("\\d{1,9}")){
            cinError.setTextFill(Color.RED);
            cinError.setText("Le CIN est invalide");
            return true;
        }
        if(nomAjouter.getText().isBlank() || !nomAjouter.getText().matches("[a-zA-Z ]+")){
            nomError.setTextFill(Color.RED);
            nomError.setText("Le nom est invalide");
            return true;
        }

        if(prenomAjout.getText().isBlank() || !prenomAjout.getText().matches("[a-zA-Z ]+")){
            prenomError.setTextFill(Color.RED);
            prenomError.setText("Le prénom est invalide");
            return true;
        }
        if(ageAjout.getText().isBlank() || !ageAjout.getText().matches("\\d+") || Integer.parseInt(ageAjout.getText()) < 18){
            ageError.setTextFill(Color.RED);
            ageError.setText("L'âge doit être un nombre valide et être supérieur à 18");
            return true;
        }
        if(numtelAjout.getText().isBlank() || !numtelAjout.getText().matches("\\d{1,12}")){
            numtelError.setTextFill(Color.RED);
            numtelError.setText("Le numéro de téléphone est invalide");
            return true;
        }
        if(emailAjout.getText().isBlank() || !emailAjout.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            emailError.setTextFill(Color.RED);
            emailError.setText("L'email est invalide");
            return true;
        }
        if(mdpAjout.getText().isBlank()|| mdpAjout.getText().length() < 8 || mdpAjout.getText().matches("[^a-zA-Z0-9]")){
            mdpError.setTextFill(Color.RED);
            mdpError.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmMdpAjout.getText().isBlank()){
            confirmMdpError.setTextFill(Color.RED);
            confirmMdpError.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmMdpAjout.getText(), mdpAjout.getText())){
            confirmMdpError.setTextFill(Color.RED);
            confirmMdpError.setText("Le mot de passe doit etre le meme");
            return true;
        }
        if(!roleAdminSignup.isSelected() && !roleLivreurSignup.isSelected()){
            roleSignupError.setTextFill(Color.RED);
            roleSignupError.setText("Le role est obligatoire");
            return true;
        }
        try {
            if (userService.pseudoExiste(pseudoAjout.getText())){
                pseudoError.setTextFill(Color.RED);
                pseudoError.setText("Ce pseudo est déjà utilisé, veuillez en choisir un autre");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    @FXML
    private void ajoutAdminButtonOnClick(ActionEvent event) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (!getErrors1()) {
            user newuser = new user(pseudoAjout.getText(), Integer.parseInt(cinAjout.getText()), nomAjouter.getText(),
                    prenomAjout.getText(), Integer.parseInt(ageAjout.getText()), Integer.parseInt(numtelAjout.getText()), emailAjout.getText(),
                    encryptor.encryptString(mdpAjout.getText()), (roleAdminSignup.isSelected() ? "Admin" : "Livreur"));
            try {
                userService.add(newuser);
                System.out.println("Utilisateur ajouté avec succès !");
                JOptionPane.showMessageDialog(null,"Utilisateur ajouté avec succès !");
                userService.changeScreen(event, "/dashboard.fxml", "DASHBOARD");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout de l'user : " + e.getMessage());
            }
        }
    }

    @FXML
    private void profileButtonOnClick(ActionEvent event){
        usersAnchorPane.setVisible(false);
        ajouterAnchorPane.setVisible(true);
        pseudoAjout.setDisable(true);
        cinAjout.setDisable(true);
        nomAjouter.setDisable(true);
        prenomAjout.setDisable(true);
        ageAjout.setDisable(true);
        numtelAjout.setDisable(true);
        emailAjout.setDisable(true);
        mdpAjout.setDisable(true);
        confirmMdpAjout.setDisable(true);
        pseudoAjout.setText(loggedInuser.getPseudo());
        cinAjout.setText(String.valueOf(loggedInuser.getCin()));
        nomAjouter.setText(loggedInuser.getNom());
        prenomAjout.setText(loggedInuser.getPrenom());
        ageAjout.setText(String.valueOf(loggedInuser.getAge()));
        numtelAjout.setText(String.valueOf(loggedInuser.getNumTel()));
        emailAjout.setText(loggedInuser.getEmail());
        mdpLabel.setVisible(false);
        confirmMdpLabel.setVisible(false);
        mdpAjout.setVisible(false);
        confirmMdpAjout.setVisible(false);
        ajoutAdminButton.setVisible(false);
        annulerButton.setVisible(false);
    }
    @FXML
    private void deconnecterButtonOnClick(ActionEvent event){
        loggedInuser = null;
        userService.changeScreen(event, "/login.fxml", "LOGIN");
    }
    @FXML
    private void modifierMdpOnClick(ActionEvent event){
        ajouterAnchorPane.setVisible(false);
        usersAnchorPane.setVisible(false);
        anchorPaneModifierMdp.setVisible(true);
    }
    private boolean getErrorsMdp() throws NoSuchAlgorithmException {
        String thisuserMdp = loggedInuser.getMdp();
        String mdpSaisi = encryptor.encryptString(ancienText.getText());
        if(!thisuserMdp.equals(mdpSaisi)){
            ancienError.setTextFill(Color.RED);
            ancienError.setText("Ancien Mot de passe invalide");
            return true;
        }
        if(nouveauMdp.getText().isBlank()|| nouveauMdp.getText().length() < 8 || nouveauMdp.getText().matches("[^a-zA-Z0-9]")){
            nouveauMdpError.setTextFill(Color.RED);
            nouveauMdpError.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmNouveauMdp.getText().isBlank()){
            confirmNouveauMdpError.setTextFill(Color.RED);
            confirmNouveauMdpError.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmNouveauMdp.getText(), nouveauMdp.getText())){
            confirmNouveauMdpError.setTextFill(Color.RED);
            confirmNouveauMdpError.setText("Le mot de passe doit etre le meme");
            return true;
        }
        return false;
    }
    @FXML
    private void confirmerNouveauMdpOnClick(ActionEvent event) throws NoSuchAlgorithmException {
        if (!getErrorsMdp()){
            try {
                userService.modifierMdp(loggedInuser, encryptor.encryptString(nouveauMdp.getText()));
                JOptionPane.showMessageDialog(null,"Mot de Passe modifié avec succès !");
                anchorPaneModifierMdp.setVisible(false);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
