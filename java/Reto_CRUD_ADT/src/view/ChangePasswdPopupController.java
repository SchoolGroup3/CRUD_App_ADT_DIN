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
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author 2dami
 */
public class ChangePasswdPopupController implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private PasswordField txtFieldCurrentPsswd;
    @FXML
    private PasswordField txtFieldNewPsswd;
    @FXML
    private PasswordField txtFieldNewPsswdConfirm;
    @FXML
    private Label lblNewPasswdMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void passwordButton(ActionEvent event) {
        String currentPasswd;
        String newPasswd;
        String newPasswdConfirm;
        
        currentPasswd = txtFieldCurrentPsswd.getText();
        newPasswd = txtFieldNewPsswd.getText();
        newPasswdConfirm = txtFieldNewPsswdConfirm.getText();
        
        if (newPasswd != newPasswdConfirm) {
            lblNewPasswdMessage.setText("The password are different");
        } else {
            //metodo para moficar contraseña
        }
    }
    
}
