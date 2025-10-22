/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2dami
 */
public abstract class Profile {
    private int profile_code;
    private String email;
    private String user_name;
    private String pssw;
    private int telephone;
    private String name;
    private String surname;

    public Profile(String email, String user_name, String pssw, int telephone, String name, String surname) {
        this.email = email;
        this.user_name = user_name;
        this.pssw = pssw;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }
    
     public Profile(String email, String user_name, int telephone, String name, String surname) {
        this.email = email;
        this.user_name = user_name;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }

    public int getProfile_code() {
        return profile_code;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPssw() {
        return pssw;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setProfile_code(int profile_code) {
        this.profile_code = profile_code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "profile_code=" + profile_code + ", email=" + email + ", user_name=" + user_name + ", pssw=" + pssw + ", telephone=" + telephone + ", name=" + name + ", surname=" + surname + '}';
    }
    
    public abstract void mostrar();
    
}
