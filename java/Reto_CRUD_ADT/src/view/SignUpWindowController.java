package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ImplementsBD;
import model.Profile;
import model.User;

public class SignUpWindowController implements Initializable {

    ImplementsBD im = new ImplementsBD();

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

    @FXML
    private void handleSignUp() {
        String user_name = username.getText().trim();
        String password1 = pswd1.getText().trim();
        String password2 = pswd2.getText().trim();

        if (user_name.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            showAlert("Campos vacíos", "Por favor, completa todos los campos.");
            return;
        }
        if (!password1.equals(password2)) {
            showAlert("Contraseñas no coinciden", "Las contraseñas deben ser iguales.");
            return;
        }
        Profile newUser = new User(user_name, password1);
        Profile creado = im.insertUser(newUser);

        if (creado != null) {
            showAlert("Registro exitoso", "Usuario creado correctamente.");
            redirectToMain();
        } else {
            showAlert("Error", "No se pudo crear el usuario. Intenta con otro nombre.");
        }
    }

    @FXML
    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) SignUpButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de login.");
        }
    }

    @FXML
    private void redirectToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) SignUpButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de Home.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   public static void openSignUpWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(SignUpWindowController.class.getResource("SignUpWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de Usuario");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
        private void openNewSignUpWindow() {
        openSignUpWindow();
        Stage currentStage = (Stage) SignUpButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SignUpButton.setOnAction(e -> handleSignUp());
        LogInButton.setOnAction(e -> redirectToLogin());
    }
    
    
}
