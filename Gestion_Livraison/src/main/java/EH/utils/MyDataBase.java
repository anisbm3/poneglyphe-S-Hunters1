package EH.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    private static MyDataBase instance ;
    final String URL="jdbc:mysql://127.0.0.1:3306/laugh_tale";
    final String USERNAME="root";
    final String PASSWORD="";
    Connection cnx ;
   private MyDataBase(){
     try {
         cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         System.out.println("Connected ...");
     }catch (SQLException e){
         System.out.println(e.getMessage());
         System.out.println("--------not connected------- ");
     }

   }
   public static MyDataBase getInstance(){
     if (instance == null)
         instance = new MyDataBase();
     return instance;
   }

    public Connection getCnx() {
        return cnx;
    }
}

