package tn.poneglyphe.Controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.poneglyphe.Models.entities.Materiaux;
import tn.poneglyphe.Services.CrudMateriaux;

public class AddmateriauxContr {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfdispo;

    @FXML
    private TextField tfnma;

    @FXML
    private TextField tftype;
    @FXML
    private Button ajout;

    @FXML
    void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    void ajoutmat(ActionEvent event) {
        if (tfnma.getText().isEmpty() || tftype.getText().isEmpty() || tfdispo.getText().isEmpty() ) {
            showAlert("Champs obligatoires", "Veuillez remplir tous les champs.");
            return;
        }

        CrudMateriaux cm =new CrudMateriaux();
        cm.add(new Materiaux(tfnma.getText(),tftype.getText(),tfdispo.getText()));    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}





}
