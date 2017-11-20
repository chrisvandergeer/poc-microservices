package nl.cge.toerekenen.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EventEntity extends BaseEntity {

    private Long eventId;
    private String objectId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
