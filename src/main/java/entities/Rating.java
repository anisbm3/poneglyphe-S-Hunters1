package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Rating {
    int ID_User=1;
    int ID_Debat;
    int Rating;

    public int getID_Debat() {
        return ID_Debat;
    }

    public void setID_Debat(int ID_Debat) {
        this.ID_Debat = ID_Debat;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User= ID_User;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating= Rating;
    }
}
