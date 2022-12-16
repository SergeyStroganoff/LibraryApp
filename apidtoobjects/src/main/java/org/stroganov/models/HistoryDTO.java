package org.stroganov.models;

import org.stroganov.entities.History;

import java.util.Date;
import java.util.Objects;

public class HistoryDTO {

   private int id;
   private UserDTO userDTO;
   private Date localDateTime;
   private String event;

    public HistoryDTO(UserDTO userDTO, String event) {
        this.userDTO = userDTO;
        this.localDateTime = new Date();
        this.event = event;
    }

    public HistoryDTO(History history) {
        this.userDTO = new UserDTO(history.getUser());
        this.localDateTime = history.getLocalDateTime();
        this.event = history.getEvent();
    }

    public HistoryDTO() {
    }

    public UserDTO getUser() {
        return userDTO;
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

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
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

        HistoryDTO historyDTO = (HistoryDTO) o;

        if (id != historyDTO.id) return false;
        if (!Objects.equals(userDTO, historyDTO.userDTO)) return false;
        if (!Objects.equals(localDateTime, historyDTO.localDateTime))
            return false;
        return Objects.equals(event, historyDTO.event);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userDTO != null ? userDTO.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "user=" + userDTO +
                ", localDateTime=" + localDateTime +
                ", event='" + event + '\'' +
                '}';
    }
}
