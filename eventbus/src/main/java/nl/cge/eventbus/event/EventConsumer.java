package nl.cge.eventbus.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventDao eventDao;

    @JmsListener(destination = "events")
    public void receiveQueue(String message) {
        log.info("RECEIVE: " + message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(message);
            String type = jsonNode.get("type").asText();
            if ("vordering".equals(type)) {
                handleVorderingEvent(jsonNode);
            } else if ("betaling".equals(type)) {
                handleBetalingEvent(jsonNode);
            } else {
                log.info("NOTHING TO HANDLE for message" + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleVorderingEvent(JsonNode jsonNode) {
        JsonNode body = jsonNode.get("body");
        String heffingkenmerk = body.get("heffingkenmerk").asText();
        String middel = body.get("middel").asText();
        Event event = Event.create("vordering")
                .setObjectId(middel + "/" + heffingkenmerk)
                .setBody(body.toString());
        event = eventDao.save(event);
        log.info("SAVED: " + event.getId() + "/" + event.toString() );
    }

    private void handleBetalingEvent(JsonNode jsonNode) {
        JsonNode body = jsonNode.get("body");
        Event event = Event.create("betaling")
                .setObjectId(UUID.randomUUID().toString())
                .setBody(body.toString());
        event = eventDao.save(event);
        log.info("SAVED: " + event.getId() + "/" + event.toString() );
    }

}
