package tn.esprit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class datasource {
    private Connection cnx;
    private String url="jdbc:mysql://localhost:3306/laugh-tale";
    private String login="root";
    private String password="";
    public static datasource Instance;

    private datasource(){
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("SUCCESS");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static datasource getInstance(){
        if(Instance==null)
            Instance =new datasource();
        return Instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
