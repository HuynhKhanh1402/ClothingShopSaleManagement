/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.model.order;

import java.sql.Date;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author ADMIN
 */
public class Order {

    @Getter
    private final int orderID;
    @Getter
    private final String customerName;
    @Getter
    private final String customerPhone;
    @Getter
    private final Date orderDate;
    @Getter
    private final List<OrderedProduct> orderedProducts;

    /**
     *
     * @param orderID
     * @param customerName
     * @param customerPhone
     * @param orderDate
     * @param orderedProducts
     */
    public Order(int orderID, String customerName, String customerPhone, Date orderDate, List<OrderedProduct> orderedProducts) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.orderDate = orderDate;
        this.orderedProducts = orderedProducts;
    }

    public double getTotal() {
        double total = 0;
        for (OrderedProduct product : orderedProducts) {
            total += product.getUnitPrice() * product.getQuantity();
        }
        return total;
    }
}
