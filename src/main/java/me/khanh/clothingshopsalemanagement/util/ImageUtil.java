/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.util;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The ImageUtil class provides utility methods for working with images,
 * particularly for resizing ImageIcon objects.
 *
 * @author ADMIN
 */
public class ImageUtil {

    /**
     * Resizes the given ImageIcon to the specified width and height.
     *
     * @param icon the ImageIcon to be resized
     * @param width the desired width of the resized image
     * @param height the desired height of the resized image
     * @return a new ImageIcon with the specified dimensions
     */
    public static ImageIcon resize(ImageIcon icon, int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static ImageIcon resize(ImageIcon icon, Dimension dimension) {
        return resize(icon, (int) dimension.getHeight(), (int) dimension.getHeight());
    }

}
