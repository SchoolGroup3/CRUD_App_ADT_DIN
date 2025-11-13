package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

/**
 * Controller for the delete account confirmation popup window that handles user account deletion.
 */
public class DeleteAccountPopUpController implements Initializable {

    private User user;
    private Controller cont = new Controller();

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    private Stage parent;

    private boolean admin;
    private AdminHomeWindowController parentController;

    /**
     * Sets whether this popup was opened from an admin window.
     * 
     * @param admin true if opened from admin window, false otherwise
     */
    public void fromAdminWindow(boolean admin) {
        this.admin = admin;
    }

    /**
     * Sets the parent stage for this popup window.
     * 
     * @param parent the parent stage to set
     */
    public void setParentStage(Stage parent) {
        this.parent = parent;
    }

    /**
     * Sets the parent controller to allow communication with the admin home window.
     * 
     * @param controller the AdminHomeWindowController to set as parent
     */
    public void setController(AdminHomeWindowController controller) {
        this.parentController = controller;
    }

    /**
     * Sets the user to be deleted by this controller.
     * 
     * @param user the user object to be deleted
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Handles the cancel button action by closing the popup window without deleting the account.
     * 
     * @param event the action event triggered by the cancel button
     */
    @FXML
    private void canceled(ActionEvent event) {
        Stage actualStage = (Stage) cancel.getScene().getWindow();
        actualStage.close();
    }

    /**
     * Handles the confirm button action by deleting the user account and handling post-deletion navigation.
     * If successful, refreshes the parent table and navigates to login window for non-admin deletions.
     * 
     * @param event the action event triggered by the confirm button
     */
    @FXML
    private void confirmed(ActionEvent event) {
        if (!cont.deleteUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("The user has not been deleted correctly.");
            alert.showAndWait();
        } else {
            try {
                if (parentController != null) {
                    parentController.refreshTableData();
                    System.out.println("Table data refreshed?");
                }
                // Cerrar el popup actual primero
                Stage currentPopupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentPopupStage.close();

                if (!admin) {

                    if (parent != null) {
                        parent.close();
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
                    Parent root = loader.load();
                    Stage profileStage = new Stage();
                    profileStage.setScene(new Scene(root));
                    profileStage.show();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
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