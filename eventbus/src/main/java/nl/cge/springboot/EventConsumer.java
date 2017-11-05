package nl.cge.springboot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cge.springboot.event.Event;
import nl.cge.springboot.event.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class EventConsumer {

    @Autowired
    private EventDao eventDao;

    @JmsListener(destination = "events")
    public void receiveQueue(String message) {
        System.out.println("MSG: " + message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(message);
            String type = jsonNode.get("type").asText();
            if ("vordering".equals(type)) {
                handleVorderingEvent(jsonNode);
            } else if ("betaling".equals(type)) {
                handleBetalingEvent(jsonNode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleVorderingEvent(JsonNode jsonNode) {
        JsonNode body = jsonNode.get("body");
        String heffingkenmerk = body.get("heffingkenmerk").asText();
        String middel = body.get("middel").asText();
        Event event = eventDao.save(Event.create("vordering")
                .setObjectId(middel + "/" + heffingkenmerk)
                .setBody(body.toString()));
        System.out.println("SAVED: " + event);
    }

    private void handleBetalingEvent(JsonNode jsonNode) {
        JsonNode body = jsonNode.get("body");
        Event event = eventDao.save(Event.create("betaling")
                .setObjectId(UUID.randomUUID().toString())
                .setBody(body.toString()));
        System.out.println("SAVED: " + event);
    }

}
