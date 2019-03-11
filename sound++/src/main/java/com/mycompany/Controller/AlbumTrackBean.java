/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author maian
 */
@ApplicationScoped
@Named("AlbumTrackBean")
public class AlbumTrackBean implements Serializable {

    @Inject
    private DAO dao;
    private String pattern;
    private List<Album> albums;
    private List<Track> tracks;

    public AlbumTrackBean() {
    }

    public String getPattern() {
        return pattern;
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
            System.out.println("++++++++++++++++\"" + Boolean.toString(pattern.equals("")));
            setAlbums(dao.findWithPattern(new Album(), pattern, "title"));
            setTracks(dao.findWithPattern(new Track(), pattern, "title"));
        } else {
            setAlbums(null);
            setTracks(null);
        }
    }

    /*public List<Date> getArtists(){
      // return dao.find(new Artist(), "title like %" +pattern+ "%");       
    }*/
}
