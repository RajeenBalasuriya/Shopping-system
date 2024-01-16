
package com.mycompany.shopping;

import java.io.Serializable;

/**
 * @author rajeen
 */
public class User implements Serializable {
    private int noOfPurchases;//used to track the first purchase
    private String userName;
    private String password;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.noOfPurchases = 0;


    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Validator.isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNoOfPurchases() {
        return noOfPurchases;
    }

    public void setNoOfPurchases(int noOfPurchases) {
        this.noOfPurchases = noOfPurchases;
    }


}
