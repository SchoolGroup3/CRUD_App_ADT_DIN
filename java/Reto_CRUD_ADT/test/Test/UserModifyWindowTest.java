/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import view.ProfileWindowController;

/**
 *
 * @author Mosi
 */
public class UserModifyWindowTest extends ApplicationTest {
    
   private Stage stage;
   
   @Override
    public void start(Stage stage) throws Exception {
        User testUser = new User(1, "user@test.com", "Pepe", "1234", 123456789, "pep", "jose", "Male", "E123456789");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileWindow.fxml"));
        Parent root = loader.load();
        
        ProfileWindowController controller = loader.getController();
        controller.setUser(testUser);
        
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        this.stage = stage;
    }
   
    @Test
    public void emptyFields(){
        clickOn("#txtFieldPhoneNumber");
        push(javafx.scene.input.KeyCode.DELETE);
        
        clickOn("#btnSave");
        
        FxAssert.verifyThat("#lblSavedMessage", LabeledMatchers.hasText("You have to complete all the fields"));
        
        clickOn("#nameTextField").write("123456789");
    }
    
    @Test
    public void noChanges(){
        clickOn("#btnSave");
        
        FxAssert.verifyThat("#lblSavedMessage", LabeledMatchers.hasText("No changes detected"));
        clickOn("#nameTextField").write("Pepito");
        
        clickOn("#btnSave");
        
    }
   
}
