package eh.gestionlivraison.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) {
        try {

          //  URL url = new File("src/main/resources/AjouterFacture.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}