package edu.avans.kitchen.presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import edu.avans.kitchen.businesslogic.OrderManager;
import edu.avans.kitchen.domain.Order;
import edu.avans.kitchen.domain.Dish;
import java.awt.event.ActionEvent;
import edu.avans.kitchen.businesslogic.OrderManager;
import edu.avans.kitchen.businesslogic.DishManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.avans.kitchen.presentation.GUI;
/**
 *
 * @author Bram
 */
public class OrderTab extends JPanel {
    
    //Attributen
    private JTable tableAccepted, tablePlaced;
    private String[][] aOrders, pOrders;
    private JButton acceptOrderButton,showOrderDetailsButton;
    private OrderManager om;
    private OrderTab ordertab;
    private Order order;
    private GUI gui;
    private static final int TIMER_ORDER = 10000;
    
    //Tabel instellingen
    private static final String[] COL_NAMES = { "Tafel Nummer", "Order Nummer", "Maximale Bereidingstijd" };
    private static final int COL_LENGTH = COL_NAMES.length;
    private static final Dimension MIN_DIM = new Dimension(250, 50);
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 0, 5);
    private static final Dimension MAX_DIM = new Dimension(Frame.MAXIMIZED_VERT,Frame.MAXIMIZED_HORIZ);
    private static final double ORW = 0.5d;
    private static final double SRW = 0.8d;
    
    public OrderTab(GUI gui, OrderManager om, DishManager dm) {
        super(new BorderLayout());
        

        
        //Methoden om de data in de tabel te laden
        
        
        fillAccepted(om);
        fillPlaced(om);
        
   
        //testdata om nullpointer error te voorkomen
//        String[] columnNames = {"OrderID", "TafelID", "Aantal gerechten" };
//        
//        Object[][] data = {
//            {"1", "6", "4" },
//            {"2", "3" , "8"},
//            {"3", "1" , "1"},
//        };
//        
//        Object[][] data2 = {
//            {"4", "2", "2" },
//            {"5", "7" , "3"},
//            {"6", "8" , "1"},
//        };
       

        //De tabel van geplaatste orders
        tableAccepted = new JTable(new NoEditTableModel(aOrders, COL_NAMES));
        tableAccepted.setDragEnabled(false);
        tableAccepted.setRowSelectionAllowed(true);
        
        ListSelectionModel tableAcceptedSM = tableAccepted.getSelectionModel();
        tableAcceptedSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAccepted.setSelectionModel(tableAcceptedSM);
        
        JScrollPane tableAcceptedScroll = new JScrollPane(tableAccepted);
        
        
        //Instellen van de placed orders tabel
        tablePlaced = new JTable(new NoEditTableModel(pOrders, COL_NAMES));
        tablePlaced.setDragEnabled(false);
        tablePlaced.setRowSelectionAllowed(true);
        
        ListSelectionModel tablePlacedSM = tablePlaced.getSelectionModel();
        tablePlacedSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePlaced.setSelectionModel(tablePlacedSM);
        
        JScrollPane tablePlacedScroll = new JScrollPane(tablePlaced);
        
        
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
        showOrderDetailsButton = new JButton("Inhoud bestelling bekijken");
        showOrderDetailsButton.setEnabled(false);
        buttonContainer.add(acceptOrderButton);
        buttonContainer.add(showOrderDetailsButton);
        
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
        
        
        //ActionListners
//        acceptOrderButton.addActionListener((ActionEvent event) -> {
//            tablePlaced.isRowSelected(tablePlaced.getSelectedRow());
//                String placedID = (String) tablePlaced.getValueAt(tablePlaced.getSelectedRow(), 1);
//                 om.acceptOrder(order, placedID);  
//                      
//        });

//        acceptOrderButton.addActionListener((ActionEvent event) -> {
//            om.AcceptOrder2(order);
//                      
//        });
        


        acceptOrderButton.addActionListener((ActionEvent event) -> {
            if(tablePlaced.isRowSelected(tablePlaced.getSelectedRow())){
                String orderId = (String) tablePlaced.getValueAt(tablePlaced.getSelectedRow(), 0);
                for(Order o : om.getPlacedOrders()){
                    if(Integer.toString(o.getOrderId()).equals(orderId)){
                        om.acceptOrder(o);

                    }
                }
            }   
        });
        
        tablePlaced.getSelectionModel().addListSelectionListener(evt -> {
            acceptOrderButton.setEnabled(true);
            tableAccepted.getSelectionModel().clearSelection();
        });
          
//        acceptOrderButton.addActionListener((ActionEvent evt) -> {
//            om.acceptOrder(order);            
//        });
        
        //ActionListner
//        acceptOrderButton.addActionListener((ActionEvent evt) -> {
//           om.acceptOrder(order);
//        });
        
//        tablePlaced.getSelectionModel().addListSelectionListener(evt -> {
//            showOrderDetailsButton.setEnabled(true);
//            tableAccepted.getSelectionModel().clearSelection();
//        });
//        
//        tableAccepted.getSelectionModel().addListSelectionListener(evt -> {
//            showOrderDetailsButton.setEnabled(true);
//            tablePlaced.getSelectionModel().clearSelection();
//        });   

//        //Actionlisteners NUMMER 2 kan verwijderd worden
//        showOrderDetailsButton.addActionListener((ActionEvent event) -> {
//            if(tableAccepted.isRowSelected(tableAccepted.getSelectedRow())){
//                String acceptedID = (String) tableAccepted.getValueAt(tableAccepted.getSelectedRow(), 1);
//                showAcceptedOrder(om, dm, gui, acceptedID);
//            } else {
//                String placedID = (String) tablePlaced.getValueAt(tablePlaced.getSelectedRow(), 1);
//                showPlacedOrder(om, dm, gui, placedID);
//            }            
//        });

        //Actionlisteners
        showOrderDetailsButton.addActionListener((ActionEvent event) -> {
            if(tableAccepted.isRowSelected(tableAccepted.getSelectedRow())){
                String acceptedID = (String) tableAccepted.getValueAt(tableAccepted.getSelectedRow(), 1);
                showAcceptedOrder(om, dm, gui, acceptedID);
            } else {
                String placedID = (String) tablePlaced.getValueAt(tablePlaced.getSelectedRow(), 1);
                showPlacedOrder(om, dm, gui, placedID);
            }            
        });
        
        
        
 
        
        tablePlaced.getSelectionModel().addListSelectionListener(evt -> {
            showOrderDetailsButton.setEnabled(true);
            tableAccepted.getSelectionModel().clearSelection();
        });
        
        tableAccepted.getSelectionModel().addListSelectionListener(evt -> {
            showOrderDetailsButton.setEnabled(true);
            tablePlaced.getSelectionModel().clearSelection();
        });     
        
    
    }
    
        
        //Methods
    
        public void refresh(){
        //Declare and instantiate Timer       
        Timer t = new Timer(0, (ActionEvent e) -> {
            Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Refreshing Orders...");
            //Ordermanager refreshes the lists
            om.findPlacedOrders();
            om.findAcceptedOrders();
            //Summarypanel refresh the arrays and replace the table model
            ordertab.doRefresh(om);
        });
        t.setDelay(TIMER_ORDER);
        t.start();
    }
        public void doRefresh(OrderManager om){
            fillAccepted(om);
            fillPlaced(om);
            refreshAccepted();
            refreshPlaced();
            resetButton();
        }
    
        public void fillAccepted(OrderManager om){
            aOrders = new String[om.getAcceptedOrders().size()][COL_LENGTH];
            int j = 0;
            for(Order o : om.getAcceptedOrders()){
                aOrders[j][0] = Integer.toString(o.getTableNr());
                aOrders[j][1] = Integer.toString(o.getOrderId());
                aOrders[j][2] = toMinutes(o.getMaxCookingTime());
                j++; 
            }
        }
    
        public void refreshAccepted(){
            NoEditTableModel dtm = new NoEditTableModel(aOrders, COL_NAMES);
            tableAccepted.setModel(dtm);      
        }

        public void fillPlaced(OrderManager om){
            pOrders = new String[om.getPlacedOrders().size()][COL_LENGTH];
            int i = 0;
            for(Order o : om.getPlacedOrders()){
                pOrders[i][0] = Integer.toString(o.getTableNr());
                pOrders[i][1] = Integer.toString(o.getOrderId());
                pOrders[i][2] = toMinutes(o.getMaxCookingTime());
                i++;
            }
        }

        public void refreshPlaced(){
            NoEditTableModel dtm = new NoEditTableModel(pOrders, COL_NAMES);
            tablePlaced.setModel(dtm);        
        }

        public void resetButton(){
            showOrderDetailsButton.setEnabled(false);
        }

        public void showAcceptedOrder(OrderManager om, DishManager dm, GUI gui, String acceptedID){
            for(Order o : om.getAcceptedOrders()){
                if(Integer.toString(o.getOrderId()).equals(acceptedID)){
                    for(Dish d : dm.findDishes(Integer.valueOf(acceptedID))){
                        o.addDish(d);
                    }
                gui.setOrderDetailsTab(this, o);
                }
            }
        }

        public void showPlacedOrder(OrderManager om, DishManager dm, GUI gui, String placedID){
            for(Order o : om.getPlacedOrders()){
                if(Integer.toString(o.getOrderId()).equals(placedID)){
                    for(Dish d : dm.findDishes(Integer.valueOf(placedID))){
                        o.addDish(d);
                    }
                gui.setOrderDetailsTab(this, o);
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
