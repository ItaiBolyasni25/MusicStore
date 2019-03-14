package com.mycompany.Controller;


import com.mycompany.Model.Roles;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.Validator;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ManagedBean for determining if a user can log in
 * 
 * @author aantoine97
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String companyName;
    private String address1;
    private String postalCode;
    private String address2;
    private String city;
    private String province;
    private String country;
    private String cellphone;
    private String homephone;
    private String title;
    private boolean is_manager; 
    private Boolean error;
    private Boolean invalidEmail;
    
    @Inject
    private DAO DAO;

    public LoginBean() {}
    
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Boolean getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(Boolean is_manager) {
        this.is_manager = is_manager;
    }
    
    public Boolean getError() {
        return error;
    }
    
    public Boolean getInvalidEmail() {
        return invalidEmail;
    }
    
    
    /**
     * Determines if user can log in
     * 
     * @return boolean 
     */
    public String canLogIn() {
        if (Validator.isRegistered(email, password, DAO)) {
            User user = DAO.find(new User(), "identifier.email = '" + email + "'").get(0);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userObj", user);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", firstName);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", email);
            error = false;
            return "index.xhtml";
        } else {
            error = true;
            return "login.xhtml";
        }       
    }
    
    /**
     * If credentials are valid, add the newly registered user to the database
     * 
     * @return String page to redirect to
     */
    public String addUser() {
        User user = new User(firstName, lastName, email, password, title);
        if (!Validator.emailExists(user, DAO)) {
            DAO.write(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userObj", user);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", firstName);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", email);
            String role = "manager";
            Roles roles = new Roles(user.getEmail(), role);
            DAO.write(roles);
            error = false;
            return "index.xhtml";
        } else {
            error = true;
            return "register.xhtml";
        }
    }
}
