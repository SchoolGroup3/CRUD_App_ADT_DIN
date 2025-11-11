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
            showAlert("Successful registration", "User created successfully.");
            redirectToMain(creado);
        } else {
            showAlert("Error", "The user could not be created. Please try another name.");
        }
    }

    @FXML
    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage actualStage = (Stage) pswd1.getScene().getWindow();
            actualStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "The login window could not be opened.");
        }
    }

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
