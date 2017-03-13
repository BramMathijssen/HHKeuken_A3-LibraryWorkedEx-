package edu.avans.kitchen.presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import edu.avans.kitchen.businesslogic.OrderManager;
import edu.avans.kitchen.domain.Order;
import edu.avans.kitchen.domain.Dish;
import java.awt.event.ActionEvent;
import edu.avans.kitchen.businesslogic.OrderManager;
/**
 *
 * @author Bram
 */
public class OrderTab extends JPanel {
    
    //References to other classes
    private Order order;
    private OrderManager om;

    //Attributen
    private JTable tableAccepted, tablePlaced;
    private String[][] acceptedOrders, placedOrders;
    private JButton acceptOrderButton,showOrderDetailsButton;
    
    //Tabel instellingen
    private static final String[] COL_NAMES = { "Tafel Nummer", "Order Nummer", "Maximale Bereidingstijd" };
    private static final int COL_LENGTH = COL_NAMES.length;
    private static final Dimension MIN_DIM = new Dimension(250, 50);
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 0, 5);
    private static final Dimension MAX_DIM = new Dimension(Frame.MAXIMIZED_VERT,Frame.MAXIMIZED_HORIZ);
    private static final double ORW = 0.5d;
    private static final double SRW = 0.8d;
    
    public OrderTab(){
        setLayout (new BorderLayout());
        
        //Methoden om de data in de tabel te laden
        fillAccepted(om);
        fillPlaced(om);
   
        //testdata om nullpointer error te voorkomen
        String[] columnNames = {"OrderID", "TafelID", "Aantal gerechten" };
        
        Object[][] data = {
            {"1", "6", "4" },
            {"2", "3" , "8"},
            {"3", "1" , "1"},
        };
        
        Object[][] data2 = {
            {"4", "2", "2" },
            {"5", "7" , "3"},
            {"6", "8" , "1"},
        };
       

        //De tabel van geplaatste orders
        tablePlaced = new JTable(data,columnNames);
    
        JScrollPane tablePlacedScroll= new JScrollPane(tablePlaced);
        
        
        //De tabel van geaccepteerde orders
        tableAccepted = new JTable(data2,columnNames);
        
        JScrollPane tableAcceptedScroll = new JScrollPane(tableAccepted);
        
        
        //De layout
        JSplitPane orderSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(orderSplitPane, BorderLayout.CENTER);
        orderSplitPane.setResizeWeight(ORW);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(SRW);
        
        //Het paneel van geplaatste orders
        JPanel placedHalf = new JPanel();
        placedHalf.setLayout(new BoxLayout(placedHalf, BoxLayout.LINE_AXIS));
        JPanel placedContainer = new JPanel(new GridLayout(1,1));
        placedContainer.setBorder(BorderFactory.createTitledBorder("Geplaatste bestellingen"));
        placedContainer.add(tablePlacedScroll);
        placedHalf.setBorder(BORDER);
        placedHalf.add(placedContainer);
        placedHalf.setMaximumSize(MAX_DIM);
        placedHalf.setMinimumSize(MIN_DIM);
        
        //Het paneel van geaccepteerde orders
        JPanel acceptedHalf = new JPanel();
        acceptedHalf.setLayout(new BoxLayout(acceptedHalf, BoxLayout.LINE_AXIS));
        JPanel acceptedContainer = new JPanel(new GridLayout(1,1));
        acceptedContainer.setBorder(BorderFactory.createTitledBorder("Geaccepteerde bestellingen"));
        acceptedContainer.add(tableAcceptedScroll);
        acceptedHalf.setBorder(BORDER);
        acceptedHalf.add(acceptedContainer);
        acceptedHalf.setMaximumSize(MAX_DIM);
        acceptedHalf.setMinimumSize(MIN_DIM);
        
        //Paneel voor de accepteer knop
        JPanel buttonHalf = new JPanel();
        buttonHalf.setLayout(new BoxLayout(buttonHalf, BoxLayout.LINE_AXIS));
        JPanel buttonContainer = new JPanel(new GridLayout(1,1));
        buttonContainer.setBorder(BorderFactory.createTitledBorder(""));
        
        acceptOrderButton = new JButton("Accepteer bestelling");
        acceptOrderButton.setEnabled(false);
        buttonContainer.add(acceptOrderButton);
        
        buttonHalf.setBorder(BORDER);
        buttonHalf.add(buttonContainer);
        buttonHalf.setMaximumSize(MAX_DIM);
        buttonHalf.setMinimumSize(MIN_DIM);
        
        //Alle losse componenten toevoegen aan het paneel
        orderSplitPane.add(placedHalf);
        orderSplitPane.add(acceptedHalf);
        orderSplitPane.setDividerSize(0);
        
        splitPane.add(orderSplitPane);
        splitPane.add(buttonHalf);
        splitPane.setDividerSize(0);
        
        //ActionListner
        acceptOrderButton.addActionListener((ActionEvent evt) -> {
           om.acceptOrder(order);
        });
        
        //Methods
        public void doRefresh(OrderManager om){
            fillAccepted(om);
            fillPlaced(om);
            refreshAccepted();
            refreshPlaced();
            resetButton();
        }
    
        public void fillAccepted(OrderManager om){
            acceptedOrders = new String[om.getAcceptedOrders().size()][COL_LENGTH];
            int j = 0;
            for(Order o : om.getAcceptedOrders()){
                acceptedOrders[j][0] = Integer.toString(o.getTableNr());
                acceptedOrders[j][1] = Integer.toString(o.getOrderId());
                acceptedOrders[j][2] = toMinutes(o.getMaxCookingTime());
                j++; 
            }
        }
    
        public void refreshAccepted(){
            NoEditTableModel dtm = new NoEditTableModel(acceptedOrders, COL_NAMES);
            tableAccepted.setModel(dtm);      
        }

        public void fillPlaced(OrderManager om){
            placedOrders = new String[om.getPlacedOrders().size()][COL_LENGTH];
            int i = 0;
            for(Order o : om.getPlacedOrders()){
                placedOrders[i][0] = Integer.toString(o.getTableNr());
                placedOrders[i][1] = Integer.toString(o.getOrderId());
                placedOrders[i][2] = toMinutes(o.getMaxCookingTime());
                i++;
            }
        }

        public void refreshPlaced(){
            NoEditTableModel dtm = new NoEditTableModel(placedOrders, COL_NAMES);
            tablePlaced.setModel(dtm);        
        }

        public void resetButton(){
            showOrderDetailsButton.setEnabled(false);
        }

        public void showAcceptedOrder(OrderManager om, DishManager mm, String acceptedID){
            for(Order o : om.getAcceptedOrders()){
                if(Integer.toString(o.getOrderId()).equals(acceptedID)){
                    for(Meal m : mm.findMeals(Integer.valueOf(acceptedID))){
                        o.addMeal(m);
                    }
                    mf.setPlanningPanel(this, o);
                }
            }
        }

        public void showPlacedOrder(OrderManager om, MealManager mm, MainFrame mf, String placedID){
            for(Order o : om.getPlacedOrders()){
                if(Integer.toString(o.getOrderId()).equals(placedID)){
                    for(Meal m : mm.findMeals(Integer.valueOf(placedID))){
                        o.addMeal(m);
                    }
                    mf.setPlanningPanel(this, o);
                }
            }
        }

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
