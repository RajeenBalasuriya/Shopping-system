/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
public class User {
    
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    
    
    public User(String userName,String firstName,String lastName){
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  

    
    
    
    
    
    
}
