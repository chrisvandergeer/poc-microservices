package nl.cge.ontvangenvordering.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class EventProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue sendEventQueue;

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.sendEventQueue, msg);
    }

}
