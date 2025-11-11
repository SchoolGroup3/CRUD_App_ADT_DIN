/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Custom runtime exception class for handling application-specific exceptions.
 * Extends RuntimeException to create unchecked exceptions that don't require
 * explicit handling in method signatures.
 * 
 * @author kevin
 */
public class CustomException extends RuntimeException {
    
    /**
     * Constructs a new CustomException with the specified detail message.
     * 
     * @param message the detail message that describes the reason for the exception
     */
    public CustomException (String message){
        super(message);
    }
}