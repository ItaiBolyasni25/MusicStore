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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    private String title;
    private String lastname;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
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
    private String email;
    private String hash;
    private String salt;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password,
            String companyName, String address1, String postalCode, String address2,
            String city, String province, String country, String cellphone,
            String homephone) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.hash = password;
        this.company_name = companyName;
        this.address1 = address1;
        this.postal_code = postalCode;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.cellphone = cellphone;
        this.home_telephone = homephone;
    }

    public Integer getUser_Id() {
        return user_id;
    }

    public void setUser_Id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String companyName) {
        this.company_name = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postalCode) {
        this.postal_code = postalCode;
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

    public String getHome_telephone() {
        return home_telephone;
    }

    public void setHome_telephone(String homephone) {
        this.home_telephone = homephone;
    }

    @Override
    public String toString() {
        return this.email;
    }
}
