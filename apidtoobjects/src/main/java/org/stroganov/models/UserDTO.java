package org.stroganov.models;

import org.stroganov.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private int userID;

    private String fullName;

    private String login;

    private String passcodeHash;

    private boolean isBlocked;

    private boolean isAdmin;

    public UserDTO(User user) {
        this.userID = user.getUserID();
        this.fullName = user.getFullName();
        this.login = user.getLogin();
        this.passcodeHash = user.getPasscodeHash();
        this.isBlocked = user.isBlocked();
        this.isAdmin = user.isAdmin();
    }

    public UserDTO() {

    }

    public int getUserID() {
        return userID;
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

    public void setBlocked(boolean isBlocked) {
        isBlocked = isBlocked;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setUserID(int numberID) {
        this.userID = numberID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (userID != userDTO.userID) return false;
        if (isBlocked != userDTO.isBlocked) return false;
        if (isAdmin != userDTO.isAdmin) return false;
        if (!fullName.equals(userDTO.fullName)) return false;
        if (!login.equals(userDTO.login)) return false;
        return passcodeHash.equals(userDTO.passcodeHash);
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + fullName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + passcodeHash.hashCode();
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "numberID=" + userID +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", passcodeHash='" + passcodeHash + '\'' +
                ", isBlocked=" + isBlocked +
                ", isAdmin=" + isAdmin +
                '}';
    }


}
