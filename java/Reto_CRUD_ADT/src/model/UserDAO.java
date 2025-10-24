/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Interfaz que define las operaciones básicas de acceso a datos de usuarios (DAO).
 * <p>
 * Cualquier clase que implemente esta interfaz debe proporcionar la funcionalidad
 * para verificar la existencia de un usuario en el sistema.
 * </p>
 * 
 * @author 2dami
 */
public interface UserDAO {
    public Profile checkUser(Profile profile);
    public boolean modifyProfile (Profile profile);
    public boolean modifyUser (User user);
    public boolean modifyAdmin (Admin admin);
}
