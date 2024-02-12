package tn.esprit.Test;
import tn.esprit.Models.Evenement;
import tn.esprit.Models.Reservation;
import tn.esprit.Services.ServiceEvenement;
import tn.esprit.Services.ServiceReservation;
import java.sql.SQLException;
import java.util.List;


import java.time.LocalDateTime;
public class Main {
    public static void main(String[] args) {
        // Creating instances of service classes
        ServiceEvenement evenementService = new ServiceEvenement();
        ServiceReservation reservationService = new ServiceReservation();

        try {
            // Adding an event
            Evenement newEvent = new Evenement();
            newEvent.setNom_Event("New Event");
            newEvent.setDescription_Event("Description of the new event");
            newEvent.setLieu_Event("Event Location");

            // Set the date and time of the event
            // Set the date and time of the event
            LocalDateTime eventDateTime = LocalDateTime.of(2024, 2, 12, 20, 30);
            newEvent.setDate_Event(eventDateTime);


            // Display the date and time of the event
            System.out.println("Event Date and Time: " + newEvent.getDate_Event());
            evenementService.ajouter(newEvent);
            System.out.println("Event added successfully.");

            // Adding a reservation
            Reservation newReservation = new Reservation();
            newReservation.setNom_Reseervation("New Reservation");
            newReservation.setNB_Places(5);
            newReservation.setEtat("Pending");
            newReservation.setID_Event(1); // Assuming the event ID is 1
            reservationService.ajouter(newReservation);
            System.out.println("Reservation added successfully.");
            Reservation newReservation1 = new Reservation(5,"h",5,"gt",2);

            // Displaying all events
            System.out.println("Events:");
            List<Evenement> events = evenementService.afficher();
            for (Evenement event : events) {
                System.out.println("ID: " + event.getID_Event());
                System.out.println("Name: " + event.getNom_Event());
                System.out.println("Description: " + event.getDescription_Event());
                System.out.println("Location: " + event.getLieu_Event());
                System.out.println("Date: " + event.getDate_Event());
                System.out.println();
            }

            // Displaying all reservations
            System.out.println("Reservations:");
            List<Reservation> reservations = reservationService.afficher();
            for (Reservation reservation : reservations) {
                System.out.println("ID: " + reservation.getID_Reservation());
                System.out.println("Name: " + reservation.getNom_Reseervation());
                System.out.println("Number of Places: " + reservation.getNB_Places());
                System.out.println("State: " + reservation.getEtat());
                System.out.println("Event ID: " + reservation.getID_Event());

                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
