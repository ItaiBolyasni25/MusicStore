package com.mycompany.Utilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;

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

//    public void setLanguage(String lan) {
//    public void setLanguage(String lan) {
//        if (lan.equals("fr")) {
//            language = "Francais";
//        } else if (lan.equals("en")) {
//            language = "English";
//        }
//    }
    public void languageChange(ValueChangeEvent e) {
        String lan = e.getNewValue().toString();
        if (lan.equals("fr")) {
            language = "Francais";
        } else if (lan.equals("en")) {
            language = "English";
        }
    }

    public String getLanguage() {
        return language;
    }

    public String getHome() {
        return language.equals("English") ? "Home" : "Accueuil";
    }

    public String getAlbums() {
        return language.equals("English") ? "Albums" : "Albums";
    }

    public String getTracks() {
        return language.equals("English") ? "Tracks" : "Chansons";
    }

    public String getAbout() {
        return language.equals("English") ? "About" : "A propos";
    }

    public String getLogin() {
        return language.equals("English") ? "Log In/ Sign Up" : "Se Registrer/ Se Connecter";
    }

    public String getCart() {
        return language.equals("English") ? "Cart" : "Panier";
    }

    public String getLangue() {
        return language.equals("English") ? "Language" : "Langue";
    }

    public String getEnglish() {
        return language.equals("English") ? "English" : "Anglais";
    }

    public String getFrench() {
        return language.equals("English") ? "French" : "Francais";
    }

    public String getSearch() {
        return language.equals("English") ? "Search" : "Chercher";
    }

    public String getProfile() {
        return language.equals("English") ? "Profile" : "Profil";
    }

    public String getWelcome() {
        return language.equals("English") ? "Welcome" : "Bienvenue";
    }

    public String getDescription() {
        return language.equals("English")
                ? "The world of Music. " + " Available wherever you are."
                : "C'est un magasin de musique en ligne où vous pouvez acheter des singles et des albums des meilleur artiste et des meilleurs genres!";
    }

    public String getNewsfeed() {
        return language.equals("English") ? "News Feed" : "Fill de Nouvelles";
    }

    public String getNewreleases() {
        return language.equals("English") ? "New Releases" : "Nouveaute";
    }

    public String getNewsongs() {
        return language.equals("English") ? "New Songs!" : "Nouvelles Chansons!";
    }

    public String getNewalbums() {
        return language.equals("English") ? "New Albums!" : "Nouveaux Albums!";
    }

    public String getGenres() {
        return language.equals("English") ? "Genres" : "Categories";
    }

    public String getAlbumtitle() {
        return language.equals("English") ? "Album Title" : "Nom de l'album";
    }

    public String getAlbumdate() {
        return language.equals("English") ? "Release Date" : "Date de Sortie";
    }

    public String getArtist() {
        return language.equals("English") ? "Artist" : "Artiste";
    }

    public String getNumberSongs() {
        return language.equals("English") ? "# of Tracks" : "# de Pistes";
    }

    public String getCost() {
        return language.equals("English") ? "Price" : "Prix";
    }

    public String getSongtitle() {
        return language.equals("English") ? "Track Title" : "Nom de la chanson";
    }

    public String getSongdate() {
        return language.equals("English") ? "Release Date" : "Date de Sortie";
    }

    public String getPlaylength() {
        return language.equals("English") ? "Play Length" : "Duree de la chanson";
    }

    public String getAlbumimage() {
        return language.equals("English") ? "Album Cover" : "Image Album";
    }

    public String getLoginopt() {
        return language.equals("English") ? "Log In" : "Se Connecter";
    }

    public String getSignup() {
        return language.equals("English") ? "Sign up" : "S'inscrire";
    }

    public String getFirstname() {
        return language.equals("English") ? "Firstname" : "Prenom";
    }

    public String getLastname() {
        return language.equals("English") ? "Lastname" : "Nom de famille";
    }

    public String getCompany() {
        return language.equals("English") ? "Company name" : "Prenom";
    }

    public String getAddress() {
        return language.equals("English") ? "Address" : "Addresse";
    }

    public String getPostalcode() {
        return language.equals("English") ? "Postal Code" : "Code Postal";
    }

    public String getCity() {
        return language.equals("English") ? "City" : "Ville";
    }

    public String getProvince() {
        return language.equals("English") ? "Province" : "Province";
    }

    public String getCountry() {
        return language.equals("English") ? "Country" : "Pays";
    }

    public String getCellphone() {
        return language.equals("English") ? "Cell Phone" : "Telephone cellulaire";
    }

    public String getHometel() {
        return language.equals("English") ? "Home Phone" : "Telephone residentielle";
    }

    public String getEmail() {
        return language.equals("English") ? "Email" : "Courriel Electronique";
    }

    public String getPassword() {
        return language.equals("English") ? "Password" : "Mot de passe";
    }

    public String getInvalidEmail() {
        return language.equals("English") ? "There's already an existing account with that email address" : "Il y a déjà un compte existant avec cet courriel";
    }

    public String getLoginError() {
        return language.equals("English") ? "There was a problem logging you in" : "Il y a eu un problème pour vous connecter";
    }

    public String getChange() {
        return language.equals("English") ? "Change" : "Changer";
    }

    public String getInventory() {
        return language.equals("English") ? "Inventory" : "Inventaire";
    }

    public String getSales() {
        return language.equals("English") ? "Sales" : "Ventes";
    }

    public String getClientmngt() {
        return language.equals("English") ? "Client Management" : "Gestion des clients";
    }

    public String getOrdermngt() {
        return language.equals("English") ? "Order Management" : "Gestion des orders";
    }

    public String getReviewmngt() {
        return language.equals("English") ? "Review Management" : "Gestion des commentaires";
    }

    public String getReporting() {
        return language.equals("English") ? "Reports" : "Rapports";
    }

    public String getNewsmngt() {
        return language.equals("English") ? "News Management" : "Gestion de Nouvelles";
    }

    public String getSurveymngt() {
        return language.equals("English") ? "Survey Management" : "Gestion de questionnaires";
    }

    public String getBannerad() {
        return language.equals("English") ? "Banner Ad Management" : "Gestion de publicites";
    }

    public String getInventoryadd() {
        return language.equals("English") ? "Add" : "Ajouter";
    }

    public String getInventoryremove() {
        return language.equals("English") ? "Remove" : "Enlever";
    }

    public String getInventoryedit() {
        return language.equals("English") ? "Edit" : "Modifier";
    }

    public String getQuestion() {
        return language.equals("English") ? "Question" : "Question";
    }

    public String getOption() {
        return language.equals("English") ? "Option" : "Option";
    }

    public String getQuestionRequired() {
        return language.equals("English") ? "Question is required" : "Question est un champ obligatoire";
    }

    public String getOptionRequired() {
        return language.equals("English") ? "Option 1 and 2 are required" : "Les options 1 et 2 sont obligatoires";
    }

    public String getAdd() {
        return language.equals("English") ? "Add" : "Ajouter";
    }

    public String getAddSurvey() {
        return language.equals("English") ? "Add New Survey" : "Ajouter Nouveau Sondage";
    }

    public String getPastSurvey() {
        return language.equals("English") ? "Past Surveys" : "Sondages passees";
    }

    public String getSurvey() {
        return language.equals("English") ? "Surveys" : "Sondages";
    }

    public String getTitle() {
        return language.equals("English") ? "Title" : "Titre";
    }

    public String getSurveyuser() {
        return language.equals("English") ? "Log In or Sign up to complete surveys about your favourites artists" : "Connectez ou enregistrez vous pour repondre a different sondages sur vos artistes favoris";
    }

    public String getSubmit() {
        return language.equals("English") ? "Submit" : "Completer";
    }

    public String getGoal() {
        return language.equals("English") ? "Our Goal" : "Notre Mission";
    }

    public String getAboutUs() {
        return language.equals("English") ? "About us" : "A propos de nous";
    }

    public String getDevelopers() {
        return language.equals("English") ? "Our Developers" : "Nos Developpeurs";
    }

    public String getDevices() {
        return language.equals("English") ? "Accessible in all devices" : "Accessuble dans tous les appareils";
    }

    public String getLearnmore() {
        return language.equals("English") ? "Learn more about Sound++" : "Apprendre plus sur Sound++";
    }

    public String getPastnewsfeeds() {
        return language.equals("English") ? "Past News Feeds" : "Derniers Fil d'actualites";
    }

    public String getChangenewsfeed() {
        return language.equals("English") ? "Change News Feed" : "Changer Fil d'actualites";
    }

    public String getCurrentnewsfeed() {
        return language.equals("English") ? "Current News Feeds" : "Fil d'actualites courant";
    }

    public String getRecommendations() {
        return language.equals("English") ? "Recommended News Feeds" : "Fil d'actualites recommandes";
    }

    public String getRss() {
        return language.equals("English") ? "New RSS" : "Nouveau RSS";
    }

    public String getRequired() {
        return language.equals("English") ? "This field is required" : "Cette section est requise";
    }

    public String getInvalidlength() {
        return language.equals("English") ? "Invalid length minimum 10, maximum 160" : "Longueur invalide entre 10 et 160 caracteres";
    }
    
    public String getSlogan(){
        return language.equals("English") ? "When words fail, music speaks." : "Quand les mots manquent, la musique parle.";
    }

    public String getAboutText1() {
        return language.equals("English")
                ? "Sound++ makes it easy to find the right music for any moment on any device"
                : "Sound++ permet l'access facile a la bonne musique dans n'importe quel moment sur n'importe quel appareil."
                ;
    }
    
    public String getAboutText2() {
        return language.equals("English")
                ? "We have an imense variety of albums, tracks and artists for everyone."
                : "Nous avons une immense variete de chansons, albums et artistes pour tout le monde. "
                ;
    }
    
    public String getAboutText3() {
        return language.equals("English")
                ? " Whether you’re partying, relaxing or working, the right music is at every moment a click away."
                : "Ce soit pour faire la fete, relaxer ou travailler, A tout moment vous etes a un click de la bonne musique."
                ;
    }

    public String getAboutText4() {
        return language.equals("English")
                ? "You will also know the latest news about your favourite artists and you can prove your extensive knowledge in music through different surveys."
                : "Vous allez aussi en savoir les dernieres nouvelles de vos artistes favoris et vous pouvez prendre des questionnaires pour en demontrer vos connaissances."
                ;
    }
    
    public String getAnswer(){
        return language.equals("English")? "Submit": "Soumettre";
    }
    
    public String getSignOut(){
        return language.equals("English")? "Sign Out": "Se deconnecter";
    }
    
    public String getSubmitReview() {
        return language.equals("English") ? "Submit review" : "Poster le commentaire";
    }
}
