package org.stroganov.entities;

public class User {

    private int numberID;
    private String fullName;
    private String login;
    private String passcodeHash;
    private boolean isBlocked;
    private boolean isAdmin;


    public User(int ID, String fullName, String login, String passcodeHash, boolean isBlocked, boolean isAdmin) {
        this.numberID = ID;
        this.fullName = fullName;
        this.login = login;
        this.passcodeHash = passcodeHash;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }

    public int getNumberID() {
        return numberID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPasscodeHash() {
        return passcodeHash;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
