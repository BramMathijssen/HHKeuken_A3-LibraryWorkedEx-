package edu.avans.kitchen.generics;

public enum Error {
    STATUS("Status kan niet veranderd worden naar %status%, want %reason%!"),
    PRESENCE("Aanwezigheid kan niet veranderd worden naar %presence%, want hij is al %presence%");
    
    private final String errorMessage;
    
    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}