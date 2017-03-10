package edu.avans.kitchen.domain;


import edu.avans.kitchen.domain.Availability;
import edu.avans.kitchen.domain.PartOfDay;
/**
 *
 * @author Bram
 */
public class Employee {
    
    private int employeeId;
    private String employeeName;
    private Availability presence;
    private PartOfDay partOfDay;

    //Constructor
    public Employee(int employeeId, String employeeName, Availability presence, PartOfDay partOfDay) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.presence = presence;
        this.partOfDay = partOfDay;
    }
    
    //Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Availability getPresence() {
        return presence;
    }

    public PartOfDay getPartOfDay() {
        return partOfDay;
    }

    //Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPresence(Availability presence) {
        this.presence = presence;
    }

    public void setPartOfDay(PartOfDay partOfDay) {
        this.partOfDay = partOfDay;
    }
    
    
    
}
