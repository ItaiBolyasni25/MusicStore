/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author maian
 */
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("albumBean")
public class AlbumBean implements Serializable {
   private DAO dao;
   public AlbumBean(){
       dao = new DAO("songstore");
   }
   public List<Album> getAll(){
       return dao.findAll(new Album());
   }
   public Album getOne(){
       List<Album> als = dao.findAll(new Album());
       return als.get(0);
   }
   
}