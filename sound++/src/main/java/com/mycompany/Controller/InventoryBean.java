/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
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

    // Track
    private String trackTitle;
    private String songwriter;
    private String play_length;
    private String trackGenre;
    // private Album album;
    private double trackCost;
    private double trackListPrice;
    private double trackSalePrice;
    private Date trackDateAdded;
    private boolean individual;
    
    // Album
    private String albumTitle;
    private Date releaseDate;
    private Date albumDateAdded;
    private String recordingLabel;
    private int numberSongs;
    private double albumCost;
    private double albumListPrice;
    private double albumSalePrice;
    private String image;
    private String albumGenre;

    @Inject
    private DAO dao;

    public void addTrack() {
        Track track = new Track();
        // Find out about setting the album
        // track.setAlbum(album);
        track.setTitle(trackTitle);
        track.setSongwriter(songwriter);
        track.setPlay_length(play_length);
        track.setGenre(trackGenre);
        track.setCost(trackCost);
        track.setList_price(trackListPrice);
        track.setSale_price(trackSalePrice);
        track.setDate_added(trackDateAdded);
        track.setIndividual(individual);
        
        dao.write(track);
    }

    public void addAlbum() {
        Album album = new Album();
        album.setTitle(albumTitle);
        album.setReleasedate(releaseDate);
        album.setAddedDate(albumDateAdded);
        album.setLabel(recordingLabel);
        album.setNumberofsong(numberSongs);
        album.setCost(albumCost);
        album.setList_price(albumListPrice);
        album.setSale_price(albumSalePrice);
        album.setImage(image);
        album.setGenre(albumGenre);
        
        dao.write(album);
    }

    public void editTrack(int track_id, double sale) {
        Track updated = dao.read(new Track(), track_id).get(0);
        updated.setSale_price(sale);
        dao.updateEntity(updated);
    }

    public void editAlbum(int album_id, double sale) {
        Album updated = dao.read(new Album(), album_id).get(0);
        updated.setSale_price(sale);
        dao.updateEntity(updated);
    }

    public void deleteTrack(int track_id) {
        Track updated = dao.read(new Track(), track_id).get(0);
        updated.setRemoval_status(true);
        updated.setRemoval_date(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        dao.updateEntity(updated);
    }

    public void deleteAlbum(int album_id) {
        Album updated = dao.read(new Album(), album_id).get(0);
        updated.setRemoval_status(true);
        updated.setRemoval_date(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        dao.updateEntity(updated);
    }
}
