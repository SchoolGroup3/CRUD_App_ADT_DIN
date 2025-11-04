package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Admin;
import model.Profile;
import model.User;

public class AdminHomeWindowController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<User> adminTable;
    @FXML
    private TableColumn<User, String> user_name;
    @FXML
    private TableColumn<User, String> card_no;
    @FXML
    private TableColumn buttons;
    @FXML
    private Button logOut;

    private Controller cont = new Controller();
    private Profile admin;
    private HashMap<Integer, User> users = cont.getAllUsers();

    @FXML
    private void settingsWindow(MouseEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminModifyAdmin.fxml"));
            root = loader.load();

            AdminModifyAdminController controller = loader.getController();
            controller.setAdmin((Admin) admin);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void logOutPopup(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogOutPopUp.fxml"));
            LogOutPopUpController controller = new LogOutPopUpController();
            controller.getStage((Stage)logOut.getScene().getWindow());
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    ObservableList<User> initialData() {
        return FXCollections.observableArrayList(users.values());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_name.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
        card_no.setCellValueFactory(new PropertyValueFactory<User, String>("card_no"));
        buttons.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                // Style buttons
                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                //Button onClick methods
                editButton.setOnAction(e -> {
                    try {
                        User user = getTableView().getItems().get(getIndex());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminModifyUser.fxml"));
                        Parent root = loader.load();

                        AdminModifyUserController controller = loader.getController();
                        controller.setUser(user);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException("Error creating modify user window", ex);
                    }
                });

                deleteButton.setOnAction(e -> {
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println("Delete clicked for: " + user.getUser_name());

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteAccountPopUp.fxml"));
                        DeleteAccountPopUpController controller = new DeleteAccountPopUpController();
                        controller.setUser(user);
                        controller.fromAdminWindow(true);
                        loader.setController(controller);
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException("Error creating delete popup", ex);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    javafx.scene.layout.HBox buttonsBox = new javafx.scene.layout.HBox(10, editButton, deleteButton);
                    setGraphic(buttonsBox);
                }
            }
        });

        adminTable.setItems(initialData());
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
