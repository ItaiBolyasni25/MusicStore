/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Cart;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author GamingDanik
 */
@Named("cartController")
@SessionScoped
public class CartController implements Serializable {

    private int cart_id;
    private String songName;
    @Inject
    private DAO dao;
    private Integer price = 0;

    @PersistenceContext(unitName = "usersPU")
    private EntityManager em;


    public String addTrack(Track track, User user) {
        Cart cart = new Cart();
        cart.setTrack(track);
        cart.setUser(user);
        dao.write(cart);
        return "albums.xhtml";
    }

    public String addAlbum(Album album, User user) {
        Cart cart = new Cart();
        cart.setAlbum(album);
        cart.setUser(user);
        dao.write(cart);
        System.out.println("test");
        return "albums.xhtml";
    }

    public List<Cart> getCartItems(User user) {
        return dao.find(new Cart(), "user.email = '" + user.getEmail() + "' + AND invoice.invoice_id IS NULL");
    }
    
    public List<Cart> getBoughtItems(User user) {
        return dao.find(new Cart(), "invoice.invoice_id IS NOT NULL AND user.email = '" + user.getEmail() + "'");
    }

    public int addPrice(int price) {
        this.price += price;
        return price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

}
