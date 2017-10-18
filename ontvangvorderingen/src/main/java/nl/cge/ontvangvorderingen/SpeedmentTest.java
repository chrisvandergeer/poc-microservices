package nl.cge.ontvangvorderingen;

import com.company.microservices.MicroservicesApplication;
import com.company.microservices.MicroservicesApplicationBuilder;
import com.company.microservices.microservices.microservices.event.Event;
import com.company.microservices.microservices.microservices.event.EventImpl;
import com.company.microservices.microservices.microservices.event.EventManager;

import java.util.UUID;

public class SpeedmentTest {

    public static void main(String... args) {
        MicroservicesApplication entityManager = new MicroservicesApplicationBuilder().build();
        EventManager events = entityManager.getOrThrow(EventManager.class);
        Event event = new EventImpl();
        event.setEventId(UUID.randomUUID().toString());
        event.setSource("{ gevalId: 123dfg; heffingkenmerk: HF123}");
        events.persist(event);

    }
}
