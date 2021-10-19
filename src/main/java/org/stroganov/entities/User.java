package org.stroganov.entities;

public class User {

    private int numberID;
    private String fullName;
    private String login;
    private int passcodeHash;
    private boolean isBlocked;
    private boolean isAdmin;


    public User(int ID, String fullName, String login, int passcodeHash, boolean isBlocked, boolean isAdmin) {
        this.numberID = ID;
        this.fullName = fullName;
        this.login = login;
        this.passcodeHash = passcodeHash;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }
}
