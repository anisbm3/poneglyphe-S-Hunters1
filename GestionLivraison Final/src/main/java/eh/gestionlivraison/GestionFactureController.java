package eh.gestionlivraison;
import javafx.scene.image.Image;
import eh.gestionlivraison.Services.ServiceFacture;
import eh.gestionlivraison.models.Facture;
import eh.gestionlivraison.FactureCardController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.Color;
public class GestionFactureController implements Initializable {

    @FXML
    private TextField MontantAvecRemise;

    @FXML
    private Button Statistique1;

    @FXML
    private TextField TFSearch;

    @FXML
    private Button brtn_DeleteAll;

    @FXML
    private Button btn_DeleteSelected;

    @FXML
    private Button btn_Edit;

    @FXML
    private Button btn_Refresh;

    @FXML
    private Button btn_Refresh1;

    @FXML
    private Button btn_Refresh2;
    @FXML
    private ComboBox<String> cb_Produits;
    @FXML
    private ComboBox<String> cb_Adresse;

    @FXML
    private ComboBox<Date> cb_Date;

    @FXML
    private ComboBox<String> cb_NomPrenom;

    @FXML
    private ComboBox<Float> cb_montant;

    @FXML
    private ComboBox<Integer> cb_quantity;
    @FXML
    private DatePicker date;

    @FXML
    private FlowPane flowPaneLFacture;

    @FXML
    private AnchorPane left_main;

    @FXML
    private Rectangle montant;

    @FXML
    private HBox recherche_avance;

    @FXML
    private TextField remise;

    @FXML
    private Button trie;
    private Facture selectedFacture;
    ServiceFacture sf = new ServiceFacture();
    Facture facture;
    private FXMLLoader FactureCardLoader;
    @FXML
    private ObservableList<String> optionsNomPrenom = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> optionsAdresse = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> optionsProduit = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Integer> optionsquantity = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Float> optionsMantantAvantRemise = FXCollections.observableArrayList();
    @FXML
    private ObservableList<java.util.Date> optionsDateLivraison = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Chargement des données de facture...");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionFacture.fxml"));

        fillcomboNomPrenom();
        fillcomboAdresse();
        fillcomboProduit();
        fillcomboqunantity();
        fillcomboLMantantAvantRemise();
        fillcomboDateLivraison();

        FactureCardLoader = new FXMLLoader(getClass().getResource("/FactureCard.fxml"));
        loadFactureData();

        System.out.println("Chargement des données de Facture...");

        // Charger les données depuis la base de données
        loadNomPrenomFromDatabase();
        loadAdresseFreomDatabase();
        loadProduitsFromDatabase();
        loadQuantityFromDatabase();
        loadMontantFromDatabase();
        loadDateFromDatabase();
        List<String> NomPrenomClient = Arrays.asList();
        List<String> Adresse = Arrays.asList();
        List<String> Produits = Arrays.asList("game", "pull");
        List<Integer> quantity = Arrays.asList(); // Exemple de liste de quantités
        List<Float> montant = Arrays.asList(); // Exemple de liste de montants
        List<Date> Date = Arrays.asList(); // Exemple de liste de dates

        cb_NomPrenom.getItems().addAll(NomPrenomClient);
        cb_Adresse.getItems().addAll(Adresse);
        cb_Produits.getItems().addAll(Produits);
        cb_quantity.getItems().addAll(quantity);
        cb_montant.getItems().addAll(montant);
        cb_Date.getItems().addAll(Date);

        afficherNomprenomDansComboBox();
        afficherAdresseDansComboBox();
        afficherProduitsDansComboBox();
        afficherQuantitysDansComboBox();
        affichermontantsDansComboBox();
        afficherDateDansComboBox();
        // Correction de l'appel de la méthode
    }

    public void initData(Facture facture) {

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

    // Charger les données depuis la base de données
    private void loadNomPrenomFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT NomPrenomClient FROM livraison"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String NomPrenomClient = rs.getString("NomPrenomClient");
                optionsNomPrenom.add(NomPrenomClient);
                System.out.println("NomPrenomClient récupéré depuis la base de données : " + NomPrenomClient);
            }
            cb_NomPrenom.setItems(optionsNomPrenom); // Associer les produits à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAdresseFreomDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT Adresse FROM livraison"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String Adresse = rs.getString("Adresse");
                optionsAdresse.add(Adresse);
                System.out.println("Adresse récupéré depuis la base de données : " + Adresse);
            }
            cb_Adresse.setItems(optionsAdresse); // Associer les produits à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadProduitsFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT Produits FROM panier"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String Produits = rs.getString("produits");
                optionsProduit.add(Produits);
                System.out.println("Produit récupéré depuis la base de données : " + Produits);
            }
            cb_Produits.setItems(optionsProduit); // Associer les produits à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadQuantityFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT quantity FROM livraison"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                optionsquantity.add(quantity);
                System.out.println("Produit récupéré depuis la base de données : " + quantity);
            }
            cb_quantity.setItems(optionsquantity); // Associer les produits à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMontantFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT montant FROM livraison"; // Sélectionnez les montants distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Float montant = rs.getFloat("montant");
                optionsMantantAvantRemise.add(montant); // Ajouter le montant à la liste
                System.out.println("Montant récupéré depuis la base de données : " + montant);
            }
            cb_montant.setItems(optionsMantantAvantRemise); // Associer les montants à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void loadDateFromDatabase() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT date FROM livraison"; // Sélectionnez les dates distinctes
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<java.sql.Date> optionsDateLivraison = FXCollections.observableArrayList();
            while (rs.next()) {
                java.sql.Date date = rs.getDate("date");
                optionsDateLivraison.add(date);
                System.out.println("Date récupérée depuis la base de données : " + date);
            }
            cb_Date.setItems(optionsDateLivraison); // Associer les dates à la ComboBox
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Facture> getFactures() {
        ServiceFacture serviceFacture = new ServiceFacture();
        return serviceFacture.getAll();
    }

    private void afficherNomprenomDansComboBox() {
        if (cb_NomPrenom != null) {
            System.out.println("ComboBoxNomPrenom n'est pas null");
            cb_NomPrenom.setItems(optionsNomPrenom);
        } else {
            System.out.println("ERREUR : ComboBoxProduits est null");
        }
    }

    private void afficherAdresseDansComboBox() {
        if (cb_Adresse != null) {
            System.out.println("ComboBoxAdresse n'est pas null");
            cb_Adresse.setItems(optionsAdresse);
        } else {
            System.out.println("ERREUR : ComboBoxAdresse est null");
        }
    }

    private void afficherProduitsDansComboBox() {
        if (cb_Produits != null) {
            System.out.println("ComboBoxProduits n'est pas null");
            cb_Produits.setItems(optionsProduit);
        } else {
            System.out.println("ERREUR : ComboBoxProduits est null");
        }
    }

    private void afficherQuantitysDansComboBox() {
        if (cb_quantity != null) {
            System.out.println("ComboBoxQuantity n'est pas null");
            cb_quantity.setItems(optionsquantity);
        } else {
            System.out.println("ERREUR : ComboBoxquantity est null");
        }
    }

    private void affichermontantsDansComboBox() {
        if (cb_montant != null) {
            System.out.println("ComboBoxMontant n'est pas null");
            cb_montant.setItems(optionsMantantAvantRemise);
        } else {
            System.out.println("ERREUR : ComboBoxmontant est null");
        }
    }

    private void afficherDateDansComboBox() {
       /* if (cb_Date != null) {
            System.out.println("ComboBoxDate n'est pas null");
            cb_Date.setItems(optionsDateLivraison);
        } else {
            System.out.println("ERREUR : ComboBoxDate est null");
        }*/
    }

    private void fillcomboNomPrenom() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT NomPrenomClient FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsNomPrenom = FXCollections.observableArrayList();
            while (rs.next()) {
                String NomPrenomClient = rs.getString("NomPrenomClient");
                optionsNomPrenom.add(NomPrenomClient);
                System.out.println("NomPrenom récupéré depuis la base de données : " + NomPrenomClient);
            }
            cb_NomPrenom.setItems(optionsNomPrenom);
        } catch (SQLException ex) {
            /* Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

    private void fillcomboAdresse() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT Adresse FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsAdresse = FXCollections.observableArrayList();
            while (rs.next()) {
                String Adresse = rs.getString("Adresse");
                optionsAdresse.add(Adresse);
                System.out.println("Adresse récupéré depuis la base de données : " + Adresse);
            }
            cb_Adresse.setItems(optionsAdresse);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillcomboProduit() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT DISTINCT Produits FROM panier"; // Sélectionnez les produits distincts
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<String> optionsProduits = FXCollections.observableArrayList();
            while (rs.next()) {
                String Produits = rs.getString("Produits");
                optionsProduits.add(Produits);
                System.out.println("Produit récupéré depuis la base de données : " + Produits);
            }
            cb_Produits.setItems(optionsProduits);
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcomboqunantity() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT quantity FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<Integer> optionsquantity = FXCollections.observableArrayList();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                optionsquantity.add(quantity);
                System.out.println("Adressequantity depuis la base de données : " + quantity);
            }
            cb_quantity.setItems(optionsquantity);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcomboLMantantAvantRemise() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT montant FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<Float> optionsMantantAvantRemise = FXCollections.observableArrayList();
            while (rs.next()) {
                String montant = rs.getString("montant");
                optionsMantantAvantRemise.add(Float.parseFloat(montant));
                System.out.println("MantantAvantRemiserécupéré depuis la base de données : " + montant);
            }
            cb_montant.setItems(optionsMantantAvantRemise);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcomboDateLivraison() {
        try {
            Connection cnx = MyDataBase.getInstance().getCnx();
            String req = "SELECT date FROM livraison"; // Sélectionnez les livraison
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery();
            ObservableList<java.sql.Date> optionsDateLivraison = FXCollections.observableArrayList();
            while (rs.next()) {
                java.sql.Date date = rs.getDate("date");
                optionsDateLivraison.add(date);
                System.out.println("Date récupérée depuis la base de données : " + date);
            }
            cb_Date.setItems(optionsDateLivraison);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void updateFactureCards() {
        for (Node node : flowPaneLFacture.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane card = (AnchorPane) node;
                FactureCardController controller = (FactureCardController) card.getProperties().get("controller");
                Facture facture = controller.getFacture();
                if (facture.isSelected()) {
                    card.setStyle("-fx-background-color: lightblue;");
                } else {
                    card.setStyle("-fx-background-color: white;");
                }
            }
        }
    }

    private void loadFactureData() {
        List<Facture> factures = sf.getAll();

        flowPaneLFacture.getChildren().clear();
        for (Facture facture : factures) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureCard.fxml"));
                AnchorPane card = loader.load();
                FactureCardController controller = loader.getController();
                controller.initialize(facture); // Initialise la carte avec les données de la livraison
                card.setUserData(facture); // Définit l'objet Livraison comme userData de la carte
                card.getProperties().put("controller", controller); // Définit le contrôleur comme propriété de la carte
                flowPaneLFacture.getChildren().add(card);

                // Mettre en place l'événement de sélection de la carte de livraison
                card.setOnMouseClicked(event -> {
                    onFactureSelected(event);
                    updateFactureCards(); // Mettre à jour l'apparence des cartes de livraison
                });
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des données de livraison.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeleteAllFacture(ActionEvent event) {
        ServiceFacture serviceFacture = new ServiceFacture();
        boolean success = serviceFacture.deleteAll();
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "tous les Factures sont supprimés!");
            loadFactureData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "erreur.");
        }

    }

    @FXML
    void DeleteFactureSelected(ActionEvent event) {
        sf.delete(selectedFacture);
        loadFactureData(); // Recharger les données de livraison
    }

    public FactureCardController getFactureCard() {
        AnchorPane selectedCard = getSelectedCard();
        if (selectedCard != null) {
            return (FactureCardController) selectedCard.getProperties().get("controller");
        }
        return null;
    }

    private AnchorPane getSelectedCard() {
        for (Node node : flowPaneLFacture.getChildren()) {
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
    void onFactureSelected(MouseEvent event) {
        AnchorPane selectedCard = (AnchorPane) event.getSource();
        Facture selectedFacture = (Facture) selectedCard.getUserData(); // Mettre à jour selectedLivraison avec la livraison sélectionnée

        System.out.println("Facture sélectionnée : " + selectedFacture.getNomPrenomClient()); // Vérifier la sélection de la livraison

        // Mettre à jour selectedLivraison avec la livraison sélectionnée
        this.selectedFacture = selectedFacture;

        // Appeler initData pour mettre à jour les champs avec les données de la livraison sélectionnée
        initData(selectedFacture);
    }

    @FXML
    void EditFacture(ActionEvent event) {
        if (selectedFacture != null) {
            System.out.println("ooooooooooooooo");
            try {
                // Mise à jour des attributs de la livraison avec les nouvelles valeurs
                selectedFacture.setRemise(Integer.parseInt(remise.getText()));
                System.out.println(remise.getText());
                selectedFacture.setMontantAvecRemise(Float.parseFloat(MontantAvecRemise.getText()));
                selectedFacture.setDateFacture(Date.valueOf(date.getValue()));
                selectedFacture.setNomPrenomClient(cb_NomPrenom.getValue());
                selectedFacture.setAdresse(cb_Adresse.getValue());
                selectedFacture.setProd_name(cb_Produits.getValue());
                selectedFacture.setQuantity(cb_quantity.getValue());
                selectedFacture.setMontant(cb_montant.getValue());
                selectedFacture.setDate(cb_Date.getValue());

                System.out.println(selectedFacture);
                // Appel de la méthode de mise à jour du service de livraison
                sf.update(selectedFacture);

                // Rafraîchir la liste des livraisons pour afficher les modifications
                loadFactureData();

                // Afficher un message de succès
                showAlert(Alert.AlertType.INFORMATION, "Succès", "La facture a été mise à jour avec succès.");

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
    void Imprimez(ActionEvent event) {
        if (selectedFacture != null) {
            String nomClient = selectedFacture.getNomPrenomClient();
            String date = selectedFacture.getDateFacture().toString();
            String produits = selectedFacture.getProd_name();
            String prixTotal = String.valueOf(selectedFacture.getMontant());
            String tva = String.valueOf(selectedFacture.getMontant() * 0.2); // Exemple de calcul de TVA
            String prixTTC = String.valueOf(selectedFacture.getMontant() * 1.2); // Exemple de calcul de prix TTC

            // Appeler la méthode imprimezFacture avec les données de la facture et le nom du fichier
            imprimezFacture("facture.pdf", nomClient, date, produits, prixTotal, tva, prixTTC);

            // Afficher le fichier PDF généré dans une nouvelle fenêtre
            File file = new File("facture.pdf");
            System.out.println("Fichier PDF généré : " + file.getAbsolutePath());

            afficherPDF(file);
        } else {
            // Afficher un message d'erreur si aucune facture n'est sélectionnée
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner une facture.");
        }
    }

    private void imprimezFacture(String nomFichier, String nomClient, String date, String produits, String prixTotal, String tva, String prixTTC) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Chargement de la police
            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 12;

            // Ajouter le logo
            PDImageXObject logo = PDImageXObject.createFromFile("/logo.png", document);
            contentStream.drawImage(logo, 50, 750);

            // Ajouter le nom du groupe
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Nom du groupe");
            contentStream.endText();

            // Ajouter le cadre
            contentStream.addRect(50, 50, page.getMediaBox().getWidth() - 100, page.getMediaBox().getHeight() - 100);
            contentStream.stroke();

            // Ajouter le contenu de la facture
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(100, 650);
            contentStream.showText("Facture pour : " + nomClient);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date : " + date);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Produits : " + produits);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Prix Total : " + prixTotal);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("TVA : " + tva);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Prix TTC : " + prixTTC);
            contentStream.endText();

            contentStream.close();

            document.save(nomFichier);
            document.close();

            // Affichage d'un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Facture enregistrée avec succès !");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Affichage d'un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'enregistrement de la facture : " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void afficherPDF(File file) {
        try {
            if (!file.exists()) {
                System.err.println("Le fichier PDF n'existe pas.");
                return;
            }

            // Charger le fichier PDF généré
            try (PDDocument pdfDocument = PDDocument.load(file)) {
                // Convertir le PDF en une image pour l'afficher dans JavaFX
                PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300); // 300 DPI

                // Convertir BufferedImage en Image JavaFX
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                Image image = new Image(inputStream);

                // Afficher l'image dans une ImageView
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(800); // Ajuster la largeur de l'image à la fenêtre
                imageView.setPreserveRatio(true);

                // Créer une nouvelle fenêtre pour afficher l'image
                Stage stage = new Stage();
                stage.setTitle("Facture");
                stage.setScene(new Scene(new StackPane(imageView), 800, 600));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'affichage du fichier PDF : " + e.getMessage());
        }
    }

    @FXML
    void RefrecheListe(ActionEvent event) {
        loadFactureData();
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
        List<Facture> factures = getFactures();
        flowPaneLFacture.getChildren().clear();
        for (Facture facture : factures) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureCard.fxml"));
                AnchorPane card = loader.load();
                FactureCardController controller = loader.getController();
                // Initialiser les éléments de la carte avec les données de la livraison
                System.out.println("Contrôleur chargé: " + controller.getClass().getName()); // Vérifier le contrôleur chargé
                controller.initialize(facture);
                // Ajouter la carte au FlowPane
                flowPaneLFacture.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private List<Facture> chargerDonneesFactures() {
        List<Facture> factures = new ArrayList<>();
        try {
            ServiceFacture serviceFacture = new ServiceFacture();
            factures = serviceFacture.getAll();

            // Vérification des dates récupérées
            for (Facture facture : factures) {
                System.out.println("Date de facture : " + facture.getDateFacture());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factures;
    }
    @FXML
    void TrieparDate(ActionEvent event) {
        List<Facture> factures = getFactures();

        // Tri des factures par date en utilisant Java Streams
        factures = factures.stream()
                .sorted(Comparator.comparing(Facture::getDateFacture))
                .collect(Collectors.toList());


        // Recharger les données de livraison triées
        flowPaneLFacture.getChildren().clear();
        for (Facture facture : factures) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FactureCard.fxml"));
                AnchorPane card = loader.load();
                FactureCardController controller = loader.getController();
                controller.initialize(facture);
                flowPaneLFacture.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void recherche_avance(ActionEvent event) {
        String NomPrenomClient = TFSearch.getText().trim();
        if (!NomPrenomClient.isEmpty()) {
            boolean found = false;
            List<Node> allCards = new ArrayList<>(flowPaneLFacture.getChildren());
            flowPaneLFacture.getChildren().clear();
            for (Node node : allCards) {
                if (node instanceof AnchorPane) {
                    AnchorPane card = (AnchorPane) node;
                    FactureCardController controller = (FactureCardController) card.getProperties().get("controller");
                    Facture facture = controller.getFacture();
                    if (facture != null && facture.getNomPrenomClient() != null) {
                        String nomPrenomClient = facture.getNomPrenomClient();
                        System.out.println("Nom et prénom du client sur la carte de facture : " + nomPrenomClient);
                        System.out.println("Nom et prénom saisi dans le champ de recherche : " + NomPrenomClient);
                        if (nomPrenomClient.equals(NomPrenomClient)) {
                            // Afficher la carte de livraison trouvée
                            flowPaneLFacture.getChildren().add(card);
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (!found) {
                // Avertir l'utilisateur si aucune carte de livraison correspondante n'a été trouvée
                showAlert(Alert.AlertType.WARNING, "Carte non trouvée", "Aucune carte de Facture correspondante n'a été trouvée pour : " + NomPrenomClient);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Recherche vide", "Veuillez saisir un nom et prénom pour la recherche.");
        }
    }


    @FXML
    void Calculez(ActionEvent event) {

    }
}