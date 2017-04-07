/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.kitchen.domain;

/**
 *
 * @author Bram
 */
public enum Presence {
    //Enum types
    NOTPRESENT("Absent"),
    PRESENT("Present");
    
    //Attributes
    private String presenceType;
    
    //Constructor
    Presence(String presenceType) {
        this.presenceType = presenceType;
    }
    
    // Getters
    public String getPresence(){
        return presenceType;
    }
    
    // Setters
    public void setPresence(String presenceType){
        this.presenceType = presenceType;
    }
    
}
