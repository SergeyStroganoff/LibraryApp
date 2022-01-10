package org.stroganov.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = ("history"))
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "user_login")
    User user;
    Date localDateTime;
    String event;

    public History(User user, String event) {
        this.user = user;
        this.localDateTime = new Date();
        this.event = event;
    }

    public History() {
    }

    public User getUser() {
        return user;
    }

    public Date getLocalDateTime() {
        return localDateTime;
    }

    public String getEvent() {
        return event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocalDateTime(Date localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (id != history.id) return false;
        if (!Objects.equals(user, history.user)) return false;
        if (!Objects.equals(localDateTime, history.localDateTime))
            return false;
        return Objects.equals(event, history.event);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "user=" + user +
                ", localDateTime=" + localDateTime +
                ", event='" + event + '\'' +
                '}';
    }
}
