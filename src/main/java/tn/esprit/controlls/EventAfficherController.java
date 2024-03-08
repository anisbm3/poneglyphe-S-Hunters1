package tn.esprit.controlls;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.userService;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;


public class EventAfficherController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button triBtn;

    @FXML
    private TextField searchBar;
    userService serviceUtilisateurs = new userService();


    @FXML
    private Button revenirEnArriere;
    @FXML
    private Button reserverBtn;
    @FXML
    private VBox EventVBox;

    @FXML
    private TextField Descfield;
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField nomfield;
    @FXML
    private TextField lieufield;
    @FXML
    private Button suppbtn;

    @FXML
    private Button modfierbtn;
    private Evenement selectedEvenement;

        @FXML
        void onClicekdSupp(ActionEvent event) {
            if (selectedEvenement != null) {
                int res = selectedEvenement.getID_Event();
                SE.supprimer(new Evenement(res, nomfield.getText(),Descfield.getText(), lieufield.getText() , dateField.getValue().atStartOfDay()));
                refreshEvents();
            }

        }

        @FXML
        void onClickedModifier(ActionEvent event) {

            if (selectedEvenement != null) {
                selectedEvenement.setNom_Event(nomfield.getText());

                selectedEvenement.setDescription_Event(Descfield.getText());
                selectedEvenement.setLieu_Event(lieufield.getText());

                selectedEvenement.setDate_Event(dateField.getValue().atStartOfDay());


                SE.modifier(selectedEvenement);
                refreshEvents();
            }



        }

    private String tri="ASC";
    private int i = 0;

    private final ServiceEvenement SE = new ServiceEvenement();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshEvents();
        triBtn.setOnAction(this::OnClickedTri);
    }


    @FXML
    void OnClickedTri(ActionEvent event) {
if(i % 2 == 0){
    tri = "ASC";
}else{
    tri = "DESC";

}
        i++;
        refreshEvents();
    }




    private void refreshEvents() {
        List<Evenement> events = SE.afficherbyNOM(tri);
        EventVBox.getChildren().clear();
        for (Evenement evenement : events) {

            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_Event() +
                            ", Description: " + evenement.getDescription_Event() +
                            ", Lieu: " + evenement.getLieu_Event() +
                            ", Date: " + evenement.getDate_Event().toString()
            );
            eventLabel.setOnMouseClicked(event -> {
                selectedEvenement = evenement;
               nomfield.setText(selectedEvenement.getNom_Event());
                Descfield.setText(selectedEvenement.getDescription_Event());
               lieufield.setText(selectedEvenement.getLieu_Event());
               dateField.setValue(selectedEvenement.getDate_Event().toLocalDate());
            });

            Separator separator = new Separator(Orientation.HORIZONTAL);

            EventVBox.getChildren().addAll(eventLabel, separator);
        }
    }


    @FXML
    void onclickedRevenir(ActionEvent event) {
        serviceUtilisateurs.changeScreen(event, "/tn/esprit/dashboard.fxml", "dashboard");

    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        refreshEvents();
    }


    @FXML
    void onReserverClicked(ActionEvent event) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ajouterResevation.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
            refreshEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


