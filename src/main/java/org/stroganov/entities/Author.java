package org.stroganov.entities;

public class Author {
    private int numberID;
    private String authorName;

    public Author(int numberID, String authorName) {
        this.numberID = numberID;
        this.authorName = authorName;
    }

    public int getNumberID() {
        return numberID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setNumberID(int numberID) {
        this.numberID = numberID;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
