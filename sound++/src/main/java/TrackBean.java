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
import javax.inject.Inject;

@ManagedBean(name = "trackBean", eager = true)
public class TrackBean implements Serializable {
   @Inject
   private DAO dao;
   public List<Track> getAll(){
       return dao.findAll(new Track());
   }
   
}