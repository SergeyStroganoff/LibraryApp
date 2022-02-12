package org.stroganov.models;

import org.stroganov.entities.Author;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

    private int authorID;
    private String authorName;

    public AuthorDTO(int numberID, String authorName) {
        this.authorID = numberID;
        this.authorName = authorName;
    }

    public AuthorDTO(Author author) {
        this.authorID = author.getAuthorID();
        this.authorName = author.getAuthorName();
    }

    public AuthorDTO() {
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorID(int numberID) {
        this.authorID = numberID;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDTO authorDTO = (AuthorDTO) o;

        if (authorID != authorDTO.authorID) return false;
        return authorName.equals(authorDTO.authorName);
    }

    @Override
    public int hashCode() {
        int result = authorID;
        result = 31 * result + authorName.hashCode();
        return result;
    }
}
