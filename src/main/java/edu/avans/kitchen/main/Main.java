package edu.avans.kitchen.main;

import edu.avans.kitchen.presentation.GUI;
import javax.swing.JFrame;
import edu.avans.kitchen.businesslogic.DishManager;
import edu.avans.kitchen.businesslogic.LoginManager;
import edu.avans.kitchen.businesslogic.OrderManager;

public class Main {
    
    private static final OrderManager OM = new OrderManager();
    private static final DishManager DM = new DishManager();
    
    //Private constructor
    private Main(){
        
    }

    public static void main(String[] args) {       
        GUI gui = new GUI(OM,DM);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle( "Deelsysteem Keuken" );
        gui.setSize( 1500, 800 );

    }
}