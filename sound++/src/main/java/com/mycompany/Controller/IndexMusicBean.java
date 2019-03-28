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
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author maian
 */
@ViewScoped
@Named("IndexMusicBean")
public class IndexMusicBean implements Serializable  {
    @Inject
    private DAO dao;
    @Inject
    private PaginationBean albumPagination;
    @Inject
    private PaginationBean trackPagination;
    private List<Album> albums;
    public IndexMusicBean(){        
    }
    public List<Album> getRecentAlbum(){
        return dao.findRecent(new Album());
    }
     public List<Album> getRandomAlbum(){
        return dao.findRandom(new Album());
    }
     
    public void init(){
        albumPagination.initialTotalRow("album");
         updateAlbumsView();
    }
      public void albumNext(){
        if (albumPagination.getCurrentsPage() < albumPagination.getTotalPages()) {
            albumPagination.setCurrentsPage(albumPagination.getCurrentsPage()+1);
        }
        updateAlbumsView();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
      
    public void albumPrev() throws ParseException {
         if (albumPagination.getCurrentsPage() > 1) {
            albumPagination.setCurrentsPage(albumPagination.getCurrentsPage()-1);
        }
        updateAlbumsView();
    }

    public void updateAlbumsView(){
        setAlbums(dao.findLimitRandom(new Album(), albumPagination.getOffset(), albumPagination.getItemPerPage()));
    }

}
