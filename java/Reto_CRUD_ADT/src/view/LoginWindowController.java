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

    @FXML
    private void handleLogin() {
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        Profile profile = cont.checkUser(username, password);

        if (profile != null) {
            showAlert("Login successful", "Welcome, " + username);
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}