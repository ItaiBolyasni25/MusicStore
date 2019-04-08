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
    private double price = 0;

    @PersistenceContext(unitName = "usersPU")
    private EntityManager em;

    public String addTrack(Track track, User user) {
        Cart cart = new Cart();
        cart.setTrack(track);
        cart.setUser(user);
        dao.write(cart);
        return "albums.xhtml";
    }

    public String removeTrack(Track track, User user) {
        Cart cart = dao.find(new Cart(), "track.track_id = '" + track.getId() + "' AND identifier.user.email = '" + user.getEmail() + "'").get(0);
        dao.deleteCart(cart, true);
        return "checkout.xhtml";
    }

    public String removeAlbum(Album album, User user) {
        Cart cart = dao.find(new Cart(), "album.album_id = '" + album.getId() + "' AND identifier.user.email = '" + user.getEmail() + "'").get(0);
        dao.deleteCart(cart, true);
        return "checkout.xhtml";
    }

    public String addAlbum(Album album, User user) {
        Cart cart = new Cart();
        cart.setAlbum(album);
        cart.setUser(user);
        dao.write(cart);
        return "albums.xhtml";
    }

    public List<Cart> getCartItems(User user) {
        List<Cart> list = dao.find(new Cart(), "user.email = '" + user.getEmail() + "' AND identifier.invoice IS NULL");
        for (Cart c : list) {
            System.out.println(c.getAlbum() == null ? "Album is null" : "Album not null");
            System.out.println(c.getTrack() == null ? "Track is null" : "Track not null");

        }
        return list;
    }

    public List<Track> getCartTracks(User user) {
        List<Cart> list = dao.find(new Cart(), "user.email = '" + user.getEmail() + "' AND identifier.invoice IS NULL AND identifier.album IS NULL");
        List<Track> tracks = new ArrayList<>();
        for (Cart c : list) {
            tracks.add(c.getTrack());
        }
        return tracks;
    }

    public List<Album> getCartAlbum(User user) {
        List<Cart> list = dao.find(new Cart(), "user.email = '" + user.getEmail() + "' AND identifier.invoice IS NULL AND identifier.track IS NULL");
        List<Album> albums = new ArrayList<>();
        for (Cart c : list) {
            albums.add(c.getAlbum());
        }
        return albums;
    }

    public List<Cart> getBoughtItems(User user) {
        return dao.find(new Cart(), "invoice.invoice_id IS NOT NULL AND identifier.user.email = '" + user.getEmail() + "'");
    }

    public double getPrice(User user) {
        List<Cart> list = dao.find(new Cart(), "user.email = '" + user.getEmail() + "' AND identifier.invoice IS NULL");
        for (Cart c : list) {
            if (c.getAlbum() == null) {
                this.price += c.getTrack().getList_price();
            } else {
                this.price += c.getAlbum().getList_Price();
            }
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

}
