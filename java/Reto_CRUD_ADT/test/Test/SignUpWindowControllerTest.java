package Test;

import controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
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

public class SignUpWindowControllerTest extends ApplicationTest {

    Stage stage;
    Controller cont;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUpWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        this.stage = stage;
        this.cont = new Controller();
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void signUpTest() {
        clickOn("#username").write("newuser");
        clickOn("#pswd1").write("pswd123");
        clickOn("#pswd2").write("pswd123");
        
        TextField username = lookup("#username").query();
        assertEquals("newuser", username.getText());
        
        PasswordField pswd1 = lookup("#pswd1").query();
        assertEquals("pswd123", pswd1.getText());
        
        PasswordField pswd2 = lookup("#pswd2").query();
        assertEquals("pswd123", pswd2.getText());
        
        clickOn("#SignUpButton");
        
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("User created successfully"));
        clickOn("Aceptar");
    }

    @Test
    public void signUpMethodTest() throws IOException {
        Profile newuser = cont.insertUser("testUser", "testPswd");
        
        if (newuser != null) {
            assertTrue(newuser instanceof User);
            assertEquals("testUser", newuser.getUser_name());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
            Parent root = loader.load();
            HomeWindowController controller = loader.getController();
            controller.setUser((User) newuser);
            
            interact(() -> {
                Stage homeStage = new Stage();
                homeStage.setScene(new Scene(root));
                homeStage.setTitle("Home");
                homeStage.show();
                verifyThat(window("Home"), isShowing());
                homeStage.close();
            });
        }
    }
    
    @Test
    public void signUpWithExistingUserTest() {
        clickOn("#username").write("juanP");
        clickOn("#pswd1").write("1234");
        clickOn("#pswd2").write("1234");
        clickOn("#SignUpButton");
        
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("The user could not be created"));
        clickOn("Aceptar");
    }

    @Test
    public void signUpWithNonMatchingPasswordsTest() {
        clickOn("#username").write("Testuser");
        clickOn("#pswd1").write("password1");
        clickOn("#pswd2").write("password2");
        clickOn("#SignUpButton");
        
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Passwords must be the same"));
        clickOn("Aceptar");
    }

    @Test
    public void signUpWithEmptyFieldsTest() {
        clickOn("#SignUpButton");
        
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Please complete all fields"));
        clickOn("Aceptar");
    }

    @Test
    public void redirectToLoginTest() {
        clickOn("#LogInButton");
        verifyThat(window("Login"), isShowing());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}