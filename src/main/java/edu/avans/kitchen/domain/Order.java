package edu.avans.kitchen.domain;

import java.util.ArrayList;
import java.util.List;
import edu.avans.kitchen.domain.Dish;
/**
 *
 * @author Bram
 */
public class Order {
    //Attributes
    private int tableNr, orderId, maxCookingTime;
    private long endTime;
    private Status status;
    private final List<Dish> dishes;
    private static final String STATE = "%status%";
    private static final String REASON = "%reason%";
    
    //Default Constructor om de ArrayList van dishes the initializeren     
    public Order() {
        this.dishes = new ArrayList<>();
    }
    
    //Constructor
    public Order(int tableNr, int orderId, int maxCookingTime, long endTime, Status status, List<Dish> dishes) {
        this.tableNr = tableNr;
        this.orderId = orderId;
        this.maxCookingTime = maxCookingTime;
        this.endTime = endTime;
        this.status = status;
        this.dishes = dishes;
    }
    
    //Getters
    public int getTableNr() {
        return tableNr;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getMaxCookingTime() {
        return maxCookingTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public Status getStatus() {
        return status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public static String getSTATE() {
        return STATE;
    }

    public static String getREASON() {
        return REASON;
    }
    
    //Setters
    public void setTableNr(int tableNr) {
        this.tableNr = tableNr;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setMaxCookingTime(int maxCookingTime) {
        this.maxCookingTime = maxCookingTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    //Methoden
    public void addDish(Dish dish) {
       dishes.add(dish);
    }
    
 }
