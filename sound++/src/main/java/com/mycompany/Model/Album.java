package com.mycompany.Model;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author 1633867
 */
@Entity
@Table(name = "Album")
public class Album implements EntityModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int album_id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    private String title;
    private Date release_date;
    private Date date_added;
    
    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Date getAddedDate() {
        return date_added;
    }

    public void setAddedDate(Date addedDate) {
        this.date_added = addedDate;
    }
    private String recording_label;
    private int number_songs;
    private double cost;
    private double list_price;
    private double sale_price;
    private boolean removal_status;
    private Date removal_date;
    private String image;
    private String genre;
    
    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Review> reviews;

    private int total_sales;

    public int getId() {
        return album_id;
    }

    public void setId(int id) {
        this.album_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasedate() {
        return release_date;
    }

    public void setReleasedate(Date releaseDate) {
        this.release_date = releaseDate;
    }

    public String getLabel() {
        return recording_label;
    }

    public void setLabel(String label) {
        this.recording_label = label;
    }

    public int getNumberofsong() {
        return number_songs;
    }

    public void setNumberofsong(int numberOfSong) {
        this.number_songs = numberOfSong;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getList_Price() {
        return list_price;
    }

    public void setList_price(double listPrice) {
        this.list_price = listPrice;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double salePrice) {
        this.sale_price = salePrice;
    }

    public boolean isRemoval_status() {
        return removal_status;
    }

    public void setRemoval_status(boolean removalStatus) {
        this.removal_status = removalStatus;
    }

    public Date getRemoval_date() {
        return removal_date;
    }

    public void setRemoval_date(Date removalDate) {
        this.removal_date = removalDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public int getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }
}
