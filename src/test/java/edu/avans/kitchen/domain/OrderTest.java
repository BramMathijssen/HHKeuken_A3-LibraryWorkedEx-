/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bram
 */

public class OrderTest {
    //Attributes
    private Order o;
    
    public OrderTest() {
        this.o = new Order();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //Testing the setStatus method in the Order class
    @Test
    public void test1(){
        o.setStatus(Status.PLACED);
        Assert.assertTrue(o.setStatus(Status.ACCEPTED));
    }
    
    @Test
    public void test2(){
        o.setStatus(Status.READY);
        Assert.assertFalse(o.setStatus(Status.ACCEPTED));
    }
    
    @Test 
    public void test3(){
        o.setStatus(Status.PLACED);
        Assert.assertFalse(o.setStatus(Status.READY));
    }
    
    @Test
    public void test4(){
        o.setStatus(Status.READY);
        Assert.assertFalse(o.setStatus(Status.READY));
    }
}