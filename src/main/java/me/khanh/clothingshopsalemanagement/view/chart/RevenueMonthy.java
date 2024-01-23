/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.chart;

import lombok.Getter;

/**
 *
 * @author ADMIN
 */
public class RevenueMonthy {

    @Getter
    private final int monthOfYear;
    @Getter
    private final int year;
    @Getter
    private final double revenue;

    public RevenueMonthy(int monthOfYear, int year, double revenue) {
        this.monthOfYear = monthOfYear;
        this.year = year;
        this.revenue = revenue;

    }
}
