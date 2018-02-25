package nl.cge.ontvangenvordering.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OntvangVorderingConsumer {

    private static final Logger log = LoggerFactory.getLogger(OntvangVorderingConsumer.class);

    @JmsListener(destination = "ontvangVorderingQueue")
    public void receiveQueue(String message) {
        log.info(message);
    }

}
