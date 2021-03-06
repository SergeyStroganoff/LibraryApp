package org.stroganov.entities;

import org.stroganov.utils.PasswordAuthentication;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = ("users"))
public class User implements Serializable {

    @Transient
    private int userID;
    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "passcode")
    private String passcodeHash;
    @Column(name = "isblocked")
    private boolean isBlocked;
    @Column(name = "isadmin")
    private boolean isAdmin;

    public User(int userID, String fullName, String login, String password, boolean isBlocked, boolean isAdmin) {
        this.userID = userID;
        this.fullName = fullName;
        this.login = login;
        this.passcodeHash = PasswordAuthentication.hash(password.toCharArray());
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }

    public User() {

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
        this.isBlocked = isBlocked;
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

        User user = (User) o;

        if (userID != user.userID) return false;
        if (isBlocked != user.isBlocked) return false;
        if (isAdmin != user.isAdmin) return false;
        if (!fullName.equals(user.fullName)) return false;
        if (!login.equals(user.login)) return false;
        return passcodeHash.equals(user.passcodeHash);
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
