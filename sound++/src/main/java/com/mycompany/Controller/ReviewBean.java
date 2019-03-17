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
import javax.transaction.Transactional;

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

    public ReviewBean() {
    }

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
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
        review.setUser(user);
        // This doesn't work properly for now because the beans apparently have to be sessionscoped
        // need to find an alternative
        if (track.getSelectedTrack() == null) {
            review.setAlbum(album.getSelectedAlbum());
        } else {
            review.setTrack(track.getSelectedTrack());
        }

        dao.write(review);
    }

    public List<Review> getUnapproved() {
        return dao.find(new Review(), "isApproved = 0");
    }

    
    public String approveReview(int review_id) {
        Review review = dao.read(new Review(), review_id).get(0);
        review.setIsApproved(true);
        dao.updateEntity(review);
        return "managerreviews.xhtml";
    }

    @Transactional
    public String deleteReview(int review_id) {
        Review review = dao.read(new Review(), review_id).get(0);
        dao.delete(review);
        return "managerreviews.xhtml";
    }

    public List<Review> getAlbumReviews(Album album) {
        return dao.find(new Review(), "album.album_id = '" + album.getId() + "' AND identifier.isApproved = 1");
    }

    public List<Review> getTrackReviews(Track track) {
        return dao.find(new Review(), "track.track_id = '" + track.getId() + "' AND identifier.isApproved = 1");
    }
}
