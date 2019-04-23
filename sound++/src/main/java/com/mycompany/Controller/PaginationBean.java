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
import java.text.ParseException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named("PaginationBean")
public class PaginationBean implements Serializable {
    @Inject
    private DAO dao;
    private int totalRows;
    private int itemPerPage;
    private int totalPages;
    private int albumCurrentPage;
    private int trackCurrentPage;
        

    public PaginationBean() {
        this.albumCurrentPage = 1;
        this.trackCurrentPage = 1;
        this.itemPerPage = 3;
        
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getAlbumCurrentPage() {
        return albumCurrentPage;
    }

    public void setAlbumCurrentPage(int albumCurrentPage) {
        this.albumCurrentPage = albumCurrentPage;
    }

    public int getTrackCurrentPage() {
        return trackCurrentPage;
    }

    public void setTrackCurrentPage(int trackCurrentPage) {
        this.trackCurrentPage = trackCurrentPage;
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
     public int getAlbumOffset() {
        return (albumCurrentPage - 1) * itemPerPage;
    }
     public int getTrackOffset() {
        return (trackCurrentPage - 1) * itemPerPage;
    }
     
   public void initialTotalRow(){
       totalRows = 9;
       totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
   }
  
}
