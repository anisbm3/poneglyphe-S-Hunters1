


package tn.esprit.controlls;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;

        import java.io.IOException;

public class MenuController {

    @FXML
    private Button Eventbtn;

    @FXML
    private Button RESbtn;


    @FXML
    private Button AjoutBTN;

    @FXML
    void OnClickedEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/EventAfficher.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onClickedRes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ReservationAfficher.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void OnClickedAjout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/AjouterResevation.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
