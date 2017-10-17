package nl.cge.ms.btv.generator;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class BetalingsverplichtingGenerator {

    public static void main(String... args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        System.out.println("connection started");
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("VorderingQueue");
        MessageProducer producer = session.createProducer(destination);

        System.out.println("start");
        for (int i = 0; i < 1000; i++) {
            TextMessage message = session
                    .createTextMessage("Hello !!! Welcome to the world of ActiveMQ.");
            producer.send(message);
        }
        System.out.println("finished");
        session.close();
        connection.close();
    }
}
