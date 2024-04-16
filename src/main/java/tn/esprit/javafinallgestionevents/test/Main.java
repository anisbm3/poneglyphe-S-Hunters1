/*package tn.esprit.javafinallgestionevents.test;


import tn.esprit.javafinallgestionevents.models.Evenement;
import tn.esprit.javafinallgestionevents.models.Reservation;
import tn.esprit.javafinallgestionevents.services.ServiceEvenement;
import tn.esprit.javafinallgestionevents.services.ServiceReservation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Creating instances of service classes
        ServiceEvenement evenementService = new ServiceEvenement();
        ServiceReservation reservationService = new ServiceReservation(nomReservation.getText(), nbPlaces, Etat.getText(), evenementId.getValue(), userId.getValue());

        try {
            // Adding an event
           /* Evenement newEvent = new Evenement();
            newEvent.setNom_event("NewEvent1");
            newEvent.setDescription_event("Description of the new event");
            newEvent.setLieu_event("Event Location");
            // Set the date and time of the event
            LocalDateTime eventDateTime = LocalDateTime.of(2024, 2, 12, 20, 30);
            newEvent.setDate_event(eventDateTime);
            // Display the date and time of the event
            // System.out.println("Event Date and Time: " + newEvent.getDate_Event());
            newEvent.setImage("url");*/
            /*evenementService.ajouter(newEvent);
            System.out.println("Event added successfully.");*/
/*newEvent.setId(2);
evenementService.modifier(newEvent);
 System.out.println("Event update successfully.");*/

          /* newEvent.setId(2);
            evenementService.supprimer(newEvent);
             System.out.println("Event deleted successfully.");*/


            // Adding a reservation
           /* Reservation newReservation = new Reservation();
            newReservation.setNom_reservation("New Reservation");
            newReservation.setNb_place(5);
            newReservation.setEtat("Gg");
            LocalDateTime eventDateTime = LocalDateTime.of(2024, 2, 12, 20, 30);

            newReservation.setResdate(eventDateTime);
            newReservation.setUser_id(1);
            newReservation.setEvenement_id(3);

          /*  reservationService.ajouter(newReservation);
            System.out.println("Reservation added successfully.");*/


          /*  newReservation.setId(39);
            reservationService.modifier(newReservation);
            System.out.println("Reservation updated successfully.");*/
           /* newReservation.setId(6);
            reservationService.supprimer(newReservation);
            System.out.println("Reservation deleted successfully.");*/

            // Displaying all events
       /*   System.out.println("Events:");
            List<Evenement> events = evenementService.afficher();
            for (Evenement event : events) {
                System.out.println("ID: " + event.getId());
                System.out.println("Name: " + event.getNom_event());
                System.out.println("Description: " + event.getDescription_event());
                System.out.println("Location: " + event.getLieu_event());
                System.out.println("Date: " + event.getDate_event());
                System.out.println("images: " + event.getImage());
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/