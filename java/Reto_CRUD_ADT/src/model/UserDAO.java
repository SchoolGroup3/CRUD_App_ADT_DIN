package model;

import java.util.HashMap;

/**
 * Interface that defines the basic user data access operations (DAO).
 * <p>
 * Any class implementing this interface must provide functionality
 * for user authentication and user management operations in the system.
 * </p>
 *
 */
public interface UserDAO {

    /**
     * Verifies user credentials and returns the corresponding profile.
     *
     * @param username the username to verify
     * @param password the password to verify
     * @return the Profile object if credentials are valid, null otherwise
     */
    public Profile checkUser(String username, String password);

    /**
     * Modifies an existing user's information in the system.
     *
     * @param user the User object containing updated information
     * @return true if the modification was successful, false otherwise
     */
    public boolean modifyUser(User user);

    /**
     * Modifies an existing administrator's information in the system.
     *
     * @param user the Admin object containing updated information
     * @return true if the modification was successful, false otherwise
     */
    public boolean modifyAdmin(Admin user);

    /**
     * Modifies a user's password in the system.
     *
     * @param user the Profile whose password will be changed
     * @param passwd the new password to set
     * @return true if the password was successfully modified, false otherwise
     */
    public boolean modifyPassword(Profile user, String passwd);

    /**
     * Deletes a user from the system.
     *
     * @param user the User object to be deleted
     * @return true if the user was successfully deleted, false otherwise
     */
    public boolean deleteUser(User user);

    /**
     * Retrieves all users from the system.
     *
     * @return a HashMap containing all users with their profile codes as keys
     */
    public HashMap<Integer, User> getAllUsers();

    /**
     * Inserts a new user into the system.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the created Profile if successful, null otherwise
     */
    public Profile insertUser(String username, String password);
}