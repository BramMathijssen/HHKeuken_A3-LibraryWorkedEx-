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
    private static final String MID = "KitchenOrderId";
    private final String AcceptedString = "Accepted";
    private final String PlacedString = "Placed";

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
            String query = "SELECT * FROM `kitchenorder` WHERE Status = 'Accepted' ;";
            activeRS = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }

        try {
            while (activeRS.next()) {
                Order order = new Order();
                order.setTableNr(activeRS.getInt("TableId"));
                order.setOrderId(activeRS.getInt(MID));
                order.setMaxCookingTime(activeRS.getInt("CookingTime"));
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
            String query = "SELECT * FROM `kitchenorder` WHERE Status = 'Placed' ;";
            placedRS = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        try {
            while (placedRS.next()) {
                Order order = new Order();
                order.setTableNr(placedRS.getInt("TableId"));
                order.setOrderId(placedRS.getInt(MID));
                order.setMaxCookingTime(placedRS.getInt("CookingTime"));
                order.setStatus(Status.PLACED);
                
                placedOrders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        return placedOrders;
    }

    public void setActive(int orderId) {
        try {
            Statement st = con.createStatement();
            String query = "UPDATE `kitchenorder` SET Status='Accepted' WHERE `KitchenOrderId` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
    
//    public void linkEmployee(int employeeid, int dishId, int mealorderid){
//        try{
//            Statement st = con.createStatement();
//            String query = "UPDATE `kitchenorder_dish` SET `EmployeeId` = " + employeeid + " WHERE `DishId` = " + dishId + " AND mealorderid = " + mealorderid + ";";
//            st.executeUpdate(query);
//        } catch(SQLException ex){
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
//        }
//    }

    public void setReady(int orderId) {
        try {
            Statement st = con.createStatement();
                String query = "UPDATE `kitchenorder` SET Status='ready' WHERE `KitchenOrderId` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
    
    public void setDenied(int orderId) {
        try {
            Statement st = con.createStatement();
                String query = "UPDATE `kitchenorder` SET Status='denied' WHERE `KitchenOrderId` = " + orderId + ";";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    }
}