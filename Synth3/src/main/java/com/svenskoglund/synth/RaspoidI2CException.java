package com.svenskoglund.synth;
public class RaspoidI2CException extends RaspoidException {
    
    /**
     * Constructs a new I²C exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause to be retrieved later
     */
    public RaspoidI2CException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new I²C exception with the specified detail message.
     * @param message the detail message
     */
    public RaspoidI2CException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new I²C exception with the specified cause.
     * @param cause the cause to be retrieved later
     */
    public RaspoidI2CException(Throwable cause) {
        super(cause);
    }
}