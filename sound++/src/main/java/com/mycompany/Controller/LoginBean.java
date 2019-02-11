package com.mycompany.Controller;

import com.mycompany.Model.User;
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
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;

    private User loggedIn;

    private Boolean error;

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

    public User getLoggedIn() {
        return loggedIn;
    }

    public Boolean getError() {
        return error;
    }

    /**
     * Determines if user can log in
     *
     * @return boolean
     */
    public String canLogIn() {
        if (Validator.isRegistered(email, password, DAO)) {
            loggedIn = DAO.find(new User(), "email = '" + email + "'").get(0);
            error = false;
            return "index.xhtml";
        } else {
            error = true;
            return "login.xhtml";
        }
    }
}
