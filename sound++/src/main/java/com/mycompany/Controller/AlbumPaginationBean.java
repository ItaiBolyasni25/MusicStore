package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Persistence.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maian
 */
@SessionScoped
@Named("AlbumPaginationBean")
public class AlbumPaginationBean implements Serializable {

    @Inject
    private DAO dao;
    private List<Album> dataList;
    private int totalRows;
    private int currentPage;
    private int itemPerPage;
    private int totalPages;

    public AlbumPaginationBean() {
        this.itemPerPage = 9;
        this.currentPage = 1;

    }
    
    public void initialize(){
        String current = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
        this.currentPage = 1;
        if(current != null) {
            int currentInt = Integer.parseInt(current);
            if(!(currentInt > this.totalPages))
                this.currentPage = currentInt;
        }
        updateView();
    }
    public List<Album> getDatalist() {
        return dataList;
    }

    public void setDatalist(List<Album> dataList) {
        this.dataList = dataList;
    }

    public int getOffset() {
        return (currentPage - 1) * itemPerPage;
    }

    public int getTotalrows() {
        return totalRows;
    }

    public void setTotalrows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getItem_per_page() {
        return itemPerPage;
    }

    public void setItem_per_page(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotal_pages() {
        return totalPages;
    }

    public void setTotal_pages(int totalPages) {
        this.totalPages = totalPages;
    }

    @PostConstruct
    public void init() {
        this.totalRows = dao.findAll(new Album()).size();
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }

//        updateView();
    }

    public int getCurrent_page() {
        return this.currentPage;
    }

    public void setCurrent_page(int newCurrentPage) {
        currentPage = newCurrentPage;
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestMap()
                .put("current", this.currentPage);
        updateView();
    }

    public void updateView() {
        int offset = getOffset();
        setDatalist(dao.findWithLimit(new Album(), offset, itemPerPage));
    }

    public String next() {
        if (this.currentPage < totalPages) {
            setCurrent_page(this.currentPage + 1);
            return "albums?faces-redirect=true&page="+(this.currentPage);
        }
        return "";
    }


    public void prev() {
        if (this.currentPage > 1) {
            setCurrent_page(this.currentPage - 1);
        }
        updateView();
    }
     public String redirect(){         
        return "albums?faces-redirect=true&page="+this.currentPage;  
    }
}
