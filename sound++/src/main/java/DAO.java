

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 1633867
 */
public class DAO {


    protected EntityManager em;

    public DAO(String databaseName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(databaseName);
        this.em = emf.createEntityManager();

    }

    public <E extends EntityModel> boolean write(E entityModel) {
        em.getTransaction().begin();
        em.persist(entityModel);
        em.getTransaction().commit();
        return true;
    }

    public <E extends EntityModel> List<E> read(E entityModel, int id) {
        em.getTransaction().begin();
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("FROM " + className + " WHERE " + className + "_id = '" + id + "'");

        return q.getResultList();
    }
    
    public <E extends EntityModel> List<E> findAll(E entityModel) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("FROM " + className);
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
        Query q = em.createQuery("FROM " + className + " a ORDER BY a.title ASC" );
        q.setFirstResult(offset);
        q.setMaxResults(display);
        return q.getResultList();
    }
    
    

}
