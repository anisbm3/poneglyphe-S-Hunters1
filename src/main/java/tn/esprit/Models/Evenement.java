package tn.esprit.Models;
import java.time.LocalDateTime;

public class Evenement {

    private int ID_Event;
    private String Nom_Event, Description_Event,Lieu_Event;
 //private LocalDate Date_Event;
 private LocalDateTime Date_Event;


    public Evenement() {
    }

    public Evenement(int ID_Event ,String Nom_Event, String Description_Event, String Lieu_Event,LocalDateTime Date_Event) {
        this.ID_Event = ID_Event;
        this.Nom_Event = Nom_Event;
        this.Description_Event = Description_Event;
        this.Lieu_Event = Lieu_Event;
        this.Date_Event = Date_Event;
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");
       // this.Date_Event = LocalTime.parse(temps, DateTimeFormatter.ofPattern("HH:mm"));
        //this.Date_Event = LocalDateTime.parse(temps, formatter);

    }

    public int getID_Event() {
        return ID_Event;
    }

    public void setID_Event(int ID_Event) {
        this.ID_Event = ID_Event;
    }


    public String getNom_Event() {
        return Nom_Event;
    }

    public void setNom_Event(String Nom_Event) {
        this.Nom_Event = Nom_Event;
    }

    public String getDescription_Event() {
        return Description_Event;
    }

    public void setDescription_Event(String Description_Event) {
        this.Description_Event = Description_Event;
    }

    public String getLieu_Event(){
        return Lieu_Event;
    }

    public void setLieu_Event(String Lieu_Event){
        this.Lieu_Event = Lieu_Event;
    }

   /* public LocalDate getDate_Event() {
        return Date_Event;
    }*/
   /* public void setDate_Event(String timeString) {
        this.Date_Event = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }*/
    //public void setDate_Event (String Date_Event){this.Date_Event = LocalDateTime.parse(Date_Event);}
   /* public void setDate_Event(String date_Event) {
        this.Date_Event = LocalDate.parse(date_Event); // Assuming date_Event is of format 'YYYY-MM-DD'
    }*/




        // Other attributes and methods

        public void setDate_Event(LocalDateTime Date_Event) {
            this.Date_Event = Date_Event;
        }

        public LocalDateTime getDate_Event() {
            return Date_Event;
        }

    @Override
    public String toString() {
        return "Evenement{" +
                "ID_Event=" + ID_Event +
                ", Nom_Event=" + Nom_Event +
                ", Description_Event='" + Description_Event + '\'' +
                ", Lieu_Event='" + Lieu_Event + '\'' +
                ", Date_Event='" + Date_Event + '\'' +
                "}\n";
    }
}
