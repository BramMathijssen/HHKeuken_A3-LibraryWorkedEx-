/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.datastorage;

import edu.avans.kitchen.datastorage.DatabaseConnection;
import java.sql.ResultSet;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * For this test to pass a database must be present
 *
 * @author ppthgast
 */
public class DatabaseConnectionTest {

    public DatabaseConnectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void doConnectTest() {
        // test preparation and execution
        ResultSet rs = null;
        DatabaseConnection connection = new DatabaseConnection();
        boolean result = connection.openConnection();
        if (result) {
            rs = connection.executeSQLSelectStatement("select * from member");
        }

        // test verification
        assertTrue("database connection successfully established", result);
        assertTrue("result set no null", rs != null);
    }
}
