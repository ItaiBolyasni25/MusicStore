
import java.util.List;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Validates user information
 *
 * @author aantoine97
 */
public class Validator {
    /*@Inject
    private DAO dao = new DAO("songstore");
    
    /**
     * Will be used to validate a user trying to register
     * 
     * @param user User to be registered
     * @return boolean
     */
 /*public static boolean hasValidInformation(User user) {
        List<User> existingUser = dao.find(new User(), "email = '" + user.getEmail() + "'");
        System.out.println(user.getEmail());
        return existingUser.isEmpty();
    }
    
    /**
     * Will be used to validate if a user is already registered
     * 
     * @param email User email
     * @param password User password
     * @return boolean
     */
 /*public static boolean isRegistered(String email, String password) {
        List<User> user = dao.find(new User(), "email = '" + email + "'");
        
        if (user.size() == 1) {
            return user.get(0).getHash().equals(password);
        }
        
        return false;
    }*/
}
