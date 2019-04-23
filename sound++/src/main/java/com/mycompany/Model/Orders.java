package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aantoine97
 */
@Entity
@Table(name = "orders")
public class Orders implements EntityModel, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    @OneToOne(optional = false)
    private Invoice invoice;
    
    public Orders(){}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

 


    public Invoice getInvoiceId() {
        return invoice;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoice = invoiceId;
    }


    @Override
    public String toString() {
        return "com.mycompany.Model.Order1[ orderId=" + orderId + " ]";
    }
}    
