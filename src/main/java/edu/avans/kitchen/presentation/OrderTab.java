package edu.avans.kitchen.presentation;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Bram
 */
public class OrderTab extends JPanel {
    
    JLabel label = new JLabel();
    JTextField txtField;
    
    public OrderTab(){
        
        setBackground(Color.GRAY);
        txtField = new JTextField (10);
        
        this.add(txtField);
    }

}
