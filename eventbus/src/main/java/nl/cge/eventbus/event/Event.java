package nl.cge.eventbus.event;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class Event {

    /**
     * Unique eventId.
     */
    @Id @GeneratedValue
    private Long eventId;

    /**
     * event name.
     */
    private String eventName;

    /**
     * process name.
     */
    private String procesName;

    /**
     * procesId.
     */
    private String procesId;

    /**
     * Creation date/time.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime created;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setProcesName(String procesName) {
        this.procesName = procesName;
    }

    public void setProcesId(String procesId) {
        this.procesId = procesId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Event body.
     */
    private String body;

    @PrePersist
    public void onPersist() {
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", procesName='" + procesName + '\'' +
                ", procesId='" + procesId + '\'' +
                ", created=" + created +
                ", body='" + body + '\'' +
                '}';
    }
}
