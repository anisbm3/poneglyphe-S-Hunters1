package tn.esprit.javafinallgestionevents.services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.javafinallgestionevents.interfaces.IService;
import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.test.HelloApplication;
import tn.esprit.javafinallgestionevents.utils.MyDataBase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceEvenement implements IService<Evenement> {
    private Connection cnx;

    public ServiceEvenement() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement evenement) throws SQLException {
        String req = "INSERT INTO evenement (nom_event, description_event,lieu_event, date_event,image) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, evenement.getNom_event());
            pre.setString(2, evenement.getDescription_event());
            pre.setString(3, evenement.getLieu_event());
            pre.setString(4, String.valueOf(evenement.getDate_event()));
            pre.setString(5, evenement.getImage());


            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET nom_event=?, description_event=?, lieu_event=?, date_event=?, image=? WHERE id=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, evenement.getNom_event());
            pre.setString(2, evenement.getDescription_event());
            pre.setString(3, evenement.getLieu_event());
            pre.setString(4, String.valueOf(evenement.getDate_event()));
            pre.setString(5, evenement.getImage());
            pre.setInt(6, evenement.getId());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifying event: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement evenement) throws SQLException {

        String req = "DELETE FROM evenement WHERE id=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setInt(1, evenement.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
        }

    }

    public int getIdByNomEvent(String nom_event) {
        String req = "SELECT id FROM evenement WHERE nom_event = ?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, nom_event);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    return res.getInt("ID");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting ID by nom-event: " + e.getMessage());
        }
        return -1;
    }

    public int getEventIdByName(String eventName) throws SQLException {
        String query = "SELECT id FROM evenement WHERE nom_event = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, eventName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        // Gérer le cas où aucun ID n'est trouvé pour le nom de l'événement
        return -1;
    }

    public List<String> getAllEventNames() {
        String req = "SELECT nom_event FROM evenement";
        List<String> eventNames = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                String eventName = res.getString("nom_event");
                eventNames.add(eventName);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving event names: " + e.getMessage());
        }
        return eventNames;
    }

   /* public List<String> getUserIdByName() {
        String req = "SELECT roles FROM user WHERE id = ?";
        List<String> userIds = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                String rolesusers = res.getString("roles");
                userIds.add(rolesusers);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user IDs by name: " + e.getMessage());
        }
        return userIds;
    }*/

    /*public List<String> getUserIdByName() {
        String req = "SELECT roles FROM user";
        List<String> userRoles = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                String userRole = res.getString("roles");
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving event names: " + e.getMessage());
        }
        return userRoles;
    }*/


    /*public String getUserIdByName1(int idUser) throws SQLException {
        String roles = "";
        String query = "SELECT roles FROM user WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // return resultSet.getInt("id");
                roles = resultSet.getString("roles");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }*/
    public List<String> listevenement() {
        String req = "SELECT e.nom_event FROM evenement e JOIN reservation r ON e.id = r.evenement_id";
        List<String> list = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                String nomEvent = res.getString(1);
                list.add(nomEvent);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        return list;
    }

        public List<String> getAllEventIds() throws SQLException {
        String query = "SELECT id FROM evenement";
        List<String> eventIds = new ArrayList<>();

        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                    String eventId = resultSet.getString("id");
                eventIds.add(eventId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving event IDs: " + e.getMessage());
            throw e; // Re-throw the exception to be handled by the caller
        }

        return eventIds;
    }

    public String getEventNameByEventId(int eventId) {
        String eventName = "";
        String query = "SELECT nom_event FROM evenement WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                eventName = resultSet.getString("nom_event");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventName;
    }


    @Override
    public List<Evenement> afficher() throws SQLException {
        String req = "SELECT * FROM evenement";
        List<Evenement> list = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Evenement evenement = new Evenement();
                //Evenement evenement = new Evenement();
                evenement.setId(res.getInt(1));
                evenement.setNom_event(res.getString(2));
                evenement.setDescription_event(res.getString(3));
                evenement.setLieu_event(res.getString(4));
                evenement.setDate_event(res.getTimestamp(5).toLocalDateTime());
                evenement.setImage(res.getString(6));

                list.add(evenement);
            }
            return list;
        }
    }

    /*public void modifierImageEvenement(int eventId, String newImagePath) throws SQLException {
        String req = "UPDATE evenement SET image=? WHERE id=?";
        try (PreparedStatement pre = cnx.prepareStatement(req)) {
            pre.setString(1, newImagePath);
            pre.setInt(2, eventId);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifying event image: " + e.getMessage());
        }
    }*/

    public List<Evenement> afficherbyTri(String tri) throws SQLException {
        String req = "SELECT * FROM evenement";
        List<Evenement> list = new ArrayList<>();
        try (Statement ste = cnx.createStatement(); ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Evenement evenement = new Evenement();
                //Evenement evenement = new Evenement();
                evenement.setId(res.getInt(1));
                evenement.setNom_event(res.getString(2));
                evenement.setDescription_event(res.getString(3));
                evenement.setLieu_event(res.getString(4));
                evenement.setDate_event(res.getTimestamp(5).toLocalDateTime());
                evenement.setImage(res.getString(6));

                list.add(evenement);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        } if (tri.equals("ASC"))
        {
            Collections.sort(list, Comparator.comparing(Evenement::getNom_event));}
        else {
            Collections.sort(list, Comparator.comparing(Evenement::getNom_event).reversed());
        }
        return list;
    }

    public void changeScreen(ActionEvent event, String s, String dashboard) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(s));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(dashboard);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
