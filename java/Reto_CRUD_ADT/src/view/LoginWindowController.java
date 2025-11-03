package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Admin;
import model.ImplementsBD;
import model.Profile;
import javafx.stage.Stage;
import model.User;

public class LoginWindowController implements Initializable {

    ImplementsBD im = new ImplementsBD();
    ActionEvent event;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void handleLogin() {

        /*if (usernameTextField.isEmpty || passwordTextField.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, completa todos los campos.");
            return;
        }*/
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        Profile y = im.checkUser(username, password);
        System.out.println(y); //debug

        if (y != null) {
            showAlert("Login correcto", "Bienvenido, " + username);
            if (y instanceof Admin) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeAdminWindow.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (y instanceof User) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
                    // Change to Singleton pattern
                    HomeWindowController controller = new HomeWindowController();
                    controller.setUser((User)y);
                    loader.setController(controller);
                    Parent root = loader.load();
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
            showAlert("Login fallido", "Usuario o contraseña incorrectos");
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
    private void openSignup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
        Parent root = null;
        root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage actualStage = (Stage) usernameTextField.getScene().getWindow();
        actualStage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
