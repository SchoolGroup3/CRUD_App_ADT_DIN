/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.net.URL;
import java.util.ResourceBundle;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.HomeWindowController;

/**
 *
 * @author USUARIO
 */
public class HomeWindowControllerTest {
    
    public HomeWindowControllerTest() {
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

    /**
     * Test of setUser method, of class HomeWindowController.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        HomeWindowController instance = new HomeWindowController();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeCheck method, of class HomeWindowController.
     */
    @Test
    public void testTimeCheck() {
        System.out.println("timeCheck");
        HomeWindowController instance = new HomeWindowController();
        instance.timeCheck();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class HomeWindowController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        HomeWindowController instance = new HomeWindowController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
