package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

public class HomeWindowController implements Initializable {

    private User user;

    @FXML
    private Label time;

    @FXML
    private Label settings;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private ImageView logOut;

    @FXML
    private AnchorPane pane;

    public void setUser(User user) {
        this.user = user;
    }

    public void timeCheck() {
        LocalTime curTime = LocalTime.now();

        if (curTime.isAfter(LocalTime.parse("07:00")) && curTime.isBefore(LocalTime.NOON)) {
            time.setText("Good morning " + user.getUser_name() + "!");
        } else if (curTime.isAfter(LocalTime.NOON) && curTime.isBefore(LocalTime.parse("20:00"))) {
            time.setText("Good afternoon " + user.getUser_name() + "!");
        } else {
            time.setText("Good night " + user.getUser_name() + "!");
        }
    }

    @FXML
    private void logOutPopup(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogOutPopUp.fxml"));
            root = loader.load();
            LogOutPopUpController controller = loader.getController();
            controller.getStage((Stage) settings.getScene().getWindow());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingsIcon.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            Parent root;
            try {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileWindow.fxml"));
                root = loader.load();
                ProfileWindowController controller = loader.getController();
                controller.setUser(user);

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

                currentStage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            });
        }
    
}
