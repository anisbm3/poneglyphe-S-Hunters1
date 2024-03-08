package tn.esprit.controlls;

import tn.esprit.models.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import tn.esprit.services.ServiceCommentaire;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCommentairesController implements Initializable {

    @FXML
    private VBox CommentairesVBox;

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
        List<Commentaire> commentaires = SC.afficherByuser(clientFrontController.loggedInUser.getPseudo());
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

}
