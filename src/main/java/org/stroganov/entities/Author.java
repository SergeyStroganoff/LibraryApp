package org.stroganov.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = ("authors"))
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorID;
    @Column(name = "author_name")
    private String authorName;

    public Author(int numberID, String authorName) {
        this.authorID = numberID;
        this.authorName = authorName;
    }

    public Author() {
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

        Author author = (Author) o;

        if (authorID != author.authorID) return false;
        return authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        int result = authorID;
        result = 31 * result + authorName.hashCode();
        return result;
    }
}
