


import java.util.List;

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
    private static final DAO DAO = new DAO("songstore");
    
    /**
     * Will be used to validate a user trying to register
     * 
     * @param user User to be registered
     * @return boolean
     */
    public static boolean hasValidInformation(User user) {
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
    public static boolean isRegistered(String email, String password) {
        List<User> user = DAO.read(email);
        
        if (user.size() == 1) {
            return user.get(0).getHash().equals(password);
        }
        
        return false;
    }
}
