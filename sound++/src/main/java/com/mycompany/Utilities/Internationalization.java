package com.mycompany.Utilities;

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

    private String language = "English";
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
    
    public String getAlbumtitle(){
        return language.equals("English")? "Album Title": "Nom de l'album";
    }
    
    public String getAlbumdate(){
        return language.equals("English")? "Release Date": "Date de Sortie";
    }
    
    public String getArtist(){
        return language.equals("English")? "Artist": "Artiste";
    }
    
    public String getNumberSongs(){
        return language.equals("English")? "# of Tracks": "# de Pistes";
    }
    
    public String getCost(){
        return language.equals("English")? "Price": "Prix";
    }
    
    public String getSongtitle(){
        return language.equals("English")? "Track Title": "Nom de la chanson";
    }
    
    public String getSongdate(){
        return language.equals("English")? "Release Date": "Date de Sortie";
    }
    
    public String getPlaylength(){
        return language.equals("English")? "Play Length": "Duree de la chanson";
    }
    
    public String getAlbumimage(){
        return language.equals("English")? "Album Cover": "Image Album";
    }
    
    public String getLoginopt(){
        return language.equals("English")? "Log In": "Se Connecter";
    }
    
    public String getSignup(){
        return language.equals("English")? "Sign up": "S'inscrire";
    }
    
    public String getFirstname(){
        return language.equals("English")? "Firstname": "Prenom";
    }
    
    public String getLastname(){
        return language.equals("English")? "Lastname": "Nom de famille";
    }
    
    public String getCompany(){
        return language.equals("English")? "Company name": "Prenom";
    }
    
    public String getAddress(){
        return language.equals("English")? "Address": "Addresse";
    }
    
    public String getPostalcode(){
        return language.equals("English")? "Postal Code": "Code Postal";
    }
    
    public String getCity(){
        return language.equals("English")? "City": "Ville";
    }
    
    public String getProvince(){
        return language.equals("English")? "Province": "Province";
    }
    
    public String getCountry(){
        return language.equals("English")? "Country": "Pays";
    }
    
    public String getCellphone(){
        return language.equals("English")? "Cell Phone": "Telephone cellulaire";
    }
    
    public String getHometel(){
        return language.equals("English")? "Home Phone": "Telephone residentielle";
    }
    
    public String getEmail(){
        return language.equals("English")? "Email": "Courriel Electronique";
    }
    
    public String getPassword(){
        return language.equals("English")? "Password": "Mot de passe";
    }
}
