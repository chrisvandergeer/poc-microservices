package nl.cge.eventbus;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class EventbusApplication {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("events");
	}

	public static void main(String[] args) {
		SpringApplication.run(EventbusApplication.class, args);
	}
}
