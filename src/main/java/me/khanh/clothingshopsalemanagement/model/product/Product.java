/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.product;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * The Product class represents a product in the clothing shop. It encapsulates
 * various attributes such as ID, name, description, price, stock, brand, size,
 * gender, color, etc.
 *
 * @author ADMIN
 */
public class Product {

    /**
     * Getter for the product ID.
     */
    @Getter
    private final int id;

    /**
     * Getter and setter for the product name.
     */
    @Getter
    @Setter
    private String name;

    /**
     * Getter and setter for the product description.
     */
    @Getter
    @Setter
    private String description;

    /**
     * Getter and setter for the product price.
     */
    @Getter
    @Setter
    private double price;

    /**
     * Getter and setter for the product stock.
     */
    @Getter
    @Setter
    private int stock;

    /**
     * Getter and setter for the product brand.
     */
    @Getter
    @Setter
    private String brand;

    /**
     * Getter and setter for the product size.
     */
    @Getter
    @Setter
    private String size;

    /**
     * Getter and setter for the product gender.
     */
    @Getter
    @Setter
    private Gender gender;

    /**
     * Getter and setter for the product color.
     */
    @Getter
    @Setter
    private String color;

    /**
     * Getter and setter for the product image ID.
     */
    @Getter
    @Setter
    private int imageId;

    /**
     * Getter and setter for the product added by.
     */
    @Getter
    @Setter
    private String addedBy;

    /**
     * Getter and setter for the product added date.
     */
    @Getter
    @Setter
    private Date addedDate;

    /**
     * Constructs a new Product with the specified attributes.
     *
     * @param id the product ID
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param stock the product stock
     * @param brand the product brand
     * @param size the product size
     * @param gender the product gender
     * @param color the product color
     * @param imageId the product image ID
     * @param addedBy the user who added the product
     * @param addedDate the date when the product was added
     */
    public Product(
            int id,
            String name,
            String description,
            double price,
            int stock,
            String brand,
            String size,
            Gender gender,
            String color,
            int imageId,
            String addedBy,
            Date addedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
        this.size = size;
        this.gender = gender;
        this.color = color;
        this.imageId = imageId;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
    }
}
