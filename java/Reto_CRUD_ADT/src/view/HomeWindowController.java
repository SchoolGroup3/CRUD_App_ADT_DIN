package view;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeWindowController implements Initializable {

    @FXML
    private Label time;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        time.setText(timeCheck());
    }
}
