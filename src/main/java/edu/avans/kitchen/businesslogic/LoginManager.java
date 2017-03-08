package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.datastorage.LoginDAO;
/**
 *
 * @author Bram
 */
public class LoginManager {
    private boolean loggedIn;
        
    public LoginManager() {
        loggedIn = false;
    }
    //Method
    public boolean checkPassword(String username, String password){
        String hashedPasswordFromDB = (new LoginDAO()).
            getHashedPasswordForUsername(username);                
        if (hashedPasswordFromDB == null ){
            return false;
        }        
        return true;
    }
    
    //Setters
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}