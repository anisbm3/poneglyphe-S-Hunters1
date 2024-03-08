package tn.esprit.controlls;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import tn.esprit.models.Materiaux;
import tn.esprit.services.CrudMateriaux;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;



public class AddmateriauxContr implements Initializable   {
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button del;
    @FXML
    private Button edit;
    @FXML
    private FlowPane flowPaneMat;

    @FXML
    private VBox evbox;


    @FXML
    private URL location;

    @FXML
    private TextField tfdispo;

    @FXML
    private TextField tfnma;

    @FXML
    private TextField tftype;
    @FXML
    private Button ajout;
    @FXML
    private VBox EventVbox;
    @FXML
    private Label dispoLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    private FXMLLoader MatCardLoader;
   private final CrudMateriaux cm =new CrudMateriaux();
     private ArrayList<Materiaux> materiau = new ArrayList<>();
    private Materiaux selectedMateriau;

@Override
    public void initialize(URL location, ResourceBundle resources) {

       refreshMaterials();

    }
    @FXML
    void ajoutmat(ActionEvent event) {
        if (tfnma.getText().isEmpty() || tftype.getText().isEmpty() || tfdispo.getText().isEmpty() ) {
            showAlert("Champs obligatoires", "Veuillez remplir tous les champs.");
            return;
        }
             //EventVbox.getChildren().clear();
        for (Materiaux materiaux : materiau) {
            Label vboxMat = new Label ("Nom: " + materiaux.getNomMa() + ",Type: " + materiaux.getTypeMa()+
                    ",Disponibilité: " + materiaux.getDisponibilite() );
            Separator separator = new Separator();
            EventVbox.getChildren().addAll(vboxMat, separator);
        }
        cm.add(new Materiaux(tfnma.getText(),tftype.getText(),tfdispo.getText()));

        Label vboxMat = new Label("Nom: " + tfnma.getText() + ", Type: " + tftype.getText() +
                ", Disponibilité: " + tfdispo.getText());
        vboxMat.setStyle("-fx-padding: 0 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        EventVbox.getChildren().add(0, vboxMat);
        EventVbox.getChildren().add(1, separator);
        //EventVbox.getChildren().addAll(0,vboxMat, separator);

        tfnma.clear();
        tftype.clear();
        tfdispo.clear();
    }
    @FXML
    void onModifierMatClicked(ActionEvent event) {
        if (selectedMateriau != null) {
            try {
                selectedMateriau.setNomMa(tfnma.getText());
                selectedMateriau.setTypeMa(tftype.getText());
                selectedMateriau.setDisponibilite(tfdispo.getText());


                cm.update(selectedMateriau);


                refreshMaterials();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }
@FXML
    void onDeleteMatClicked(ActionEvent event) {
        if (selectedMateriau != null) {
            try {
                // Delete the selected material from the database
                cm.delete(selectedMateriau); // Assuming you have a method to delete by ID

                // Refresh the display of materials
                refreshMaterials();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }

    private void refreshMaterials() {
        try {
            // Clear the existing content of EventVbox
            EventVbox.getChildren().clear();

            // Fetch the updated list of materials from the database
            ArrayList<Materiaux> materiau = cm.getAll();
            if (materiau!= null) {
                // Add each material to the EventVbox
                for (Materiaux materiaux : materiau) {
                    Label vboxMat = new Label("Nom: " + materiaux.getNomMa() +
                            ", Type: " + materiaux.getTypeMa() +
                            ", Disponibilité: " + materiaux.getDisponibilite());

                    // Set mouse click event handler for each label
                    vboxMat.setOnMouseClicked(event -> {
                        // Handle mouse click on label

                        selectedMateriau = materiaux;

                        // Set the extracted material data into text fields for modification

                        tfnma.setText(selectedMateriau.getNomMa());
                        tftype.setText(selectedMateriau.getTypeMa());
                        tfdispo.setText(selectedMateriau.getDisponibilite());

                    });

                    Separator separator = new Separator();
                    EventVbox.getChildren().addAll(vboxMat, separator);
                }
            }else {
                System.out.println("No materials found."); // Print message or handle accordingly
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }














    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}





}
