package com.mycompany.Controller;


import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.Validator;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ManagedBean for determining if a user can log in
 * 
 * @author aantoine97
 */
@Named
@SessionScoped
public class LoginBean implements Serializable{
    private String email;
    private String password;
    
    @Inject
    private DAO DAO;

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
        return Validator.isRegistered(email, password, DAO);           
    }
}
