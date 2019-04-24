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
    private List<Album> allAlbums;
    private List<Album> albums;
    private List<Track> allTracks;
    private List<Track> tracks;
    private int toIndex;
    private int itemLeft;
    public IndexMusicBean(){  
    }
    public List<Album> getRecentAlbum(){
        return dao.findRecent(new Album());
    }
      @PostConstruct
    public void init(){
        albumPagination.initialTotalRow();
        trackPagination.initialTotalRow();
        allAlbums = dao.findLimitRandom(new Album(),9);
        allTracks = dao.findLimitRandom(new Track(),9);
        updateAlbumsView();
        updateTrackView();
           System.out.println("hi");
    }
      public void albumNext(){
        if (albumPagination.getAlbumCurrentPage() < albumPagination.getTotalPages()) {
            albumPagination.setAlbumCurrentPage(albumPagination.getAlbumCurrentPage()+1); 
        }
        updateAlbumsView();
    }

    public List<Album> getAlbums() {
        System.out.println("hi" +albums.size());
        return albums;
    }
    public List<Album> getRecent(){
        return dao.findRecent(new Album());
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
      
    public void albumPrev(){
         if (albumPagination.getAlbumCurrentPage() > 1) {
            albumPagination.setAlbumCurrentPage(albumPagination.getAlbumCurrentPage()-1);
           
        }
        updateAlbumsView();
    }

    public void updateAlbumsView(){
        toIndex =albumPagination.getAlbumOffset() + albumPagination.getItemPerPage();
        if(toIndex < allAlbums.size()){
        setAlbums(allAlbums.subList(albumPagination.getAlbumOffset(), toIndex));
        }
        else{
            setAlbums(allAlbums.subList(albumPagination.getAlbumOffset(), allAlbums.size()));
        }
    }

    public int getAlbumCurrentPage() {
        return albumPagination.getAlbumCurrentPage();
    }

    public void setAlbumCurrentPage(int current) {
        this.albumPagination.setAlbumCurrentPage(current);
    }
    public int getAlbumTotalPage() {
        return albumPagination.getTotalPages();
    }
    public int getTrackCurrentPage() {
        return trackPagination.getTrackCurrentPage();
    }

    public void setTrackCurrentPage(int current) {
        this.trackPagination.setTrackCurrentPage(current);
    }
    public int getTrackTotalPage() {
        return trackPagination.getTotalPages();
    }

    public void trackPrev(){
         if (trackPagination.getTrackCurrentPage()> 1) {
            trackPagination.setTrackCurrentPage(trackPagination.getTrackCurrentPage()-1);
           
        }
        updateTrackView();
    }

    public void updateTrackView(){
        if((trackPagination.getTrackOffset() + albumPagination.getItemPerPage()) < allTracks.size()){
        setTracks(allTracks.subList(trackPagination.getTrackOffset(), (trackPagination.getTrackOffset() + albumPagination.getItemPerPage())));
        }
        else{
            setTracks(allTracks.subList(trackPagination.getTrackOffset(), allTracks.size()));
        }
    }
      public void trackNext(){
        if (trackPagination.getTrackCurrentPage() < trackPagination.getTotalPages()) {
            trackPagination.setTrackCurrentPage(trackPagination.getTrackCurrentPage()+1); 
        }
         updateTrackView();
    }
    

}
