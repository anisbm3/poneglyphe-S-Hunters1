package tn.esprit.controlls;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import tn.esprit.models.Cosplay;
import tn.esprit.services.CrudCosplay;

    public class AjouterCosplayController  implements Initializable {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;
        @FXML
        private VBox cosplayContainer;
        @FXML
        private Label caption;

        @FXML
        private ImageView imgPost;

        @FXML
        private Label labeldate;

        @FXML
        private Label labeldate1;

        @FXML
        private Label nomCosp;

        @FXML
        private Label personnage;

        @FXML
        private Label typemat;
        @FXML
        private VBox cosplayCard;

        private ArrayList<Cosplay> cosplays;
        private final CrudCosplay cs = new CrudCosplay();


        @Override
        public void initialize(URL location, ResourceBundle resources) {


            if (cosplayContainer != null) {
                System.out.println("cosplayContainer is initialized");

                // Display the cosplays
                addCosplayCard(cosplays);
            } else {
                System.out.println("cosplayContainer is not initialized");

            }


        }


        private void handleCardClick(Cosplay cosplay) {

        }



        @FXML
        private void addcosplay(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCosplay.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PostCosplayContr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private Cosplay selectedCosplay;

        public void setSelectedCosplay(Cosplay cosplay) {
            this.selectedCosplay = cosplay;
        }

        public void addCosplayCard(ArrayList<Cosplay> cosplays) {
            if (cosplayContainer == null) {
                System.out.println("Error: cosplayContainer is null.");
                return;
            }

            cosplays = cs.getAll();
            if (cosplays == null) {
                System.out.println("Cosplays list is null. Cannot add cosplay cards.");
                return;
            }

            for (Cosplay cosplay : cosplays) {
                try {
                    // Load the FXML file for the cosplay card
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPost.fxml"));
                    Node cosplayCard = loader.load(); // Load the root node of the FXML file

                    // Retrieve the controller for the cosplay card
                    CardPostController cosplayCardController = loader.getController();

                    // Initialize the data for the cosplay card
                    cosplayCardController.initData(cosplay,this);

                    // Add the cosplay card to the cosplayContainer
                    cosplayContainer.getChildren().add(cosplayCard);

                    cosplayCard.setOnMouseClicked(event -> {
                        // Retrieve the cosplay object associated with this card
                        Cosplay selectedCosplay = cosplayCardController.getCosplay();
                        // Set the selected cosplay
                        setSelectedCosplay(selectedCosplay);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void deleteCosplay(Cosplay cosplayToDelete) {
            // If a cosplay is selected, proceed with deletion
            try {
                cs.delete(cosplayToDelete);
                System.out.println("Cosplay deleted successfully.");
                // Optionally, refresh the UI or update the list view to reflect the changes
            } catch (Exception e) {
                System.out.println("Failed to delete cosplay: " + e.getMessage());
                // Handle the failure, display error message, etc.
            }
        }
        public void refreshCosplays() {
            // Implement your logic to refresh the display of cosplays
            cosplayContainer.getChildren().clear();

            // Get the updated list of cosplays
            ArrayList<Cosplay> updatedCosplays = cs.getAll();

            // Add the updated cosplay cards
            addCosplayCard(updatedCosplays);
        }
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;
    /*public AjouterCosplayController() {
        this.client = new OkHttpClient();
    }
    private  OkHttpClient client = new OkHttpClient();
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";*/
    @FXML
    public void initialize() {
        sendButton.setOnAction(e -> sendMessage());
    }
  /*  @FXML
    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            inputField.clear();
            appendToChat("You: " + message);
            callChatGPT(message); // Call the chatGPT method instead of requestChatCompletion
        }
    }*/
    @FXML
    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            inputField.clear();
            appendToChat("You: " + message);
            callChatGPT(message); // Call the chatGPT method instead of requestChatCompletion
        }
    }

    private void appendToChat(String message) {
        chatArea.appendText(message + "\n");
    }

    private void callChatGPT(String message) {
        // Call the chatGPT method here
        String response = ChatGPTAPIExample.chatGPT(message);
        appendToChat("ChatGPT: " + response);
    }
    public class ChatGPTAPIExample {

        public static String chatGPT(String prompt) {
            final String url = "https://api.openai.com/v1/chat/completions";
             final String apiKey = "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";
             final String model = "gpt-3.5-turbo";

            int maxRetries = 3; // Maximum number of retries
            int retryDelay = 1000; // Initial retry delay in milliseconds

            for (int retry = 0; retry < maxRetries; retry++) {
                try {
                    final URL obj = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "Bearer " + apiKey);
                    connection.setRequestProperty("Content-Type", "application/json");

                    // The request body
                    String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
                    connection.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(body);
                    writer.flush();
                    writer.close();

                    // Response from ChatGPT
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();

                    // Calls the method to extract the message.
                    return extractMessageFromJSONResponse(response.toString());

                } catch (IOException e) {
                    // Retry on IOException
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Retry attempt: " + (retry + 1));
                    try {
                        // Implement exponential backoff by doubling the delay time on each retry
                        Thread.sleep(retryDelay);
                        retryDelay *= 2;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            // Return an error message if maxRetries are reached
            return "Error: Maximum number of retries reached. Unable to process the request.";
        }

          public static String extractMessageFromJSONResponse(String response) {
            int start = response.indexOf("content") + 11;
            int end = response.indexOf("\"", start);
            return response.substring(start, end);
          }
        }

        public static String extractMessageFromJSONResponse(String response) {
            int start = response.indexOf("content")+ 11;

            int end = response.indexOf("\"", start);

            return response.substring(start, end);





        }


    // Method to call the ChatGPT API


   /* private void requestChatCompletion(String message) {
        // Implement the request logic similar to the ChatGPTIntegration class
        client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("model", "text-davinci-003")
                .add("messages", "[{\"role\": \"user\", \"content\": \"" + message + "\"}]")
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Extract response from JSON and handle it
                    handleResponse(extractResponse(responseBody));
                } else {
                    // Handle unsuccessful response
                    System.out.println("Error: Failed to get response from ChatGPT API");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                System.out.println("Error: " + e.getMessage());
            }
        });
    }

    private String extractResponse(String responseBody) {
        // Implement the response extraction logic similar to the ChatGPTIntegration class
        return "Sample response from ChatGPT";
    }
    private void handleResponse(String response) {
        // Handle the response, for example, update UI with the response
        System.out.println("ChatGPT: " + response);
    }

    private void appendToChat(String message) {
        chatArea.appendText(message + "\n");
    }*/

  /*  @FXML
    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            inputField.clear();
            appendToChat("You: " + message);

            // Send the message to ChatGPT API and append the response to the chat
            String response = chatGPT(message);
            appendToChat("ChatGPT: " + response);
        }
    }

   private void appendToChat(String message) {
        chatArea.appendText(message + "\n");
    }
        public static String chatGPT (String message){
            String url = "https://api.openai.com/v1/chat/completions";
            String apiKey = "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"; // Replace with your API key
            String model = "gpt-3.5-turbo";

            try {
                // Create the HTTP POST request
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization", "Bearer " + apiKey);
                con.setRequestProperty("Content-Type", "application/json");

                // Build the request body
                String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(body);
                writer.flush();
                writer.close();

                // Get the response
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // returns the extracted contents of the response.
                return extractContentFromResponse(response.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // This method extracts the response expected from chatgpt and returns it.
        public static String extractContentFromResponse (String response){
            int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
            int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
            return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
        }*/
    }


