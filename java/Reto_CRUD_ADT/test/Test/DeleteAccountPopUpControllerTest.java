package Test;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.WindowMatchers.isShowing;

import controller.Controller;
import javafx.scene.text.Text;
import model.User;

public class DeleteAccountPopUpControllerTest extends ApplicationTest {

    private Stage curStage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
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
    public void deleteAccTest() {
        User user = new User(1, "", "", "", 0, "", "", "", "");;
        Controller cont = new Controller();
        if (!cont.deleteUser(user)) {
            interact(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El usuario no se ha eliminado correctamente.");
                    alert.show();
                    Node dialogPane = lookup(".dialog-pane").query();
                    from(dialogPane).lookup((Text t) -> t.getText().startsWith("El usuario"));
                    clickOn("Aceptar");
                }
            });
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
                Parent root = loader.load();
                interact(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Login");
                        stage.show();
                        verifyThat(window("Login"), isShowing());
                        curStage.close();
                    }
                });

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void failedDeleteAccTest() {
        Controller cont = new Controller();
        User user = new User(0, "", "", "", 0, "", "", "", "");
        if (!cont.deleteUser(user)) {
            interact(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El usuario no se ha eliminado correctamente.");
                    alert.show();
                    Node dialogPane = lookup(".dialog-pane").query();
                    from(dialogPane).lookup((Text t) -> t.getText().startsWith("El usuario"));
                    clickOn("Aceptar");
                }
            });
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
                Parent root = loader.load();
                interact(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Login");
                        stage.show();
                        verifyThat(window("Login"), isShowing());
                        curStage.close();
                    }
                });

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
