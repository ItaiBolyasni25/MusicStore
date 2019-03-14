package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Review;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aantoine97
 */
@Named(value = "reviewBean")
@RequestScoped
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
        review.setIsApproved(false);
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
        review.setUser(user);
        if (track.getSelectedTrack() == null)
            review.setAlbum(album.getSelectedAlbum());
        else
            review.setTrack(track.getSelectedTrack());

        dao.write(review);
    }
}
