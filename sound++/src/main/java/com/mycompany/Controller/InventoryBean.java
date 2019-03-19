/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;


import com.mycompany.Interface.Inventory;
import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aantoine97
 */
@Named(value = "inventoryBean")
@SessionScoped
public class InventoryBean implements Serializable {
    private Album album;
    private Track track;
    
    @Inject
    private DAO dao;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
    
    public void addInventory(Inventory inventory) {
        dao.write(inventory);
    } 
    
    public void editInventory(Inventory inventory, double sale) {
        if (inventory.getClass() == Track.class) {
            ((Track)inventory).setSale_price(sale);
        } else {
            ((Album)inventory).setSale_price(sale);
        }
        
        dao.write(inventory);
    }
    
    public void deleteInventory(Inventory inventory) {
        dao.delete(inventory);
    }
}
