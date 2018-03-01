package nl.cge.ontvangenvordering.consumer;

import nl.cge.ontvangenvordering.producer.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OntvangVorderingConsumer {

    private static final Logger log = LoggerFactory.getLogger(OntvangVorderingConsumer.class);

    @Autowired
    private VorderingOntvangenEventAssembler assembler;

    @Autowired
    private EventProducer eventProducer;

    @JmsListener(destination = "ontvangVorderingQueue")
    public void receiveQueue(String message) {
        log.info(message);
        eventProducer.send(assembler.assemble(message).toString());
    }

}
