package controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import entities.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import service.userService;
import util.Encryptor;
import util.SendSMS;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class clientFrontController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Menu;

    @FXML
    private Button MenuClose;

    @FXML
    private Label acienMdpErrorLabel;

    @FXML
    private TextField ageTF;

    @FXML
    private PasswordField ancienMdpTF;

    @FXML
    private Pane changementPane;

    @FXML
    private TextField cinTF;

    @FXML
    private TextField codeVerification;

    @FXML
    private ProgressBar complexiteBar;

    @FXML
    private Label complexiteLabel;

    @FXML
    private Button confirmerAdresseCode, confirmerAdresseCode1;

    @FXML
    private Label confirmerMdpErrorLabel;

    @FXML
    private PasswordField confirmerNouveauMdpTF;

    @FXML
    private Button deconnecterButton;

    @FXML
    private TextField emailTF;

    @FXML
    private Label errorCodeVerificationLabel;

    @FXML
    private Hyperlink mdpOublieHL;

    @FXML
    private Label messageLabel;

    @FXML
    private Button modifierProfile;

    @FXML
    private Button motDePasseButton;

    @FXML
    private TextField nomTF;

    @FXML
    private PasswordField nouveauMdpTF;

    @FXML
    private TextField numTelTF;

    @FXML
    private Pane parametresMdpPane;

    @FXML
    private TextArea politiquesText;

    @FXML
    private TextField prenomTF;

    @FXML
    private Button profileButton;

    @FXML
    private Pane profilePane;

    @FXML
    private TextField pseudoTF;

    @FXML
    private Button retourToMdp;

    @FXML
    private Button retourToProfile;

    @FXML
    private Button sauvegarderMdpChangement;

    @FXML
    private Button sauvegarderModificationsButton;

    @FXML
    private VBox slider;

    @FXML
    private VBox vboxDown;

    @FXML
    private VBox vboxUp;
    @FXML
    private AnchorPane selectModeAnchor;

    private static user loggedInUser;
    public static void setLoggedInUser(user user) {
        loggedInUser = user;
    }
    private static int rand;
    private static void setRand(int r) {
        rand = r;
    }
    userService serviceusers;
    Encryptor encryptor = new Encryptor();

    private void sendMail(ActionEvent event, int Rand, user user){
        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","bchirben8@gmail.com");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.debug",true);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback",false);
        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setSubject("Code de Confirmation d'oublie le mot de passe");
            message.setFrom(new InternetAddress("bchirben8@gmail.com"));
            message.setText("Voici code de Confirmation d'oublie le mot de passe : " + String.valueOf(Rand));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            try
            {
                clientFrontController.setRand(Rand);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com","bchirben8@gmail.com","oeopsajyvhamngzz");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void deconnecterButtonOnClick(ActionEvent event) {
        loggedInUser = null;
        serviceusers.changeScreen(event, "/login.fxml", "LOGIN");
    }

    @FXML
    void mdpOublieHLOnClick(ActionEvent event) {
        if (loggedInUser!=null){
            parametresMdpPane.setVisible(false);
            selectModeAnchor.setVisible(true);
        }
    }
    @FXML
    private void verifierEmailOnClick(ActionEvent event) throws SQLException {
        Random rd = new Random();
        int Rand = rd.nextInt(1000000 + 1);
        motDePasseOublie.setLoggedInUser(loggedInUser);
        motDePasseOublie.setRand(Rand);
        serviceusers.changeScreen(event, "/motDePasseOublie.fxml", "Vérifier le code");
        sendMail(event, Rand, loggedInUser);
    }

    @FXML
    private void verifierNumTelOnClick(ActionEvent event){
        Random rd = new Random();
        int Ra = rd.nextInt(1000000+1);
        motDePasseOublie.setRand(Ra);
        String message = "Bonjour " + loggedInUser.getNom() + " Voici votre code de Confirmation de le mot de passe : " + String.valueOf(Ra);
        System.out.println(message);
        String num = "+216"+String.valueOf(loggedInUser.getNumTel());
        System.out.println(num);
        SendSMS.SendSMS(message, num);
        motDePasseOublie.setLoggedInUser(loggedInUser);
        serviceusers.changeScreen(event, "/motDePasseOublie.fxml", "Vérifier le code");

    }
    @FXML
    private void retourButtonOnClick(ActionEvent event){
        parametresMdpPane.setVisible(true);
        selectModeAnchor.setVisible(false);
    }

    @FXML
    void motDePasseButtonOnClick(ActionEvent event) {
        changementPane.setVisible(false);
        parametresMdpPane.setVisible(true);
        profilePane.setVisible(false);

    }


    @FXML
    void profileButtonOnClick(ActionEvent event) {
        changementPane.setVisible(false);
        parametresMdpPane.setVisible(false);
        profilePane.setVisible(true);
        sauvegarderModificationsButton.setVisible(false);
    }

    private boolean getErrorsMdp() throws NoSuchAlgorithmException {
        String thisuserMdp = loggedInUser.getMdp();
        String mdpSaisi = encryptor.encryptString(ancienMdpTF.getText());
        if(!thisuserMdp.equals(mdpSaisi)){
            acienMdpErrorLabel.setTextFill(Color.RED);
            acienMdpErrorLabel.setText("Ancien Mot de passe invalide");
            return true;
        }
        if(nouveauMdpTF.getText().isBlank()|| nouveauMdpTF.getText().length() < 8 || nouveauMdpTF.getText().matches("[^a-zA-Z0-9]")){
            confirmerMdpErrorLabel.setTextFill(Color.RED);
            confirmerMdpErrorLabel.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmerNouveauMdpTF.getText().isBlank()){
            confirmerMdpErrorLabel.setTextFill(Color.RED);
            confirmerMdpErrorLabel.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmerNouveauMdpTF.getText(), nouveauMdpTF.getText())){
            confirmerMdpErrorLabel.setTextFill(Color.RED);
            confirmerMdpErrorLabel.setText("Le mot de passe doit etre le meme");
            return true;
        }
        return false;
    }
    @FXML
    void sauvegarderMdpChangementOnClick(ActionEvent event) throws NoSuchAlgorithmException {
        if (!getErrorsMdp()){
            try {
                serviceusers.modifierMdp(loggedInUser, encryptor.encryptString(nouveauMdpTF.getText()));
                JOptionPane.showMessageDialog(null,"Mot de Passe modifié avec succès !");
                parametresMdpPane.setVisible(false);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void modifierProfileOnClick(ActionEvent event) {
        changementPane.setVisible(false);
        parametresMdpPane.setVisible(false);
        profilePane.setVisible(true);
        sauvegarderModificationsButton.setVisible(true);
    }


    public boolean getErrors(){
        if(pseudoTF.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Le Pseudo est invalid", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(cinTF.getText().isBlank() || !cinTF.getText().matches("\\d{8}")){
            JOptionPane.showMessageDialog(null, "Le CIN est invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(nomTF.getText().isBlank() || !nomTF.getText().matches("[a-zA-Z ]+")){
            JOptionPane.showMessageDialog(null, "Le nom est invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(prenomTF.getText().isBlank() || !prenomTF.getText().matches("[a-zA-Z ]+")){
            JOptionPane.showMessageDialog(null, "Le prénom est invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(ageTF.getText().isBlank() || !ageTF.getText().matches("\\d+") || Integer.parseInt(ageTF.getText()) < 18){
            JOptionPane.showMessageDialog(null, "L'âge doit être un nombre valide et être supérieur à 18", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(numTelTF.getText().isBlank() || !numTelTF.getText().matches("\\d{1,12}")){
            JOptionPane.showMessageDialog(null, "Le numéro de téléphone est invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(emailTF.getText().isBlank() || !emailTF.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            JOptionPane.showMessageDialog(null, "L'email est invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    @FXML
    void sauvegarderModificationsButtonOnClick(ActionEvent event) {
        if (!getErrors()) {
            try {
                String oldEmail = loggedInUser.getEmail();
                user newUser = new user(loggedInUser.getPseudo(), Integer.parseInt(cinTF.getText()), nomTF.getText(),
                        prenomTF.getText(), Integer.parseInt(ageTF.getText()), Integer.parseInt(numTelTF.getText()),
                        emailTF.getText(), loggedInUser.getMdp(), loggedInUser.getRole());
                if (!oldEmail.equals(emailTF.getText())){
                    Random rd = new Random();
                    int Ra = rd.nextInt(1000000+1);
                    setRand(Ra);
                    sendMail(event, Ra, loggedInUser);
                    changementPane.setVisible(true);
                    profilePane.setVisible(false);
                    messageLabel.setTextFill(Color.WHITE);
                    messageLabel.setText("Vous avez modifié votre adresse e-mail c'est pour cela vous avez reçu un code de vérification dans votre ancien email pour vérifier votre identité!");
                    confirmerAdresseCode1.setVisible(false);
                    confirmerAdresseCode.setVisible(true);
                }else {
                    if (serviceusers.pseudoExiste(newUser.getPseudo())) {
                        serviceusers.update(newUser);
                        setData();
                        JOptionPane.showMessageDialog(null,"Modification effectuée! ");
                        System.out.println("user modifié avec succès !");
                    }else {
                        // Si l'utilisateur n'existe pas, appeler la méthode ajouter
                        newUser.setRole("Client");
                        Random rd = new Random();
                        int Rdd = rd.nextInt(1000000 + 1);
                        String newMdp = encryptor.encryptString(String.valueOf(Rdd));
                        newUser.setMdp(newMdp);
                        serviceusers.add(newUser);
                        setData();
                        ancienMdpTF.setText(String.valueOf(Rdd));
                        loggedInUser = newUser;
                        JOptionPane.showMessageDialog(null, "Inscription avec succés! Bienvenue à XtraTime \n Voici votre nouveau mot de passe : " + Rdd + " \n vous pouvez le modifier quand tu veux.");
                    }
                }
            } catch (SQLException | NoSuchAlgorithmException e) {
                System.out.println("Erreur lors de la modification de l'user : " + e.getMessage());
            }

        }
    }

    @FXML
    void retourToProfileOnClick(ActionEvent event) {
        changementPane.setVisible(false);
        profilePane.setVisible(true);
    }
    @FXML
    void confirmerAdresseCodeOnClick(ActionEvent event) {
        String codeTexte = codeVerification.getText().trim(); // Supprimer les espaces blancs au début et à la fin de la chaîne
        try {
            int code = Integer.parseInt(codeTexte);
            if (code == rand) {
                user newUser = new user(loggedInUser.getPseudo(), Integer.parseInt(cinTF.getText()), nomTF.getText(),
                        prenomTF.getText(), Integer.parseInt(ageTF.getText()), Integer.parseInt(numTelTF.getText()),
                        emailTF.getText(), loggedInUser.getMdp(), loggedInUser.getRole());
                messageLabel.setTextFill(Color.WHITE);
                messageLabel.setText("Merci de votre coopération, maintenant vous avez reçu un autre code de vérification dans votre nouvelle adresse e-mail pour vérifier votre identité!");
                confirmerAdresseCode1.setVisible(true);
                confirmerAdresseCode.setVisible(false);
                codeVerification.clear();
                Random rd = new Random();
                int Ra = rd.nextInt(1000000+1);
                setRand(Ra);
                sendMail(event, Ra, newUser);
            } else {
                JOptionPane.showMessageDialog(null,"Code incorrect ! ");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Code incorrect ! ");
            e.printStackTrace(); // Afficher la trace de la pile pour le débogage
        }
    }

    @FXML
    void confirmerAdresseCodeOnClick1(ActionEvent event) {
        if (Integer.parseInt(codeVerification.getText()) == rand){
            user newUser = new user(loggedInUser.getPseudo(), Integer.parseInt(cinTF.getText()), nomTF.getText(),
                    prenomTF.getText(), Integer.parseInt(ageTF.getText()), Integer.parseInt(numTelTF.getText()),
                    emailTF.getText(), loggedInUser.getMdp(), loggedInUser.getRole());
            try {
                serviceusers.update(newUser);
                JOptionPane.showMessageDialog(null,"Modification effectuée! ");
                System.out.println("user modifié avec succès !");
                changementPane.setVisible(false);
                profilePane.setVisible(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Code incorrecte! ");
        }
    }

    @FXML
    void initialize() throws SQLException {
        pseudoTF.setStyle("-fx-text-fill: black;");
        cinTF.setStyle("-fx-text-fill: black;");
        nomTF.setStyle("-fx-text-fill: black;");
        prenomTF.setStyle("-fx-text-fill: black;");
        numTelTF.setStyle("-fx-text-fill: black;");
        emailTF.setStyle("-fx-text-fill: black;");
        ageTF.setStyle("-fx-text-fill: black;");
        ancienMdpTF.setStyle("-fx-text-fill: black;");
        confirmerNouveauMdpTF.setStyle("-fx-text-fill: black;");
        nouveauMdpTF.setStyle("-fx-text-fill: black;");
        codeVerification.setStyle("-fx-text-fill: black;");
        setData();
    }
    private void setData() throws SQLException {
        serviceusers = new userService();
        if (loggedInUser!=null){
            if (serviceusers.pseudoExiste(loggedInUser.getPseudo())){
                user user = serviceusers.afficherParPseudo(loggedInUser.getPseudo());
                pseudoTF.setText(user.getPseudo());
                cinTF.setText(String.valueOf(user.getCin()));
                nomTF.setText(user.getNom());
                prenomTF.setText(user.getPrenom());
                ageTF.setText(String.valueOf(user.getAge()));
                numTelTF.setText(String.valueOf(user.getNumTel()));
                emailTF.setText(user.getEmail());
            }
            else {
                pseudoTF.setText(loggedInUser.getPseudo());
                cinTF.setText(String.valueOf(loggedInUser.getCin()));
                nomTF.setText(loggedInUser.getNom());
                prenomTF.setText(loggedInUser.getPrenom());
                ageTF.setText(String.valueOf(loggedInUser.getAge()));
                numTelTF.setText(String.valueOf(loggedInUser.getNumTel()));
                emailTF.setText(loggedInUser.getEmail());
            }
        }
    }

}