package nl.cge.eventbus.subsribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SubscribtionConsumer {

    private static final Logger log = LoggerFactory.getLogger(SubscribtionConsumer.class);

    @JmsListener(destination = "subscriptions")
    public void receiveQueue(String message) {
        log.info("RECEIVE: " + message);
    }
}
