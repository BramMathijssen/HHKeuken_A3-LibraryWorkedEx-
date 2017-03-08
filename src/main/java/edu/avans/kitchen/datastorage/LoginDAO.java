package edu.avans.kitchen.datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.avans.kitchen.datastorage.DatabaseConnection;



public class LoginDAO {
    // Attributes
    private final DatabaseConnection dbc ;
    private static final String SQL = "SQL: ";

    public LoginDAO() {
        this.dbc = new DatabaseConnection();
    }
  

    public String getHashedPasswordForUsername(String username) {
        ResultSet resultset;
        String s = null;
        try {
            dbc.openConnection();
            String query = "SELECT `password` FROM `employee` WHERE `userName` = \"" + username + "\";";
            resultset = dbc.executeSQLSelectStatement(query);
                
            if (resultset != null & resultset.next()) {
                s = resultset.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        return s;
    }
}