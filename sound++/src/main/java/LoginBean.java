


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@ManagedBean(name = "loginBean", eager = true) 
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
