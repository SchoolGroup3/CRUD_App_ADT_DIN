/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author 2dami
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        //Estaba puesto para login window y no funcionaba, igual prueba las cosas antes de pushearlas
        //Esto ha pasado en varios proyecto ya, PRUEBA ANTES DE PUSHEAR
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
        Parent root = loader.load();
        
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
