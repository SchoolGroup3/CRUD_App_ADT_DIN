package controller;

import java.util.HashMap;

import model.*;

public class Controller {

    UserDAO dao = new ImplementsBD();

    /**
     * Modifies an existing user in the system.
     * 
     * @param user the user object to be modified
     * @return true if the user was successfully modified, false otherwise
     */
    public boolean modifyUser(User user) {
        return dao.modifyUser(user);
    }

    /**
     * Modifies an existing admin user in the system.
     * 
     * @param user the admin object to be modified
     * @return true if the admin was successfully modified, false otherwise
     */
    public boolean modifyAdmin(Admin user) {
        return dao.modifyAdmin(user);
    }

    /**
     * Modifies the password for a given user profile.
     * 
     * @param user the profile for which to change the password
     * @param passwd the new password to set
     * @return true if the password was successfully modified, false otherwise
     */
    public boolean modifyPassword(Profile user, String passwd) {
        return dao.modifyPassword(user, passwd);
    }

    /**
     * Deletes a user from the system.
     * 
     * @param user the user object to be deleted
     * @return true if the user was successfully deleted, false otherwise
     */
    public boolean deleteUser(User user) {
        return dao.deleteUser(user);
    }

    /**
     * Retrieves all users from the system.
     * 
     * @return a HashMap containing all users with their IDs as keys
     */
    public HashMap<Integer, User> getAllUsers() {
        return dao.getAllUsers();
    }

    /**
     * Verifies user credentials against the database.
     * 
     * @param username the username to check
     * @param password the password to verify
     * @return the user profile if credentials are valid, null otherwise
     */
    public Profile checkUser(String username, String password) {
        return dao.checkUser(username, password);
    }

    /**
     * Inserts a new user into the system.
     * 
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the created user profile if successful, null otherwise
     */
    public Profile insertUser(String username, String password) {
        return dao.insertUser(username, password);
    }
}