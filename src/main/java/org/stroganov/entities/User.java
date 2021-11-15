package org.stroganov.entities;

import org.stroganov.util.PasswordAuthentication;

import javax.persistence.*;

@Entity
@Table(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "login")
    private String login;
    @Column(name = "pass_code")
    private String passcodeHash;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public User(int userID, String fullName, String login, String passcodeHash, boolean isBlocked, boolean isAdmin) {
        this.userID = userID;
        this.fullName = fullName;
        this.login = login;
        this.passcodeHash = PasswordAuthentication.hash(passcodeHash.toCharArray());
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

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUserID(int numberID) {
        this.userID = numberID;
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
