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
import edu.avans.kitchen.domain.Ingredient;
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
            String query = " SELECT `dish`.`DishId`, `dish`.`DishName`, `dish`.`CookingTime`, `kitchenorder_dish`.`Quantity`, `kitchenorder_dish`.`EmployeeId`\n" +
            "FROM `dish`\n" +
            "JOIN `kitchenorder_dish`\n" +
            "ON `kitchenorder_dish`.`DishId` = `dish`.`DishId`\n" +
            "AND `kitchenorder_dish`.`KitchenOrderId` = " + orderId + " ORDER BY dish.CookingTime DESC;"; 
            dishRS = st.executeQuery(query);
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, "SQL Error:", e);
        }

        try {
            List<Ingredient> tempIngredient;
            while (dishRS.next()) {
                Dish dish = new Dish();
                dish.setDishId(dishRS.getInt("DishId"));
                dish.setDishName(dishRS.getString("DishName"));
                dish.setCookingTime(dishRS.getInt("CookingTime"));
                dish.setAmount(dishRS.getInt("Quantity"));
                dish.setEmployeeId(dishRS.getInt("EmployeeId"));
                dishes.add(dish);
                
                tempIngredient = iDAO.getIngredients(dishRS.getInt("DishId"));
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
