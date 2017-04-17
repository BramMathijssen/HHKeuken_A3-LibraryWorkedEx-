/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.businesslogic;

import java.sql.ResultSet;
import edu.avans.kitchen.datastorage.LoginDAO;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bram
 */
public class LoginManagerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of checkPassword method, of class LoginManager. Using a correct password
     */
    @Test
    public void testCheckCorrectPassword() {
        
        LoginDAO logindao = new LoginDAO();
        LoginManager instance = new LoginManager();
        System.out.println("checkPassword");
        String username = "admin";
        String password = logindao.getPasswordForUsername(username);
        if(password.equals("admin")){
           boolean expResult = true;
           boolean result = instance.checkPassword(username, password);
           assertEquals(expResult, result);
        }
        else{
           boolean expResult = false;
           boolean result = instance.checkPassword(username, password);
           assertEquals(expResult, result);
           fail("The test failed");
        }
    }
    
     /**
     * Test of checkPassword method, of class LoginManager using a wrong password.
     */
    @Test
    public void testCheckWrongPassword() {
        
        LoginDAO logindao = new LoginDAO();
        LoginManager instance = new LoginManager();
        System.out.println("checkPassword");
        String username = "admin";
        String password = logindao.getPasswordForUsername(username);
        if(password.equals("aap")){
           fail("The test failed");
        }
        else{
           boolean expResult = true;
           boolean result = instance.checkPassword(username, password);
           assertEquals(expResult, result);
            
        }
    }
    
    
}
