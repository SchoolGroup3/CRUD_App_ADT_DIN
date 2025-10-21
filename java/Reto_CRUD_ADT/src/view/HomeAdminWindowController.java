package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

public class HomeAdminWindowController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<User> adminTable;
    @FXML
    private TableColumn<User, String> user_name;
    @FXML
    private TableColumn<User, String> card_no;
    @FXML
    private TableColumn<User, String> email;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        User newUser = new User();
        newUser.setUser_name("NewUser123");
        newUser.setCard_no("xxxx-xxxx-xxxx");
        newUser.setEmail("test@test.com");
        adminTable.getItems().add(newUser);
    }
    
    ObservableList<User> initialData(){
        User user1 = new User();
        user1.setUser_name("user1_Username");
        user1.setCard_no("card_no_test");
        user1.setEmail("test@test.com");
        
        User user2 = new User();
        user2.setUser_name("user2_Username");
        user2.setCard_no("card_no_test");
        user2.setEmail("test@test.com");
        
        return FXCollections.observableArrayList(user1, user2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_name.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
        card_no.setCellValueFactory(new PropertyValueFactory<User, String>("card_no"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        
        adminTable.setItems(initialData());
    }
}
