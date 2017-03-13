package edu.avans.kitchen.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.avans.kitchen.domain.Order;
import edu.avans.kitchen.domain.Status;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//Class that executes statements regarding the orders
public class OrderDAO {
    //Attributes
    private final DatabaseConnection dbc;
    private final Connection con;
    private static final String SQL = "SQL: ";
    private static final String MID = "kitchenorderid";

    //Constructor
    public OrderDAO() {
        this.dbc = new DatabaseConnection();
        this.con = dbc.getConnection();
    }

    //Methods
    public List<Order> findAcceptedOrders() {
        List<Order> activeOrders = new ArrayList<>();
        ResultSet activeRS = null;
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM `kitchenorder` WHERE `Status` = `Accepted` ;";
            activeRS = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }

        try {
            while (activeRS.next()) {
                Order order = new Order();
                order.setTableNr(activeRS.getInt("tableid"));
                order.setOrderId(activeRS.getInt(MID));
                order.setMaxCookingTime(activeRS.getInt("cookingtime"));
                order.setEndTime(activeRS.getLong("endtime"));
                order.setStatus(Status.ACCEPTED);
                
                activeOrders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        return activeOrders;
    }

    public List<Order> findPlacedOrders() {
        List<Order> placedOrders = new ArrayList<>();
        ResultSet placedRS = null;
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM `kitchenorder` WHERE `Status` = `Placed` ;";
            placedRS = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        try {
            while (placedRS.next()) {
                Order order = new Order();
                order.setTableNr(placedRS.getInt("tableid"));
                order.setOrderId(placedRS.getInt(MID));
                order.setMaxCookingTime(placedRS.getInt("cookingtime"));
                order.setStatus(Status.PLACED);
                
                placedOrders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        return placedOrders;
    }

    public void setActive(int orderId, long endTime) {
        try {
            Statement st = con.createStatement();
            String query = "UPDATE `mealorder` SET status='accepted', `endtime` = " + endTime + " WHERE `mealorderid` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
    
    public void linkEmployee(int employeeid, int mealid, int mealorderid){
        try{
            Statement st = con.createStatement();
            String query = "UPDATE `mealorder_meal` SET `employeeid` = " + employeeid + " WHERE `mealid` = " + mealid + " AND mealorderid = " + mealorderid + ";";
            st.executeUpdate(query);
        } catch(SQLException ex){
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }

    public void setReady(int orderId) {
        try {
            Statement st = con.createStatement();
                String query = "UPDATE `mealorder` SET status='ready' WHERE `mealorderid` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
    
    public void setDenied(int orderId) {
        try {
            Statement st = con.createStatement();
                String query = "UPDATE `mealorder` SET status='denied' WHERE `mealorderid` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
}