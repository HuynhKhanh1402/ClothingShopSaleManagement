/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.khanh.clothingshopsalemanagement.util;

import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class NumberUtil {

    public static boolean isDouble(String value) {
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String format(double value) {
        return new DecimalFormat("#,###.##").format(value);
    }
}
