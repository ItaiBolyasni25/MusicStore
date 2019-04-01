/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Gabriela
 */
@Named(value = "genreSearchBean")
@SessionScoped
public class GenreSearchBean implements Serializable {

    @Inject 
    private DAO dao;
    
    private String genre;
    private HtmlInputHidden data = new HtmlInputHidden();
    
    /**
     * Creates a new instance of GenreSearchBean
     */
    public GenreSearchBean() {
    }
    
    public List<String> getGenres(){
        return dao.findGenres();
    }
    
    public void setSelectedGenre(String genre){
        this.genre = genre;
    }
    
    public String getSelectedGenre(){
        return this.genre;
    }
    
    public HtmlInputHidden getGenre(){
        return data;
    }
    
    public String searchGenre(){
        if (genre != null){
            data.setValue(genre);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lastgenre", genre);
        } else {
            User user = (User)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj"));
            user.setLast_genre(genre);
            dao.updateEntity(user);
        }
        return "genre?faces-redirect=true";
    }
    
    public List<Track> getTracks(){
        return dao.find(new Track(), "genre = \""+ genre + "\"");
    }
    
    public List<Album> getAlbums(){
        return dao.find(new Album(), "genre = \"" + genre + "\"");
    }
}
