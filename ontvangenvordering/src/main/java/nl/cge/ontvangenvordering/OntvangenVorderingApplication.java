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
    public Queue queue() {
        return new ActiveMQQueue("ontvangVorderingQueue");
    }

    public static void main(String[] args) {
        SpringApplication.run(OntvangenVorderingApplication.class, args);
    }

}
