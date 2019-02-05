
import java.util.List;
import javax.persistence.EntityManager;
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
public class AlbumDao extends DAO {
    public AlbumDao(String databaseName){
        super(databaseName);
    }
    public List<Album> getAll(){
        em.getTransaction().begin();
        Query q = em.createQuery("Select al from Album al");
        return q.getResultList();
    }
}
