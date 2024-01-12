/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.io.Serializable;

/**
 *
 * @author rajeen
 */
public class User implements Serializable {
    private final boolean isFirstPurchase;//used to track the first purchase
    private String userName;
    private String password;

    
    
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        this.isFirstPurchase=true;
       
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(Validator.isValidPassword(password)){
            this.password = password;
        }else{
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    
    
    
    
    
    
}
