/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.avans.kitchen.domain.Ingredient;

/**
 *
 * @author Bram
 */
public class IngredientDAO {
    //Attributes
    private final DatabaseConnection dbc;
    private final Connection con;
    private static final String SQL = "SQL Error: ";
    
    //Constructor
    public IngredientDAO() {
        this.dbc = new DatabaseConnection();
        this.con = dbc.getConnection();
    }
    
    //Methods
    public List<Ingredient> getIngredients(int dishId) {
        ResultSet rs = null;
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = " SELECT `ingredients`.`IngredientId`, `ingredients`.`IngredientName`, `ingredients`.`CurrentStock`, `dish_ingredients`.`Quantity`\n" +
            "FROM `ingredients`\n" +
            "JOIN `dish_ingredients`\n" +
            "ON `dish_ingredients`.`DishId` = " + dishId + "\n" +
            "AND `dish_ingredients`.`IngredientId` = `ingredients`.`IngredientId`;"; 
            rs = st.executeQuery(query);
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, e);
        }

        try {
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.addAmount(rs.getInt("Quantity"));
                ingredient.setName(rs.getString("IngredientName"));
                ingredient.setCurrentStock(rs.getInt("CurrentStock"));
                ingredients.add(ingredient);
            }
        } catch (Exception e) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, SQL, e);
        }
        return ingredients;
    }
    
    public void amortIngredient(Ingredient in){
        String name = in.getName();
        int amount = in.getAmount();
        int nAmount = 0;
        try {
            Statement s = con.createStatement();
            String query = "SELECT `CurrentStock` FROM `ingredients` WHERE `IngredientName` = '" + name + "';";
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                nAmount = rs.getInt("CurrentStock") - amount;
            }            
            String query2 = "UPDATE `ingredients` SET `CurrentStock` = " + nAmount + " WHERE `IngredientName` = '" + name + "';";
            s.executeUpdate(query2);            
        } catch(Exception e){
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, SQL, e);
        }
    }
}
