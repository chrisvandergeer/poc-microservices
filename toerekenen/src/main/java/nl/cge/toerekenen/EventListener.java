package nl.cge.toerekenen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventListener {

    private static final Logger log = LoggerFactory.getLogger(EventListener.class);

    @Scheduled(fixedRate = 5000)
    public void execute() {
        log.info("Hello World: " + new Date());
    }
}
