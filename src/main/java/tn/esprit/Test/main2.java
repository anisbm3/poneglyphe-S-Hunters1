/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.Test;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;

/**
 *
 * @author
 */
public class main2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
    public static void  main (String[] args){
        launch(args);
    }

}
