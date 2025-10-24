/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;

/**
 *
 * @author 2dami
 */
public class ProfileWindowController implements Initializable {

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
    private TextField txtFieldCardNumber;

    @FXML
    private ComboBox comboGender;

    private User logedProfile;

    private Controller cont = new Controller();


    @FXML
    private void handleButtonActionSave(ActionEvent event) {
        String name;
        String surname;
        String email;
        String username;
        String password;
        int phoneNumber;
        String cardNumber;
        String card_no;
        String gender;

        if (txtFieldName.getText() != null || !txtFieldName.getText().equalsIgnoreCase(logedProfile.getName())
                || txtFieldSurname.getText() != null || !txtFieldSurname.getText().equalsIgnoreCase(logedProfile.getName())
                || txtFieldEmail.getText() != null || !txtFieldEmail.getText().equalsIgnoreCase(logedProfile.getEmail())
                || txtFieldUsername.getText() != null || !txtFieldUsername.getText().equalsIgnoreCase(logedProfile.getUser_name())
                || txtFieldPhoneNumber.getText() != null || !txtFieldPhoneNumber.getText().equalsIgnoreCase(String.valueOf(logedProfile.getTelephone()))
                || txtFieldCardNumber.getText() != null || !txtFieldCardNumber.getText().equalsIgnoreCase(String.valueOf(logedProfile.getCard_no()))) {

            name = txtFieldName.getText();
            surname = txtFieldSurname.getText();
            email = txtFieldEmail.getText();
            username = txtFieldUsername.getText();
            phoneNumber = parseInt(txtFieldPhoneNumber.getText());
            card_no = txtFieldCardNumber.getText();
            gender = comboGender.getSelectionModel().getSelectedItem().toString();

            logedProfile.setName(name);
            logedProfile.setSurname(surname);
            logedProfile.setEmail(email);
            logedProfile.setUser_name(username);
            logedProfile.setTelephone(phoneNumber);
            logedProfile.setCard_no(card_no);

            //if the fills are empty or different
            cont.modifyProfile(logedProfile);
            cont.modifyUser(logedProfile);
        } else {
            lblSavedMessage.setText("You have to complete all the fields");
        }

    }

    public void setProfile(User profile) {
        this.logedProfile = profile;
    }

    public void loadData() {

        //fill the combobox with options
        comboGender.getItems().addAll(
                "Hombre",
                "Mujer",
                "Otro"
        );

        comboGender.setEditable(false);

        //loads the texfields
        txtFieldName.setText(logedProfile.getName());
        txtFieldSurname.setText(logedProfile.getSurname());
        txtFieldEmail.setText(logedProfile.getEmail());
        txtFieldUsername.setText(logedProfile.getUser_name());
        txtFieldPhoneNumber.setText(String.valueOf(logedProfile.getTelephone()));
        
        txtFieldCardNumber.setText(logedProfile.getCard_no());
        comboGender.getSelectionModel().select(logedProfile.getGender());
             
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
