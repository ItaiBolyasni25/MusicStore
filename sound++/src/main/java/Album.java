

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author 1633867
 */
@Entity
@Table(name = "Album")
public class Album extends Model implements EntityModel {

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
    public Album(){
        super();
    }
    

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
    private Timestamp removal_date;
    private String image;

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

    public double getListPrice() {
        return list_price;
    }

    public void setListprice(double listPrice) {
        this.list_price = listPrice;
    }

    public double getSaleprice() {
        return sale_price;
    }

    public void setSaleprice(double salePrice) {
        this.sale_price = salePrice;
    }

    public boolean isRemovalstatus() {
        return removal_status;
    }

    public void setRemovalstatus(boolean removalStatus) {
        this.removal_status = removalStatus;
    }

    public Timestamp getRemovaldate() {
        return removal_date;
    }

    public void setRemovaldate(Timestamp removalDate) {
        this.removal_date = removalDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
