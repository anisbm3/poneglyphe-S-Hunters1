package tn.poneglyphe.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import  java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.poneglyphe.Models.entities.Cosplay;

public class AjouterCosplayController implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button btn;
    @FXML
    private URL location;
    @FXML
    private VBox cosplayContainer;
    private   ArrayList<Cosplay> cosplays ;
    @FXML
    void onbutton(ActionEvent event) {}
    public void setCosplays(ArrayList<Cosplay> cosplays) {
        // Set the received list of cosplays to the local field
        this.cosplays = cosplays;
        cosplayContainer.getChildren().clear();
    }

    @FXML
   public void initialize(URL url, ResourceBundle rb) {

        cosplays = new ArrayList<>(getCosplays());
        for (Cosplay cosplay : cosplays) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPost.fxml"));
            try {
                Node cardNode = loader.load();
                CardPostController cosplayCardController = loader.getController();
                cosplayCardController.setData(cosplay); // Set data for the cosplay card
                cosplayContainer.getChildren().add(cardNode); // Add the card to the container
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

public ArrayList<Cosplay>getCosplays(){
        ArrayList<Cosplay> cs = new ArrayList<>();

    int totalCosplays = getNumberOfCosplays(); // Method to get total number of cosplays

    for (int i = 1; i <= totalCosplays; i++) {

        Cosplay cosplay = getCosplayById(i); // Method to get cosplay by ID
        if (cosplay != null) {
            cs.add(cosplay);
        }
    }

    return cs;
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
    private Cosplay getCosplayById(int id) {
        // Implement logic to fetch a cosplay from your data source based on its ID (e.g., database query)
        // For demonstration purposes, let's assume you have a list of cosplays stored in a variable named `cosplaysList`
        // and you want to retrieve a cosplay by its index in the list.

        // Check if the ID is within the valid range of indices in the cosplaysList
        if (id >= 0 && id < cosplays.size()) {
            return cosplays.get(id); // Return the cosplay at the specified index
        } else {
            return null; // Return null if the ID is out of range
        }
    }

    private int getNumberOfCosplays() {
        // Implement logic to retrieve the number of cosplays from your data source
        // For example, if you're fetching cosplays from a database, you can execute a query to count the number of cosplays.

        // For demonstration purposes, let's assume you have a list of cosplays stored in a variable named `cosplaysList`.
        // You can return the size of this list as the number of cosplays.

        if (cosplays != null) {
            return cosplays.size(); // Return the number of cosplays in the list
        } else {
            return 0; // Return 0 if the list is null or empty
        }
    }



}

