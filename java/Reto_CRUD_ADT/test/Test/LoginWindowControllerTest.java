package Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;

import org.junit.Test;
import org.testfx.matcher.base.NodeMatchers;

public class LoginWindowControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void loginTest() {
        clickOn("#usernameTextField").write("juanP");
        clickOn("#passwordTextField").write("1234");
        clickOn("#loginButton");
    }

    @Test
    public void testShowAlert() {
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Login successful"));
    }
}
