/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement;

import me.khanh.clothingshopsalemanagement.model.ClothingShopSaleManagement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Bootstrap class serves as the entry point for the Clothing Shop Sale
 * Management application. It initializes the main application instance and
 * handles any exceptions that may occur.
 */
public class Bootstrap {

    /**
     * The main method of the application. It initializes the
     * ClothingShopSaleManagement instance. If any exception occurs, it displays
     * an error dialog to the user.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            ClothingShopSaleManagement.getInstance();
        } catch (Exception e) {
            JFrame frame = new JFrame();
            showErrorDialog(frame, e);
        }
    }

    /**
     * Displays an error dialog containing the exception details.
     *
     * @param parent the parent JFrame for the dialog
     * @param ex the exception to display
     */
    private static void showErrorDialog(JFrame parent, Exception ex) {
        StringBuilder sb = new StringBuilder();
        collectExceptionInfo(ex, sb);
        JOptionPane.showMessageDialog(parent, sb.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Collects the details of the exception and appends them to the
     * StringBuilder.
     *
     * @param ex the exception to collect details from
     * @param sb the StringBuilder to append the details to
     */
    private static void collectExceptionInfo(Throwable ex, StringBuilder sb) {
        sb.append(ex.toString()).append("\n");

        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append("\t").append(element.toString()).append("\n");
        }

        if (ex.getCause() != null) {
            sb.append("\nCaused by: ");
            collectExceptionInfo(ex.getCause(), sb);
        }
    }
}
