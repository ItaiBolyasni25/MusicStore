/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author 1635547
 */
@Named(value = "internationalization")
@SessionScoped
public class Internationalization implements Serializable {

    private String language = "French";
    /**
     * Creates a new instance of Internationalization
     */
    public Internationalization() {
    }
    
    public void setLanguage(String lan){
        if (lan.equals("fr")){
            language = "Francais";
        } else if (lan.equals("en")) {
            language = "English";
        }
    }
    
    public String getLanguage(){
        return language;
    }
    
    public String getHome(){
        return language.equals("English")? "Home": "Accueuil";
    }
    
    public String getAlbums(){
        return language.equals("English")? "Albums": "Albums";
    }
    
    public String getTracks(){
        return language.equals("English")? "Tracks": "Chansons";
    }
    
    public String getAbout(){
        return language.equals("English")? "About": "A propos";
    }
    
    public String getLogin(){
        return language.equals("English")? "Log In/ Sign Up": "Se Registrer/ Se Connecter";
    }
    
    public String getCart(){
        return language.equals("English")? "Cart": "Panier";
    }
    
    public String getLangue(){
        return language.equals("English")? "Language": "Langue";
    }
    
    public String getEnglish(){
        return language.equals("English")? "English": "Anglais";
    }
    
    public String getFrench(){
        return language.equals("English")? "French": "Francais";
    }
    
    public String getSearch(){
        return language.equals("English")? "Search": "Chercher";
    }
    
    public String getProfile(){
        return language.equals("English")? "Profile": "Profil";
    }
    
    public String getWelcome(){
        return language.equals("English")? "Welcome!": "Bienvenue!";
    }
    
    public String getDescription(){
        return language.equals("English")? 
                "This is an online music store where you can purchase singles and albums from the best artist and genres!": 
                "C'est un magasin de musique en ligne où vous pouvez acheter des singles et des albums des meilleur artiste et des meilleurs genres!";
    }
    
    public String getNewsfeed(){
        return language.equals("English")? "News Feed": "Fill de Nouvelles";
    }
    
    public String getNewreleases(){
        return language.equals("English")? "New Releases": "Nouveaute";
    }
    
    public String getNewsongs(){
        return language.equals("English")? "New Songs!": "Nouvelles Chansons!";
    }
    
    public String getNewalbums(){
        return language.equals("English")? "New Albums!": "Nouveaux Albums!";
    }
    
    public String getGenres(){
        return language.equals("English")? "Genres": "Categories";
    }
}
