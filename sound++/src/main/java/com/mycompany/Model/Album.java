package com.mycompany.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Interface.EntityModel;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author 1633867
 */
@Entity
@Table(name="Album")
public class Album implements EntityModel {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int albumId;
    private String title;
    private Timestamp release_date;
    private Timestamp date_added;

    public Timestamp getAddedDate() {
        return date_added;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.date_added = addedDate;
    }
    private String recording_label;
    private int number_songs;
    private double cost;
    private double list_price;
    private double sale_price;
    private boolean removal_status;
    private Timestamp removal_date;
    private String image;
    private String genre;

    public int getId() {
        return albumId;
    }

    public void setId(int id) {
        this.albumId = id;
    }

    public String getTitle() {
        return title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Timestamp getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Timestamp release_date) {
        this.release_date = release_date;
    }

    public Timestamp getDate_added() {
        return date_added;
    }

    public void setDate_added(Timestamp date_added) {
        this.date_added = date_added;
    }

    public String getRecording_label() {
        return recording_label;
    }

    public void setRecording_label(String recording_label) {
        this.recording_label = recording_label;
    }

    public int getNumber_songs() {
        return number_songs;
    }

    public void setNumber_songs(int number_songs) {
        this.number_songs = number_songs;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public boolean isRemoval_status() {
        return removal_status;
    }

    public void setRemoval_status(boolean removal_status) {
        this.removal_status = removal_status;
    }

    public Timestamp getRemoval_date() {
        return removal_date;
    }

    public void setRemoval_date(Timestamp removal_date) {
        this.removal_date = removal_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.release_date = releaseDate;
    }

    public String getLabel() {
        return recording_label;
    }

    public void setLabel(String label) {
        this.recording_label = label;
    }

    public int getNumberOfSong() {
        return number_songs;
    }

    public void setNumberOfSong(int numberOfSong) {
        this.number_songs = numberOfSong;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getListPrice() {
        return list_price;
    }

    public void setListPrice(double listPrice) {
        this.list_price = listPrice;
    }

    public double getSalePrice() {
        return sale_price;
    }

    public void setSalePrice(double salePrice) {
        this.sale_price = salePrice;
    }

    public boolean isRemovalStatus() {
        return removal_status;
    }

    public void setRemovalStatus(boolean removalStatus) {
        this.removal_status = removalStatus;
    }

    public Timestamp getRemovalDate() {
        return removal_date;
    }

    public void setRemovalDate(Timestamp removalDate) {
        this.removal_date = removalDate;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
