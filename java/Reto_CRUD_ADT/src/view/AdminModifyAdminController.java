package view;

import controller.Controller;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Admin;

public class AdminModifyAdminController implements Initializable {

    @FXML
    private Label lblPasswordMessage;
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
    private TextField txtFieldPassword;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private Button btnChangePassword;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFieldAccountNumber;
    @FXML
    private TextField txtFieldProfileCode;
    @FXML
    private ImageView iconTrash;
    @FXML
    private ImageView iconHome;
    @FXML
    private Label lblHome;

    private Admin admin;

    private Controller cont = new Controller();

    @FXML
    private void handleButtonActionSave(ActionEvent event) {
        String name;
        String surname;
        String email;
        String username;
        int phoneNumber;
        String account_no;

        //Verifies if there are no fills empty and there are changes
        if (!isAnyFieldEmpty() && hasUserDataChanged()) {

            name = txtFieldName.getText();
            surname = txtFieldSurname.getText();
            email = txtFieldEmail.getText();
            username = txtFieldUsername.getText();
            phoneNumber = parseInt(txtFieldPhoneNumber.getText());
            account_no = txtFieldAccountNumber.getText();

            admin.setName(name);
            admin.setSurname(surname);
            admin.setEmail(email);
            admin.setUser_name(username);
            admin.setTelephone(phoneNumber);
            admin.setCurrent_account(account_no);

            cont.modifyAdmin(admin);
            lblSavedMessage.setText("Correctly modified");
            lblSavedMessage.setStyle("-fx-text-fill: green;");

        } else if (isAnyFieldEmpty()) {
            lblSavedMessage.setText("You have to complete all the fields");
        } else {
            lblSavedMessage.setText("No changes detected");
        }

    }

    @FXML
    private void handleButtonActionChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChangePasswdPopup.fxml"));
            Parent root = loader.load();

            //passing the admin as a parameterS
            ChangePasswdPopupController controller = loader.getController();
            controller.setUser(admin);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Change Password Window");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Error creating main window", e);
        }
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        if (txtFieldName != null) {
            loadData();
        }
    }

    public void openHomeWindow(MouseEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
            root = loader.load();
            AdminHomeWindowController controller = loader.getController();
            controller.setAdmin(admin);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadData() {
        //loads the texfields
        txtFieldName.setText(admin.getName());
        txtFieldSurname.setText(admin.getSurname());
        txtFieldEmail.setText(admin.getEmail());
        txtFieldUsername.setText(admin.getUser_name());
        txtFieldPhoneNumber.setText(String.valueOf(admin.getTelephone()));
        txtFieldProfileCode.setText(String.valueOf(admin.getProfile_code()));
        txtFieldAccountNumber.setText(admin.getCurrent_account());
    }

    private boolean isAnyFieldEmpty() {
        return txtFieldName.getText().isEmpty()
                || txtFieldSurname.getText().isEmpty()
                || txtFieldEmail.getText().isEmpty()
                || txtFieldUsername.getText().isEmpty()
                || txtFieldPhoneNumber.getText().isEmpty()
                || txtFieldAccountNumber.getText().isEmpty();
    }

    private boolean hasUserDataChanged() {
        return !txtFieldName.getText().equals(admin.getName())
                || !txtFieldSurname.getText().equals(admin.getSurname())
                || !txtFieldEmail.getText().equals(admin.getEmail())
                || !txtFieldUsername.getText().equals(admin.getUser_name())
                || !txtFieldPhoneNumber.getText().equals(String.valueOf(admin.getTelephone()))
                || !txtFieldAccountNumber.getText().equals(admin.getCurrent_account());
    }

    @FXML
    private void deleteAccount(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteAccountPopUp.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException("Error creating main window", ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (admin != null ) {
            loadData();
        }

    }
}
