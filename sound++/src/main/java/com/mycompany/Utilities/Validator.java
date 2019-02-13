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
     * @return boolean
     */
    public static boolean hasValidInformation(User user, DAO DAO) {
        List<User> existingUser = DAO.read(user.getEmail());
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
    public static boolean isRegistered(String email, String password, DAO DAO) {
        List<User> user = DAO.read(email);
        
        if (user.size() == 1) {
            return user.get(0).getHash().equals(password);
        }
        
        return false;
    }
}
