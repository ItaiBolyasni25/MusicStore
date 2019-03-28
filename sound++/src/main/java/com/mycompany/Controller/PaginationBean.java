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
    private int currentPage;
    private int itemPerPage;
    private int totalPages;

    public PaginationBean() {
        this.currentPage = 1;
        this.itemPerPage = 3;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrentsPage() {
        return currentPage;
    }

    public void setCurrentsPage(int currentsPage) {
        this.currentPage = currentsPage;
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
     
   public void initialTotalRow(String str){
       if(str.equals("album")){
           totalRows = dao.findRandom(new Album()).size();
       }
       else if(str.equals("track")){
           totalRows = dao.findRandom(new Track()).size();
       }
   }
  
}
