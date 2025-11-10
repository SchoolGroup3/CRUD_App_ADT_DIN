package Test;

import controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.WindowMatchers.isShowing;
import view.*;

public class LoginWindowControllerTest extends ApplicationTest {

    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        this.stage = stage;
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void loginTest() {
        clickOn("#usernameTextField").write("juanP");
        clickOn("#passwordTextField").write("1234");
        TextField username = lookup("#usernameTextField").query();
        String uName = username.getText();
        assertEquals("juanP", uName);
        TextField password = lookup("#passwordTextField").query();
        String pass = password.getText();
        assertEquals("1234", pass);
        clickOn("#loginButton");
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Login successful"));
        clickOn("Aceptar");
    }

    @Test
    public void loginMethodTest() throws IOException {
        Controller cont = new Controller();
        Profile profile = cont.checkUser("juanP", "1234");
        Profile profile2 = cont.checkUser("anam", "1234");
        if (profile != null) {
            assertEquals("profile_code=1, email=juan.perez@email.com, user_name=juanP, pssw=1234, telephone=611223344, name=Juan, surname=Pérez", profile.toString());
            if (profile2 instanceof Admin) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
                Parent root = loader.load();
                AdminHomeWindowController controller = loader.getController();
                controller.setAdmin((Admin) profile2);
                interact(() -> {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("HomeAdmin");
                    stage.show();
                    verifyThat(window("HomeAdmin"), isShowing());
                    stage.close();
                });
            }

            if (profile instanceof User) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
                Parent root = loader.load();
                HomeWindowController controller = loader.getController();
                controller.setUser((User) profile);
                controller.timeCheck();
                interact(() -> {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Home");
                    stage.show();
                    verifyThat(window("Home"), isShowing());
                    stage.close();
                });

            }
        }
    }

    @Test
    public void signUpTest() {
        interact(() -> {
            stage.toFront();
        });
        clickOn("#signUp");
        verifyThat(window("Sign Up"), isShowing());
    }

    @Test
    public void failedLoginTest() throws IOException {
        Controller cont = new Controller();
        Profile profile = cont.checkUser("e", "e");
        if (profile != null) {
            assertEquals("profile_code=1, email=juan.perez@email.com, user_name=juanP, pssw=1234, telephone=611223344, name=Juan, surname=Pérez", profile.toString());
            if (profile instanceof Admin) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
                Parent root = loader.load();
                AdminHomeWindowController controller = loader.getController();
                controller.setAdmin((Admin) profile);
                interact(() -> {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("HomeAdmin");
                    stage.show();
                    verifyThat(window("HomeAdmin"), isShowing());
                    stage.close();
                });
            } else if (profile instanceof User) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
                Parent root = loader.load();
                HomeWindowController controller = loader.getController();
                controller.setUser((User) profile);
                controller.timeCheck();
                interact(() -> {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Home");
                    stage.show();
                    verifyThat(window("Home"), isShowing());
                    stage.close();
                });

            }
        } else {
            interact(() -> {
                showAlert("Login failed", "Incorrect username or password");
                Node dialogPane = lookup(".dialog-pane").query();
                from(dialogPane).lookup((Text t) -> t.getText().startsWith("Incorrect"));
                clickOn("Aceptar");
            });
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
