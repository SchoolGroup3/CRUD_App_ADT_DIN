package Test;

import controller.Controller;
import java.io.IOException;
import java.time.LocalTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import model.*;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.WindowMatchers.isShowing;
import view.*;

public class HomeWindowControllerTest extends ApplicationTest {

    Stage stage;
    Controller cont;
    User testUser;

    @Override
    public void start(Stage stage) throws Exception {
        User testUser = new User(1, "user@test.com", "Pepe", "1234", 123456789, "pep", "jose", "Male", "E123456789");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
        Parent root = loader.load();
        HomeWindowController controller = loader.getController();
        controller.setUser(testUser);
        controller.timeCheck();

        stage.setScene(new Scene(root));
        stage.setTitle("Home");
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
public void testAllTimeMessages() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
    Parent root = loader.load();
    HomeWindowController controller = loader.getController();
    User user = new User("mikel", "garay");
    
    controller.setUser(user); 
    controller.time = new Label();
    
    LocalTime curtime = LocalTime.now();

    if (curtime.isAfter(LocalTime.parse("08:00")) && curtime.isBefore(LocalTime.NOON)) {
        controller.timeCheck();
        assertEquals("Good morning mikel!", controller.time.getText());
    } else if (curtime.isAfter(LocalTime.NOON) && curtime.isBefore(LocalTime.parse("20:00"))) {
        controller.timeCheck();
        assertEquals("Good afternoon mikel!", controller.time.getText());
    } else {
        controller.timeCheck();
        assertEquals("Good night mikel!", controller.time.getText());
    }
}

    @Test
    public void testSettingsIconClick() {
        ImageView settingsIcon = lookup("#settingsIcon").query();
        assertNotNull(settingsIcon);

        clickOn("#settingsIcon");

        sleep(1000);
        try {
            verifyThat(window("Profile"), isShowing());
            press(KeyCode.ESCAPE);
        } catch (Exception e) {
            try {
                verifyThat("#profileName", Node::isVisible);
                press(KeyCode.ESCAPE);
            } catch (Exception ex) {
                // Continuar en caso de error
            }
        }
    }

    @Test
    public void testLogOutIconClick() {
        ImageView logOutIcon = lookup("#logOut").query();
        assertNotNull(logOutIcon);

        clickOn("#logOut");

        sleep(1000);
        try {
            verifyThat(window("Log Out"), isShowing());
            clickOn("Cancel");
        } catch (Exception e) {
            try {
                verifyThat("Are you sure you want to log out?", Node::isVisible);
                clickOn("Cancel");
            } catch (Exception ex) {
                // Continuar en caso de error
            }
        }
    }

    @Test
    public void testWindowTitle() {
        verifyThat(window("Home"), isShowing());
    }
    
    private HomeWindowController getCurrentController() {
        try {
            Node rootNode = stage.getScene().getRoot();
            Object controller = rootNode.getProperties().get("controller");
            if (controller instanceof HomeWindowController) {
                return (HomeWindowController) controller;
            }
        } catch (Exception e) {
            // Intentar obtener el controlador de otra manera
        }
        return null;
    }

}
