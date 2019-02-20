
package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Album;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1633867
 */
@Entity
@Table(name = "Artist")
public class Artist implements EntityModel, Serializable {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int artist_id;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "artists")
    private List<Album> albums;

    public Artist() {
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }
}
