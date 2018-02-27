package nl.cge.ontvangenvordering;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms

public class OntvangenVorderingApplication {

    @Bean
    public Queue ontvangVorderingQueue() {
        return new ActiveMQQueue("ontvangVorderingQueue");
    }

    @Bean
    public Queue sendEventQueue() {
        return new ActiveMQQueue("events");
    }

    public static void main(String[] args) {
        SpringApplication.run(OntvangenVorderingApplication.class, args);
    }

}
