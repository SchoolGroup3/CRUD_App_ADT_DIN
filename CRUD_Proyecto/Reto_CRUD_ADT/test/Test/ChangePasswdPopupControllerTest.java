package Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;

import controller.Controller;
import model.User;
import view.ChangePasswdPopupController;

public class ChangePasswdPopupControllerTest extends ApplicationTest {

    Stage stage;
    Controller cont;
    User user;

    @Override
    public void start(Stage stage) throws Exception {
        user = new User(1, "juan.perez@email.com", "juanP", "1234", 611223344, "Juan", "Pérez", "Man", "1234-5678-9012-3456");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChangePasswdPopup.fxml"));
        Parent root = loader.load();
        ChangePasswdPopupController controller = loader.getController();
        controller.setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        this.stage = stage;
        this.cont = new Controller();
    }

    @Test
    public void passwordChangeTest() {
        clickOn("#txtFieldCurrentPsswd").write("1234");
        clickOn("#txtFieldNewPsswd").write("fnbrlmfao");
        clickOn("#txtFieldNewPsswdConfirm").write("fnbrlmfao");

        TextField pass1 = lookup("#txtFieldCurrentPsswd").query();
        assertEquals("1234", pass1.getText());

        TextField pass2 = lookup("#txtFieldNewPsswd").query();
        assertEquals("fnbrlmfao", pass2.getText());

        TextField pass3 = lookup("#txtFieldNewPsswdConfirm").query();
        assertEquals("fnbrlmfao", pass3.getText());

        clickOn("#btnConfirm");
    }

    @Test
    public void notActualPassTest() {
        clickOn("#txtFieldCurrentPsswd").write("yeet");
        clickOn("#txtFieldNewPsswd").write("fnbrlmao");
        clickOn("#txtFieldNewPsswdConfirm").write("fnbrlmao");

        TextField pass1 = lookup("#txtFieldCurrentPsswd").query();
        assertEquals("yeet", pass1.getText());

        TextField pass2 = lookup("#txtFieldNewPsswd").query();
        assertEquals("fnbrlmao", pass2.getText());

        TextField pass3 = lookup("#txtFieldNewPsswdConfirm").query();
        assertEquals("fnbrlmao", pass3.getText());

        clickOn("#btnConfirm");
    }

    @Test
    public void notLongPassTest() {
        clickOn("#txtFieldCurrentPsswd").write("fnbrlmfao");
        clickOn("#txtFieldNewPsswd").write("yeet");
        clickOn("#txtFieldNewPsswdConfirm").write("yeet");

        TextField pass1 = lookup("#txtFieldCurrentPsswd").query();
        assertEquals("fnbrlmfao", pass1.getText());

        TextField pass2 = lookup("#txtFieldNewPsswd").query();
        assertEquals("yeet", pass2.getText());

        TextField pass3 = lookup("#txtFieldNewPsswdConfirm").query();
        assertEquals("yeet", pass3.getText());

        clickOn("#btnConfirm");
    }

    @Test
    public void prevPassTest() {
        clickOn("#txtFieldCurrentPsswd").write("fnbrlmfao");
        clickOn("#txtFieldNewPsswd").write("1234");
        clickOn("#txtFieldNewPsswdConfirm").write("1234");

        TextField pass1 = lookup("#txtFieldCurrentPsswd").query();
        assertEquals("fnbrlmfao", pass1.getText());

        TextField pass2 = lookup("#txtFieldNewPsswd").query();
        assertEquals("1234", pass2.getText());

        TextField pass3 = lookup("#txtFieldNewPsswdConfirm").query();
        assertEquals("1234", pass3.getText());

        clickOn("#btnConfirm");
    }

    @Test
    public void notSameNewPassCheck() {
        clickOn("#txtFieldCurrentPsswd").write("fnbrlmfao");
        clickOn("#txtFieldNewPsswd").write("fnbrlmao");
        clickOn("#txtFieldNewPsswdConfirm").write("fnbrlmfao");

        TextField pass1 = lookup("#txtFieldCurrentPsswd").query();
        assertEquals("fnbrlmfao", pass1.getText());

        TextField pass2 = lookup("#txtFieldNewPsswd").query();
        assertEquals("fnbrlmao", pass2.getText());

        TextField pass3 = lookup("#txtFieldNewPsswdConfirm").query();
        assertEquals("fnbrlmfao", pass3.getText());

        clickOn("#btnConfirm");
    }
}
