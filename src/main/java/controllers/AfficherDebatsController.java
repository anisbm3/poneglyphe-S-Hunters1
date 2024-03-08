package controllers;

import entities.Debat;
import entities.ExcelExporter;
import entities.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import services.ServiceDebat;
import controllers.AjouterDebatsController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import services.ServiceDebat;
import services.ServiceRating;

public class AfficherDebatsController {

    @FXML
    private VBox DebatVBox;

    @FXML
    private Button excelBTN;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button statBtn;

    @FXML
    private TextField Descfield;


    @FXML
    private TextField ResearchBar;

    @FXML
    private TextField notefield;
    @FXML
    private TextField animefield;

    @FXML
    private Button modfierbtn;

    @FXML
    private TextField nomfield;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button suppbtn;

    @FXML
    private Button triBtn;
    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;


    private String tri = "ASC";
    private int i = 0;
    private int rate =0;

    private final ServiceDebat SD = new ServiceDebat();
    private Debat selectedDebat ;
    @FXML
    private void initialize() {
        // Set EventVBox as the content of ScrollPane
        scrollPane.setContent(DebatVBox);
        setupSearchBarListener();
        // Call refreshDebat to populate VBox on initialization
        refreshDebat();
    }

    private void setupSearchBarListener() {
        ResearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                filterReservations(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void filterReservations(String searchText) throws SQLException {
        List<Debat> debats = SD.afficher();
        DebatVBox.getChildren().clear();

        for (Debat debat : debats) {
            // Vérifiez si le nom de la réservation contient le texte de recherche
            if (debat.getNom_Anime().toLowerCase().contains(searchText.toLowerCase())) {
                Label reservationLabel = new Label("Nom Anime: " + debat.getNom_Anime() +
                        ", Description Debat: " + debat.getDescription_Debat() +
                        ", Sujet Debat: " + debat.getSujet_Debat());

                reservationLabel.setOnMouseClicked(event -> {
                    selectedDebat = debat;
                    nomfield.setText(debat.getNom_Anime());
                    Descfield.setText(debat.getDescription_Debat());
                    animefield.setText(debat.getSujet_Debat());
                });

                DebatVBox.getChildren().add(reservationLabel);
            }
        }
    }
    private void refreshDebat() {
        List<Debat> debats = SD.afficherbyNOM(tri);
        DebatVBox.getChildren().clear(); // Clear existing content

        for (Debat debat : debats) {
            Label eventLabel = new Label(
                    "Nom Anime: " + debat.getNom_Anime() +
                            ", Description Debat: " + debat.getDescription_Debat() +
                            ", Sujet Debat: " + debat.getSujet_Debat()

            );
            Separator separator = new Separator(Orientation.HORIZONTAL);

            // Set action for the label to select the event
            eventLabel.setOnMouseClicked(event -> {
                selectedDebat = debat;
                nomfield.setText(debat.getNom_Anime());
                Descfield.setText(debat.getDescription_Debat());
                animefield.setText(debat.getSujet_Debat());
                rate=0;
                star2.setImage(new Image("/empty_star.png"));
                star3.setImage(new Image("/empty_star.png"));
                star4.setImage(new Image("/empty_star.png"));
                star5.setImage(new Image("/empty_star.png"));
                star1.setImage(new Image("/empty_star.png"));
                           });

            DebatVBox.getChildren().addAll(eventLabel, separator);
        }
    }

    private void displayDebates(List<Debat> debats) {
        DebatVBox.getChildren().clear(); // Clear existing content

        for (Debat debat : debats) {
            Label eventLabel = new Label(
                    "Anime Name: " + debat.getNom_Anime() +
                            ", Debate Description: " + debat.getDescription_Debat() +
                            ", Debate Subject: " + debat.getSujet_Debat()
            );
            Separator separator = new Separator(Orientation.HORIZONTAL);

            // Set action for the label to select the event
            eventLabel.setOnMouseClicked(event -> {
                selectedDebat = debat;
                nomfield.setText(debat.getNom_Anime());
                Descfield.setText(debat.getDescription_Debat());
                animefield.setText(debat.getSujet_Debat());
            });

            DebatVBox.getChildren().addAll(eventLabel, separator);
        }
    }

    @FXML
    void onSearch(ActionEvent event) throws SQLException {
        String searchName = ResearchBar.getText();
        if (!searchName.isEmpty()) {
            List<Debat> searchResults = SD.searchByName(searchName);
            displayDebates(searchResults);
        } else {
            refreshDebat(); // If the search bar is empty, refresh the debate list
        }
    }
    @FXML
    void onClickedExport(ActionEvent event) {
        // Call your exportToExcel method here
        ExcelExporter excelExporter = new ExcelExporter();
        List<Debat> debats = SD.afficherbyNOM(tri); // Assuming this fetches the necessary data
        //String filePath = System.getProperty("USER.Bureau") + "/debats.xlsx";

String filePath = "path_to_your_excel_file.xlsx"; // Provide the file path here
        excelExporter.exportToExcel(debats, filePath);
    }

    @FXML
    void OnClickedTri(ActionEvent event) {
        if (i % 2 == 0) {
            tri = "ASC";
        } else {
            tri = "DESC";
        }
        i++;
        refreshDebat();
    }

    @FXML
    void onClicekdSupp(ActionEvent event) {
        // Check if selectedDebat is not null
        if (selectedDebat != null) {
            try {
                // If it's not null, proceed with deleting
                SD.supprimer(selectedDebat);
                refreshDebat();
               clearFields();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQL exception appropriately
            }
        } else {
            // If selectedDebat is null, display a message or handle it in your UI as needed
            System.out.println("No debat selected for deletion."); // Print a message for debugging
        }
    }

    @FXML
    void onClickedStat(ActionEvent event) {
        try {
            generateAnimeStatistics(); // Call the method to generate statistics
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exception appropriately
        }
    }

   /* private void generateAnimeStatistics() throws SQLException {
        Map<String, Integer> animeFrequency = new HashMap<>();
        List<Debat> debats = SD.afficher(); // Assuming a method to retrieve all debates

        // Count frequency of each anime name
        for (Debat debat : debats) {
            String animeName = debat.getNom_Anime();
            animeFrequency.put(animeName, animeFrequency.getOrDefault(animeName, 0) + 1);
        }

        // Display the statistics
        for (Map.Entry<String, Integer> entry : animeFrequency.entrySet()) {
            System.out.println("Anime: " + entry.getKey() + ", Frequency: " + entry.getValue());
        }
    }*/
   private void generateAnimeStatistics() throws SQLException {
       Map<String, Integer> animeFrequency = new HashMap<>();
       List<Debat> debats = SD.afficher(); // Assuming a method to retrieve all debates

       // Count frequency of each anime name
       for (Debat debat : debats) {
           String animeName = debat.getNom_Anime();
           animeFrequency.put(animeName, animeFrequency.getOrDefault(animeName, 0) + 1);
       }

       // Prepare data for the PieChart
       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
       for (Map.Entry<String, Integer> entry : animeFrequency.entrySet()) {
           pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
       }

       // Set the data to the PieChart
       pieChart.setData(pieChartData);
   }


    @FXML
    void onClickedModifier(ActionEvent event) {
        if (selectedDebat != null) {
            selectedDebat.setNom_Anime(nomfield.getText());
            selectedDebat.setDescription_Debat(Descfield.getText());
            selectedDebat.setSujet_Debat(animefield.getText());


            try {
                SD.modifier(selectedDebat);
                refreshDebat();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQL exception appropriately
            }
        }
    }
    private void clearFields() {
        animefield.clear();
        Descfield.clear();
        nomfield.clear();

    }

    public void onClickedComment(ActionEvent actionEvent) {
        try{   FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommentaires.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.showAndWait();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    void rate1(MouseEvent event) {
       rate=1;
        star2.setImage(new Image("/empty_star.png"));
        star3.setImage(new Image("/empty_star.png"));
        star4.setImage(new Image("/empty_star.png"));
       star5.setImage(new Image("/empty_star.png"));
        star1.setImage(new Image("/star.png"));

    }

    @FXML
    void rate2(MouseEvent event) {
        rate=2;
       star2.setImage(new Image("/star.png"));
        star3.setImage(new Image("/empty_star.png"));
        star4.setImage(new Image("/empty_star.png"));
        star5.setImage(new Image("/empty_star.png"));
        star1.setImage(new Image("/star.png"));
    }

    @FXML
    void rate3(MouseEvent event) {
        rate=3;
        star2.setImage(new Image("/star.png"));
        star3.setImage(new Image("/star.png"));
        star4.setImage(new Image("/empty_star.png"));
        star5.setImage(new Image("/empty_star.png"));
        star1.setImage(new Image("/star.png"));
    }

    @FXML
    void rate4(MouseEvent event) {
        rate=4;
        star2.setImage(new Image("/star.png"));
        star3.setImage(new Image("/star.png"));
        star4.setImage(new Image("/star.png"));
        star5.setImage(new Image("/empty_star.png"));
        star1.setImage(new Image("/star.png"));
    }

    @FXML
    void rate5(MouseEvent event) {
        rate=5;
        star2.setImage(new Image("/star.png"));
        star3.setImage(new Image("/star.png"));
        star4.setImage(new Image("/star.png"));
        star5.setImage(new Image("/star.png"));
        star1.setImage(new Image("/star.png"));
    }
@FXML
    public void Rating( MouseEvent event) throws SQLException {
    ServiceRating serviceRating = new ServiceRating();
    Rating rating=new Rating();
    rating.setRating(rate);
    rating.setID_Debat(selectedDebat.getID_Debat());
    rating.setID_User(1);
    serviceRating.ajouter(rating);
    }


}






