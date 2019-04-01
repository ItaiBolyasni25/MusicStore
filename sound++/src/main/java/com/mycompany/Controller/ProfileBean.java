package com.mycompany.Controller;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Gabriela
 */
@Named(value = "profileBean")
@SessionScoped
public class ProfileBean implements Serializable{
    
    public ProfileBean(){}
    
    public void getSongPurchases(){
        
    }
    
    public void getAlbumPurchases(){
        
    }
}
