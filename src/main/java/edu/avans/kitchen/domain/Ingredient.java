package edu.avans.kitchen.domain;

/**
 *
 * @author Bram
 */
public class Ingredient {
    // Attributes
    private int amount, currentStock;
    private String name;
    
    // Constructor    
    public Ingredient() {
        this.amount = 0;
    }
    
    // Getters    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAmount(){
        return amount;
    }
    
    // Setters    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    
}
