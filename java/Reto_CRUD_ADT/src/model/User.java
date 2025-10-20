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
public class User extends Profile {
    private String gender;
    private String card_no;

    public User(int profile_code, String email, String user_name, String pssw, int telephone, String name, String surname, String gender, String card_no) {
        super(profile_code, email, user_name, pssw, telephone, name, surname);
        this.gender=gender;
        this.card_no=card_no;
    }

    public String getGender() {
        return gender;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    @Override
    public void mostrar() {
        System.out.println(super.toString() + "Gender: " + this.gender + "Card number: " + this.card_no);
    }

   
    
    
}
