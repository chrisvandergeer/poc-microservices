package nl.cge.eventbus.event;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id @GeneratedValue
    private Long id;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime created;

    private String body;
    private String objectId;

    public Long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Event setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public Event setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static Event create(String type) {
        Event event = new Event();
        event.setType(type);
        return event;
    }

    @PrePersist
    public void onPersist() {
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", objectId='" + objectId + '\'' +
                ", type='" + type + '\'' +
                ", created=" + created +
                ", body='" + body + '\'' +
                '}';
    }

}
