//package nl.cge.eventbus.subscribers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import nl.cge.eventbus.event.Event;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Arrays;
//
//@Component
//public class ToerekenenSubscriber {
//
//    private static final Logger log = LoggerFactory.getLogger(ToerekenenSubscriber.class);
//
//    private Long lastId = 0L;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Scheduled(fixedRate = 5000)
//    public void execute() {
//        log.info("looking for new Events for Toerekenen, lastId = " + lastId);
//        TypedQuery<Event> query = entityManager.createQuery(
//                "select e from Event e where id > :id and type in :type", Event.class);
//        query.setParameter("id", this.lastId);
//        query.setParameter("type", Arrays.asList("vordering", "betaling"));
//        query.getResultList().forEach(e -> sendEvent(e));
//        log.info("no more Events for Toerekenen, lastId = " + lastId);
//    }
//
//    private void sendEvent(Event event) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            ObjectWriter writer = objectMapper.writerFor(Event.class);
//            String jsonEvent = writer.writeValueAsString(event);
//            jmsMessagingTemplate.convertAndSend("toerekenenEventQueue", jsonEvent);
//            this.lastId = event.getEventId();
//            log.info("Event send to Toerekenen: " + jsonEvent);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
