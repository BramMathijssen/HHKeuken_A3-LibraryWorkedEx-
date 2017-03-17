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
import edu.avans.kitchen.businesslogic.LoginManager;
import edu.avans.kitchen.presentation.OrderTab;

/**
 *
 * @author Bram
 */
public class LoginTab extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private JTextField txtLogin;
    private JPasswordField pwdWachtwoord;
    private LoginManager loginManager;

    
    public LoginTab(GUI gui){
        
//        loginManager = new LoginManager();
//        
//       JFrame frame = new JFrame("JOptionPane showMessageDialog login");
//
////        orderManager = new OrderManager();
//
//        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//        tabbedPane.setBackground(Color.GRAY);
//        tabbedPane.setBounds(0, 0, 1500, 800);
//        getContentPane().add(tabbedPane);
//
//        JPanel Logintab = new JPanel();
//        Logintab.setBackground(Color.GRAY);
//        tabbedPane.addTab("Login", null, Logintab, null);
//        Logintab.setLayout(null);
//
//        txtLogin = new JTextField();
//        txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
//        txtLogin.setText("login");
//        txtLogin.setBounds(143, 172, 116, 30);
//        Logintab.add(txtLogin);
//        txtLogin.setColumns(10);
//
//        pwdWachtwoord = new JPasswordField();
//        pwdWachtwoord.setText("Wachtwoord");
//        pwdWachtwoord.setBounds(147, 256, 112, 22);
//        Logintab.add(pwdWachtwoord);
//
//        //Gebruikersnaam
//        JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam: ");
//        lblGebruikersnaam.setBounds(141, 143, 118, 16);
//        Logintab.add(lblGebruikersnaam);
//
//        //Wachtwoord
//        JLabel lblWachtwoord = new JLabel("Wachtwoord: ");
//        lblWachtwoord.setBounds(143, 229, 116, 16);
//        Logintab.add(lblWachtwoord);
//
//        //Overzicht met bestellingen
//        JPanel OrderTab = new JPanel();
//        OrderTab.setBackground(Color.GRAY);
//        OrderTab.setVisible(false);
//
//        //Toevoegen van een gerecht
//        JPanel Toevoegen_Gerechttab = new JPanel();
//        Toevoegen_Gerechttab.setBackground(Color.GRAY);
//        Toevoegen_Gerechttab.setLayout(null);
//        Toevoegen_Gerechttab.setVisible(false);
//
//        //Status
//        JPanel StatusTab = new JPanel();
//        StatusTab.setBackground(Color.GRAY);
//        StatusTab.setLayout(null);
//        StatusTab.setVisible(false);
//        
//
//        //Gerecht toewijzen
//        JPanel GerechtTab = new JPanel();
//        GerechtTab.setBackground(Color.GRAY);
//        GerechtTab.setLayout(null);
//        GerechtTab.setVisible(false);
//        
//        //Login 
//        JButton btnLogin = new JButton("Login");
//        btnLogin.addActionListener((ActionEvent e) -> {
//            if (loginManager.checkPassword(txtLogin.getText(), pwdWachtwoord.getText()) == false) {
//                loginManager.setLoggedIn(false);
//                JOptionPane.showMessageDialog(frame,"De inloggegevens zijn niet juist, probeer het opnieuw.");
//            } else {
//                loginManager.setLoggedIn(true);
//                tabbedPane.addTab("Bestellingen", ordertab);
//                tabbedPane.addTab("Toevoegen Gerecht", null, Toevoegen_Gerechttab, null);
//                tabbedPane.addTab("Status", null, StatusTab, null);
//                tabbedPane.addTab("Gerecht toewijzen", null, GerechtTab, null);
//                tabbedPane.setSelectedIndex(1);
//            }
//        });
//        btnLogin.setBounds(149, 317, 97, 25);
//        Logintab.add(btnLogin);
//    }
    
}
}
