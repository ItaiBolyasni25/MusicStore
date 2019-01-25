/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author 1633867
 */
@Entity
@Table(name="Tracks")
public class Track {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int track_id;
    private int selection_number;
    private String title;
    private String songwriter;
    private Timestamp play_length;
    private String genre;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "albumId")
    private Album album;
    private double cost;
    private double list_price;
    private double sale_price;
    private Timestamp date_added;
    private boolean individual;
    private boolean removal_status;
    private Timestamp removal_date;

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public int getSelection_number() {
        return selection_number;
    }

    public void setSelection_number(int selection_number) {
        this.selection_number = selection_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongwriter() {
        return songwriter;
    }

    public void setSongwriter(String songwriter) {
        this.songwriter = songwriter;
    }

    public Timestamp getPlay_length() {
        return play_length;
    }

    public void setPlay_length(Timestamp play_length) {
        this.play_length = play_length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public Timestamp getDate_added() {
        return date_added;
    }

    public void setDate_added(Timestamp date_added) {
        this.date_added = date_added;
    }

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
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
}
