
import java.util.List;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maian
 */
public class TrackDAO extends DAO{
     public TrackDAO(String databaseName){
        super(databaseName);
    }
    public List<Track> getAll(){
        em.getTransaction().begin();
        Query q = em.createQuery("Select tr from Track tr");
        return q.getResultList();
    }
}
