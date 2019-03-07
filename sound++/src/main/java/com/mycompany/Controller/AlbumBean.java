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
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("albumBean")
public class AlbumBean implements Serializable {
   @Inject
   private DAO dao; 
   private Album selectedAlbum;
   public AlbumBean(){
      
   }

    public Album getSelectedAlbum() {

        return selectedAlbum;
    }

    public void setSelectedAlbum(Album selectedAlbum) {
                System.out.println("hihihihihihi" + selectedAlbum.getTitle());
        this.selectedAlbum = selectedAlbum;
    }
    
    public List<Album> getSimilarAlbum(){
         List<Album> albums = dao.findWithLimitGenre(new Album(), 3, selectedAlbum.getGenre(), "title", selectedAlbum.getTitle());
         return albums;
    }
  
   
}
