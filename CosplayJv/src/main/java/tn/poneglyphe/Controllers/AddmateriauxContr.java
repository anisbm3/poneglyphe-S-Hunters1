package tn.poneglyphe.Controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tn.poneglyphe.Models.entities.Materiaux;
import tn.poneglyphe.Controllers.ViewmateriauxController;
import tn.poneglyphe.Services.CrudMateriaux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    //private Materiaux selectedMateriaux;
   //  int id;
   // Materiaux materiaux;
   // ObservableList<Materiaux> data = FXCollections.observableArrayList();
   /* MatCardLoader = new FXMLLoader(getClass().getResource("/Viewmateriaux.fxml"));
    loadLivraisonData();*/
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






   /* @FXML
    public void EditMateriaux() {
        if (selectedMateriaux != null) {
            System.out.println("ooooooooooooooo");
            try {
                // Mise à jour des attributs de la livraison avec les nouvelles valeurs
                selectedMateriaux.setNomMa(tfnma.getText());
                System.out.println(tfnma.getText());
                selectedMateriaux.setTypeMa(tftype.getText());
                selectedMateriaux.setDisponibilite(tfdispo.getText());

                System.out.println(selectedMateriaux);
                // Appel de la méthode de mise à jour du service de livraison
                cm.update(selectedMateriaux);

                // Rafraîchir la liste des livraisons pour afficher les modifications
                loadMatData();

                // Afficher un message de succès
                showAlert(Alert.AlertType.INFORMATION, "Succès", "La livraison a été mise à jour avec succès.");

            } catch (NumberFormatException e) {
                // En cas d'erreur de conversion de type, afficher un message d'erreur
                showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir des valeurs numériques valides pour la quantité et le montant.");
            } catch (Exception e) {
                // En cas d'erreur, afficher un message d'erreur
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de la mise à jour de la livraison : " + e.getMessage());
            }
        }

    }*/
/*  private  void  loadMatData(){

        ArrayList<Materiaux> materiaux =cm.getAll() ;


      flowPaneMat.getChildren().clear();
      for (Materiaux  material: materiaux) {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/Viewmateriaux.fxml"));
              AnchorPane card = loader.load();
              ViewmateriauxController controller = loader.getController();
              controller.initialize(material); // Initialise la carte avec les données de la livraison
              card.setUserData(material); // Définit l'objet Livraison comme userData de la carte
              card.getProperties().put("controller", controller); // Définit le contrôleur comme propriété de la carte
              flowPaneMat.getChildren().add(card);

              // Mettre en place l'événement de sélection de la carte de livraison
              card.setOnMouseClicked(event -> {
                  onLivraisonSelected(event);
                  updateLivraisonCards(); // Mettre à jour l'apparence des cartes de livraison
              });
          } catch (IOException e) {
              showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des données de livraison.");
              e.printStackTrace();
          }
      }
    }*/
  /*  @FXML
    void DeleteMateriauxSelected(ActionEvent event) {
        cm.delete(selectedMateriaux);
        loadMatData(); // Recharger les données de livraison
    }*/


   /* private ViewmateriauxController getSelectedCardController() {
        AnchorPane selectedCard = getSelectedCard();
        if (selectedCard != null) {
            return (ViewmateriauxController)  selectedCard.getProperties().get("controller");
        }
        return null;
    }

    @FXML
    void RefrecheListe(ActionEvent event) {
        loadMatData();
    }
    public void showCards() {
        List<Materiaux> material = getMateriaux();
        flowPaneMat.getChildren().clear();
        for (Materiaux material : Materiaux) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Viewmateriaux.fxml"));
                AnchorPane card = loader.load();
                ViewmateriauxController controller = loader.getController();
                // Initialiser les éléments de la carte avec les données de la livraison
                System.out.println("Contrôleur chargé: " + controller.getClass().getName()); // Vérifier le contrôleur chargé
                controller.initialize(material);
                // Ajouter la carte au FlowPane
                flowPaneMat.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}





}
