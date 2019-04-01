/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author maian
 */
public class SaleReport {
    private List<Invoice> invoices;
    private int totalSale;
    private String user;

    public SaleReport() {
    }
    
    public List<Invoice> getInvoice() {
        return invoices;
    }

    public void setInvoice(List<Invoice> newInvoice) {
        this.invoices = newInvoice;
    }

    public int getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
