package edu.avans.kitchen.domain;


import edu.avans.kitchen.domain.Availability;
import edu.avans.kitchen.domain.PartOfDay;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.avans.kitchen.generics.Error;
/**
 *
 * @author Bram
 */
public class Employee {
    
    private int employeeId;
    private String employeeName;
    private Presence presence;
    private Daypart daypart;

    //Constructor
    public Employee(int employeeId, Presence presence, Daypart daypart) {
        this.employeeId = employeeId;
        this.presence = presence;
        this.daypart = daypart;
    }
    
    
    public Employee() {
        
    }
    //Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Presence getPresence() {
        return presence;
    }

    public Daypart getDaypart() {
        return daypart;
    }

    //Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean setPresence(Presence presence) {
        boolean pres = false;
        if(getPresence() == presence) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, Error.PRESENCE.toString().replace("%presence%", presence.toString()));
        } else {
            this.presence = presence;
            pres = true;
        }
        return pres;
    }

    public void setDaypart(Daypart daypart) {
        this.daypart = daypart;
    }
    
    
    
}
