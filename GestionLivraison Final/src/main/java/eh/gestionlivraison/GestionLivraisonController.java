package eh.gestionlivraison;
import  eh.gestionlivraison.LivraisonCardController;
import eh.gestionlivraison.Services.ServiceLivraison;
import eh.gestionlivraison.models.Livraison;
import eh.gestionlivraison.utils.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
public class GestionLivraisonController implements Initializable {
    @FXML
    private Label labelNomPrenom;
    @FXML
    private Label labelAdresse;
    @FXML
    private Label labelIDPannier;
    @FXML
    private Label labelQuantity;
    @FXML
    private Label labelMontant;
    @FXML
    private Label labelDate;
    @FXML
    private TextField AdresseField;
    @FXML
    private FlowPane flowPaneLivraison;
    @FXML
    private Button Qrcode;
    @FXML
    private TextField ID_PannierField;
    @FXML
    private TextField NomPrenomClientField;
    @FXML
    private Button Statistique1;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField TfMontantField;
    @FXML
    private TextField TfQuantityField;
    @FXML
    private Button brtn_DeleteAll;
    @FXML
    private Button btn_DeleteSelected;
    @FXML
    private Button btn_Edit;
    @FXML
    private Button btn_Refresh;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> cb_Produits;
    @FXML
    private Label labelProduits;
    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox recherche_avance;
    private Livraison selectedLivraison;
    ServiceLivraison sl = new ServiceLivraison();
    int id;
    Livraison l;
    private FXMLLoader livraisonCardLoader;
    private ObservableList<String> optionsProduits = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Chargement des données de livraison...");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionLivraison.fxml"));
        fillcomboProduit();

        livraisonCardLoader = new FXMLLoader(getClass().getResource("/LivraisonCard.fxml"));
        loadLivraisonData();

        System.out.println("Chargement des données de livraison...");

        // Remarque : il est généralement préférable de ne pas charger de nouvelles FXML ici, mais de le faire dans le chargement initial du contrôleur

        // Charger les données depuis la base de données
        loadProduitsFromDatabase();
        List<String> prod_name = Arrays.asList("game", "pull"); // Liste de produits récupérée depuis la base de données ou autre source
        cb_Produits.getItems().addAll(prod_name);
        // Afficher les produits dans le ComboBox
        // cb_Produits.setItems(optionsProduits); // Il semble que optionsProduits ne soit pas défini dans le code que vous avez partagé

        if (AdresseField != null) {
            AdresseField.setText("Votre texte ici");
        } else {
            System.err.println("AdresseField est null");
        }

        afficherProduitsDansComboBox(); // Correction de l'appel de la méthode
    }

    private void loadProduitsFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT prod_name FROM panier"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String prod_name= rs.getString("prod_name");
                optionsProduits.add(prod_name);
                System.out.println("Produit récupéré depuis la base de données : " + prod_name);
            }
            cb_Produits.setItems(optionsProduits); // Associer les produits à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    private void afficherProduitsDansComboBox() {
        if (cb_Produits != null) {
            System.out.println("ComboBoxProduits n'est pas null");
            cb_Produits.setItems(optionsProduits);
        } else {
            System.out.println("ERREUR : ComboBoxProduits est null");
        }
    }
    public void fillcomboProduit() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT prod_name FROM panier"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsProduits = FXCollections.observableArrayList();
            while (rs.next()) {
                String prod_name = rs.getString("prod_name");
                optionsProduits.add(prod_name);
                System.out.println("Produit récupéré depuis la base de données : " + prod_name);
            }
            cb_Produits.setItems(optionsProduits);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Livraison getL() {
        return l;
    }

    public void setL(Livraison l) {
        this.l = l;
    }

    public void initData(Livraison livraison) {
        if (livraison != null) {
            if (NomPrenomClientField != null) {
                NomPrenomClientField.setText(livraison.getNomPrenomClient());
            } else {
                System.err.println("NomPrenomClientField est null");
            }

            if (AdresseField != null) {
                AdresseField.setText(livraison.getAdresse());
            } else {
                System.err.println("AdresseField est null");
            }

          /*  if (ID_PannierField != null) {
                ID_PannierField.setText(String.valueOf(livraison.getID_Pannier()));
            } else {
                System.err.println("ID_PannierField est null");
            }*/

            if (TfQuantityField != null) {
                TfQuantityField.setText(String.valueOf(livraison.getQuantity()));
            } else {
                System.err.println("TfQuantityField est null");
            }

            if (TfMontantField != null) {
                TfMontantField.setText(String.valueOf(livraison.getMontant()));
            } else {
                System.err.println("TfMontantField est null");
            }

            if (dateField != null) {
                System.out.println("im here ");
                if (livraison.getDate() != null) {
                    System.out.println("Date avant assignation au DatePicker : " + livraison.getDate()); // Vérifiez la date avant l'assignation
                    dateField.setValue(livraison.getDate().toLocalDate());
                } else {
                    dateField.setValue(null);
                }
            } else {
                System.err.println("dateField est null");
            }

            if (labelNomPrenom != null) {
                labelNomPrenom.setText(livraison.getNomPrenomClient());
            } else {
                System.err.println("labelNomPrenom est null");
            }

            if (labelAdresse != null) {
                labelAdresse.setText(livraison.getAdresse());
            } else {
                System.err.println("labelAdresse est null");
            }

            if (labelIDPannier != null) {
                labelIDPannier.setText(String.valueOf(livraison.getPanier_id()));
            } else {
                System.err.println("labelIDPannier est null");
            }

            if (labelQuantity != null) {
                labelQuantity.setText(String.valueOf(livraison.getQuantity()));
            } else {
                System.err.println("labelQuantity est null");
            }

            if (labelMontant != null) {
                labelMontant.setText(String.valueOf(livraison.getMontant()));
            } else {
                System.err.println("labelMontant est null");
            }

            if (labelProduits != null) {
                labelProduits.setText(livraison.getProd_name());
            } else {
                System.err.println("labelProduits est null");
            }


            System.out.println("Date is :" + livraison.getDate());
            if (labelDate != null) {

                if (livraison.getDate() != null) {
                    Date date = livraison.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String dateString = sdf.format(date);
                    labelDate.setText(dateString);
                } else {
                    labelDate.setText("N/A");
                }
            } else {
                System.err.println("labelDate est null");
            }
        } else {
            System.err.println("Livraison est null");
        }
    }




    private List<Livraison> getLivraisons() {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        return serviceLivraison.getAll();
    }


    @FXML
    void DeleteAllLivraison(ActionEvent event) {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        boolean success = serviceLivraison.deleteAll();
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "tous les livraison sont supprimés!");
            loadLivraisonData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "erreur.");
        }
    }




    private void updateLivraisonCards() {
        for (Node node : flowPaneLivraison.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane card = (AnchorPane) node;
                LivraisonCardController controller = (LivraisonCardController) card.getProperties().get("controller");
                Livraison livraison = controller.getLivraison();
                if (livraison.isSelected()) {
                    card.setStyle("-fx-background-color: lightblue;");
                } else {
                    card.setStyle("-fx-background-color: white;");
                }
            }
        }
    }


    private void loadLivraisonData() {
        List<Livraison> livraisons = sl.getAll();

        flowPaneLivraison.getChildren().clear();
        for (Livraison livraison : livraisons) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LivraisonCard.fxml"));
                AnchorPane card = loader.load();
                LivraisonCardController controller = loader.getController();
                controller.initialize(livraison); // Initialise la carte avec les données de la livraison
                card.setUserData(livraison); // Définit l'objet Livraison comme userData de la carte
                card.getProperties().put("controller", controller); // Définit le contrôleur comme propriété de la carte
                flowPaneLivraison.getChildren().add(card);

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
    }


    public LivraisonCardController getLivraisonCard() {
        AnchorPane selectedCard = getSelectedCard();
        if (selectedCard != null) {
            return (LivraisonCardController) selectedCard.getProperties().get("controller");
        }
        return null;
    }

    private AnchorPane getSelectedCard() {
        for (Node node : flowPaneLivraison.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane card = (AnchorPane) node;
                if (card.getStyle().contains("-fx-background-color: lightblue;")) {
                    return card;
                }
            }
        }
        return null;
    }

    @FXML
    void onLivraisonSelected(MouseEvent event) {
        AnchorPane selectedCard = (AnchorPane) event.getSource();
        Livraison selectedLivraison = (Livraison) selectedCard.getUserData(); // Mettre à jour selectedLivraison avec la livraison sélectionnée

        System.out.println("Livraison sélectionnée : " + selectedLivraison.getNomPrenomClient()); // Vérifier la sélection de la livraison

        // Mettre à jour selectedLivraison avec la livraison sélectionnée
        this.selectedLivraison = selectedLivraison;

        // Appeler initData pour mettre à jour les champs avec les données de la livraison sélectionnée
        initData(selectedLivraison);
    }
    @FXML
    public void EditLivraison() {
        if (selectedLivraison != null) {
            System.out.println("ooooooooooooooo");
                try {
                    // Mise à jour des attributs de la livraison avec les nouvelles valeurs
                    selectedLivraison.setNomPrenomClient(NomPrenomClientField.getText());
                    System.out.println(NomPrenomClientField.getText());
                    selectedLivraison.setAdresse(AdresseField.getText());
                    selectedLivraison.setProd_name(cb_Produits.getValue());
                    selectedLivraison.setQuantity(Integer.parseInt(TfQuantityField.getText()));
                    selectedLivraison.setMontant(Float.parseFloat(TfMontantField.getText()));
                    selectedLivraison.setDate(Date.valueOf(dateField.getValue()));
                    System.out.println(selectedLivraison);
                    // Appel de la méthode de mise à jour du service de livraison
                    sl.update(selectedLivraison);

                    // Rafraîchir la liste des livraisons pour afficher les modifications
                    loadLivraisonData();

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

    }




    @FXML
    void DeleteLivraisonSelected(ActionEvent event) {
        sl.delete(selectedLivraison);
        loadLivraisonData(); // Recharger les données de livraison
    }


    private LivraisonCardController getSelectedCardController() {
        AnchorPane selectedCard = getSelectedCard();
        if (selectedCard != null) {
            return (LivraisonCardController) selectedCard.getProperties().get("controller");
        }
        return null;
    }


    @FXML
    void RefrecheListe(ActionEvent event) {
        loadLivraisonData();
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showCards() {
        List<Livraison> livraisons = getLivraisons();
        flowPaneLivraison.getChildren().clear();
        for (Livraison livraison : livraisons) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LivraisonCard.fxml"));
                AnchorPane card = loader.load();
                LivraisonCardController controller = loader.getController();
                // Initialiser les éléments de la carte avec les données de la livraison
                System.out.println("Contrôleur chargé: " + controller.getClass().getName()); // Vérifier le contrôleur chargé
                controller.initialize(livraison);
                // Ajouter la carte au FlowPane
                flowPaneLivraison.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void recherche_avance(ActionEvent event) {
        String NomPrenomClient = TFSearch.getText().trim();
        if (!NomPrenomClient.isEmpty()) {
            boolean found = false;
            List<Node> allCards = new ArrayList<>(flowPaneLivraison.getChildren());
            flowPaneLivraison.getChildren().clear();
            for (Node node : allCards) {
                if (node instanceof AnchorPane) {
                    AnchorPane card = (AnchorPane) node;
                    LivraisonCardController controller = (LivraisonCardController) card.getProperties().get("controller");
                    Livraison livraison = controller.getLivraison();
                    if (livraison != null && livraison.getNomPrenomClient() != null) {
                        String nomPrenomLivraison = livraison.getNomPrenomClient();
                        System.out.println("Nom et prénom du client sur la carte de livraison : " + nomPrenomLivraison);
                        System.out.println("Nom et prénom saisi dans le champ de recherche : " + NomPrenomClient);
                        if (nomPrenomLivraison.equals(NomPrenomClient)) {
                            // Afficher la carte de livraison trouvée
                            flowPaneLivraison.getChildren().add(card);
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (!found) {
                // Avertir l'utilisateur si aucune carte de livraison correspondante n'a été trouvée
                showAlert(Alert.AlertType.WARNING, "Carte non trouvée", "Aucune carte de livraison correspondante n'a été trouvée pour : " + NomPrenomClient);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Recherche vide", "Veuillez saisir un nom et prénom pour la recherche.");
        }
    }


    private List<Livraison> chargerDonneesLivraisons() {
        List<Livraison> livraisons = new ArrayList<>();
        try {
            ServiceLivraison serviceLivraison = new ServiceLivraison();
            livraisons = serviceLivraison.getAll();

            // Vérification des dates récupérées
            for (Livraison livraison : livraisons) {
                System.out.println("Date de livraison : " + livraison.getDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livraisons;
    }

    @FXML
    void getQRCode(ActionEvent event) {
        if (selectedLivraison != null) {
            // Générer le contenu du QR code à partir des données de la livraison
            String qrContent = selectedLivraison.toString();

            // Générer le QR code sous forme d'image
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
            } catch (WriterException | IOException e) {
                e.printStackTrace();
                return;
            }

            // Convertir l'image en base64 pour l'afficher dans l'interface utilisateur
            byte[] byteArray = out.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(byteArray);
            InputStream inputStream = new ByteArrayInputStream(byteArray);

            // Afficher l'image dans une fenêtre d'alerte
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("QR Code");
            alert.setHeaderText(null);
            alert.setContentText("QR Code de la livraison sélectionnée :");

            // Créer un ImageView pour afficher l'image du QR code
            ImageView imageView = new ImageView(new Image(inputStream));
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            alert.setGraphic(imageView);

            alert.showAndWait();
        } else {
            // Avertir l'utilisateur s'il n'y a pas de livraison sélectionnée
            showAlert(Alert.AlertType.WARNING, "Aucune livraison sélectionnée", "Veuillez sélectionner une livraison pour générer le QR Code.");
        }
    }
    @FXML
    void onSortByQuantity(ActionEvent event) {
        List<Livraison> livraisons = getLivraisons();

        // Tri des livraisons par quantité en utilisant Java Streams
        livraisons = livraisons.stream()
                .sorted(Comparator.comparingInt(Livraison::getQuantity))
                .collect(Collectors.toList());

        // Recharger les données de livraison triées
        flowPaneLivraison.getChildren().clear();
        for (Livraison livraison : livraisons) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LivraisonCard.fxml"));
                AnchorPane card = loader.load();
                LivraisonCardController controller = loader.getController();
                controller.initialize(livraison);
                flowPaneLivraison.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}
