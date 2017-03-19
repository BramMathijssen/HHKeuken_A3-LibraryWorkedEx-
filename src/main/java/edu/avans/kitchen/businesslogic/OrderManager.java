package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.datastorage.IngredientDAO;
import edu.avans.kitchen.datastorage.OrderDAO;
import edu.avans.kitchen.domain.Ingredient;
import edu.avans.kitchen.domain.Order;
import edu.avans.kitchen.domain.Status;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    //Attributes
    private final OrderDAO orderDAO;
    private final IngredientDAO ingredientDAO;
    private List<Order> placedOrders, acceptedOrders;

    //Constructor
    public OrderManager() {
        this.orderDAO = new OrderDAO();
        this.ingredientDAO = new IngredientDAO();
        this.placedOrders = new ArrayList<>();
        this.acceptedOrders = new ArrayList<>();
    }

    //Getters
    public List<Order> getPlacedOrders() {
        return placedOrders;
    }

    public List<Order> getAcceptedOrders() {
        return acceptedOrders;
    }

    //Methods    
    public void findPlacedOrders() {
        this.placedOrders = orderDAO.findPlacedOrders();
    }

    public void findAcceptedOrders() {
        this.acceptedOrders = orderDAO.findAcceptedOrders();
    }

    public void readyOrder(Order o) {
        if(o.setStatus(Status.READY)){
            orderDAO.setReady(o.getOrderId());
        }
    }

    public void acceptOrder(Order o , String placedID) {
        if(o.setStatus(Status.ACCEPTED)){
            orderDAO.setActive(o.getOrderId());
        }
    }
    
    public void denyOrder(Order o) {
        if(o.setStatus(Status.DENIED)){
            orderDAO.setDenied(o.getOrderId());
        }
    }
    
//    public void linkEmployee(int dishid, int employeeid, int kitchenorderid){
//        orderDAO.linkEmployee(employeeid, dishid, kitchenorderid);
//    }
    
    public void amortIngredient(Ingredient ing){
        ingredientDAO.amortIngredient(ing);
    }
}