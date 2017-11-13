package nl.cge.toerekenen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;

@Component
public class EventSubscriber {

    private static final Logger log = LoggerFactory.getLogger(EventSubscriber.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue subscriptionsQueue;

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.subscriptionsQueue, msg);
    }

    @Scheduled(fixedRate = 5000)
    public void execute() {
        log.info("send message " + new Date());
        jmsMessagingTemplate.convertAndSend("subscriptions", "please give me events");
    }
}
