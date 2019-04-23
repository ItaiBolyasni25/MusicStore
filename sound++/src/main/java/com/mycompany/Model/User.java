package com.mycompany.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mycompany.Interface.EntityModel;
import javax.persistence.OneToOne;

/**
 * Entity class for a user
 *
 * @author aantoine97
 */
@Entity
@Table(name = "User")
public class User implements Serializable, EntityModel {

    private static final long serialVersionUID = 1L;
    @Id
    private String email;
    private String title;
    private String lastname;
    private String firstname;
    private String company_name;
    private String address1;
    private String address2;
    private String city;
    private String province;
    private String country;
    private String postal_code;
    private String home_telephone;
    private String cellphone;
    private String hash;
    private String salt;
    private String last_genre;
    private boolean is_manager;
    private String language;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password,
            String title) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.hash = password;
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
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

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getHome_telephone() {
        return home_telephone;
    }

    public void setHome_telephone(String home_telephone) {
        this.home_telephone = home_telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLast_genre() {
        return last_genre;
    }

    public void setLast_genre(String last_genre) {
        this.last_genre = last_genre;
    }

    public boolean isIs_manager() {
        return is_manager;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}
