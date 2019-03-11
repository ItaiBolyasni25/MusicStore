/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Cart;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    public void addSong(Track track, User user) {
        Cart cart = new Cart();
        cart.setTrack(track);
        cart.setUser(user);
        dao.write(cart);
    }
    
    public List<Cart> getCartItems(User user) {
        return dao.find(new Cart(), "user_id = " + user.getUser_Id());    
    }
    
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    
    
}
