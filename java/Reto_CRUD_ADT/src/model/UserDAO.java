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

    /**
     * Verifica si un usuario existe en el sistema.
     * <p>
     * Implementaciones concretas pueden buscar el usuario en una base de datos,
     * un archivo u otro tipo de almacenamiento.
     * </p>
     * 
     * @param user Objeto {@link User} con el nombre y contraseña a verificar.
     * @return Un objeto {@link User} con todos los datos completos si el usuario existe.
     *         En caso contrario, puede devolver {@code null} o un objeto sin datos.
     */
    public Profile checkUser(Profile profile);
    public boolean modifyProfile (Profile profile);
    public boolean modifyUser (User user);
    public boolean modifyAdmin (Admin admin);
    public boolean modifyPassword(User user, String passwd);
}
