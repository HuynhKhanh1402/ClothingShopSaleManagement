/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package me.khanh.clothingshopsalemanagement.model;

import com.formdev.flatlaf.FlatLightLaf;
import me.khanh.clothingshopsalemanagement.model.database.DatabaseConnector;
import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.manager.DAOManager;
import me.khanh.clothingshopsalemanagement.view.MainFrame;

/**
 * The ClothingShopSaleManagement class represents the core of the application.
 * It provides an instance to access the database connector and DAO manager, as
 * well as initializes the application's user interface.
 *
 * This class follows the Singleton pattern, ensuring that only one instance of
 * the class can exist at a time.
 *
 * @author ADMIN
 */
public class ClothingShopSaleManagement {

    private static ClothingShopSaleManagement INSTANCE;

    /**
     * Getter for the database connector instance.
     *
     * @return the database connector instance
     */
    @Getter
    private final DatabaseConnector databaseConnector;

    /**
     * Getter for the DAO manager instance.
     *
     * @return the DAO manager instance
     */
    @Getter
    private final DAOManager DAOManager;

    /**
     * Private constructor to prevent instantiation from outside the class. It
     * initializes the database connector, DAO manager, and sets up the
     * application's user interface.
     */
    private ClothingShopSaleManagement() {
        databaseConnector = new DatabaseConnector();
        DAOManager = new DAOManager(databaseConnector);
        FlatLightLaf.setup();
        new MainFrame().setVisible(true);
    }

    /**
     * Returns the singleton instance of ClothingShopSaleManagement. If the
     * instance does not exist, it creates a new one.
     *
     * @return the singleton instance of ClothingShopSaleManagement
     */
    public static ClothingShopSaleManagement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClothingShopSaleManagement();
        }
        return INSTANCE;
    }
}
