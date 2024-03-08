package tn.esprit.test;

/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/Menu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
    }
}*/








/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Chargement du FXML pour l'interface graphique
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/Menu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Instanciation de la classe CalendarQuickstart et appel de sa méthode quickstart
            CalendarQuickstart calendarQuickstart = new CalendarQuickstart();
            calendarQuickstart.quickstart();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
    }
}*/


/*import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
//import com.sun.net.httpserver.HttpHandler;

public class MainFX extends Application {

    // Google Calendar API variables
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/tn/esprit/credentials.json";
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize Google Calendar service and retrieve events
            List<String> eventSummaries = retrieveCalendarEvents();

            // Display events in a JavaFX ListView
            ObservableList<String> items = FXCollections.observableArrayList(eventSummaries);
            ListView<String> listView = new ListView<>(items);
            StackPane root = new StackPane();
            root.getChildren().add(listView);
            Scene scene = new Scene(root, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private List<String> retrieveCalendarEvents() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        ObservableList<String> eventSummaries = FXCollections.observableArrayList();
        for (Event event : items) {
            eventSummaries.add(event.getSummary());
        }
        return eventSummaries;
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = MainFX.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}*/


import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.controlls.ReservationAfficherController;
import tn.esprit.models.Reservation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFX extends Application {
    private static final Logger LOGGER = Logger.getLogger(MainFX.class.getName());

    /*public void createReservation() {
        Reservation selectedReservation = getSelectedReservationFromUI();
        if (selectedReservation != null) {
            String reservationTitle = selectedReservation.getNom_Reseervation();
            // Récupérer d'autres détails de la réservation

            // Créer un nouvel événement avec les détails de la réservation
            Event event = new Event().setSummary(reservationTitle);
            // Ajouter d'autres détails de l'événement

            // Appeler l'API Google Calendar pour créer l'événement
            Calendar service = getCalendarService();
            try {
                service.events().insert("primary", event).execute();
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer les erreurs
            }
        } else {
            System.out.println("Aucune réservation sélectionnée.");
        }
    }*/

    private Reservation getSelectedReservationFromUI() {
        ReservationAfficherController controller = getReservationController();
        if (controller != null) {
            return controller.getSelectedReservation();
        }
        return null;
    }
    private ReservationAfficherController getReservationController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/Menu.fxml"));
            Parent root = loader.load();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void createReservation() {
        Reservation selectedReservation = getSelectedReservationFromUI();
        if (selectedReservation != null) {
            String reservationTitle = selectedReservation.getNom_Reseervation();
            Event event = new Event().setSummary(reservationTitle);

            Calendar service = getCalendarService();
            if (service != null) {
                try {
                    Event createdEvent = service.events().insert("primary", event).execute();
                    LOGGER.log(Level.INFO, "Event created: " + createdEvent.getSummary());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Failed to create event", e);
                }
            } else {
                LOGGER.log(Level.SEVERE, "Failed to obtain Calendar service");
            }
        } else {
            System.out.println("Aucune réservation sélectionnée.");
        }
    }
    /*public void createReservation() {
        // Récupérer les détails de la réservation depuis votre interface utilisateur
        String reservationTitle = getTitleFromUI();
        // Récupérer d'autres détails comme la date, l'heure, etc.

        // Créer un nouvel événement avec les détails de la réservation
        Event event = new Event()
                .setSummary(reservationTitle);
        // Ajouter d'autres détails de l'événement

        // Appeler l'API Google Calendar pour créer l'événement
        Calendar service = getCalendarService();
        try {
            service.events().insert("primary", event).execute();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }*/

    private String getTitleFromUI() {
        // Implémentez la logique pour récupérer le titre de la réservation depuis votre interface utilisateur
        return null;
    }




    private Calendar getCalendarService() {
        // Implémentez la logique pour obtenir une instance du service Google Calendar
        return null;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML file", e);
        }
    }



}

