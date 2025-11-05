package controller;

import java.util.HashMap;

import model.*;

public class Controller {

    UserDAO dao = new ImplementsBD();

    public boolean modifyUser(User user) {
        return dao.modifyUser(user);
    }

    public boolean modifyAdmin(Admin user) {
        return dao.modifyAdmin(user);
    }

    public boolean modifyPassword(Profile user, String passwd) {
        return dao.modifyPassword(user, passwd);
    }

    public boolean deleteUser(User user) {
        return dao.deleteUser(user);
    }

    public HashMap<Integer, User> getAllUsers() {
        return dao.getAllUsers();
    }

    public Profile checkUser(String username, String password) {
        return dao.checkUser(username, password);
    }

    public Profile insertUser(String username, String password) {
        return dao.insertUser(username, password);
    }
}
