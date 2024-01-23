/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.order;

import lombok.Getter;

/**
 *
 * @author ADMIN
 */
public enum ViewMode {
    TOTAL("Total"),
    MONTH("Month"),
    DAY("Day");

    @Getter
    private final String value;

    private ViewMode(String value) {
        this.value = value;
    }
}
