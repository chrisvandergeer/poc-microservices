package nl.cge.eventbus.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class EventMapper {

    public Event map(String jsonEvent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonEvent);
            Event event = new Event();
            event.setEventName(jsonNode.get("eventName").asText());
            event.setProcesName(jsonNode.get("processName").asText());
            event.setProcesId(jsonNode.get("processId").asText());
            event.setBody(jsonNode.get("body").toString());
            LocalDateTime created = LocalDateTime.parse(jsonNode.get("created").asText());
            event.setCreated(Date.from(created.atZone(ZoneId.systemDefault()).toInstant()));
            return event;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
