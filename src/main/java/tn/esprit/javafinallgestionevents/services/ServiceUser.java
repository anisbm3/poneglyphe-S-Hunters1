package tn.esprit.javafinallgestionevents.services;

import tn.esprit.javafinallgestionevents.models.User;
import tn.esprit.javafinallgestionevents.utils.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    private Connection cnx;

    public ServiceUser() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    // Méthode pour récupérer tous les utilisateurs depuis la base de données
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setRoles(resultSet.getString("roles"));
                userList.add(user);
            }
        }
        return userList;
    }

    // Méthode pour récupérer l'ID de l'utilisateur en fonction de son rôle
    public int getUserIdByRole(String role) throws SQLException {
        String query = "SELECT id FROM user WHERE roles = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1; // Si aucun utilisateur correspondant n'est trouvé
    }


   /* public String getRoleByUserId(int userId) throws SQLException {
        String query = "SELECT roles FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("roles");
            }
        }
        return null; // Si aucun utilisateur correspondant n'est trouvé
    }*/


    public List<String> getRoleByUserId(int userId) throws SQLException {
        String query = "SELECT roles FROM user WHERE id = ?";
        List<String> roles = new ArrayList<>();
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roles.add(resultSet.getString("roles"));
            }
        }
        return roles;
    }

    public List<Integer> getAllUserIds() throws SQLException {
        List<Integer> userIds = new ArrayList<>();
        String query = "SELECT id FROM user";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userIds.add(resultSet.getInt("id"));
            }
        }
        return userIds;
    }

}
