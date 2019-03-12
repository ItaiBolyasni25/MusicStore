/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Cart;
import com.mycompany.Model.Invoice;
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
    private double gst = 0;
    private double hst = 0;
    private double pst = 0;
    private double total_gross = 0;
    private String email;
    private int invoice_id_generated;
    @Inject
    private DAO dao;
    
    public void addInvoice() {
        Invoice invoice = new Invoice();
        invoice.setDate(date);
        invoice.setGst(gst);
        invoice.setHst(hst);
        invoice.setPst(pst);
        List<Cart> list = dao.find(new Cart(), "email = " + this.email); 
        for (Cart cart: list) {
            this.total_gross += cart.getTrack().getCost();
        }
        invoice.setTotal_gross(total_gross);
        this.total_cost = total_gross + gst + hst + pst;
        invoice.setTotal_cost(total_cost);
        invoice = dao.write(invoice);
        this.invoice_id_generated = invoice.getInvoice_id();
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
    
    
}
