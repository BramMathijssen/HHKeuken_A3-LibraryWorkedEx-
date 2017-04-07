
package edu.avans.kitchen.domain;

/**
 *
 * @author Bram
 */
public enum PartOfDay {
    MORNING("OCHTEND"),
    AFTERNOON("MIDDAG"),
    EVENING("AVOND");
   
    //Attributes
    private String daypartString;
    
    //Methods
    PartOfDay(String daypart) {
        this.daypartString = daypart;
    }
        
    //Getters
    public String getDaypart(){
        return daypartString;
    }
    
    //Setters
    public void setDaypart(String daypart){
        this.daypartString = daypart;
    }  
}
