package edu.avans.kitchen.generics;

/**
 *
 * @author Bram
 */

public enum Error {
    STATUS("Status kan niet veranderd worden naar %status%, want %reason%!");

    
    private final String errorMessage;
    
    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}