package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the logout confirmation popup window that handles user logout process.
 */
public class LogOutPopUpController implements Initializable {

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;
    
    private Stage parent;
    
    /**
     * Sets the parent stage that will be closed upon logout confirmation.
     * 
     * @param parent the parent stage to set for closure
     */
    public void setParentStage(Stage parent) {
        this.parent = parent;
    }

    /**
     * Handles the cancel button action by closing the popup window without logging out.
     * 
     * @param event the action event triggered by the cancel button
     */
    @FXML
    private void canceled(ActionEvent event) {
        Stage actualStage = (Stage) cancel.getScene().getWindow();
        actualStage.close();
    }

    /**
     * Handles the confirm button action by closing the parent window and navigating to the login window.
     * 
     * @param event the action event triggered by the confirm button
     */
    @FXML
    private void confirmed(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            parent.close();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
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