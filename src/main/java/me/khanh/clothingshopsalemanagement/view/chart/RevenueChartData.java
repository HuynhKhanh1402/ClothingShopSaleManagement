/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.view.chart;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author ADMIN
 */
public class RevenueChartData {

    @Getter
    private final List<RevenueMonthy> data;

    public RevenueChartData(List<RevenueMonthy> data) {
        this.data = data;
    }
}
