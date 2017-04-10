package edu.avans.kitchen.presentation;

import edu.avans.kitchen.businesslogic.DishManager;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import edu.avans.kitchen.businesslogic.LoginManager;
import edu.avans.kitchen.businesslogic.OrderManager;
import edu.avans.kitchen.domain.Dish;
import edu.avans.kitchen.domain.Order;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Bram
 */

public class GUI extends JFrame {

    private static final int TIMER_ORDER = 10000;

    private static final long serialVersionUID = 1L;
    private JTextField txtLogin;
    private JPasswordField pwdWachtwoord;
    private LoginManager loginManager;
    private final OrderManager om;
    private final OrderTab ordertab;
    private OrderDetailsTab orderdetailstab;
    public JTabbedPane tabbedPane;

    public GUI(OrderManager om, DishManager dm) {
        this.om = om;
         
        ordertab = new OrderTab(this, om, dm);
        orderdetailstab = new OrderDetailsTab(this, om);
        loginManager = new LoginManager();
        
        //Methode die de bestellingen in de tabel ververst.
        refresh();
        
       
        JFrame frame = new JFrame("JOptionPane showMessageDialog login");


        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setBounds(0, 0, 1500, 800);
        getContentPane().add(tabbedPane);

        JPanel Logintab = new JPanel();
        Logintab.setBackground(Color.GRAY);
        tabbedPane.addTab("Login", null, Logintab, null);
        Logintab.setLayout(null);

        txtLogin = new JTextField();
        txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtLogin.setText("login");
        txtLogin.setBounds(143, 172, 116, 30);
        Logintab.add(txtLogin);
        txtLogin.setColumns(10);

        pwdWachtwoord = new JPasswordField();
        pwdWachtwoord.setText("Wachtwoord");
        pwdWachtwoord.setBounds(147, 256, 112, 22);
        Logintab.add(pwdWachtwoord);

        //Gebruikersnaam
        JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam: ");
        lblGebruikersnaam.setBounds(141, 143, 118, 16);
        Logintab.add(lblGebruikersnaam);

        //Wachtwoord
        JLabel lblWachtwoord = new JLabel("Wachtwoord: ");
        lblWachtwoord.setBounds(143, 229, 116, 16);
        Logintab.add(lblWachtwoord);

        //Overzicht met bestellingen
        JPanel OrderTab = new JPanel();
        OrderTab.setBackground(Color.GRAY);
        OrderTab.setVisible(false);

        //Toevoegen van een gerecht
        JPanel OrderDetailsTab = new JPanel();
        OrderDetailsTab.setBackground(Color.GRAY);
        OrderDetailsTab.setLayout(null);
        OrderDetailsTab.setVisible(false);
       
        //Login 
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener((ActionEvent e) -> {
            if (loginManager.checkPassword(txtLogin.getText(), pwdWachtwoord.getText()) == false) {
                loginManager.setLoggedIn(false);
                JOptionPane.showMessageDialog(frame,"De inloggegevens zijn niet juist, probeer het opnieuw.");
            } else {
                loginManager.setLoggedIn(true);
                tabbedPane.addTab("Bestellingen", ordertab);
                tabbedPane.addTab("Bestelling Details", orderdetailstab);

                tabbedPane.setSelectedIndex(1);
            }
        });
        btnLogin.setBounds(149, 317, 97, 25);
        Logintab.add(btnLogin);

        //Maakt het alleen mogelijk om de andere panels (tabbladen) te gebruiken, als een gebruiker correct is ingelogd.
        Logintab.setEnabled(true);
        OrderTab.setEnabled(false);
        OrderDetailsTab.setEnabled(false);

    }
    
        //Methode om van buiten de gui klassen en tab in te stellen(werkt niet(Nullpointer exception bij aanroep methode))
        public void setOrderTab(int i){
            tabbedPane.setSelectedIndex(i);
        }

        public void setOrderDetailsTab(JPanel panel, Order o){
            //tabbedPane.setSelectedIndex(2);
            orderdetailstab.setOrder(o);
        }


        private void refresh(){
            //Declareer een instantie van de Timer methode    
            Timer t = new Timer(0, (ActionEvent e) -> {
                Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Refreshing Orders...");
                //Ordermanager refresht de lijst
                om.findPlacedOrders();
                om.findAcceptedOrders();

                ordertab.doRefresh(om);
            });
            t.setDelay(TIMER_ORDER);
            t.start();
        }
    }

