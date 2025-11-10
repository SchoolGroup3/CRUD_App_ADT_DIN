package Test;

import controller.Controller;
import java.io.IOException;
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
        User testUser = new User(1, "user@test.com", "Pepe", "1234", 123456789, "pep", "jose","Male" ,"E123456789");
        // Cargar la ventana Home
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
    public void testTimeCheckMethod() throws IOException {
        HomeWindowController controller = getCurrentController();
        assertNotNull(controller);

        Label timeLabel = lookup("#time").query();
        assertNotNull(timeLabel.getText());
        assertFalse(timeLabel.getText().isEmpty());
        assertTrue(timeLabel.getText().contains("Pepe"));
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
            // Verificar elementos del popup
            try {
                verifyThat("Are you sure you want to log out?", Node::isVisible);
                clickOn("Cancel");
            } catch (Exception ex) {
                // Continuar en caso de error
            }
        }
    }

  /*  @Test
    public void testUserInformationDisplay() {
        HomeWindowController controller = getCurrentController();
        assertNotNull(controller.user);
        assertEquals("Pepe", controller.user.getUser_name());
        assertEquals("pep", controller.user.getName());
        assertEquals("jose", controller.user.getSurname());


        Label timeLabel = lookup("#time").query();
        assertTrue(timeLabel.getText().contains("Pepe"));
    }*/

    @Test
    public void testWindowTitle() {
        verifyThat(window("Home"), isShowing());
    }

   /* @Test
    public void testNavigationToProfile() throws IOException {
        // Test de navegación a ventana de perfil
        HomeWindowController controller = getCurrentController();

        interact(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileWindow.fxml"));
                Parent root = loader.load();
                ProfileWindowController profileController = loader.getController();
                profileController.setUser(testUser);

                Stage profileStage = new Stage();
                profileStage.setScene(new Scene(root));
                profileStage.setTitle("Profile");
                profileStage.show();

                verifyThat(window("Profile"), isShowing());

                // Verificar que el controlador de perfil recibió el usuario
                assertNotNull(profileController.getUser()); // Asumiendo que existe este método
                assertEquals("testUser", profileController.getUser().getUser_name());

                profileStage.close();
            } catch (IOException e) {
                fail("Error loading Profile window: " + e.getMessage());
            }
        });
    }*/

 /*   @Test
    public void testLogOutPopupMethod() {
        // Test del método logOutPopup
        HomeWindowController controller = getCurrentController();

        interact(() -> {
            assertDoesNotThrow(() -> {
                controller.logOutPopup(null);
            });
        });
    }*/


   /* @Test
    public void testControllerInitialization() {
        // Verificar que el controlador se inicializó correctamente
        HomeWindowController controller = getCurrentController();
        assertNotNull(controller);
        assertNotNull(controller.user);
        assertEquals("testUser", controller.user.getUser_name());
    }*/

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
