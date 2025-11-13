package view;

import controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Profile;

/**
 * Controller for the change password popup window that handles password modification for user profiles.
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

    private Controller cont = new Controller();

    private Profile user;

    /**
     * Sets the user profile for which the password will be changed.
     * 
     * @param user the profile object representing the user whose password will be modified
     */
    public void setUser(Profile user) {
        this.user = user;
    }

    /**
     * Handles the password change confirmation button action.
     * Validates current password, new password requirements, and confirms password match before updating.
     * 
     * @param event the action event triggered by the confirm button
     */
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
                lblNewPasswdMessage.setText("Password modified correctly");
                lblNewPasswdMessage.setStyle("-fx-text-fill: green;");
            } else {
                lblNewPasswdMessage.setText("Error modifiying the password");
            }
        }
    }

    /**
     * Initializes the controller class.
     * 
     * @param url the location used to resolve relative paths for the root object, or null if unknown
     * @param rb the resources used to localize the root object, or null if not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}