/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Admin;
import model.ImplementsBD;
import model.Profile;
import model.User;
import model.UserDAO;

/**
 *
 * @author 2dami
 */
public class Controller {
    UserDAO dao = new ImplementsBD();
      
    public boolean modifyUser(User user){
        return dao.modifyUser(user);
    }
    
    public boolean modifyPassword(User user, String passwd){
        return dao.modifyPassword(user, passwd);
    }
    
    public boolean deleteUser(User user){
        return dao.deleteUser(user);
    }
    
}
