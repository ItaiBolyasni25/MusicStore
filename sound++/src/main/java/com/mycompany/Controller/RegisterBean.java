package com.mycompany.Controller;

import com.mycompany.Model.Roles;
import com.mycompany.Utilities.Validator;
import com.mycompany.Persistence.DAO;
import com.mycompany.Model.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * ManagedBean for registering a user
 *
 * @author aantoine97
 */
@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String title;
    // Will be set to true if user tries to register with existing email
    private boolean invalidEmail;

    @Inject
    private DAO DAO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean getInvalidEmail() {
        return invalidEmail;
    }

    /**
     * If credentials are valid, add the newly registered user to the database
     *
     * @return String page to redirect to
     */
    public String addUser() {
        User user = new User(firstName, lastName, email, password, title);
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre") != null) {
            user.setLast_genre((String) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre")));
        }
        if (!Validator.emailExists(user, DAO)) {
            DAO.write(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userObj", user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", firstName);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", email);
            String role = "manager";
            Roles roles = new Roles(user.getEmail(), role);
            DAO.write(roles);
            invalidEmail = false;

            return "index.xhtml?faces-redirect=true";
        } else {
            invalidEmail = true;
            return "register?faces-redirect-true";
        }
    }

    public void signOut() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userObj");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("id");
    }

    public void logIn() {
        User user = DAO.find(new User(), "email = '" + email + "'").get(0);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userObj", user);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", firstName);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", email);
    }

}
