
package edu.avans.kitchen.businesslogic;

import edu.avans.kitchen.datastorage.EmployeeDAO;
import edu.avans.kitchen.domain.Daypart;
import edu.avans.kitchen.domain.Employee;
import edu.avans.kitchen.domain.Presence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    //References to other classes.
    private final EmployeeDAO presenceDAO;
    
    //Attributes
    private List<Employee> presentEmployees, absentEmployees;
    private static final int MOR = 6, AFT = 12, EVE = 17;
    
    //Constructor
    public EmployeeManager() {
        this.presenceDAO = new EmployeeDAO();
        presentEmployees = new ArrayList<>();
        absentEmployees = new ArrayList<>();
        findEmployees();
    }
    
    //Getters
    public List<Employee> getPresentEmployees() {
        return presentEmployees;
    }
    
    public List<Employee> getAbsentEmployees() {
        return absentEmployees;
    }
    
    //Methods
    public List<Employee> findEmployees() {
        List<Employee> employees;
        switch (getCurrentDaypart()) {
            case MORNING:
                employees = presenceDAO.findEmployees("morning");
                break;
            case AFTERNOON:
                employees = presenceDAO.findEmployees("afternoon");
                break;
            default:
                employees = presenceDAO.findEmployees("evening");
                break;
        }
        presentEmployees = new ArrayList<>();
        absentEmployees = new ArrayList<>();
        for(Employee e : employees) {
            if(e.getDaypart() == getCurrentDaypart() && e.getPresence() == Presence.PRESENT){
                presentEmployees.add(e);
            } else {
               absentEmployees.add(e);
            }
        }
        return employees;
    }
    
    public Daypart getCurrentDaypart() {
        LocalDateTime time = LocalDateTime.now();
        if(time.getHour() >= AFT && time.getHour() < EVE) {
            return Daypart.AFTERNOON;
        } else if(time.getHour() >= EVE || time.getHour() < MOR) {
            return Daypart.EVENING;
        } else {
            return Daypart.MORNING;
        }
    }
    
    public void setPresent(Employee e) {
        if(e.setPresence(Presence.PRESENT)){
        presenceDAO.setPresence(e, Presence.PRESENT);
        e.setPresence(Presence.PRESENT);
        }
    }

    public void setNotPresent(Employee e) {
        if(e.setPresence(Presence.NOTPRESENT)){
            presenceDAO.setPresence(e, Presence.NOTPRESENT); 
            e.setPresence(Presence.NOTPRESENT);
        }
    }
    
    
    
}
