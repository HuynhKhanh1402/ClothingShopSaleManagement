
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * The DatabaseConnector class provides functionalities for managing database
 * connections and operations. It uses the HikariCP connection pool for
 * efficient connection management.
 *
 * The class also contains methods to create required database tables upon
 * initialization.
 *
 * @author ADMIN
 */
public class DatabaseConnector {

    /**
     * Database connection properties
     */
    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "clothing_shop";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private final HikariDataSource dataSource;

    /**
     * Constructs a new DatabaseConnector and initializes the database
     * connection pool. Also creates necessary database tables if they do not
     * exist.
     */
    public DatabaseConnector() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE));
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        // Initialize database connection
        try {
            dataSource = new HikariDataSource(config);
            Logger.getLogger(DatabaseConnector.class.getName()).info("Connecting database successfully.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while connecting to database", e);
        }

        createTables();
    }

    /**
     * Retrieves a database connection from the connection pool.
     *
     * @return a Connection object representing the database connection
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Creates required database tables if they do not exist.
     */
    private void createTables() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            // Create authentiacation table
            statement.execute("CREATE TABLE IF NOT EXISTS AUTHENTICATION("
                    + "  USERNAME VARCHAR(128) NOT NULL PRIMARY KEY, "
                    + "  PASSWORD VARCHAR(256) NOT NULL, "
                    + "  NAME VARCHAR(64) NOT NULL,"
                    + "  PERMISSION VARCHAR(16) NOT NULL CHECK (PERMISSION = 'ADMINISTRATOR' OR PERMISSION = 'MODERATOR')"
                    + ");");

            // Create image table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS IMAGE("
                    + "  ImageID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "  IMG     LONGBLOB NOT NULL"
                    + ");");

            // Create product table
            statement.executeUpdate("""
                                    CREATE TABLE IF NOT EXISTS PRODUCT (
                                      ProductID   INT PRIMARY KEY AUTO_INCREMENT, 
                                      NAME        NVARCHAR(255) NOT NULL, 
                                      AddedBy     VARCHAR(128) NOT NULL, 
                                      AddedDate   TIMESTAMP NOT NULL, 
                                      DESCRIPTION TEXT, 
                                      PRICE       DECIMAL(10, 2) NOT NULL, 
                                      STOCK       INT NOT NULL, 
                                      BRAND       NVARCHAR(255), 
                                      SIZE        NVARCHAR(50), 
                                      GENDER      ENUM('MALE', 'FEMALE', 'UNISEX'), 
                                      COLOR       NVARCHAR(50), 
                                      ImageID     INT,     
                                      FOREIGN KEY (AddedBy) REFERENCES AUTHENTICATION(USERNAME),
                                      FOREIGN KEY (ImageID) REFERENCES IMAGE(ImageID)
                                    );""");

            // Create orderS table
            statement.executeUpdate("""
                                    CREATE TABLE IF NOT EXISTS ORDERS(
                                      OrderID   INT PRIMARY KEY AUTO_INCREMENT, 
                                      Name      NVARCHAR(255) NOT NULL,
                                      Phone     VARCHAR(10) NOT NULL,
                                      OrderDate TIMESTAMP NOT NULL
                                    );
                                    """);

            // Create order details table
            statement.executeUpdate("""
                                    CREATE TABLE IF NOT EXISTS OrderDetails (
                                      OrderID   INT,
                                      ProductID INT,
                                      Quantity  INT NOT NULL CHECK (Quantity > 0),
                                      UnitPrice DECIMAL(10, 2) NOT NULL,
                                      PRIMARY KEY(OrderID, ProductID),
                                      FOREIGN KEY (OrderID) REFERENCES ORDERS(OrderID),
                                      FOREIGN KEY (ProductID) REFERENCES PRODUCT(ProductID)
                                    ); 
                                    """);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Executes the specified SQL statement and returns the result.
     *
     * @param sql the SQL statement to execute
     * @return the result of the SQL update operation
     */
    public int executeUpdate(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
