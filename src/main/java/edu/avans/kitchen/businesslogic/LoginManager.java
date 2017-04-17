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
    
    //Methode
    public boolean checkPassword(String username, String password){
        String PasswordFromDB = (new LoginDAO()).
            getPasswordForUsername(username);                
        if (PasswordFromDB == null ){
            return false;
        }        
        return true;
    }
    
    //Setters
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}