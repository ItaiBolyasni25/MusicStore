

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 1633867
 */
public class DAO {

   @PersistenceContext(unitName = "songstore")
    protected EntityManager em;

    public DAO() {
    }

    public <E extends EntityModel> boolean write(E entityModel) {
        em.getTransaction().begin();
        em.persist(entityModel);
        em.getTransaction().commit();
        return true;
    }

    public <E extends EntityModel> List<E> read(E entityModel, int id) {
        //em.getTransaction().begin();
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("SELECT c FROM " + className + " c WHERE c." + className.toLowerCase() + "_id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public <E extends EntityModel> List<E> findAll(E entityModel) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("Select e FROM " + className + " e");
        return q.getResultList();
    }
  
    public <E extends EntityModel> boolean delete(E entityModel) {
        em.remove(entityModel);
        return true;
    }
    
    public <E extends EntityModel> List<E> find(E entityModel, String whereClause) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("FROM " + className + " WHERE " + whereClause);
        return q.getResultList();
    }
    
    public <E extends EntityModel> List<E> findWithLimit(E entityModel, int offset, int display) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("SELECT a FROM " + className + " a ORDER BY a.title ASC" );
        q.setFirstResult(offset);
        q.setMaxResults(display);
        return q.getResultList();
    }
    /* public <E extends EntityModel> List<E> findWithJoin(E entityModel, String whereClause) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("SELECT a FROM " + className + "a JOIN a.track WHERE c." + className.toLowerCase() + "title = :whereClause");
        q.setParameter("whereClause", whereClause);
        return q.getResultList();
     }*/
   
    public void setEntityManager(EntityManager manager) {
        this.em = manager;
    }

    

}
