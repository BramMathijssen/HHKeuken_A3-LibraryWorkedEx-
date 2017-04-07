package edu.avans.kitchen.datastorage;

import edu.avans.kitchen.domain.Daypart;
import edu.avans.kitchen.domain.Employee;
import edu.avans.kitchen.domain.Presence;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {    
    //Attributes
    private final DatabaseConnection dbc;
    private final Connection con;
    private static final String SQL = "SQL: ";
        
    //Constructor
    public EmployeeDAO(){
        this.dbc = new DatabaseConnection();
        this.con = dbc.getConnection();
    }
    
    //Methods
    public List<Employee> findEmployees(String dp){
        List<Employee> employees = new ArrayList<>();
        ResultSet presenceRS = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Statement st = con.createStatement();
            String query = "SELECT `daypart_employee`.`employeeid`, `employee`.`name`, `daypart_employee`.`presencestatus`, `daypart_employee`.`date`, `daypart_employee`.`dayparttype`\n" +
                "FROM `daypart_employee`\n" +
                "JOIN `employee`\n" +
                "ON `employee`.`employeeid` = `daypart_employee`.`employeeid`\n" +
                "WHERE `daypart_employee`.`date` ='" + dateFormat.format(cal.getTime()) +
                "' AND `daypart_employee`.dayparttype = '" + dp + "';";
            presenceRS = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
    
        try {
            while (presenceRS.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(presenceRS.getInt("employeeid"));
                employee.setEmployeeName(presenceRS.getString("name"));
                String presence = presenceRS.getString("presencestatus");
                if("present".equals(presence)) {
                    employee.setPresence(Presence.PRESENT);
                }else if("planned".equals(presence)) {
                 employee.setPresence(Presence.NOTPRESENT);   
                } else {
                    employee.setPresence(Presence.NOTPRESENT);
                }
                String daypart = presenceRS.getString("dayparttype");
                if("morning".equals(daypart)) {
                    employee.setDaypart(Daypart.MORNING);                    
                } else if("afternoon".equals(daypart)) {
                    employee.setDaypart(Daypart.AFTERNOON);
                }else {
                    employee.setDaypart(Daypart.EVENING);
                }
                employees.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, SQL, ex);
        }
        return employees;
    }
    
    public boolean setPresence(Employee e, Presence p) {
        boolean pres = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if(p == Presence.NOTPRESENT ){
            try {
                Statement st = con.createStatement();
                Daypart dayparttype = e.getDaypart();
                String dtostring = dayparttype.toString().toLowerCase();
                
                String query = "UPDATE `daypart_employee` SET presencestatus = 'notpresent' WHERE employeeid = " + e.getEmployeeId() + " AND dayparttype = '" + dtostring + "' AND date = '" + dateFormat.format(cal.getTime()) + "';";
                st.executeUpdate(query);
                pres = (st.executeUpdate(query) > 0);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, SQL, ex);
            } 
        }else {
            try {
                Statement st = con.createStatement();                
                Daypart dayparttype = e.getDaypart();
                String dtostring = dayparttype.toString().toLowerCase();
                
                String query = "UPDATE `daypart_employee` SET presencestatus = 'present' WHERE employeeid = " + e.getEmployeeId() + " AND dayparttype = '" + dtostring + "' AND date = '" + dateFormat.format(cal.getTime()) + "';";
                st.executeUpdate(query);
                pres = (st.executeUpdate(query) > 0);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, SQL, ex);
            }            
        }
        return pres;
    }    
}
