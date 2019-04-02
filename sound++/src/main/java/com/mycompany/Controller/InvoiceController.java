/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Cart;
import com.mycompany.Model.Invoice;
import com.mycompany.Model.Orders;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author GamingDanik
 */
@Named("invoiceController")
@SessionScoped
public class InvoiceController implements Serializable {

    private Date date;
    private double total_cost;
    private double gst = 1.7;
    private double hst = 1.8;
    private double pst = 1.2;
    private double total_gross = 0;
    private String email;
    private int invoice_id_generated;
    private String address1;
    private String address2 = "";
    private String country;
    private String state;
    private String zip;
    private String paymentType;
    private String nameOfCard;
    private String creditNumber;
    private String expiration;
    private String cvv;
    @Inject
    private DAO dao;

    public String addInvoice(List<Cart> cartItems, User user) {
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCountry(country);
        user.setProvince(state);
        user.setPostal_code(zip);
        dao.updateEntity(user);
        
        Invoice invoice = new Invoice();
        invoice.setDate(Date.valueOf(java.time.LocalDate.now()));
        invoice.setGst(gst);
        invoice.setHst(hst);
        invoice.setPst(pst);
        invoice.setCartItems(cartItems);
        for (Cart cart : cartItems) {
            if (cart.getTrack() == null) {
                this.total_gross += cart.getAlbum().getCost();
            } else {
                this.total_gross += cart.getTrack().getCost();
            }
        }
        invoice.setTotal_gross(total_gross);
        invoice.setUser(user);
        this.total_cost = total_gross * gst * hst * pst;
        invoice.setTotal_cost(total_cost);
        invoice = dao.write(invoice);
        this.invoice_id_generated = invoice.invoice_id;
        
        Orders order = new Orders();
        order.setInvoiceId(invoice);
        order.setPrice(this.total_cost);
        dao.write(order);
        
        for (Cart cart : cartItems) {
            cart.setInvoice(invoice);
            System.out.println("Updated cart with " + invoice.invoice_id);
            dao.updateEntity(cart);
        }
        return "invoice.xhtml";
    }
    
    public List<Cart> getCartItems() {
        return dao.find(new Cart(), "invoice.invoice_id = '" + this.invoice_id_generated + "'");
    }

    public Invoice getInvoice() {
        return dao.read(new Invoice(), this.invoice_id_generated).get(0);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getHst() {
        return hst;
    }

    public void setHst(double hst) {
        this.hst = hst;
    }

    public double getPst() {
        return pst;
    }

    public void setPst(double pst) {
        this.pst = pst;
    }

    public double getTotal_gross() {
        return total_gross;
    }

    public void setTotal_gross(double total_gross) {
        this.total_gross = total_gross;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInvoice_id_generated() {
        return invoice_id_generated;
    }

    public void setInvoice_id_generated(int invoice_id_generated) {
        this.invoice_id_generated = invoice_id_generated;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNameOfCard() {
        return nameOfCard;
    }

    public void setNameOfCard(String nameOfCard) {
        this.nameOfCard = nameOfCard;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

}
