/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.authentication.User;
import me.khanh.clothingshopsalemanagement.model.manager.DAOManager;
import me.khanh.clothingshopsalemanagement.model.order.OrderedProduct;

/**
 * The ProductDAO class provides methods to interact with the database related
 * to Product entities. This class encapsulates database operations such as
 * retrieving, saving, updating, and deleting products. It utilizes asynchronous
 * programming using CompletableFuture for database operations.
 *
 * @author ADMIN
 */
public class ProductDAO {

    @Getter
    private final DAOManager manager;
    @Getter
    private final List<Product> cachedProducts = new ArrayList<>();

    /**
     * Constructs a new ProductDAO with the specified DAOManager.
     *
     * @param manager the DAOManager instance
     */
    public ProductDAO(DAOManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a CompletableFuture containing a list of all products
     */
    public CompletableFuture<List<Product>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "SELECT * FROM PRODUCT";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                List<Product> products = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("ProductID");
                    String name = rs.getNString("NAME");
                    String addedBy = rs.getString("AddedBy");
                    Timestamp addedTime = rs.getTimestamp("AddedDate");
                    String description = rs.getNString("DESCRIPTION");
                    double price = rs.getDouble("PRICE");
                    int stock = rs.getInt("STOCK");
                    String brand = rs.getNString("BRAND");
                    String size = rs.getNString("SIZE");
                    Gender gender = Gender.valueOf(rs.getString("GENDER").toUpperCase());
                    String color = rs.getNString("COLOR");
                    int imageID = rs.getInt("ImageID");

                    Product product = new Product(id, name, description, price, stock, brand, size, gender, color, imageID, addedBy, new Date(addedTime.getTime()));
                    products.add(product);
                }

                cachedProducts.clear();
                cachedProducts.addAll(products);

                return products;
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Retrieves a product by its ID from the database.
     *
     * @param id the ID of the product to retrieve
     * @return a CompletableFuture containing an Optional of the product if
     * found, otherwise empty
     */
    public CompletableFuture<Optional<Product>> get(int id) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "SELECT * FROM PRODUCT WHERE ProductID = ?";
                PreparedStatement ps = connection.prepareCall(sql);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getNString("NAME");
                    String addedBy = rs.getString("AddedBy");
                    Timestamp addedTime = rs.getTimestamp("AddedDate");
                    String description = rs.getNString("DESCRIPTION");
                    double price = rs.getDouble("PRICE");
                    int stock = rs.getInt("STOCK");
                    String brand = rs.getNString("BRAND");
                    String size = rs.getNString("SIZE");
                    Gender gender = Gender.valueOf(rs.getString("GENDER").toUpperCase());
                    String color = rs.getNString("COLOR");
                    int imageID = rs.getInt("ImageID");
                    Product product = new Product(id, name, description, price, stock, brand, size, gender, color, imageID, addedBy, new Date(addedTime.getTime()));
                    return Optional.of(product);
                }
                return Optional.empty();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Saves a product to the database.
     *
     * @param product the product to save
     * @return a CompletableFuture representing the completion of the save
     * operation
     */
    public CompletableFuture<Void> save(Product product) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO PRODUCT(ProductID, NAME, AddedBy, AddedDate, DESCRIPTION, PRICE, STOCK, BRAND, SIZE, GENDER, COLOR, ImageID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, product.getId());
                ps.setNString(2, product.getName());
                ps.setString(3, product.getAddedBy());
                ps.setTimestamp(4, new Timestamp(product.getAddedDate().getTime()));
                ps.setNString(5, product.getDescription());
                ps.setDouble(6, product.getPrice());
                ps.setInt(7, product.getStock());
                ps.setNString(8, product.getBrand());
                ps.setNString(9, product.getSize());
                ps.setString(10, product.getGender().toString());
                ps.setNString(11, product.getColor());
                ps.setInt(12, product.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Integer> save(String name, User addedBy, String description, double price, int stock, String brand, String size, Gender gender, String color, int imageID) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO PRODUCT(NAME, AddedBy, AddedDate, DESCRIPTION, PRICE, STOCK, BRAND, SIZE, GENDER, COLOR, ImageID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, addedBy.getUsername());
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.setString(4, description);
                ps.setDouble(5, price);
                ps.setInt(6, stock);
                ps.setString(7, brand);
                ps.setString(8, size);
                ps.setString(9, gender.toString());
                ps.setString(10, color);
                ps.setInt(11, imageID);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new RuntimeException("Saving product failed, no ID obtained.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Integer> save(String name, User addedBy, String description, double price, int stock, String brand, String size, Gender gender, String color) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO PRODUCT(NAME, AddedBy, AddedDate, DESCRIPTION, PRICE, STOCK, BRAND, SIZE, GENDER, COLOR) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, addedBy.getUsername());
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.setString(4, description);
                ps.setDouble(5, price);
                ps.setInt(6, stock);
                ps.setString(7, brand);
                ps.setString(8, size);
                ps.setString(9, gender.toString());
                ps.setString(10, color);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new RuntimeException("Saving product failed, no ID obtained.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Updates a product in the database.
     *
     * @param product the product to update
     * @return a CompletableFuture representing the completion of the update
     * operation
     */
    public CompletableFuture<Void> update(Product product) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "UPDATE PRODUCT SET NAME = ?, AddedBy = ?, AddedDate = ?, DESCRIPTION = ?, PRICE = ?, STOCK = ?, BRAND = ?, SIZE = ?, GENDER = ?, COLOR = ?, ImageID = ? WHERE ProductID = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setNString(1, product.getName());
                ps.setString(2, product.getAddedBy());
                ps.setTimestamp(3, new Timestamp(product.getAddedDate().getTime()));
                ps.setNString(4, product.getDescription());
                ps.setDouble(5, product.getPrice());
                ps.setInt(6, product.getStock());
                ps.setNString(7, product.getBrand());
                ps.setNString(8, product.getSize());
                ps.setString(9, product.getGender().toString());
                ps.setNString(10, product.getColor());
                if (product.getImageId() <= 0) {
                    ps.setObject(11, null);
                } else {
                    ps.setInt(11, product.getImageId());
                }
                ps.setInt(12, product.getId());

                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param productID the ID of the product to delete
     * @return a CompletableFuture representing the completion of the delete
     * operation
     */
    public CompletableFuture<Void> delete(int productID) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "DELETE FROM PRODUCT WHERE PRODUCTID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, productID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Void> orderProduct(OrderedProduct orderedProduct) {
        if (orderedProduct.getProduct().getStock() < 1) {
            throw new IllegalArgumentException("Out of stock!");
        }
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "UPDATE PRODUCT SET STOCK = STOCK - ? WHERE PRODUCTID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderedProduct.getQuantity());
                ps.setInt(2, orderedProduct.getProduct().getId());
                ps.executeUpdate();
                orderedProduct.getProduct().setStock(orderedProduct.getProduct().getStock() - orderedProduct.getQuantity());
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }
}
