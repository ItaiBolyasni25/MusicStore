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
@Named("trackBean")
public class TrackBean implements Serializable {
   private DAO dao;
   public TrackBean(){
       dao = new DAO("songstore");
   }
   public List<Track> getAll(){
       return dao.findAll(new Track());
   }
   
}