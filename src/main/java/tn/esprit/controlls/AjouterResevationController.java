package tn.esprit.controlls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.esprit.models.Evenement;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvenement;
import tn.esprit.services.ServiceReservation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterResevationController {

    @FXML
    private Button ajouterbtn;

    @FXML
    private TextField etatRes;

    @FXML
    private TextField nbPlace;

    @FXML
    private ChoiceBox<String> nomEvent;

    @FXML
    private TextField nomRES;

    private final ServiceReservation SR = new ServiceReservation();
    private final ServiceEvenement SE = new ServiceEvenement();
    @FXML
    public void initialize() {
        List<String> list=SE.listevenement();
        System.out.println(list);
        nomEvent.setItems(FXCollections.observableArrayList(list));
    }
    @FXML
    void onClickedAjouter(ActionEvent event) {

        try {
            Reservation reservation=new Reservation(nomRES.getText(),Integer.parseInt(nbPlace.getText()),etatRes.getText(),nomEvent.getValue());
            System.out.println(reservation);
            SR.ajouter(reservation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
