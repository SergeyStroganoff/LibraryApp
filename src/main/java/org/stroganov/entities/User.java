package org.stroganov.entities;

import org.stroganov.util.PasswordAuthentication;

public class User {

    private final int numberID;
    private final String fullName;
    private final String login;
    private String passcodeHash;
    private boolean isBlocked;
    private boolean isAdmin;

    public User(int numberID, String fullName, String login, String passcodeHash, boolean isBlocked, boolean isAdmin) {
        this.numberID = numberID;
        this.fullName = fullName;
        this.login = login;
        this.passcodeHash = PasswordAuthentication.hash(passcodeHash.toCharArray());
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

    public void setPasscodeHash(String passcodeHash) {
        this.passcodeHash = passcodeHash;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "numberID=" + numberID +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", passcodeHash='" + passcodeHash + '\'' +
                ", isBlocked=" + isBlocked +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
