package edu.avans.kitchen.domain;

import edu.avans.kitchen.generics.Error;
import java.util.ArrayList;
import java.util.List;
import edu.avans.kitchen.domain.Dish;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    
    public boolean setStatus(Status status) {
        boolean b = false;
        if(getStatus() == status) {
            Logger.getLogger(Order.class.getName()).log(Level.INFO, Error.STATUS.toString()
                    .replace(STATE, status.toString())
                    .replace(REASON, "deze order heeft al de status " + status.toString()));
        } else if(getStatus() == Status.PLACED && status == Status.READY) {
            Logger.getLogger(Order.class.getName()).log(Level.INFO, Error.STATUS.toString()
                    .replace(STATE, status.toString()
                    .replace(REASON, "een bestelling moet eerst geaccepteerd worden")));
        } else if(getStatus() == Status.READY && status != Status.READY) {
            Logger.getLogger(Order.class.getName()).log(Level.INFO, Error.STATUS.toString()
                    .replace(STATE, status.toString()
                    .replace(REASON, "de bestelling is al als gereed gemarkeerd")));
        } else {
            this.status = status;
            b = true;
        }
        return b;
    }
    
    //Methods    
    public void addDish(Dish d) {
        dishes.add(d);
    }
}
