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
    private int mealId, amount, employeeId, cookingTime;
    private final List<Ingredient> ingredients;
    
    //Default constructor om de ArrayList van ingredients te initializeren
    public Dish(){
        ingredients = new ArrayList<>();
    }

    //Misschien fout in de constructor List<Ingredient>
    public Dish(String dishName, int mealId, int amount, int employeeId, int cookingTime, List<Ingredient> ingredients) {
        this.dishName = dishName;
        this.mealId = mealId;
        this.amount = amount;
        this.employeeId = employeeId;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }
    
    //Getters
    public String getDishName() {
        return dishName;
    }

    public int getMealId() {
        return mealId;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    //Setters
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
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
    
    //Methode
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    
    
    
    
    
    
}
