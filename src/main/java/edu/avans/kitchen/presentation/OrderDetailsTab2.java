/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.presentation;

import edu.avans.kitchen.businesslogic.OrderManager;
import edu.avans.kitchen.domain.Order;
import edu.avans.kitchen.businesslogic.EmployeeManager;
import edu.avans.kitchen.businesslogic.DishManager;
import edu.avans.kitchen.domain.Status;
import edu.avans.kitchen.domain.Dish;
import edu.avans.kitchen.domain.Employee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

/**
 *
 * @author Bram
 */
public class OrderDetailsTab2 extends JPanel {
    
    //Attributen
    private JTable tableOrderDetails, tablePlanning;
    private String[][] mealDetails, ingredientDetails, planningDetails, employeeDetails;
    private JButton acceptOrderButton, readyOrderButton, denyOrderButton;
    private int longestTime;
    private String endTime;
    private long endTimeMillis;
    private OrderManager om;
    private OrderTab ordertab;
    private Order order;
    private static final int TIMER_ORDER = 10000;
    
    //Tabel instellingen
    private static final String[] D_COL_NAMES = { "Gerecht", "Aantal", "Bereidingstijd" };
    private static final String[] P_COL_NAMES = { "Gerecht", "Medewerker", "Begintijd", "Eindtijd"};
    private static final int D_COL_LENGTH = D_COL_NAMES.length + 1;
    private static final int P_COL_LENGTH = P_COL_NAMES.length;
    private static final Dimension MIN_DIM = new Dimension(250, 50);
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 0, 5);
    private static final Dimension MAX_DIM = new Dimension(Frame.MAXIMIZED_VERT,Frame.MAXIMIZED_HORIZ);
    private static final double ORW = 0.5d;
    private static final double SRW = 0.8d;
    
    //Constructor
    public OrderDetailsTab2(GUI gui, OrderManager om) {
        super(new BorderLayout());
        
        //this.ingredients = new ArrayList<>();
        this.mealDetails = new String[D_COL_LENGTH][D_COL_LENGTH];
        //this.ingredientDetails = new String[I_COL_LENGTH][I_COL_LENGTH];
        this.planningDetails = new String[P_COL_LENGTH][P_COL_LENGTH];
        
        //De tabel van de order details
        tableOrderDetails = new JTable(mealDetails, D_COL_NAMES);
        ListSelectionModel tableDetailsSM = tableOrderDetails.getSelectionModel();
        tableOrderDetails.setSelectionModel(tableDetailsSM);
        JScrollPane tableDetailsPane = new JScrollPane(tableOrderDetails);
        tableDetailsSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //de tabel van de planning
        tablePlanning = new JTable(new NoEditTableModel(planningDetails, P_COL_NAMES));
        ListSelectionModel tablePlanningSM = tablePlanning.getSelectionModel();
        tablePlanning.setSelectionModel(tablePlanningSM);
        JScrollPane tablePlanningPane = new JScrollPane(tablePlanning);
        tablePlanningSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Do the layout.
        JSplitPane orderSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(orderSplitPane, BorderLayout.CENTER);
        orderSplitPane.setResizeWeight(ORW);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(SRW);
        
        //Left half of the panel
        JPanel orderHalf = new JPanel();
        orderHalf.setLayout(new BoxLayout(orderHalf, BoxLayout.LINE_AXIS));
        JPanel acceptedContainer = new JPanel(new GridLayout(1,1));
        acceptedContainer.setBorder(BorderFactory.createTitledBorder("Bestelling details"));
        acceptedContainer.add(tableDetailsPane);
        orderHalf.setBorder(BORDER);
        orderHalf.add(acceptedContainer);
        orderHalf.setMaximumSize(MAX_DIM);
        orderHalf.setMinimumSize(MIN_DIM);
        
        //Right half of the panel
        JPanel planningHalf = new JPanel();
        planningHalf.setLayout(new BoxLayout(planningHalf, BoxLayout.LINE_AXIS));
        JPanel placedContainer = new JPanel(new GridLayout(1,1));
        placedContainer.setBorder(BorderFactory.createTitledBorder("Planning"));
        placedContainer.add(tablePlanningPane);
        planningHalf.setBorder(BORDER);
        planningHalf.add(placedContainer);
        planningHalf.setMaximumSize(MAX_DIM);
        planningHalf.setMinimumSize(MIN_DIM);
        
        //Button pane
        JPanel buttonHalf = new JPanel();
        buttonHalf.setLayout(new BoxLayout(buttonHalf, BoxLayout.LINE_AXIS));
        JPanel buttonContainer = new JPanel(new GridLayout(1,1));
        buttonContainer.setBorder(BorderFactory.createTitledBorder(""));
        
        JButton showOrderButton = new JButton("Bestellingen Overzicht");
        acceptOrderButton = new JButton("Bestelling accepteren");
        readyOrderButton = new JButton("Meld bestelling gereed");
        denyOrderButton = new JButton("Weiger bestelling");
        buttonContainer.add(showOrderButton);
        buttonContainer.add(acceptOrderButton);
        buttonContainer.add(readyOrderButton);
        buttonContainer.add(denyOrderButton);
        
        buttonHalf.setBorder(BORDER);
        buttonHalf.add(buttonContainer);
        buttonHalf.setMaximumSize(MAX_DIM);
        buttonHalf.setMinimumSize(MIN_DIM);
        
        orderSplitPane.add(orderHalf);
        orderSplitPane.add(planningHalf);
        orderSplitPane.setDividerSize(0);
        
        splitPane.add(orderSplitPane);
        splitPane.add(buttonHalf);
        splitPane.setDividerSize(0);
    }
    
        //Methods
//    public void doAmortIngredients(OrderManager om){
//        for(Ingredient ing : ingredients){
//            om.amortIngredient(ing);
//        }
//    }
    
//    public void checkIngredients() throws StockException{
//        //Check if there are enough ingredients
//        try {
//            for(int i = 0; i < ingredients.size(); i++){
//                if(Integer.valueOf(ingredientDetails[i][1]) > Integer.valueOf(ingredientDetails[i][2])){
//                    throw new StockException();
//                }
//            }
//        } catch(StockException se){
//            throw se;
//        }
//    }
    
//    public void checkLinks() throws EmployeeLinkException {
//        try {
//            //Check if meals are linked to employees
//            for(int i = 0; i < order.getMeals().size(); i++){
//                if("Kies..".equals(tablePlanning.getValueAt(i, 1))){
//                    throw new EmployeeLinkException();
//                }
//            }
//        } catch(EmployeeLinkException ele){
//            throw ele;
//        }
//    }
    
    public void setOrder(Order o, EmployeeManager pm){
        this.order = o;
//        setIngredients();
//        ingredientDetails = new String[ingredients.size()][I_COL_LENGTH];
        loopMeals();
//        loopEmployees(pm);
        int j=0;
//        for(Ingredient ingredient : ingredients){
//            ingredientDetails[j][0] = ingredient.getName();
//            ingredientDetails[j][1] = Integer.toString(ingredient.getAmount());
//            ingredientDetails[j][2] = Integer.toString(ingredient.getCurrentStock());
//            j++;
//        }
        if(o.getStatus() == Status.PLACED){
            readyOrderButton.setEnabled(false);
            acceptOrderButton.setEnabled(true);
        } else if(o.getStatus() == Status.ACCEPTED){
            acceptOrderButton.setEnabled(false);
            denyOrderButton.setEnabled(false);
            readyOrderButton.setEnabled(true);
        }
        refillDetailTables();
//        if(order.getStatus() == Status.PLACED){
//            setupEmployeeColumn(pm);
//        }
    }
    
    public void loopMeals(){
        mealDetails = new String[order.getMeals().size()][D_COL_LENGTH];
        planningDetails = new String[order.getMeals().size()][P_COL_LENGTH];
        int i = 0;
        endTime = calculateEndTime();
        for(Dish dish : order.getMeals()){
            //Fill mealDetails
            mealDetails[i][0] = dish.getDishName();
            mealDetails[i][1] = Integer.toString(dish.getAmount());
            mealDetails[i][2] = toMinutes(dish.getCookingTime());
            mealDetails[i][3] = Integer.toString(dish.getDishId());
            //Fill planningDetails
            if(order.getStatus()==Status.PLACED){
                planningDetails[i][0] = dish.getDishName();
                planningDetails[i][1] = "Kies..";
                planningDetails[i][2] = calculateStartTime(dish.getCookingTime());
                planningDetails[i][3] = endTime;
            } else {
                planningDetails[i][0] = dish.getDishName();
                planningDetails[i][1] = Integer.toString(dish.getEmployeeId());
                planningDetails[i][2] = calculateStartTime(dish.getCookingTime());
                planningDetails[i][3] = toHMS(order.getEndTime());
            }         
            i++;
        }
    }
    
//    public void loopEmployees(EmployeeManager pm){
//        employeeDetails = new String[pm.getPresentEmployees().size()][E_COL_LENGTH];
//        int k = 0;
//        for(Employee e : pm.getPresentEmployees()){
//            employeeDetails[k][0] = Integer.toString(e.getEmployeeId());
//            employeeDetails[k][1] = e.getEmployeeName();
//            employeeDetails[k][2] = e.getDaypart().getDaypart();
//            k++;
//        }
//    }
    
//    public void setupEmployeeColumn(EmployeeManager pm){
//        JComboBox<String> combo = new JComboBox<>();
//        for (Employee e : pm.getPresentEmployees()){
//            combo.addItem(Integer.toString(e.getEmployeeId()));            
//        }
//        TableColumn col = tablePlanning.getColumnModel().getColumn(1);
//        col.setCellEditor(new DefaultCellEditor(combo));      
//    }   
    
    public String toHMS(long etm){
        String nH = Long.toString(((etm/3600000)%24)+2);
        String nM = Long.toString((etm/60000)%60);
        String nS = Long.toString((etm/1000)%60);
        if((nH).length() == 1){
            nH = "0" + nH;
        }
        if((nM).length() == 1){
            nM = "0" + nM;
        }
        if((nS).length() == 1){
            nS = "0" + nS;
        }
        return nH + ":" + nM + ":" + nS;
    }
    
    public String calculateStartTime(int cTime){
        if(order.getStatus() == Status.ACCEPTED){
            endTimeMillis = order.getEndTime() - (cTime * 1000);
        } else {
            endTimeMillis = System.currentTimeMillis() + ((longestTime - cTime) * 1000);
        }
        return toHMS(endTimeMillis);
    }
    
    public String calculateEndTime(){
        longestTime = 0;
        for(Dish dish : order.getMeals()){
            if(dish.getCookingTime() > longestTime){
                longestTime = dish.getCookingTime();
            }
        }
        long nowMillis = System.currentTimeMillis() + (longestTime * 1000);        
        return toHMS(nowMillis);
    }
    
    public void refillDetailTables(){
        //Meal details table
        NoEditTableModel dtm = new NoEditTableModel(mealDetails, D_COL_NAMES);
        tableOrderDetails.setModel(dtm);
        
        //Ingredient details table
        //NoEditTableModel dtm2 = new NoEditTableModel(ingredientDetails, I_COL_NAMES);
        //tableIngredients.setModel(dtm2);
        
        //Planning details table
        if(order.getStatus() == Status.PLACED){
            NoEditTableModel dtm3 = new NoEditTableModel(planningDetails, P_COL_NAMES, "placed");
            tablePlanning.setModel(dtm3);
        } else {
            NoEditTableModel dtm3 = new NoEditTableModel(planningDetails, P_COL_NAMES);
            tablePlanning.setModel(dtm3);
        }
        //Employee details table
//        NoEditTableModel dtm4 = new NoEditTableModel(employeeDetails, E_COL_NAMES);
//        tableEmployees.setModel(dtm4);
    }
    
//    public void setIngredients(){
//        ingredients = new ArrayList<>();
//        for(Meal m : order.getMeals()){
//            for(Ingredient in : m.getIngredients()){
//                boolean check = false;
//                for(Ingredient ing : ingredients){
//                    if(in.getName().equals(ing.getName())){
//                        ing.addAmount(in.getAmount() * m.getAmount());
//                        check = true;
//                        break;
//                    }
//                }
//                if(!check){
//                    Ingredient tempIn = in;
//                    tempIn.addAmount(in.getAmount()*(m.getAmount()-1));
//                    ingredients.add(tempIn);
//                }
//            }
//        }
//    }
    
    public String toMinutes(int sec){
        int min = sec * 1000;
        String nM = Integer.toString((min/60000)%60);
        String nS = Integer.toString((min/1000)%60);
        if((nM).length() == 1){
            nM = "0" + nM;
        }
        if((nS).length() == 1){
            nS = "0" + nS;
        }
        return nM + ":" + nS;
    }
}

