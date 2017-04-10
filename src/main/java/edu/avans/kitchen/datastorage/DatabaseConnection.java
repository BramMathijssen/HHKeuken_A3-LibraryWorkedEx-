/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.datastorage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    //Attributen
    private String url, user, password;
    private Connection con;
    
    //Constructor voor connectie maken met de database
    public DatabaseConnection(){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/Config.properties"));
            
            String ip = prop.getProperty("db_ip");
            String dbName = prop.getProperty("db_name");
            
            //this.url = "jdbc:mysql://" + ip + dbName; EN in config.properties ip = localhost/ dbName = 23ivp4a userName= root password = (leeg)
            this.url = "jdbc:mysql://" + ip + dbName;
            this.user = prop.getProperty("db_user");
            this.password = prop.getProperty("db_pass");
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch(IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, "SQL Error:", e);
        }
    }
    
    //Getters
    public Connection getConnection(){
        return con;
    }
}
