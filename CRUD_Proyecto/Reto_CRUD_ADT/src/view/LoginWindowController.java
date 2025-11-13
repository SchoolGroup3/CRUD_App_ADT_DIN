package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import model.*;

/**
 * Controller for the login window that handles user authentication and navigation to appropriate home screens.
 */
public class LoginWindowController implements Initializable {

    Controller cont = new Controller();
    ActionEvent event;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUp;

    /**
     * Handles the login button action by authenticating user credentials and navigating to the appropriate home screen.
     * Validates username and password, then redirects to admin or user home window based on profile type.
     */
    @FXML
    private void handleLogin() {
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        Profile profile = cont.checkUser(username, password);

        if (profile != null) {
            //Shows a login dialog showAlert("Login successful", "Welcome, " + username);
            if (profile instanceof Admin) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
                    Parent root = loader.load();
                    AdminHomeWindowController controller = loader.getController();
                    controller.setAdmin((Admin) profile);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (profile instanceof User) {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
                    Parent root = loader.load();
                    HomeWindowController controller = loader.getController();
                    controller.setUser((User) profile);
                    controller.timeCheck();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            showAlert("Login failed", "Incorrect username or password");
        }
    }

    /**
     * Displays an alert dialog with the specified title and message.
     * 
     * @param title the title of the alert dialog
     * @param message the message content of the alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens the signup window and closes the current login window.
     */
    @FXML
    private void openSignup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
            Parent root;
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign Up");
            stage.show();
            
            Stage actualStage = (Stage) signUp.getScene().getWindow();
            actualStage.close();
        } catch (IOException e) {
            e.printStackTrace();
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