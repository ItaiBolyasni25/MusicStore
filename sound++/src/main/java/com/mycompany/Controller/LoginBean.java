package com.mycompany.Controller;


import com.mycompany.Utilities.Validator;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ManagedBean for determining if a user can log in
 * 
 * @author aantoine97
 */
@Named
@SessionScoped 
public class LoginBean {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Determines if user can log in
     * 
     * @return boolean 
     */
    public boolean canLogIn() {
        return Validator.isRegistered(email, password);           
    }
}
