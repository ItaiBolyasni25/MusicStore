package com.mycompany.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.*;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author 1633867
 */
@Named
@RequestScoped
public class DAO {

    private EntityManager em;
    private UserTransaction userTransaction;

    public <E extends EntityModel> boolean write(E entityModel) {
        try {
            userTransaction.begin();
            em.persist(entityModel);
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        Query q = em.createQuery("SELECT everything FROM User everything WHERE everything.email = '" + email + "'");

        return q.getResultList();
    }
  
    public <E extends EntityModel> boolean delete(E entityModel) {
        em.remove(entityModel);
        return true;
    }
    
    public void setEntityManager(EntityManager manager) {
        this.em = manager;
    }
    
    public void setUserTransaction(UserTransaction ut) {
        this.userTransaction = ut;
    }
}
