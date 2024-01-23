/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.product;

import lombok.Getter;

/**
 * The Gender enum represents the possible clothing gender values. It contains
 * three constant values: MALE, FEMALE, and UNISEX.
 *
 * @author ADMIN
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNISEX("Unisex");
    
    @Getter
    private final String value;
    
    private Gender(String value) {    
        this.value = value;
    }
    
}
