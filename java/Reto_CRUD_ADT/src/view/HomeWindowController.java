package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomeWindowController implements Initializable {

    @FXML
    private Label time;

    @FXML
    private Button settings;

    @FXML
    private Button logOut;

    private String timeCheck() {
        String message;
        LocalTime curTime = LocalTime.now();

        if (curTime.isAfter(LocalTime.parse("07:00")) && curTime.isBefore(LocalTime.NOON)) {
            message = "Good morning User!";
        } else if (curTime.isAfter(LocalTime.NOON) && curTime.isBefore(LocalTime.parse("20:00"))) {
            message = "Good afternoon User!";
        } else {
            message = "Good night User!";
        }

        return message;
    }

    @FXML
    private void logOutPopup(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogOutPopUp.fxml"));
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void settingsWindow(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileWindow.fxml"));
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
        time.setText(timeCheck());
    }
}
