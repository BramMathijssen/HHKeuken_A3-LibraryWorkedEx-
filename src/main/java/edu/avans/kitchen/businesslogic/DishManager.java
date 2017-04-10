package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.datastorage.DishDAO;
import edu.avans.kitchen.domain.Dish;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bram
 */

public class DishManager {
    //Attributen
    private List<Dish> tempDishes;
    private DishDAO dDAO;
    
    //Constructor
    public DishManager(){
        this.tempDishes = new ArrayList();
        this.dDAO = new DishDAO();
    }
    
    //Methoden
    public List<Dish> findDishes(int orderId){
        tempDishes = dDAO.getDishes(orderId);
        return tempDishes;
    }
}
