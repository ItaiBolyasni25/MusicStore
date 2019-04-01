package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gabriela
 */
@SessionScoped
@Named("clientManagementBean")
public class ClientManagementBean implements Serializable{

    @Inject
    private DAO dao;
    private List<User> usersEmail;
    private List<User> usersName;
    private String pattern;
    private User chosen;
    
    public ClientManagementBean() {
    }

    public List<User> getUsersByEmail() {
        return usersEmail;
    }

    public void setUsersByEmail(List<User> users) {
        this.usersEmail = users;
    }
    
    public List<User> getUsersByName() {
        return usersName;
    }

    public void setUsersByName(List<User> users) {
        this.usersName = users;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    public User getChosen(){
        return chosen;
    }
    
    public void setChosen(User chosen){
        this.chosen = chosen;
    }
   
    public void patternChanged() {
        if (pattern != null && !pattern.isEmpty() && !pattern.equals("")) {
            setUsersByName(dao.findWithLimitPattern(new User(),0,4, pattern, "firstname"));
            setUsersByEmail(dao.findWithLimitPattern(new User(),0,4, pattern, "email"));
        } else {
            setUsersByName(null);
            setUsersByEmail(null);
        }
    }

    public String redirect(){
        return "clientmngt?faces-redirect=true&pattern="+this.pattern;  
    }
    
    public void updateUser(){
        dao.updateEntity(this.chosen);
        System.out.println("********* " + this.chosen.getEmail());
    }
}
