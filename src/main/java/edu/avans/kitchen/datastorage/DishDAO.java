package edu.avans.kitchen.datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.avans.kitchen.datastorage.DatabaseConnection;
import edu.avans.kitchen.datastorage.IngredientDAO;
import edu.avans.kitchen.businesslogic.DishManager;
import edu.avans.kitchen.domain.Dish;

public class DishDAO {
    //Variabelen
    private final DatabaseConnection dbc;
    private final Connection con;
    private final IngredientDAO iDAO;

    //Constructor
    public DishDAO() {
        this.dbc = new DatabaseConnection();
        this.con = dbc.getConnection();
        this.iDAO = new IngredientDAO();
    }

    //Methods
    public List<Dish> getDishes(int orderId) {
        ResultSet dishRS = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = " SELECT `meal`.`mealid`, `meal`.`name`, `meal`.`cookingtime`, `mealorder_meal`.`amount`, `mealorder_meal`.`employeeid`\n" +
            "FROM `meal`\n" +
            "JOIN `mealorder_meal`\n" +
            "ON `mealorder_meal`.`mealid` = `meal`.`mealid`\n" +
            "AND `mealorder_meal`.`mealorderid` = " + orderId + " ORDER BY meal.cookingtime DESC;"; 
            dishRS = st.executeQuery(query);
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, "SQL Error:", e);
        }

        try {
            List<Ingredient> tempIngredient;
            while (dishRS.next()) {
                Dish dish = new Dish();
                dish.setMealId(dishRS.getInt("mealid"));
                dish.setDishName(dishRS.getString("name"));
                dish.setCookingTime(dishRS.getInt("cookingtime"));
                dish.setAmount(dishRS.getInt("amount"));
                dish.setEmployeeId(dishRS.getInt("employeeid"));
                dishes.add(dish);
                
                tempIngredient = iDAO.getIngredients(dishRS.getInt("mealid"));
                for(Ingredient ing : tempIngredient){
                    dish.addIngredient(ing);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, "SQL Error:", e);
        }
        return dishes;
    }
}
