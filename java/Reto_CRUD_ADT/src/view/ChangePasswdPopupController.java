package view;

import controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Profile;

/**
 * FXML Controller class
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

    public void setUser(Profile user) {
        this.user = user;
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
                Stage stage = (Stage) btnConfirm.getScene().getWindow();
                stage.close();

            } else {
                lblNewPasswdMessage.setText("Error mofifiying the password");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
