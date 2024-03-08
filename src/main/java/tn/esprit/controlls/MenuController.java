package tn.esprit.controlls;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import com.sun.speech.freetts.Voice;
        import com.sun.speech.freetts.VoiceManager;
        import javafx.stage.StageStyle;


public class MenuController implements Initializable {
@FXML
private Button catalogueBtn;
    @FXML
    private Button debatBtn;
    @FXML
    private Button Eventbtn;

    @FXML
    private Button RESbtn;

    @FXML
    private Button userBtn;


    @FXML
    private Button AjoutBTN;


    @FXML
    private Button AjoutEvent;

    @FXML
    private Button EVENTFrontBTN;

    @FXML
    private Button integre;

    private Voice voice;
    // Speak a phrase

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/BackReservation.fxml"));
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
    @FXML
    void OnClickedAjoutEvent(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ajouterEvent.fxml"));
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
    void onClickedUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/login.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("login");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onClickedEventFront(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/FrontEvent.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String phrase = "welcome to laugh-tale";
        voice.speak(phrase);

        // Deallocate the voice when done
        voice.deallocate();
        // Set the system property for FreeTTS voices directory
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Get the default voice
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();

        // Print available voices
        System.out.println("Available voices:");
        for (Voice v : voices) {
            System.out.println("- " + v.getName());
        }

        // Choose a voice (e.g., "kevin" or "kevin16")
        voice = voiceManager.getVoice("kevin");
        if (voice != null) {
            voice.allocate();
        } else {
            System.err.println("Cannot find voice: kevin16");
        }

    }*/
   @FXML
   void OnclickedCatalogueBtn(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/Catalogue.fxml"));
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
    void OnClickedButtonDebat(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/AjouterDebats.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            // Show the ajout scene
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the system property for FreeTTS voices directory
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Get the default voice
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();

        // Print available voices
        System.out.println("Available voices:");
        for (Voice v : voices) {
            System.out.println("- " + v.getName());
        }

        // Choose a voice (e.g., "kevin" or "kevin16")
        voice = voiceManager.getVoice("kevin");
        if (voice != null) {
            voice.allocate();
        } else {
            System.err.println("Cannot find voice: kevin16");
        }

        // Speak a phrase
        String phrase = "Welcome to laugh-tale";
        if (voice != null) {
            voice.speak(phrase);
        }

        // Deallocate the voice when done
        if (voice != null) {
            voice.deallocate();
        }
    }

    @FXML
    void onIntegreBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/Catalogue.fxml"));
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
