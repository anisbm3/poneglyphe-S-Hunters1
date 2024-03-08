package tn.esprit.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AddEventToCalendarApp  {

    private static final String APPLICATION_NAME = "calendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport httpTransport;
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);

    private Button addButton = new Button("Add Event");
    private DatePicker datePicker = new DatePicker(); // DatePicker for selecting event date


    public void addEventToCalendar(String title , LocalDate datee) {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            // Load client secrets JSON file
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                    new InputStreamReader(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\newcalendar\\src\\main\\resources\\com\\example\\newcalendar\\client_secret_614599717709-hv48osgjuavfe8o7hla74bi1mp4kjvnv.apps.googleusercontent.com.json")));

            // Build flow and trigger user authorization request
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                    .build();

            // Authorize
            String authUrl = flow.newAuthorizationUrl().setRedirectUri("urn:ietf:wg:oauth:2.0:oob").build();
            System.out.println("Please open the following URL in your browser then copy the authorization code:");
            System.out.println("  " + authUrl);

            // Enter the authorization code
            String authCode = "4/1AeaYSHB0p7bOW1OygC7J773driOEAtBQQ95n6oBNCMAQfpWAwFxf2eScGUA"; // Replace with the authorization code obtained from the user
            // Exchange authorization code for access token
            GoogleTokenResponse tokenResponse = flow.newTokenRequest(authCode).setRedirectUri("urn:ietf:wg:oauth:2.0:oob").execute();

            // Create GoogleCredential using the token response
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(JSON_FACTORY)
                    .setClientSecrets(clientSecrets)
                    .build()
                    .setFromTokenResponse(tokenResponse);

            // Initialize the calendar service
            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // Retrieve the selected date from the DatePicker
            LocalDate selectedDate = datePicker.getValue();

            // Convert LocalDate to Date
            Date date = Date.from(datee.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Create EventDateTime for the start and end times
            EventDateTime start = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(date));
            EventDateTime end = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(date));

            // Create event
            Event event = new Event()
                    .setSummary(title)
                    .setStart(start)
                    .setEnd(end);

            // Insert event
            event = service.events().insert("primary", event).execute();
            System.out.printf("Event created: %s\n", event.getHtmlLink());

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while adding the event: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
