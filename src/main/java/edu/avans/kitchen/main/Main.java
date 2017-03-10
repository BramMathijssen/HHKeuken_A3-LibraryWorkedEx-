package edu.avans.kitchen.main;

import edu.avans.kitchen.presentation.GUI;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
                  
        GUI gui = new GUI();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle( "Deelsysteem Keuken" );
        gui.setSize( 1500, 800 );

    }
}