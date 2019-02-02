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
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "albumBean", eager = true)
public class AlbumBean implements Serializable {
   private AlbumDao dao;
   public AlbumBean(){
       dao = new AlbumDao("songstore");
   }
   public List<Album> getAll(){
       return dao.getAll();
   }
   
}