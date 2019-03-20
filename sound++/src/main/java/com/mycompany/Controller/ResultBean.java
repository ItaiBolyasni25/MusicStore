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
    private int totalRows;
    private int currentPage;
    private int itemPerPage;
    private int totalPages;
    @Inject
    private DAO dao;
    public ResultBean() {
        this.itemPerPage = 4;
        this.currentPage = 1;
    }

    public void init() {
        this.pattern = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pattern");
        updateView();
         this.totalRows = dao.findWithPattern(new Album(), pattern, "title").size();
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }
        this.totalRows = dao.findWithPattern(new Album(), pattern, "title").size();
         if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
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
     public void updateView() {
        if (pattern != null && !pattern.isEmpty() && !pattern.equals("")) {
            setAlbums(dao.findWithLimitPattern(new Album(), getOffset(), itemPerPage,pattern, "title"));
            setTracks(dao.findWithLimitPattern(new Track(), getOffset(),itemPerPage, pattern, "title"));
        } else {
            setAlbums(null);
            setTracks(null);
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
     
    public int getOffset() {
        return (currentPage - 1) * itemPerPage;
    }
    

    public void next() {
        if (this.currentPage < this.totalPages) {
            this.currentPage++;
        }
        updateView();
    }

    public void prev() {
        if (this.currentPage > 1) {
            this.currentPage--;
        }
        updateView();
    }

}
