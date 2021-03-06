/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author maian
 */
@SessionScoped
@Named("selectedAlbum")
public class SelectedAlbum implements Serializable {

    private DAO dao;
    private Album album;
    private HtmlInputHidden dataItemId = new HtmlInputHidden();
    
    @Inject
    public SelectedAlbum(DAO dao) {
        this.dao = dao;
    }

    public SelectedAlbum() {}

    public String editDataItem() {
        if (album != null) {
            dataItemId.setValue(album.getId());
        }
        return "albuminfo?faces-redirect=true";
    }

    public Album getSelectedAlbum() {
        return album;
    }

    public HtmlInputHidden getAlbumId() {
        return dataItemId;
    }

    public void setSelectedAlbum(Album dataItem) {
        this.album = dataItem;
    }

    public void setDataItemId(HtmlInputHidden dataItemId) {
        this.dataItemId = dataItemId;
    }

    public List<Album> getSimilarAlbum() {
        String genre;
        if (album != null) {
            if (album.getGenre().contains("'")) {
                genre = album.getGenre().replace("'", "''");
            } else {
                genre = album.getGenre();
            }
            List<String> artists = new ArrayList<>();
            for (Artist a : album.getArtists()) {
                artists.add(a.getName());
            }
            List<Album> albums = dao.findWithLimitGenreAlbum(new Album(), 3, genre, artists, album.getTitle());
            return albums;
        }
        return null;
    }

}
