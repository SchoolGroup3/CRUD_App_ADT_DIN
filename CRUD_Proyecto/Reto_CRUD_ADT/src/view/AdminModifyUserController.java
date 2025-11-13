package view;

import controller.Controller;
import exception.CustomException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

/**
 * Controller for the admin user modification window that allows administrators to edit user profiles.
 */
public class AdminModifyUserController implements Initializable {

    @FXML
    private Label lblSavedMessage;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldSurname;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private TextField txtFieldCardNumber;
    @FXML
    private ComboBox comboGender;

    private User user;
    private AdminHomeWindowController parentController;

    private Controller cont = new Controller();
    @FXML
    private Button btnChangePassword;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblPasswordMessage;

    /**
     * Handles the save button action to update user profile information.
     * Validates fields and saves changes to the database if modifications are detected.
     * 
     * @param event the action event triggered by the save button
     */
    @FXML
    private void handleButtonActionSave(ActionEvent event) throws CustomException {
        String name;
        String surname;
        String email;
        String username;
        String password;
        int phoneNumber;
        String cardNumber;
        String card_no;
        String gender;

        //Verifies if there are no fills empty and there are changes
        if (!isAnyFieldEmpty() && hasUserDataChanged()) {

            name = txtFieldName.getText();
            surname = txtFieldSurname.getText();
            email = txtFieldEmail.getText();
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                throw new CustomException("Incorret format for the email example@example.com", "FORMAT ERROR");
            }
            username = txtFieldUsername.getText();
            phoneNumber = parseInt(txtFieldPhoneNumber.getText());

            if (phoneNumber < 100000000) { // at least 9 dígits
                throw new CustomException("The phone must be at least 9 digits", "FORMAT ERROR");
            }
            card_no = txtFieldCardNumber.getText();
            gender = comboGender.getSelectionModel().getSelectedItem().toString();

            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUser_name(username);
            user.setTelephone(phoneNumber);
            user.setCard_no(card_no);
            user.setGender(gender);

            cont.modifyUser(user);
            lblSavedMessage.setText("Correctly modified");
            lblSavedMessage.setStyle("-fx-text-fill: green;");
            parentController.refreshTableData();
        } else if (isAnyFieldEmpty()) {
            lblSavedMessage.setText("You have to complete all the fields");
        } else {
            lblSavedMessage.setText("No changes detected");
        }

    }

    /**
     * Handles the change password button action by opening the password change popup for the user.
     * 
     * @param event the action event triggered by the change password button
     */
    @FXML
    private void handleButtonActionChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChangePasswdPopup.fxml"));
            Parent root = loader.load();

            //passing the user as a parameterS
            ChangePasswdPopupController controller = loader.getController();
            if (controller != null) {
                controller.setUser(user);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Change Password Window");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Error creating main window", e);
        }
    }

    /**
     * Sets the user to be modified by this controller and loads their data into the form.
     * 
     * @param user the user object to set for modification
     */
    public void setUser(User user) {
        this.user = user;
        if (txtFieldName != null) {
            loadData();
        }
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
     * Loads the user's current data into the text fields and combo box for editing.
     */
    public void loadData() {

        //loads the texfields
        txtFieldName.setText(user.getName());
        txtFieldSurname.setText(user.getSurname());
        txtFieldEmail.setText(user.getEmail());
        txtFieldUsername.setText(user.getUser_name());
        txtFieldPhoneNumber.setText(String.valueOf(user.getTelephone()));

        txtFieldCardNumber.setText(user.getCard_no());
        comboGender.getSelectionModel().select(user.getGender());

    }

    /**
     * Checks if any of the required form fields are empty.
     * 
     * @return true if any field is empty, false otherwise
     */
    private boolean isAnyFieldEmpty() {
        return txtFieldName.getText().isEmpty()
                || txtFieldSurname.getText().isEmpty()
                || txtFieldEmail.getText().isEmpty()
                || txtFieldUsername.getText().isEmpty()
                || txtFieldPhoneNumber.getText().isEmpty()
                || txtFieldCardNumber.getText().isEmpty()
                || comboGender.getSelectionModel().getSelectedItem() == null;
    }

    /**
     * Checks if the user has made any changes to the form data compared to the original user data.
     * 
     * @return true if any field has been modified, false otherwise
     */
    private boolean hasUserDataChanged() {
        return !txtFieldName.getText().equals(user.getName())
                || !txtFieldSurname.getText().equals(user.getSurname())
                || !txtFieldEmail.getText().equals(user.getEmail())
                || !txtFieldUsername.getText().equals(user.getUser_name())
                || !txtFieldPhoneNumber.getText().equals(String.valueOf(user.getTelephone()))
                || !txtFieldCardNumber.getText().equals(user.getCard_no())
                || !comboGender.getSelectionModel().getSelectedItem().equals(user.getGender());
    }

    /**
     * Initializes the controller class. Sets up the gender combo box with available options.
     * 
     * @param url the location used to resolve relative paths for the root object, or null if unknown
     * @param rb the resources used to localize the root object, or null if not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //fill the combobox
        comboGender.getItems().addAll("Man", "Female", "Other");
        comboGender.setEditable(false);
    }
}