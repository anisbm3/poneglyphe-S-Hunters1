package tn.esprit.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tn.esprit.models.Evenement;
import tn.esprit.services.ServiceEvenement;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import tn.esprit.services.userService;

import java.io.File;
public class FrontEventController implements Initializable {

    @FXML
    private AnchorPane rootPane; // Définir la racine du fichier FXML

    @FXML
    private VBox EventVBox;

    @FXML
    private Button closeButton;

    @FXML
    private MediaView mediaview;

    @FXML
    private Button pauseButon;

    @FXML
    private Button playButton;

    @FXML
    private Button selectMedia;

    @FXML
    private Button ReserverBtn;
    @FXML
    private Slider volumeSlider;


    @FXML
    private Button BackBtn;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean isPlayed = true;

    userService serviceUtilisateurs = new userService();

    @FXML
    private Label lblDuration;
    @FXML
    void resetMedia(ActionEvent event) {
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
            playButton.setText("Pause");
        }


    }


    @FXML
    void OnReserverClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/ajouterResevation.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("reservation");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void btnPlay(MouseEvent event) {
                 mediaPlayer.play();
    }

    @FXML
    void selectMedia(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.m4v", "*.mp3", "*.wav", "*.aac"));
        File selectedFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (selectedFile != null) {
            Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
        mediaview.setVisible(true);
        pauseButon.setVisible(true);
        playButton.setVisible(true);
        volumeSlider.setVisible(true);
        lblDuration.setVisible(true);
        closeButton.setVisible(true); // Optionally hide the close button itself
        volumeSlider.setVisible(true);

    }


    @FXML
    void OnClickedBack(ActionEvent event) {
        serviceUtilisateurs.changeScreen(event, "/tn/esprit/clientFront.fxml", "client front");

    }

    @FXML
    void closeVideo(ActionEvent event) {
        // Stop the video playback
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // Hide the MediaView and other UI components
        mediaview.setVisible(false);
        pauseButon.setVisible(false);
        playButton.setVisible(false);
        volumeSlider.setVisible(false);
        lblDuration.setVisible(false);
        closeButton.setVisible(false); // Optionally hide the close button itself
        volumeSlider.setVisible(false);
    }

    private final ServiceEvenement SE = new ServiceEvenement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshEvents();

        file = new File("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\videoplayback.mp4"); // Assurez-vous que le fichier se trouve dans le répertoire de votre application
        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            mediaview.setMediaPlayer(mediaPlayer);

            // Volume control
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            volumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeSlider.getValue()));

            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                volumeSlider.setValue(newValue.toSeconds());
                lblDuration.setText("Duration: " + (int) volumeSlider.getValue() + " / " + (int) media.getDuration().toSeconds());
            }));

            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = media.getDuration();
                volumeSlider.setMax(totalDuration.toSeconds());
                lblDuration.setText("Duration: 00 / " + (int) media.getDuration().toSeconds());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshEvents() {
        List<Evenement> events = SE.afficher();
        EventVBox.getChildren().clear();
        for (Evenement evenement : events) {
            Label eventLabel = new Label(
                    "Nom: " + evenement.getNom_Event() +
                            ", Description: " + evenement.getDescription_Event() +
                            ", Lieu: " + evenement.getLieu_Event() +
                            ", Date: " + evenement.getDate_Event().toString()
            );

            Separator separator = new Separator(Orientation.HORIZONTAL);

            EventVBox.getChildren().addAll(eventLabel, separator);
        }
    }

    @FXML
    void onClickedPause(ActionEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) {
        refreshEvents();
    }

    public void sliderPressed(javafx.scene.input.MouseEvent mouseEvent) {
        mediaPlayer.seek(Duration.seconds(volumeSlider.getValue()));
    }
}

