package com.mycompany.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maian
 */
import com.mycompany.Model.Artist;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("selectedTrack")
public class SelectedTrack implements Serializable {

    private DAO dao;
    private Track track;
    private HtmlInputHidden dataItemId = new HtmlInputHidden();
    
    @Inject
    public SelectedTrack(DAO dao) {
        this.dao = dao;
    }

    public SelectedTrack() {}

    public String editDataItem() {
        dataItemId.setValue(track.getId());
        return "trackinfo?faces-redirect=true";
    }

    public Track getSelectedTrack() {
        return track;
    }

    public HtmlInputHidden getTrackId() {
        return dataItemId;
    }

    public void setSelectedTrack(Track dataItem) {
        this.track = dataItem;
    }

    public void setDataItemId(HtmlInputHidden dataItemId) {
        this.dataItemId = dataItemId;
    }

    public List<Track> getSimilarTrack() {
        String genre;
        if (track != null) {
            if (track.getGenre().contains("'")) {
                genre = track.getGenre().replace("'", "''");
            } else {
                genre = track.getGenre();
            }
            List<String> artists = new ArrayList<>();
            for (Artist a : track.getAlbum().getArtists()) {
                artists.add(a.getName());
            }
            List<Track> tracks = dao.findWithLimitGenreTrack(new Track(), 3, genre, artists, track.getTitle());
            return tracks;
        }
        return null;
    }
}
