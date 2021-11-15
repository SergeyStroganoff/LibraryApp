package org.stroganov.entities;

import javax.persistence.*;

@Entity
@Table(name = ("authors"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int numberID;
    @Column(name = "author_name")
    private String authorName;

    public Author(int numberID, String authorName) {
        this.numberID = numberID;
        this.authorName = authorName;
    }

    public Author() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (numberID != author.numberID) return false;
        return authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        int result = numberID;
        result = 31 * result + authorName.hashCode();
        return result;
    }
}
