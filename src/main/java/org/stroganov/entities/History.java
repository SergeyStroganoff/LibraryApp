package org.stroganov.entities;

import javax.persistence.*;
import java.util.Date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (id != history.id) return false;
        if (user != null ? !user.equals(history.user) : history.user != null) return false;
        if (localDateTime != null ? !localDateTime.equals(history.localDateTime) : history.localDateTime != null)
            return false;
        return event != null ? event.equals(history.event) : history.event == null;
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
