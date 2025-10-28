package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.ImplementsBD;
import model.Profile;
import model.User;
import view.*;
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
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        Profile profile = new Profile(username, password) {
            @Override
            public void mostrar() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        if (im.checkUser(profile) != null) {
            showAlert("Login correcto", "Bienvenido, " + username);
            if (profile instanceof Admin) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAdminWindow.fxml"));
                    Parent root = loader.load();
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeWindow.fxml"));
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
    private void manejarBotones(ActionEvent event) throws IOException {
        Button source = (Button) event.getSource();
        switch (source.getId()) {
            case "loginButton":
                handleLogin();
                break;
            case "signupButton":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
                Parent root = null;
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            manejarBotones(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
