package tn.poneglyphe.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public   void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCosplay.fxml"));

        try {
            Parent root = loader.load();

            Scene sc = new Scene(root);
            primaryStage.setTitle("Cosplay");
            primaryStage.setScene(sc);
            primaryStage.show();
            //sc.getStylesheets().add("C:\\Users\\sabri\\OneDrive\\Bureau\\poneglyphe-S-Hunters1\\CosplayJv\\src\\main\\resources\\style.css");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
