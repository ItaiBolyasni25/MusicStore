package com.mycompany.Controller;

import com.mycompany.Model.Roles;
import com.mycompany.Utilities.Validator;
import com.mycompany.Persistence.DAO;
import com.mycompany.Model.User;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
    private boolean is_manager;
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

    public boolean isIs_manager() {
        return is_manager;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
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
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre") != null){
            user.setLast_genre((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre")));
        }
        if (!Validator.emailExists(user, DAO)){
            DAO.write(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userObj", user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", firstName);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", email);
            String role = "manager";
            Roles roles = new Roles(user.getEmail(), role);
            DAO.write(roles);
            invalidEmail = false;
            return "index.xhtml";
        } else {
            invalidEmail = true;
            return "register.xhtml";
        }
    }
}
