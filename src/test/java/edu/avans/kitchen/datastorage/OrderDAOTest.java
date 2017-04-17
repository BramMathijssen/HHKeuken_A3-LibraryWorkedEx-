/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.datastorage;

import edu.avans.kitchen.domain.Order;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bram
 */
public class OrderDAOTest {


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

//
//    /**
//     * Test of findAcceptedOrders method, of class OrderDAO.
//     */
//    @Test
//    public void testFindAcceptedOrders() {
//        System.out.println("findAcceptedOrders");
//        OrderDAO instance = new OrderDAO();
//        List<Order> expResult = null;
//        List<Order> result = instance.findAcceptedOrders();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findPlacedOrders method, of class OrderDAO.
//     */
//    @Test
//    public void testFindPlacedOrders() {
//        System.out.println("findPlacedOrders");
//        OrderDAO instance = new OrderDAO();
//        List<Order> expResult = null;
//        List<Order> result = instance.findPlacedOrders();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of setActive method, of class OrderDAO. Using a orderId > 0
     */
    @Test
    public void testSetActiveWrongId() {
        System.out.println("setActive");
        int orderId = -1;
        if(orderId >= 0){
        OrderDAO instance = new OrderDAO();
        instance.setActive(orderId);
        }
        else{
            fail("The orderId is < 0");   
        }
    }
    
    /**
     * Test of setActive method, of class OrderDAO. Using a orderI < 0
     */
    @Test
    public void testSetActiveGoodId() {
        System.out.println("setActive");
        int orderId = 2;
        OrderDAO instance = new OrderDAO();
        if(orderId >= 0){
        instance.setActive(orderId);
        }
        else{
            fail("The orderId is < 0");   
        }
    }

    /**
     * Test of linkEmployee method, of class OrderDAO.
     */
    @Test
    public void testLinkEmployee() {
        System.out.println("linkEmployee");
        int employeeid = 1;
        int dishId = 2;
        int mealorderid = 3;
        OrderDAO instance = new OrderDAO();
        if(employeeid >= 0 || dishId >= 0 || mealorderid >= 0){
            instance.linkEmployee(employeeid, dishId, mealorderid);
        }
        else{
            fail("One of the Id's was < 0 ");
        }
    }

    /**
     * Test of setReady method, of class OrderDAO.
     */
    @Test
    public void testSetReady() {
        System.out.println("setReady");
        int orderId = 7;
        OrderDAO instance = new OrderDAO();
        if(orderId >= 0){
        instance.setReady(orderId);
        }
        else{
            fail("The orderId is < 0");
        }
    }

    /**
     * Test of setDenied method, of class OrderDAO.
     */
    @Test
    public void testSetDenied() {
        System.out.println("setDenied");
        int orderId = 0;
        OrderDAO instance = new OrderDAO();
        if(orderId >= 0){
        instance.setDenied(orderId);
        }
        else{
            fail("The orderId is < 0");
        }
    }

}
