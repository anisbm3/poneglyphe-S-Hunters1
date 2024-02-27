package tn.poneglyphe.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import  java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AjouterCosplayController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button btn;
    @FXML
    private URL location;

    @FXML
    private Label tf_label;
    @FXML
    void onbutton(ActionEvent event) {}

    @FXML
    void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void addcosplay(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCosplay.fxml"));
        try {
            Parent root =loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PostCosplayContr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

