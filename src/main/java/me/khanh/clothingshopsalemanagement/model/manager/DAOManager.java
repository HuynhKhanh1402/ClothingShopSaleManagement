/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.manager;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.image.ImageDAO;
import me.khanh.clothingshopsalemanagement.model.authentication.UserDAO;
import me.khanh.clothingshopsalemanagement.model.database.DatabaseConnector;
import me.khanh.clothingshopsalemanagement.model.order.OrderDAO;
import me.khanh.clothingshopsalemanagement.model.product.ProductDAO;

/**
 * The DAOManager class serves as a central manager for all Data Access Objects
 * (DAOs) in the application. It provides access to various DAOs such as
 * UserDAO, ProductDAO, and ImageDAO, which encapsulate the logic for
 * interacting with the database.
 *
 * The class also provides a method to retrieve a database connection from the
 * underlying DatabaseConnector.
 *
 * @author ADMIN
 */
public class DAOManager {

    /**
     * Getter for the DatabaseConnector instance.
     */
    @Getter
    private final DatabaseConnector connector;
    /**
     * Getter for the UserDAO instance.
     */
    @Getter
    private final UserDAO userDAO;
    /**
     * Getter for the ProductDAO instance.
     */
    @Getter
    private final ProductDAO productDAO;
    /**
     * Getter for the ImageDAO instance.
     */
    @Getter
    private final ImageDAO imageDAO;
    @Getter
    private final OrderDAO orderDAO;

    /**
     * Constructs a new DAOManager with the specified DatabaseConnector.
     * Initializes instances of UserDAO, ProductDAO, and ImageDAO.
     *
     * @param connector the DatabaseConnector instance
     */
    public DAOManager(DatabaseConnector connector) {
        this.connector = connector;
        productDAO = new ProductDAO(this);
        imageDAO = new ImageDAO(this);
        userDAO = new UserDAO(this);
        orderDAO = new OrderDAO(this);
    }

    /**
     * Retrieves a database connection from the underlying DatabaseConnector.
     *
     * @return a Connection object representing the database connection
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return connector.getConnection();
    }
}
