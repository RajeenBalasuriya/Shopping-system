/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.util.regex.Pattern;

/**
 *
 * @author rajeen
 */
public class Validator {
    
    
    
    //method to check wether a given string has letters and characters
    public  boolean isAlphaNumeric(String toBeValidate){
        
       //regular expression to check wether a given String  has letters and characters
       String validPattern = "^(?=.*[a-zA-Z])(?=.*[0-9]).*$";
       return (Pattern.matches(validPattern,toBeValidate));
        
        }
    
}
