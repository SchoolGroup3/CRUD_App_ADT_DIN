/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Profile;

/**
 *
 * @author 2dami
 */
public class ProfileWindowController implements Initializable {
    
    @FXML
    private Label lblPasswordMessage;
    
    @FXML
    private TextField txtFieldName;
    
    @FXML
    private TextField txtFieldSurname;
    
    @FXML
    private TextField txtFieldEmail;
    
    @FXML
    private TextField txtFieldUsername;
    
    @FXML
    private TextField txtFieldPassword;
     
    @FXML
    private TextField txtFieldPhoneNumber;
    
    @FXML
    private Button btnChangePassword;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private TextField txtFieldCardNumber;
    
    private Profile logedProfile;
    
    @FXML
    private void handleButtonActionChangePassword(ActionEvent event) {
       String password = txtFieldPassword.getText();
       
       if(password.length()<8){
            lblPasswordMessage.setText("Error password too short. Min 8 characters.");
       }else{
           //metodo para moficar contraseña
       }
    }
    
    @FXML
    private void handleButtonActionSave(ActionEvent event) {
        String name;
        String surname;
        String email;
        String username;
        String password;
        int phoneNumber;
        String cardNumber;
        
            
        
       
    }
    
    public void setProfile(Profile profile){
        this.logedProfile = profile;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
