package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Review;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aantoine97
 */
@Named(value = "reviewBean")
@ViewScoped
public class ReviewBean implements Serializable {
    
    private Integer review_id;
    private int rating;
    private String text;
    @Inject
    private DAO dao;
    
    @Inject
    private SelectedTrack track;
    
    @Inject 
    private SelectedAlbum album;
    
    public ReviewBean() {}

    public Integer getReview_Id() {
        return review_id;
    }
    
    public void setReview_Id(Integer review_id) {
        this.review_id = review_id;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Track getTrack() {
        return track.getSelectedTrack();
    }
    
    public Album getAlbum() {
        return album.getSelectedAlbum();
    }
    
    public void saveReview() {
        Review review = new Review();
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        review.setDate(sqlDate);
        review.setRating(rating);
        review.setText(text);
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
        review.setUser(user);
        if (track.getSelectedTrack() == null)
            review.setAlbum(album.getSelectedAlbum());
        else
            review.setTrack(track.getSelectedTrack());

        dao.write(review);
    }
    
    public List<Review> getUnapproved() {
        return dao.find(new Review(), "isApproved = 0");
    }
    
    public List<Review> getAlbumReviews(Album album) {
        return dao.find(new Review(), "album.album_id = '" + album.getId() + "' AND track.isApproved = 1");
    }
    
    public List<Review> getTrackReviews(Track track) {
        return dao.find(new Review(), "track.track_id = '" + track.getId() + "' AND track.isApproved = 1");
    }
}
