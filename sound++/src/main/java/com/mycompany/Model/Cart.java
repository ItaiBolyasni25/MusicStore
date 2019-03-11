/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.*;
import javax.persistence.Id;
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
    public int cart_id;
    @OneToOne(mappedBy="user_id")
    public User user;
    @OneToOne(mappedBy="track_id")
    public Track track;
    @OneToOne(mappedBy="album_id")
    public Album album;

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
    
   
    
}
