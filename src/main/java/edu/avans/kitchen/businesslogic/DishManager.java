package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.datastorage.DishDAO;
import edu.avans.kitchen.domain.Dish;
import java.util.ArrayList;
import java.util.List;

public class DishManager {
    //Attributes
    private List<Dish> tempDishes;
    private DishDAO dDAO;
    
    //Constructor
    public DishManager(){
        this.tempDishes = new ArrayList();
        this.dDAO = new DishDAO();
    }
    
    //Methods
    public List<Dish> findDishes(int orderid){
        tempDishes = dDAO.getDishes(orderid);
        return tempDishes;
    }
}
