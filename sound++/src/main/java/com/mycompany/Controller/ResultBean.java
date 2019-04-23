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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String filter;
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
        this.albumPerPage = 3;
        this.currentAlbumsPage = 1;
        this.trackPerPage = 3;
        this.currentTrackPage = 1;
    }

    public void init() throws ParseException {
        this.pattern = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pattern");
        this.filter = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("filter");
        updateView();
        if(filter.equals("false")){
           this.totalAlbumsRows = dao.findAllArtistAlbum(new Album(), pattern).size();
           this.totalTrackRows = dao.findAllTrackArtist(new Track(), pattern).size();
        }
        else {
            String[] days = pattern.split("-");
            this.totalAlbumsRows = dao.findWithAllPatternDate(new Album(), newDateFormat(days[0]), newDateFormat(days[1])).size();
            this.totalTrackRows = dao.findWithAllPatternDate(new Track(), newDateFormat(days[0]), newDateFormat(days[1])).size();
        }
        if (totalAlbumsRows > albumPerPage) {
            totalAlbumPages = (int) Math.ceil((totalAlbumsRows * 1.0) / albumPerPage);
        } else {
            totalAlbumPages = 1;
        }
        if (totalTrackRows > trackPerPage) {
            totalTrackPages = (int) Math.ceil((totalTrackRows * 1.0) / trackPerPage);
        } else {
            totalTrackPages = 1;
        }
         this.currentAlbumsPage = 1;
         this.currentTrackPage = 1;

    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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

    public int getAlbumOffset() {
        return (currentAlbumsPage - 1) * albumPerPage;
    }

    public void albumNext() throws ParseException {
        if (this.currentAlbumsPage < this.totalAlbumPages) {
            this.currentAlbumsPage++;
        }
        updateView();
    }

    public void albumPrev() throws ParseException {
        if (this.currentAlbumsPage > 1) {
            this.currentAlbumsPage--;
        }
        updateView();
    }
    public void trackNext() throws ParseException {
        System.out.println(totalTrackPages);
        if (this.currentTrackPage < this.totalTrackPages) {
            this.currentTrackPage++;
        }
        updateView();
    }

    public void trackPrev() throws ParseException {
        if (this.currentTrackPage > 1) {
            this.currentTrackPage--;
        }
        updateView();
    }

    public void updateView() throws ParseException {
        if (pattern != null && !pattern.isEmpty() && !pattern.equals("") && filter != null && !filter.isEmpty() && !filter.equals("")) {
            if(this.filter.equals("false")){
                    setAlbums(dao.findWithLimitPatternAlbum(new Album(), getAlbumOffset(), albumPerPage, pattern));
                    setTracks(dao.findWithLimitPatternTrack(new Track(), getTrackOffset(), albumPerPage, pattern));
            }
            else{
                    String[] days = pattern.split("-");
                    setAlbums(dao.findWithLimitPatternDate(new Album(), getAlbumOffset(), albumPerPage, newDateFormat(days[0]),  newDateFormat(days[1])));
                    setTracks(dao.findWithLimitPatternDate(new Track(), getTrackOffset(), albumPerPage, newDateFormat(days[0]),  newDateFormat(days[1])));
            }
        } else {
            setAlbums(null);
            setTracks(null);
        }
    }
    private java.sql.Date newDateFormat(String date) throws ParseException {
        String newString = "";
        String[] elements = date.split("/");
        newString += elements[2] + "-" + elements[0] + "-" + elements[1];
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newdate = sdf1.parse(newString.replaceAll("\\s",""));
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime());
        return sqlDate;
    }

}
