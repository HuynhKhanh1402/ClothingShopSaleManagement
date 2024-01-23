/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.image;

import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.manager.DAOManager;

/**
 * The ImageDAO class provides data access methods for handling image data in
 * the application. It supports operations such as retrieving, saving, updating,
 * and deleting images from the database asynchronously.
 *
 * @author ADMIN
 */
public class ImageDAO {

    /**
     * Getter for the DAO manager instance.
     *
     * @return the DAO manager instance
     */
    @Getter
    private final DAOManager manager;

    /**
     * Constructor for the ImageDAO class.
     *
     * @param manager the DAO manager instance
     */
    public ImageDAO(DAOManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves an image from the database based on the specified image ID.
     *
     * @param imageID the ID of the image to retrieve
     * @return a CompletableFuture containing an optional ImageIcon
     */
    public CompletableFuture<Optional<ImageIcon>> get(int imageID) {
        return CompletableFuture.supplyAsync(() -> {
            if (imageID <= 0) {
                return Optional.empty();
            }
            try (Connection connection = manager.getConnection()) {
                String sql = "SELECT * FROM IMAGE WHERE ImageID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, imageID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    byte[] byteImage = rs.getBytes("IMG");
                    ImageIcon image = new ImageIcon(byteImage);
                    return Optional.of(image);
                }
                return Optional.empty();
            } catch (Exception ex) {
                Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Saves an image to the database.
     *
     * @param image the Image object to save
     * @return a CompletableFuture containing the ID of the saved image
     */
    public CompletableFuture<Integer> save(Image image) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "INSERT INTO IMAGE(IMG) VALUES(?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setBytes(1, convertImageToByteArray(image));

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    throw new RuntimeException("Creating Image failed, no rows affected.");
                }

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new RuntimeException("Creating image failed, no ID obtained.");

            } catch (Exception ex) {
                Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Overloaded method to save an ImageIcon to the database.
     *
     * @param icon the ImageIcon to save
     * @return a CompletableFuture containing the ID of the saved image
     */
    public CompletableFuture<Integer> save(ImageIcon icon) {
        return save(icon.getImage());
    }

    /**
     * Updates an existing image in the database.
     *
     * @param imageID the ID of the image to update
     * @param image the new Image object
     * @return a CompletableFuture representing the completion of the update
     * operation
     */
    public CompletableFuture<Void> update(int imageID, Image image) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "UPDATE IMAGE SET IMG = ? WHERE ImageID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setBytes(1, convertImageToByteArray(image));
                ps.setInt(2, imageID);
                ps.executeUpdate();
            } catch (SQLException | IOException ex) {
                Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Deletes an image from the database based on the specified image ID.
     *
     * @param imageID the ID of the image to delete
     * @return a CompletableFuture representing the completion of the delete
     * operation
     */
    public CompletableFuture<Void> delete(int imageID) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = manager.getConnection()) {
                String sql = "DELETE FROM IMAGE WHERE ImageID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, imageID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Converts an Image object to a byte array.
     *
     * @param image the Image object to convert
     * @return the byte array representing the converted image
     * @throws IOException if an I/O error occurs
     */
    private byte[] convertImageToByteArray(Image image) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", outStream);
        return outStream.toByteArray();
    }

}
