/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;

/**
 *
 * @author maian
 */
import javax.inject.Named;

@ViewScoped
@Named("AlbumTrackBean")
public class AlbumTrackBean implements Serializable {

    @Inject
    private DAO dao;
    private String filter;
    private String pattern;
    private String dateRange;
    private List<Album> albums;
    private List<Track> tracks;
    private List<Artist> artists;

    public AlbumTrackBean() {
        filter = "title";
    }
    public String getFilter() {
        return filter;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getPattern() {
        return pattern;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void patternChanged() {

        if (pattern != null && !pattern.isEmpty() && !pattern.equals("")) {
            if (filter.equals("title")) {
                setAlbums(dao.findWithLimitPattern(new Album(), 0, 3, pattern));
                setTracks(dao.findWithLimitPattern(new Track(), 0, 3, pattern));
                setArtists(null);
            } else if (filter.equals("artist")) {
                setArtists(dao.findWithLimitPatternArtist(new Artist(), 0, 3, pattern));              
                setAlbums(null);
                setTracks(null);
            }
        } else {
            setAlbums(null);
            setTracks(null);
            setArtists(null);
        }
    }
     
    public String redirect() {
        return "result?faces-redirect=true&pattern=" + this.pattern + "&filter=" + this.filter;
    }
}
