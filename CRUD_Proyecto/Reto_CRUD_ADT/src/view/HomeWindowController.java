package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

/**
 * Controller for the user home window that displays personalized greetings and provides navigation to settings and logout.
 */
public class HomeWindowController implements Initializable {

    private User user;

    @FXML
    public Label time;

    @FXML
    private Label settings;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private ImageView logOut;

    @FXML
    private AnchorPane pane;

    /**
     * Sets the current user for this controller and personalizes the interface.
     * 
     * @param user the user object to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Checks the current time and displays an appropriate greeting message based on the time of day.
     * Displays "Good morning", "Good afternoon", or "Good night" with the user's name.
     */
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

    /**
     * Opens the logout confirmation popup window.
     * 
     * @param event the mouse event that triggered this action
     */
    @FXML
    private void logOutPopup(MouseEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogOutPopUp.fxml"));
            root = loader.load();
            LogOutPopUpController controller = loader.getController();
            controller.setParentStage((Stage) settings.getScene().getWindow());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Handles the settings icon click by opening the profile window and closing the current home window.
     * 
     * @param event the mouse event that triggered this action
     */
    @FXML
    private void settingsIcon(MouseEvent event) {
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
    }

    /**
     * Initializes the controller class.
     * 
     * @param url the location used to resolve relative paths for the root object, or null if unknown
     * @param rb the resources used to localize the root object, or null if not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}