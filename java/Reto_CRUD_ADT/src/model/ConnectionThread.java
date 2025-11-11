/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.CustomException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * A thread class that manages database connections with automatic timeout and cleanup.
 * This thread holds a database connection for a specified period and automatically
 * closes it to prevent connection leaks.
 * 
 * @author kevin
 */
public class ConnectionThread extends Thread {
    private Connection con;
    private boolean connectionObtained = false;

    /**
     * Constructs a ConnectionThread with the specified database connection.
     * 
     * @param con the database connection to be managed by this thread
     */
    public ConnectionThread(Connection con) {
        this.con = con;
    }

    /**
     * The main execution method of the thread.
     * Holds the connection for 30 seconds and then automatically closes it.
     * If interrupted during sleep, it will proceed to close the connection.
     */
    @Override
    public void run() {
        try {           
            connectionObtained = true; 
            
            Thread.sleep(30000);
            
        } catch (InterruptedException e) {
            System.out.println("An error occurred.");
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Hilo " + Thread.currentThread().getName() + " cerró conexión");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
            connectionObtained = false;
        }
    }

}