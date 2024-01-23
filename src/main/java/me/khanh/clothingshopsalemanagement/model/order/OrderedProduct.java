/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.order;

import lombok.Getter;
import me.khanh.clothingshopsalemanagement.model.product.Product;

/**
 *
 * @author ADMIN
 */
public class OrderedProduct {

    @Getter
    private final int orderId;
    @Getter
    private final Product product;
    @Getter
    private final int quantity;
    @Getter
    private final double unitPrice;

    /**
     *
     * @param orderId
     * @param product
     * @param quantity
     * @param unitPrice
     */
    public OrderedProduct(int orderId, Product product, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }
}
