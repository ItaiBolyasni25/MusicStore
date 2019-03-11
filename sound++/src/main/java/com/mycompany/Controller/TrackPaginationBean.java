package com.mycompany.Controller;


import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
@ViewScoped
@Named("TrackPaginationBean")
public class TrackPaginationBean implements Serializable {

    @Inject
    private DAO dao;
    private List<Track> dataList;
    ;
    private int totalRows;
    private int currentPage;
    private int itemPerPage;
    private int totalPages;

    public TrackPaginationBean() {
        this.itemPerPage = 12;
        this.currentPage = 1;

    }

    public List<Track> getDatalist() {
        return dataList;
    }

    public void setDatalist(List<Track> dataList) {
        this.dataList = dataList;
        setTotalrows(dataList.size());
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
        this.totalRows = dao.findAll(new Track()).size();
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }
        updateView();
    }

    public int getCurrent_page() {
        return this.currentPage;
    }

    public void setCurrent_page(int newCurrentPage) throws IOException {
        currentPage = newCurrentPage;
        updateView();

    }

    public void updateView() {
        int offset = getOffset();
        setDatalist(dao.findWithLimit(new Track(), offset, itemPerPage));
    }

    public void next() {
        if (this.currentPage < totalPages) {
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
