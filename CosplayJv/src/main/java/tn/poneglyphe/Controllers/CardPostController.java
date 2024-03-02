package tn.poneglyphe.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tn.poneglyphe.Models.entities.Cosplay;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CardPostController  {
    @FXML
    private ImageView audience;

    @FXML
    private Label caption;

    @FXML
    private Label labeldate;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgcosplay;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgReaction;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgVerified;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbShares;

    @FXML
    private Label reactionName;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private Label username;
    @FXML
    private Label typemat;
    @FXML
    private Label nomCosp;

    @FXML
    private Label personnage;

    private Cosplay cosplay;

    //@Override
   /* public void initialize(URL location, ResourceBundle resources) {
        //setData();
    }*/
    @FXML
    void onLikeContainerMouseReleased(MouseEvent event) {

    }

    @FXML
    void onLikeContainerPressed(MouseEvent event) {

    }

    @FXML
    void onReactionImgPressed(MouseEvent event) {

    }

    public void setData(Cosplay cosplay){
        this.cosplay = cosplay;
        Image img;
        /*img = new Image(getClass().getResourceAsStream(cosplay.getAccount().getProfileImg()));
        imgProfile.setImage(img);
        username.setText(cosplay.getAccount().getName());
        if(cosplay.getAccount().isVerified()){
            imgVerified.setVisible(true);
        }else{
            imgVerified.setVisible(false);
        }*/
        Date date = cosplay.getDateCreation();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Specify the desired date format
        String dateString = dateFormat.format(date); // Convert the date to a string in the desired format
        labeldate.setText(dateString);
       // date.setDate(cosplay.getDateCreation());
       /* if(cosplay.getAudience() == cosplayAudience.PUBLIC){
            img = new Image(getClass().getResourceAsStream(cosplayAudience.PUBLIC.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(cosplayAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);*/

        if(cosplay.getDescriptionCp() != null && !cosplay.getDescriptionCp().isEmpty()){
            caption.setText(cosplay.getDescriptionCp());
        }else{
            caption.setManaged(false);
        }

        if(cosplay.getImageCp() != null && !cosplay.getImageCp().isEmpty()){
            img = new Image(getClass().getResourceAsStream(cosplay.getImageCp()));
            imgcosplay.setImage(img);
        }else{
            imgcosplay.setVisible(false);
            imgcosplay.setManaged(false);
        }
        personnage.setText(String.valueOf(cosplay.getPersonnage()));
        nomCosp.setText(String.valueOf(cosplay.getNomCp()));
        typemat.setText(String.valueOf(cosplay.getNomMa()));
       /* nbReactions.setText(String.valueOf(cosplay.getTotalReactions()));
        currentReaction = Reactions.NON;*/
    }


}
