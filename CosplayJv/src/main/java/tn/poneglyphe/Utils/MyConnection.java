package tn.poneglyphe.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    static final String URL ="jdbc:mysql://localhost:3306/gestion_cosplay";
    static final String USER ="root";
    static final String PASSWORD ="";

    //var
    private Connection cnx;
    //1
    static MyConnection instance;

    //const
    //2
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("connexion etablie ......");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public Connection getCnx() {
        return cnx;
    }

    //3
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();

        return instance;
    }
}
