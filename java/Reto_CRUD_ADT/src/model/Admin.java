package model;

public class Admin extends Profile {

    private String current_account;

    public Admin(int profile_code, String email, String user_name, String pssw, int telephone, String name, String surname, String current_account) {

        super(profile_code, email, user_name, pssw, telephone, name, surname);
        this.current_account = current_account;
    }

    public String getCurrent_account() {
        return current_account;
    }

    public void setCurrent_account(String current_account) {
        this.current_account = current_account;
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
