package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogOutPopUpController implements Initializable {

    @FXML
    private Button comfirm;

    @FXML
    private Button cancel;

    @FXML
    private void canceled(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void confirmed(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoWindow.fxml")); //Login window here
            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
