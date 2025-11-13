package Test;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import org.junit.*;
import static org.testfx.api.FxAssert.verifyThat;

import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.WindowMatchers.isShowing;

public class LogOutPopUpControllerTest extends ApplicationTest {

    private Stage curStage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogOutPopUp.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        curStage = stage;
    }

    @Override
    public void stop() {
    }

    @Test
    public void logOutTest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
            Parent root = loader.load();
            
            interact(() -> {
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
                stage.show();
                verifyThat(window("Login"), isShowing());
                curStage.close();
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
