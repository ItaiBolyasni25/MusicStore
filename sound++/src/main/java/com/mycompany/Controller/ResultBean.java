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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("ResultBean")
public class ResultBean implements Serializable {

    private String pattern;
    private List<Album> albums;
    private List<Track> tracks;
    @Inject
    private DAO dao;
    private int totalAlbumsRows;
    private int currentAlbumsPage;
    private int albumPerPage;
    private int totalAlbumPages;
    private int totalTrackRows;
    private int currentTrackPage;
    private int trackPerPage;
    private int totalTrackPages;

      public ResultBean() {
        this.albumPerPage = 4;
        this.currentAlbumsPage = 1;
        this.trackPerPage = 4;
        this.currentTrackPage = 1;
    }

    public void init() {
        this.pattern = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pattern");
        updateView();
         this.totalAlbumsRows = dao.findWithPatternAlbum(new Album(), pattern).size();
        if (totalAlbumsRows > albumPerPage) {
            totalAlbumPages = (int) Math.ceil((totalAlbumsRows * 1.0) / albumPerPage);
        } else {
            totalAlbumPages = 1;
        }
        this.totalTrackRows = dao.findWithPatternTrack(new Track(), pattern).size();
         if (totalTrackRows > trackPerPage) {
            totalTrackPages = (int) Math.ceil((totalTrackRows * 1.0) / trackPerPage);
        } else {
            totalTrackPages = 1;
        }

    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public int getTotalAlbumsRows() {
        return totalAlbumsRows;
    }

    public void setTotalAlbumsRows(int totalAlbumsRows) {
        this.totalAlbumsRows = totalAlbumsRows;
    }

    public int getCurrentAlbumsPage() {
        return currentAlbumsPage;
    }

    public void setCurrentAlbumsPage(int currentAlbumsPage) {
        this.currentAlbumsPage = currentAlbumsPage;
    }

    public int getAlbumPerPage() {
        return albumPerPage;
    }

    public void setAlbumPerPage(int albumPerPage) {
        this.albumPerPage = albumPerPage;
    }

    public int getTotalAlbumPages() {
        return totalAlbumPages;
    }

    public void setTotalAlbumPages(int totalAlbumPages) {
        this.totalAlbumPages = totalAlbumPages;
    }

    public int getTotalTrackRows() {
        return totalTrackRows;
    }

    public void setTotalTrackRows(int totalTrackRows) {
        this.totalTrackRows = totalTrackRows;
    }

    public int getCurrentTrackPage() {
        return currentTrackPage;
    }

    public void setCurrentTrackPage(int currentTrackPage) {
        this.currentTrackPage = currentTrackPage;
    }

    public int getTrackPerPage() {
        return trackPerPage;
    }

    public void setTrackPerPage(int trackPerPage) {
        this.trackPerPage = trackPerPage;
    }

    public int getTotalTrackPages() {
        return totalTrackPages;
    }

    public void setTotalTrackPages(int totalTrackPages) {
        this.totalTrackPages = totalTrackPages;
    }
     public int getTrackOffset() {
        return (currentTrackPage - 1) * trackPerPage;
    }
    
    public int getAlbumOffset(){
           return (currentAlbumsPage - 1) * albumPerPage;
    }
    

    public void next() {
        if (this.currentAlbumsPage < this.totalAlbumPages) {
            this.currentAlbumsPage++;
        }
        if(this.currentTrackPage < this.totalTrackPages){
            this.currentTrackPage++;
        }
        updateView();
    }

    public void prev() {
        if (this.currentAlbumsPage > 1) {
            this.currentAlbumsPage--;
        }
        if (this.currentTrackPage > 1) {
            this.currentTrackPage--;
        }
        updateView();
    }

     public void updateView() {
        if (pattern != null && !pattern.isEmpty() && !pattern.equals("")) {
            setAlbums(dao.findWithLimitPatternAlbum(new Album(), getAlbumOffset(),albumPerPage ,pattern));
            setTracks(dao.findWithLimitPatternTrack(new Track(), getTrackOffset(),albumPerPage, pattern));
        } else {
            setAlbums(null);
            setTracks(null);
        }
    }

}
