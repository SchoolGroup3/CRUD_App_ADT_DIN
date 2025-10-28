/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import model.User;

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
    @FXML
    private Label lblIncorrectPassword;

    private User user;

    private Controller cont = new Controller();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void passwordButton(ActionEvent event) {
        String userPassword = user.getPssw();
        String currentPasswd;
        String newPasswd;
        String newPasswdConfirm;
        boolean hasErrors = false;
        
        lblIncorrectPassword.setText("");
        lblNewPasswdMessage.setText("");
        lblNewPasswdMessage.setText("");
        
        currentPasswd = txtFieldCurrentPsswd.getText();
        newPasswd = txtFieldNewPsswd.getText();
        newPasswdConfirm = txtFieldNewPsswdConfirm.getText();

        if (!currentPasswd.equals(userPassword)) {
            lblIncorrectPassword.setText("That is not your actual password");
            hasErrors = true;
        }

        if (newPasswd.length() < 8) {
            lblNewPasswdMessage.setText("New password is too short");
            hasErrors = true;
        }

        if (currentPasswd.equals(newPasswd)) {
            lblNewPasswdMessage.setText("Password used before");
            hasErrors = true;
        }

        if (!newPasswd.equals(newPasswdConfirm)) {
            lblNewPasswdMessage.setText("The passwords are different");
            hasErrors = true;
        }

        if (!hasErrors) {
            if (cont.modifyPassword(user, newPasswd)) {
                lblNewPasswdMessage.setText("Password mofified correctly");
                lblNewPasswdMessage.setStyle("-fx-text-fill: green;");
            } else {
                lblNewPasswdMessage.setText("Error mofifiying the password");
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
