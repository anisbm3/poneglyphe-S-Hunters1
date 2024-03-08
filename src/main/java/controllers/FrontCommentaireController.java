package controllers;


import entities.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceCommentaire;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontCommentaireController implements Initializable  {
    @FXML
    private VBox CommentairesVBox;


    @FXML
    private Button front1;
    @FXML
    private Button SuppBtn;

    @FXML
    private Button modifierBtn;

    @FXML
    private TextField nomMES;

    @FXML
    private ChoiceBox<String> nomSUJ;

    @FXML
    private VBox VBOXcommentaire;

    @FXML
    private ScrollPane scrollPane;

    private final ServiceCommentaire SC = new ServiceCommentaire();

    private Commentaire selectedCommentaire;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refrechCommentaire();
            // refreshReservations();
            // setupSearchBarListener(); // Appel du gestionnaire pour la recherche
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refrechCommentaire() throws SQLException {
        List<Commentaire> commentaires = SC.afficher();
        VBOXcommentaire.getChildren().clear();

        for (Commentaire commentaire :commentaires) {
            Label reservationLabel = new Label("Sujet_Debat: " + commentaire.getSujet_Debat() +
                    ", Message: " + commentaire.getMessage());

            reservationLabel.setOnMouseClicked(event -> {
                selectedCommentaire = commentaire;
                nomMES.setText(selectedCommentaire.getMessage());
                nomSUJ.setValue(selectedCommentaire.getSujet_Debat());
            }) ;

            VBOXcommentaire.getChildren().addAll(reservationLabel);
        }

        nomSUJ.getItems().clear();
        for (Commentaire commentaire : commentaires) {
            nomSUJ.getItems().add(commentaire.getSujet_Debat());
        }

        nomSUJ.setOnAction(event -> {
            if (selectedCommentaire != null) {
                selectedCommentaire.setSujet_Debat(nomSUJ.getValue());
            }
        });
    }

    @FXML
    void onModifierClicked(ActionEvent event) {

        if (selectedCommentaire != null) {
            try {
                selectedCommentaire.setMessage(nomMES.getText());


                String selectedEvent = nomSUJ.getValue();
                if (selectedEvent != null) {
                    selectedCommentaire.setSujet_Debat(selectedEvent);
                } else {
                    System.out.println("erreur");
                }

                SC.modifier(selectedCommentaire);
                refrechCommentaire();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void onSupprimerClicked(ActionEvent event) {
        if (selectedCommentaire != null) {
            try {
                int res = selectedCommentaire.getID_Commentaire();
                SC.supprimer(new Commentaire(res, nomMES.getText(), nomSUJ.getValue()));
                refrechCommentaire();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void onClickedAjouter(ActionEvent actionEvent) {
        if (nomMES.getText().isEmpty() ||  nomSUJ.getValue() == null) {
            afficherNotification("Erreur de saisie", "Veuillez remplir tous les champs.");
            return; // Sortir de la méthode si les champs ne sont pas remplis
        }

        // Créer la réservation si toutes les conditions sont remplies
        Commentaire commentaire = new Commentaire(nomMES.getText(),nomSUJ.getValue());
        System.out.println(commentaire);
        try {
            SC.ajouter(commentaire);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Afficher une notification de succès
        afficherNotification("Ajout avec succès", "La commentaire a été ajoutée avec succès.");
    }
    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
