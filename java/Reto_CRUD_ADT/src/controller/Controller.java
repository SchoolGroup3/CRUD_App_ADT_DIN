/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import model.ImplementsBD;
import model.User;

/**
 *
 * @author 2dami
 */
public class Controller {
    ImplementsBD dao = new ImplementsBD();
    
    public HashMap<Integer, User> getAllUsers() {
        return dao.getAllUsers();
    }
}
