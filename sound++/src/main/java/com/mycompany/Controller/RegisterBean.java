package com.mycompany.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.Utilities.Validator;
import com.mycompany.Persistence.DAO;
import com.mycompany.Model.User;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * ManagedBean for registering a user
 * 
 * @author aantoine97
 */

@Named(value = "registerBean")
@SessionScoped 
public class RegisterBean implements Serializable{
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
    private final DAO DAO = new DAO();

    @PersistenceContext(unitName = "usersPU")
    private EntityManager em;
    
    @Resource
    private UserTransaction userTransaction;
    
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
    
    /**
     * If credentials are valid, add the newly registered user to the database
     * 
     * @return boolean
     */
    public boolean addUser() {
        User user = new User(firstName, lastName, email, password, companyName, 
            address1, postalCode, address2, city, province, country, cellphone, 
            homephone);

        if (Validator.hasValidInformation(user, em)) {
           DAO.setEntityManager(em);
           DAO.setUserTransaction(userTransaction);
           DAO.write(user);
           return true;
        } else {
            return false;        
        }
    }
}
