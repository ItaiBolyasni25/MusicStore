/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author 1633867
 */
@Entity
@Table(name="Album")
public class Album implements EntityModel{
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

    public int getId() {
        return albumId;
    }

    public void setId(int id) {
        this.albumId = id;
    }

    public String getTitle() {
        return title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
