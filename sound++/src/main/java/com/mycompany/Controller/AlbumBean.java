package com.mycompany.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maian
 */
import com.mycompany.Model.Album;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("albumBean")
public class AlbumBean implements Serializable {

    @Inject
    private DAO dao;
    private Album album1;
    private Album album2;
    private Album album3;

    public AlbumBean() {
    }

    public List<Album> getAll() {
        return dao.findAll(new Album());
    }

    public Album getOne() {
        List<Album> als = dao.findAll(new Album());
        return als.get(0);
    }

    public List<Album> getByGenre() {
        String genre;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj") == null) {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre") == null) {
                genre = null;
            } else {
                genre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lastgenre");
            }
        } else {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
            if (user.getLast_genre() == null){
                genre = null;
            } else {
                genre = user.getLast_genre();
            }
        }
        List<Album> albums = null;
        
        if(genre == null){
            albums = getAll();
            
        } else {
            albums = dao.find(new Album(), "genre = '" + genre + "'");
        }
        this.album1 = albums.get(0);
        this.album2 = albums.get(1);
        this.album3 = albums.get(2);
        System.out.println("got here album 1 = " + album1.getTitle());
        return albums;
    }

    public Album getAlbum1() {
        return album1;
    }

    public void setAlbum1(Album album1) {
        this.album1 = album1;
    }

    public Album getAlbum2() {
        return album2;
    }

    public void setAlbum2(Album album2) {
        this.album2 = album2;
    }

    public Album getAlbum3() {
        return album3;
    }

    public void setAlbum3(Album album3) {
        this.album3 = album3;
    }

    
}
