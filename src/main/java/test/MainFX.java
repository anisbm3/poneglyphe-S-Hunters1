package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {
   public void start(Stage primaryStage){
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/ajouterDebat.fxml"));
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);
           primaryStage.show();
       } catch ( IOException e) {
           System.out.println(e.getMessage());
       }
   }
   }

