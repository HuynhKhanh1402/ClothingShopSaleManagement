/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.ClothingShopSaleManagement;
import me.khanh.clothingshopsalemanagement.model.database.DatabaseConnector;
import me.khanh.clothingshopsalemanagement.model.manager.DAOManager;

/**
 * The UserDAO class provides methods to perform CRUD operations on User
 * entities in the database asynchronously. It encapsulates the logic for
 * interacting with the AUTHENTICATION table in the database.
 *
 * @author ADMIN
 */
public class UserDAO {

    /**
     * Getter for the DAO manager instance.
     */
    @Getter
    private final DAOManager manager;

    /**
     * Constructs a new UserDAO with the specified DAO manager.
     *
     * @param manager the DAO manager instance
     */
    public UserDAO(DAOManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a CompletableFuture containing a list of all users
     */
    public CompletableFuture<List<User>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "SELECT * FROM AUTHENTICATION ORDER BY USERNAME ASC";
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    String username = resultSet.getString("USERNAME");
                    String hashedPass = resultSet.getString("PASSWORD");
                    String fullName = resultSet.getString("NAME");
                    Permission perm = Permission.valueOf(resultSet.getString("PERMISSION").toUpperCase());
                    User user = new User(username, hashedPass, fullName, perm);
                    users.add(user);
                }
                return users;
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Retrieves a user by username from the database.
     *
     * @param username the username of the user to retrieve
     * @return a CompletableFuture containing an optional User object
     */
    public CompletableFuture<Optional<User>> get(String username) {
        return CompletableFuture.supplyAsync(() -> {
            DatabaseConnector connector = ClothingShopSaleManagement.getInstance().getDatabaseConnector();
            try (Connection connection = connector.getConnection()) {
                String sql = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ?";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, username);

                ResultSet resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String hashedPass = resultSet.getString("PASSWORD");
                    String fullName = resultSet.getString("NAME");
                    Permission perm = Permission.valueOf(resultSet.getString("PERMISSION").toUpperCase());
                    return Optional.of(new User(username, hashedPass, fullName, perm));
                }
                return Optional.empty();
            } catch (Exception e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Saves a user to the database.
     *
     * @param user the user to save
     * @return a CompletableFuture representing the completion of the save
     * operation
     */
    public CompletableFuture<Void> save(User user) {
        return CompletableFuture.runAsync(() -> {
            DatabaseConnector connector = ClothingShopSaleManagement.getInstance().getDatabaseConnector();
            try (Connection connection = connector.getConnection()) {
                String sql = "INSERT INTO AUTHENTICATION(USERNAME, PASSWORD, NAME, PERMISSION) VALUES(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getHashedPassword());
                ps.setString(3, user.getFullName());
                ps.setString(4, user.getPermission().toString());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Updates a user in the database.
     *
     * @param user the user to update
     * @return a CompletableFuture representing the completion of the update
     * operation
     */
    public CompletableFuture<Void> update(User user) {
        return CompletableFuture.runAsync(() -> {
            DatabaseConnector connector = ClothingShopSaleManagement.getInstance().getDatabaseConnector();
            try (Connection connection = connector.getConnection()) {
                String sql = "UPDATE AUTHENTICATION SET PASSWORD = ?, NAME = ?, PERMISSION = ? WHERE USERNAME = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getHashedPassword());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getPermission().toString());
                ps.setString(4, user.getUsername());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Deletes a user from the database.
     *
     * @param username the username of the user to delete
     * @return a CompletableFuture representing the completion of the delete
     * operation
     */
    public CompletableFuture<Void> delete(String username) {
        return CompletableFuture.runAsync(() -> {
            DatabaseConnector connector = ClothingShopSaleManagement.getInstance().getDatabaseConnector();
            try (Connection connection = connector.getConnection()) {
                String sql = "DELETE FROM AUTHENTICATION WHERE USERNAME = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }
}
