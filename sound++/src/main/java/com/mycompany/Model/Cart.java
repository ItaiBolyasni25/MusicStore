/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author GamingDanik
 */
@Entity
@Table(name = "Cart")
public class Cart implements EntityModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cart_id;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "email")
    private User user;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "track_id", referencedColumnName = "track_id", nullable = true)
    private Track track;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "album_id", referencedColumnName = "album_id", nullable = true)
    private Album album;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "invoice_id", nullable = true)
    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Cart{" + "cart_id=" + cart_id + ", user=" + user + ", track=" + track + ", album=" + album + '}';
    }

}
