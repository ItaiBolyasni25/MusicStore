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
import javax.annotation.Resource;
import javax.enterprise.context.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@RequestScoped
public class DAO {

    @PersistenceContext(unitName = "usersPU")
    private EntityManager em;

    @Resource
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

    public <E extends EntityModel> List<E> findWithLimit(E entityModel, int offset, int display) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("SELECT a FROM " + className + " a ORDER BY a.title ASC");
        q.setFirstResult(offset);
        q.setMaxResults(display);
        return q.getResultList();
    }

    public <E extends EntityModel> List<E> findWithLimitGenre(E entityModel, int display, String genre) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("SELECT a FROM " + className + " a WHERE a.genre = " + genre + " ORDER BY a.title ASC");
        q.setMaxResults(display);
        return q.getResultList();
    }

    public <E extends EntityModel> List<E> find(E entityModel, String whereClause) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("FROM " + className + " identifier WHERE identifier." + whereClause);
        return q.getResultList();
    }

    public <E extends EntityModel> List<E> findAll(E entityModel) {
        String className = entityModel.getClass().getName().substring(entityModel.getClass().getName().lastIndexOf(".") + 1);
        Query q = em.createQuery("FROM " + className + " identifier");
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
