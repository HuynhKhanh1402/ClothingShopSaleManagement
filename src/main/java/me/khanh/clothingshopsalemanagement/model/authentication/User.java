/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.authentication;

import lombok.Getter;
import lombok.Setter;

/**
 * The User class represents a user in the application's authentication system.
 * It contains information such as the username, hashed password, full name, and
 * permission level of the user.
 *
 * @author ADMIN
 */
public class User {

    /**
     * Getter for the username of the user.
     */
    @Getter
    private final String username;
    /**
     * Getter and setter for the hashed password of the user.
     */
    @Getter
    @Setter
    private String hashedPassword;
    /**
     * Getter and setter for the permission level of the user.
     */
    @Getter
    @Setter
    private Permission permission;
    /**
     * Getter and setter for the full name of the user.
     */
    @Getter
    @Setter
    private String fullName;

    /**
     * Constructs a new User with the specified attributes.
     *
     * @param username the username of the user
     * @param hashedPassword the hashed password of the user
     * @param fullName the full name of the user
     * @param permission the permission level of the user
     */
    public User(String username, String hashedPassword, String fullName, Permission permission) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.permission = permission;
        this.fullName = fullName;
    }
}
