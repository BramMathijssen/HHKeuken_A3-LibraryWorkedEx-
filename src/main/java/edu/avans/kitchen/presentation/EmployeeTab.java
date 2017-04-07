
package edu.avans.kitchen.presentation;

import edu.avans.kitchen.businesslogic.EmployeeManager;
import edu.avans.kitchen.domain.Employee;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;

public class EmployeeTab extends JPanel {
    //Attributes
    private String[][] aEmployees, pEmployees;
    private JTable tablePresent, tableAbsent;
    
    //Constants used by panel
    private static final String[] COL_NAMES = { "Personeelsnummer", "Naam", "Dagdeel"};
    private static final int COL_LENGTH = COL_NAMES.length;
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 0, 5);
    private static final Dimension MAX_DIM = new Dimension(Frame.MAXIMIZED_VERT,Frame.MAXIMIZED_HORIZ);
    private static final Dimension MIN_DIM = new Dimension(250, 50);
    private static final double ORW = 0.5d;
    private static final double SRW = 0.8d;
    
    //Constructor
    public EmployeeTab(GUI gui, EmployeeManager pm) {
        super(new BorderLayout());
        
        aEmployees = fillAbsentEmployees(pm);
        pEmployees = fillPresentEmployees(pm);
 
        tablePresent = new JTable(new NoEditTableModel(pEmployees, COL_NAMES));
        tablePresent.setDragEnabled(false);
        tablePresent.setRowSelectionAllowed(true);
        
        ListSelectionModel tableAcceptedSM = tablePresent.getSelectionModel();
        tableAcceptedSM.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablePresent.setSelectionModel(tableAcceptedSM);
        
        JScrollPane tablePresentPane = new JScrollPane(tablePresent);
        
        tableAbsent = new JTable(new NoEditTableModel(aEmployees, COL_NAMES));
        tableAbsent.setDragEnabled(false);
        tableAbsent.setRowSelectionAllowed(true);
        
        ListSelectionModel tablePlacedSM = tableAbsent.getSelectionModel();
        tablePlacedSM.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tableAbsent.setSelectionModel(tablePlacedSM);
        
        JScrollPane tablePlacedPane = new JScrollPane(tableAbsent);

        //Do the layout.
        JSplitPane absentSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(absentSplitPane, BorderLayout.CENTER);
        absentSplitPane.setResizeWeight(ORW);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(SRW);
        
        //Panel with absent employees
        JPanel absentHalf = new JPanel();
        absentHalf.setLayout(new BoxLayout(absentHalf, BoxLayout.LINE_AXIS));
        JPanel absentContainer = new JPanel(new GridLayout(1,1));
        absentContainer.setBorder(BorderFactory.createTitledBorder("Afwezig personeel"));
        absentContainer.add(tablePlacedPane);
        absentHalf.setBorder(BORDER);
        absentHalf.add(absentContainer);
        absentHalf.setMaximumSize(MAX_DIM);
        absentHalf.setMinimumSize(MIN_DIM);
        
        //Panel with present employees
        JPanel presentHalf = new JPanel();
        presentHalf.setLayout(new BoxLayout(presentHalf, BoxLayout.LINE_AXIS));
        JPanel presentContainer = new JPanel(new GridLayout(1,1));
        presentContainer.setBorder(BorderFactory.createTitledBorder("Aanwezig personeel"));
        presentContainer.add(tablePresentPane);
        presentHalf.setBorder(BORDER);
        presentHalf.add(presentContainer);
        presentHalf.setMaximumSize(MAX_DIM);
        presentHalf.setMinimumSize(MIN_DIM);
        
        //Button panel
        JPanel btnHalf = new JPanel();
        btnHalf.setLayout(new BoxLayout(btnHalf, BoxLayout.LINE_AXIS));
        JPanel buttonContainer = new JPanel(new GridLayout(1,1));
        buttonContainer.setBorder(BorderFactory.createTitledBorder(""));
        
        JButton showOrdersButton = new JButton("Bestellingen Overzicht");
        showOrdersButton.setEnabled(true);
        JButton setPresentButton = new JButton("Present Melden");
        setPresentButton.setEnabled(false);
        JButton setNotPresentButton = new JButton("Absent Melden");
        setNotPresentButton.setEnabled(false);
        buttonContainer.add(showOrdersButton);
        buttonContainer.add(setPresentButton);
        buttonContainer.add(setNotPresentButton);
        
        btnHalf.setBorder(BORDER);
        btnHalf.add(buttonContainer);
        btnHalf.setMaximumSize(MAX_DIM);
        btnHalf.setMinimumSize(MIN_DIM);
        
        //Add components and set dividers
        absentSplitPane.add(absentHalf);
        absentSplitPane.add(presentHalf);
        absentSplitPane.setDividerSize(0);
        
        splitPane.add(absentSplitPane);
        splitPane.add(btnHalf);
        splitPane.setDividerSize(0);
        
       // showOrdersButton.addActionListener((ActionEvent event) -> mf.setSummaryPanel(this));
        
        setPresentButton.addActionListener((ActionEvent event) -> {
            if(tableAbsent.isRowSelected(tableAbsent.getSelectedRow())){
                String employeeId = (String) tableAbsent.getValueAt(tableAbsent.getSelectedRow(), 0);
                for(Employee em : pm.getAbsentEmployees()){
                    if(Integer.toString(em.getEmployeeId()).equals(employeeId)){
                        pm.setPresent(em);
                        Logger.getLogger(EmployeeTab.class.getName()).log(Level.INFO, "Setting employee present.");
                        //mf.setPresencePanel(this);
                        setPresentButton.setEnabled(false);
                    }
                }
            }   
        });
        
        setNotPresentButton.addActionListener((ActionEvent event) -> {
            if(tablePresent.isRowSelected(tablePresent.getSelectedRow())){
                String employeeId = (String) tablePresent.getValueAt(tablePresent.getSelectedRow(), 0);
                for(Employee em : pm.getPresentEmployees()){
                    if(Integer.toString(em.getEmployeeId()).equals(employeeId)){
                        pm.setNotPresent(em);
                        Logger.getLogger(EmployeeTab.class.getName()).log(Level.INFO, "Setting employee absent.");
                        //mf.setPresencePanel(this);
                        setNotPresentButton.setEnabled(false);
                    }
                }
            }          
        });
        
        tableAbsent.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            setPresentButton.setEnabled(true);
            setNotPresentButton.setEnabled(false);
            tablePresent.getSelectionModel().clearSelection();
        });
        
        tablePresent.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            setNotPresentButton.setEnabled(true);
            setPresentButton.setEnabled(false);
            tableAbsent.getSelectionModel().clearSelection();
        });
    }
        
    //Methods
    public String[][] fillAbsentEmployees(EmployeeManager pm){
        aEmployees = new String[pm.getAbsentEmployees().size()][COL_LENGTH];
        // Set present employees
        int j = 0;
        for(Employee em : pm.getAbsentEmployees()){
            aEmployees[j][0] = Integer.toString(em.getEmployeeId());
            aEmployees[j][1] = em.getEmployeeName();
            aEmployees[j][2] = em.getDaypart().getDaypart();
            j++; 
        }
        return aEmployees;
    }
       
    public String[][] fillPresentEmployees(EmployeeManager pm){
        pEmployees = new String[pm.getPresentEmployees().size()][COL_LENGTH];
         // Set absent employees
        int i = 0;
        for(Employee em : pm.getPresentEmployees()){
            pEmployees[i][0] = Integer.toString(em.getEmployeeId());
            pEmployees[i][1] = em.getEmployeeName();           
            pEmployees[i][2] = em.getDaypart().getDaypart();
            i++;
        }
        return pEmployees;
    }
    
    public void refillPresenceTables(EmployeeManager pm){
        pm.findEmployees();
        fillAbsentEmployees(pm);
        fillPresentEmployees(pm);
        NoEditTableModel dtm = new NoEditTableModel(pEmployees, COL_NAMES);
        tablePresent.setModel(dtm);
        NoEditTableModel dtm2 = new NoEditTableModel(aEmployees, COL_NAMES);
        tableAbsent.setModel(dtm2);
    }
    
   
}    
