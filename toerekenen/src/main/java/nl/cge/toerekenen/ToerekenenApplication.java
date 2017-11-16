package nl.cge.toerekenen;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;

@SpringBootApplication
@EnableScheduling
@EnableJms
public class ToerekenenApplication {

    @Bean
    public Queue subscriptionsQueue() {
        return new ActiveMQQueue("subscriptions");
    }

    public static void main(String[] args) {
        SpringApplication.run(ToerekenenApplication.class, args);
    }
}
