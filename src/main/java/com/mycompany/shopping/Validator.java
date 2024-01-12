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
    
    /*These methods are kept static , because validator class haven't got any instance variables which the objects will not have any state after creating.
    So methods are kept at globle level to validate throughout the entire program.
    */
    
    //method to check wether a given string has letters and characters
    public static boolean isAlphaNumeric(String toBeValidate){
        
       //regular expression to check wether a given String  has letters and characters
       String validPattern = "^(?=.*[a-zA-Z])(?=.*[0-9]).*$";
       return (Pattern.matches(validPattern,toBeValidate));
        
        }
    
    /*method to validate passwords of the account under following conditions,
            
                It contains at least 8 characters and at most 20 characters.
                It contains at least one digit.
                It contains at least one upper case alphabet.
                It contains at least one lower case alphabet.
                It contains at least one special character which includes !@#$%&*()-+=^.
                It doesn’t contain any white space.
                    
    */
    public static boolean isValidPassword(String passwordToBeValidate){
        
        //Regular expression to validate password https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        
        String passPattern= "^(?=.*[0-9])"
                            + "(?=.*[a-z])(?=.*[A-Z])"
                            + "(?=.*[@#$%^&-+=()])"
                            + "(?=\\S+$).{8,20}$";
        
        return (Pattern.matches(passPattern, passwordToBeValidate));
    }
    
}
