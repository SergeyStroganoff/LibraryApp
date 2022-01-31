package org.stroganov.rs.model;

public class UserDTO {

    private String fullName;

    private String login;

    private boolean isBlocked;

    private boolean isAdmin;

    public UserDTO(String fullName, String login, boolean isBlocked, boolean isAdmin) {
        this.fullName = fullName;
        this.login = login;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }

    public UserDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
