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

    @Resource
    private UserTransaction userTransaction;

    public String addSong(Track track, User user) {
        Cart cart = new Cart();
        cart.setTrack(track);
        cart.setAlbum(track.getAlbum());
        cart.setUser(user);
        dao.write(cart);
        return "albums.xhtml";
    }

    public String addAlbum(Album album, User user) {
        Cart cart = new Cart();
        cart.setAlbum(album);
        cart.setUser(user);
        dao.write(cart);
        return album.getTitle() + user.getFirstname();
    }

    public List<Cart> getCartItems(User user) {
        return dao.findAll(new Cart());
    }

    public List<Cart> getCartItems2(User user) {
        return em.createNativeQuery("Select * from cart where user_id = " + user.getUser_id()).getResultList();
//        return em.find(Cart.class, "user_id = " + user.getUser_id());
    }

    public int addPrice(int price) {
        this.price += price;
        return this.price;
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
