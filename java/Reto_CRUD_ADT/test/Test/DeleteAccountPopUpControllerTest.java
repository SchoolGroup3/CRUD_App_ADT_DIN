/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import view.DeleteAccountPopUpController;
import static org.junit.Assert.*;

public class DeleteAccountPopUpControllerTest {
    
    public DeleteAccountPopUpControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFromAdminWindow() {
        System.out.println("fromAdminWindow");
        boolean admin = false;
        DeleteAccountPopUpController instance = new DeleteAccountPopUpController();
        instance.fromAdminWindow(admin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetStage() {
        System.out.println("getStage");
        Stage parent = null;
        DeleteAccountPopUpController instance = new DeleteAccountPopUpController();
        instance.getStage(parent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        DeleteAccountPopUpController instance = new DeleteAccountPopUpController();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        DeleteAccountPopUpController instance = new DeleteAccountPopUpController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
