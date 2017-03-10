package edu.avans.kitchen.domain;

/**
 *
 * @author Bram
 */
public enum Status {
    PLACED("Placed"),
    ACCEPTED("Accepted"),
    READY("Ready"),
    DENIED("Denied");
    
    private String state;
    
    Status(String state) {
        this.state = state;
    }
}
