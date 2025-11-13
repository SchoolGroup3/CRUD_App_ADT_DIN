/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javafx.scene.control.Alert;

/**
 * Custom runtime exception class for handling application-specific exceptions.
 * Extends RuntimeException to create unchecked exceptions that don't require
 * explicit handling in method signatures.
 * 
 * @author kevin
 */
public class CustomException extends Exception {
    
    /**
     * Constructs a new CustomException with the specified detail message.
     * 
     * @param message the detail message that describes the reason for the exception
     */
    public CustomException (String description, String title){
        super(description);
        Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle(title);
        alert.setContentText(description);
        alert.showAndWait();
    }
}