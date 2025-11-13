package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

/**
 * Controller for the user registration/signup window that handles new user account creation.
 */
public class SignUpWindowController implements Initializable {

    Controller cont = new Controller();

    @FXML
    private TextField username;

    @FXML
    private PasswordField pswd1;

    @FXML
    private PasswordField pswd2;

    @FXML
    private Button SignUpButton;

    @FXML
    private Button LogInButton;

    /**
     * Handles the signup button action by validating input and creating a new user account.
     * Checks for empty fields, password confirmation, and creates the user if validation passes.
     */
    @FXML
    private void handleSignUp() {
        String user_name = username.getText().trim();
        String password1 = pswd1.getText().trim();
        String password2 = pswd2.getText().trim();
        if (user_name.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            showAlert("Empty fields", "Please complete all fields.");
            return;
        }
        if (!password1.equals(password2)) {
            showAlert("Passwords do not match", "Passwords must be the same.");
            return;
        }
        Profile creado = cont.insertUser(user_name, password1);

        if (creado != null) {
            showAlert("Registration", "User created successfully");
            redirectToMain(creado);
        } else {
            showAlert("Registration failed", "The user could not be created. Please try another name.");
        }

    }

    /**
     * Redirects the user to the login window and closes the current signup window.
     */
    @FXML
    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            Stage actualStage = (Stage) pswd1.getScene().getWindow();
            actualStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "The login window could not be opened.");
        }
    }

    /**
     * Redirects the newly registered user to the main home window.
     * 
     * @param user the newly created user profile
     */
    @FXML
    private void redirectToMain(Profile user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
            Parent root = loader.load();
            HomeWindowController controller = loader.getController();
            controller.setUser((User) user);
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            Stage actualStage = (Stage) pswd1.getScene().getWindow();
            actualStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "The Home window could not be opened.");
        }
    }

    /**
     * Displays an alert dialog with the specified title and message.
     * 
     * @param title the title of the alert dialog
     * @param message the message content of the alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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