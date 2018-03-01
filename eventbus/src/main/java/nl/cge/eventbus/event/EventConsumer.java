package nl.cge.eventbus.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventDao eventDao;

    @JmsListener(destination = "events")
    public void receiveQueue(String message) {
        log.info("RECEIVE: " + message);
        Event event = eventMapper.map(message);
        eventDao.save(event);
        log.info(event.toString());

    }

}
