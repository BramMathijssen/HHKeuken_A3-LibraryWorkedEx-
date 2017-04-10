package edu.avans.kitchen.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bram
 */
public class Employee {
    
    private int employeeId;
    private String employeeName;

    //Constructor
    public Employee(int employeeId) {
        this.employeeId = employeeId;

    }
    
    //Default constructor
    public Employee() {
        
    }
    
    //Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    //Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
   
}
