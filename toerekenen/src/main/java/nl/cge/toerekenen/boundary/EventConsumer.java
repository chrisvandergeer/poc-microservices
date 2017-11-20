package nl.cge.toerekenen.boundary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cge.toerekenen.base.BaseEntity;
import nl.cge.toerekenen.base.EventEntity;
import nl.cge.toerekenen.betaling.Betaling;
import nl.cge.toerekenen.betaling.ToewijzenBetalingController;
import nl.cge.toerekenen.vordering.Vordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ToewijzenBetalingController toewijzenBetalingController;


    @JmsListener(destination = "toerekenenEventQueue")
    @Transactional
    public void consume(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.reader().readTree(message);
        String type = jsonNode.get("type").asText();
        JsonNode body = mapper.reader().readTree(jsonNode.get("body").asText());
        BaseEntity entity = null;
        if ("vordering".equals(type)) {
            entity = mapVordering(jsonNode, body);
        } else if ("betaling".equals(type)) {
            entity = mapBetaling(jsonNode, body);
        } else {
            log.info("Unknown object received: " + message);
        }
        if (entity != null) {
            log.info(entity.getClass().getSimpleName() + " received: " + entity);
            entityManager.persist(entity);
            if (entity instanceof Betaling) {
                toewijzenBetalingController.execute((Betaling) entity);
            }
        }
    }

    private BaseEntity mapBetaling(JsonNode jsonNode, JsonNode body) {
        Betaling betaling = mapEntityValues(new Betaling(), jsonNode);
        betaling.setBetalingskenmerk(body.get("betalingskenmerk").asText());
        betaling.setBedrag(body.get("bedrag").asInt());
        log.info("Betaling received: " + betaling);
        return betaling;
    }

    private BaseEntity mapVordering(JsonNode jsonNode, JsonNode body) {
        Vordering vordering = mapEntityValues(new Vordering(), jsonNode);
        vordering.setHeffingkenmerk(body.get("heffingkenmerk").asText());
        vordering.setBetalingskenmerk(body.get("betalingskenmerk").asText());
        vordering.setMiddel(body.get("middel").asText());
        vordering.setBelastingjaar(body.get("belastingjaar").asInt());
        vordering.setBelasting(body.get("belasting").asInt());
        log.info("Vordering received: " + vordering);
        return vordering;
    }

    private <E extends EventEntity> E mapEntityValues(E entity, JsonNode jsonNode) {
        entity.setEventId(jsonNode.get("id").asLong());
        entity.setObjectId(jsonNode.get("objectId").asText());
        return entity;
    }
}
