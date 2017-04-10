package edu.avans.kitchen.domain;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bram
 */
public class Dish {
    //Attributen
    private String dishName;
    private int dishId, amount, employeeId, cookingTime;
    
    //Default constructor
    public Dish(){
    }

    //Misschien fout in de constructor List<Ingredient>
    public Dish(String dishName, int dishId, int amount, int employeeId, int cookingTime) {
        this.dishName = dishName;
        this.dishId = dishId;
        this.amount = amount;
        this.employeeId = employeeId;
        this.cookingTime = cookingTime;
    }
    
    //Getters
    public String getDishName() {
        return dishName;
    }

    public int getDishId() {
        return dishId;
    }

    public int getAmount() {
        return amount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCookingTime() {
        return cookingTime;
    }


    //Setters
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }  
}
