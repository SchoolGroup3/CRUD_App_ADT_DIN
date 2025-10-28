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

/**
 *
 * @author 2dami
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        User user = new User (2, "aaa@gmail.com", "ee", "1234", 123456789, "ee", "ee","Femenino", "E23457927813");
        System.out.println(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileWindow.fxml"));
            Parent root = loader.load();
            
            ProfileWindowController cont = loader.getController();
            
            if(cont != null){
                cont.setUser(user);
                System.out.println("enviado");
            }
            stage.setScene(new Scene(root));
            stage.setTitle("Profile Window");
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
