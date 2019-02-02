package com.mycompany.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.User;
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


    private EntityManager em;

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
    
    /**
     * Method for finding a user in the database based on their email address
     * 
     * @param email Email to search for
     * @return List<User> Users with that email (should be 1 or 0)
     */
    public List<User> read(String email) {
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT everything FROM User everything WHERE email = '" + email + "'");

        return q.getResultList();
    }
  
    public <E extends EntityModel> boolean delete(E entityModel) {
        em.remove(entityModel);
        return true;
    }

}
