/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.manager.DAOManager;
import static me.khanh.clothingshopsalemanagement.model.order.ViewMode.DAY;
import static me.khanh.clothingshopsalemanagement.model.order.ViewMode.MONTH;
import static me.khanh.clothingshopsalemanagement.model.order.ViewMode.TOTAL;
import me.khanh.clothingshopsalemanagement.model.product.Gender;
import me.khanh.clothingshopsalemanagement.model.product.Product;
import me.khanh.clothingshopsalemanagement.view.chart.RevenueChartData;
import me.khanh.clothingshopsalemanagement.view.chart.RevenueMonthy;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {

    @Getter
    private final DAOManager manager;

    public OrderDAO(DAOManager manager) {
        this.manager = manager;
    }

    public CompletableFuture<Optional<Order>> get(int orderID) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM ORDERS WHERE OrderID = ?";

            try (Connection connection = manager.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    List<OrderedProduct> products = getListOrderedProducts(orderID).join();

                    String name = rs.getString("Name");
                    String phone = rs.getString("Phone");
                    Date date = new Date(rs.getTimestamp("OrderDate").getTime());

                    return Optional.of(new Order(orderID, name, phone, date, products));
                }
                return Optional.empty();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<List<OrderedProduct>> getListOrderedProducts(int orderID) {
        return CompletableFuture.supplyAsync(() -> {
            List<OrderedProduct> orderedProducts = new ArrayList<>();

            String sql = "SELECT * FROM OrderDetails JOIN Product ON OrderDetails.ProductID = Product.ProductID WHERE OrderDetails.OrderID = ?";
            try (Connection connection = manager.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderID);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
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
                    Product product = new Product(productID, name, description, price, stock, brand, size, gender, color, imageID, addedBy, new Date(addedTime.getTime()));

                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("UnitPrice");

                    OrderedProduct orderedProduct = new OrderedProduct(orderID, product, quantity, unitPrice);
                    orderedProducts.add(orderedProduct);
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }

            return orderedProducts;
        });
    }

    public CompletableFuture<List<Order>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                List<Order> orders = new ArrayList<>();

                String sql = "SELECT * FROM ORDERS";
                ResultSet rs = connection.createStatement().executeQuery(sql);

                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    List<OrderedProduct> products = getListOrderedProducts(orderID).join();

                    String name = rs.getString("Name");
                    String phone = rs.getString("Phone");
                    Date date = new Date(rs.getTimestamp("OrderDate").getTime());

                    orders.add(new Order(orderID, name, phone, date, products));
                }

                return orders;
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Integer> save(String customerName, String customerPhone) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO ORDERS(Name, Phone, OrderDate) VALUE(?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customerName);
                ps.setString(2, customerPhone);
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Creating order failed, no rows affected.");
                }

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new RuntimeException("Creating order failed, no ID obtained.");
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Void> saveOrderDetails(int orderID, List<OrderedProduct> products) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO OrderDetails(OrderID, ProductID, Quantity, UnitPrice) VALUES(?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                for (int i = 0; i < products.size(); i++) {
                    OrderedProduct product = products.get(i);
                    ps.setInt(1, orderID);
                    ps.setInt(2, product.getProduct().getId());
                    ps.setInt(3, product.getQuantity());
                    ps.setDouble(4, product.getUnitPrice());
                    ps.executeUpdate();

                    manager.getProductDAO().orderProduct(product).join();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    private CompletableFuture<Void> deleleOrderDetails(int orderID) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "DELETE FROM OrderDetails WHERE OrderID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Void> deleteOrder(int orderID) {
        return deleleOrderDetails(orderID).thenRunAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "DELETE FROM ORDERS WHERE OrderID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, orderID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Double> getRevenue(ViewMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("ViewMode is null.");
        }
        return CompletableFuture.supplyAsync(() -> {
            String sql = switch (mode) {
                case TOTAL ->
                    "SELECT SUM(UnitPrice * Quantity) AS Revenue FROM OrderDetails;";
                case MONTH ->
                    "SELECT SUM(UnitPrice * Quantity) AS Revenue FROM OrderDetails OD JOIN Orders O ON OD.OrderID = O.OrderID WHERE MONTH(now()) = MONTH(O.OrderDate) AND YEAR(NOW()) = YEAR(O.OrderDate);";
                case DAY ->
                    "SELECT SUM(UnitPrice * Quantity) AS Revenue FROM OrderDetails OD JOIN Orders O ON OD.OrderID = O.OrderID WHERE current_date() = DATE(O.OrderDate);";
            };
            try (Connection connection = manager.getConnection()) {
                ResultSet rs = connection.createStatement().executeQuery(sql);
                if (rs.next()) {
                    return rs.getDouble(1);
                }
                return 0D;
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Integer> getOrderedProducts(ViewMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("ViewMode is null.");
        }
        return CompletableFuture.supplyAsync(() -> {
            String sql = switch (mode) {
                case TOTAL ->
                    "SELECT SUM(Quantity) AS ProductCount FROM OrderDetails;";
                case MONTH ->
                    "SELECT SUM(Quantity) AS ProductCount FROM OrderDetails OD JOIN Orders O ON OD.OrderID = O.OrderID WHERE MONTH(NOW()) = MONTH(O.OrderDate) AND YEAR(NOW()) = YEAR(O.OrderDate);";
                case DAY ->
                    "SELECT SUM(Quantity) AS ProductCount FROM OrderDetails OD JOIN Orders O ON OD.OrderID = O.OrderID WHERE current_date() = DATE(O.OrderDate);";
            };
            try (Connection connection = manager.getConnection()) {
                ResultSet rs = connection.createStatement().executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<RevenueChartData> getRevenueChartData() {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                List<RevenueMonthy> data = new ArrayList<>();

                String sql = "SELECT SUM(UnitPrice * Quantity) AS Revenue FROM OrderDetails OD JOIN Orders O ON OD.OrderID = O.OrderID WHERE MONTH(O.OrderDate) = ? AND YEAR(O.OrderDate) = ?";
                PreparedStatement ps = connection.prepareStatement(sql);

                LocalDate currentDate = LocalDate.now();

                for (int i = 0; i < 12; i++) {
                    if (i != 0) {
                        currentDate = currentDate.minusMonths(1);
                    }

                    int month = Integer.parseInt(currentDate.format(DateTimeFormatter.ofPattern("M")));
                    int year = Integer.parseInt(currentDate.format(DateTimeFormatter.ofPattern("yyyy")));
                    ps.setInt(1, month);
                    ps.setInt(2, year);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        double revenue = rs.getDouble(1);
                        data.add(new RevenueMonthy(month, year, revenue));
                    }
                }
                return new RevenueChartData(data);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    public CompletableFuture<Void> deleteAllOrderOfProduct(int productID) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "DELETE FROM OrderDetails WHERE ProductID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, productID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }
}
