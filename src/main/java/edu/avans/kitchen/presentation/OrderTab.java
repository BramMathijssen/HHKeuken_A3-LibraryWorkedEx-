package edu.avans.kitchen.presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 *
 * @author Bram
 */
public class OrderTab extends JPanel {
    
    private JTable tableAccepted, tablePlaced;
    private String[][] acceptedOrders, placedOrders;
    private JButton acceptOrderButton,showOrderDetailsButton;
    
    //private static final String[] COL_NAMES = { "Tafel Nummer", "Order Nummer", "Maximale Bereidingstijd" };
    //private static final int COL_LENGTH = COL_NAMES.length;
    private static final Dimension MIN_DIM = new Dimension(250, 50);
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 0, 5);
    private static final Dimension MAX_DIM = new Dimension(Frame.MAXIMIZED_VERT,Frame.MAXIMIZED_HORIZ);
    private static final double ORW = 0.5d;
    private static final double SRW = 0.8d;
    
    public OrderTab(){
        setLayout (new BorderLayout());
   
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

}
}
