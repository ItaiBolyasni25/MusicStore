package com.mycompany.Utilities;


import com.mycompany.Persistence.DAO;
import com.mycompany.Model.User;
import java.util.List;

/**
 * Validates user information
 * 
 * @author aantoine97
 */
public class Validator {
    
    
    /**
     * Will be used to validate a user trying to register
     * 
     * @param user User to be registered
     * @param DAO DAO
     * @return boolean
     */
    public static boolean emailExists(User user, DAO DAO) {
        List<User> existingUser = DAO.find(user, "email = '" + user.getEmail() +"'");
        return !existingUser.isEmpty();
    }
    
    /**
     * Will be used to validate if a user is already registered
     * 
     * @param email User email
     * @param password User password
     * @param DAO DAO
     * @return boolean
     */
    public static boolean isRegistered(String email, String password, DAO DAO) {
        List<User> user = DAO.find(new User(), "email = '" + email + "'");
        
        if (user.size() == 1) {
            return user.get(0).getHash().equals(password);
        }
        
        return false;
    }
    
    /**
     * 
     * @param password
     * @param DAO
     * @return 
     */
    public static boolean passwordExists(String password, DAO DAO) {
        return !DAO.find(new User(), "hash = '" + password + "'").isEmpty();
    }
}
