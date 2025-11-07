package model;

import java.util.HashMap;

/**
 * Interfaz que define las operaciones básicas de acceso a datos de usuarios (DAO).
 * <p>
 * Cualquier clase que implemente esta interfaz debe proporcionar la funcionalidad para verificar la existencia de un usuario en el sistema.
 * </p>
 *
 */
public interface UserDAO {

    public Profile checkUser(String username, String password);

    public boolean modifyUser(User user);

    public boolean modifyAdmin(Admin user);

    public boolean modifyPassword(Profile user, String passwd);

    public boolean deleteUser(User user);

    public HashMap<Integer, User> getAllUsers();

    public Profile insertUser(String username, String password);
}
