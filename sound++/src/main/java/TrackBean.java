/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author maian
 */
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("trackBean")
public class TrackBean implements Serializable {
    @Inject
   private DAO dao;
   public TrackBean(){
   }
   public List<Track> getAll(){
       return dao.findAll(new Track());
   }
   
}
