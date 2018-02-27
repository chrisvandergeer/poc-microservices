package nl.cge.ontvangenvordering.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cge.ontvangenvordering.producer.EventProducer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

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
        log.info("Event: " + assembler.assemble(message));
        eventProducer.send(assembler.assemble(message).toString());
    }

    private String vorderingOntvangenEventAssembler(String message) {
        try {
            JSONObject event = new JSONObject();
            event.put("eventName", "vorderingOntvangen");
            event.put("processName", "verwerkVordering");
            event.put("processId", UUID.randomUUID().toString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode bodySrc = mapper.reader().readTree(message);

            JSONObject body = new JSONObject();
            body.put("heffingkenmerk", bodySrc.get("heffingkenmerk").asText());
            body.put("middel", bodySrc.get("middel").asText());
            body.put("belastingjaar", bodySrc.get("belastingjaar").asText());
            body.put("belasting", bodySrc.get("belasting").asText());

//            JsonNode periode = bodySrc.get("periode");
//            log.info(periode.get("begindatum").asText());

            JsonNode periodeSrc = mapper.reader().readTree(bodySrc.get("periode").asText());
//            JSONObject periode = new JSONObject();
//            periode.put("begindatum", periodeSrc.get("begindatum").asText());
//            periode.put("einddatum", periodeSrc.get("einddatum").asText());
//            body.put("periode", periode);

            event.put("body", body);

            return event.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    public void sendVordering() {
        int bedrag = 99;
        String heffingkenmerk = UUID.randomUUID().toString();
        String betalingskenmerk = UUID.randomUUID().toString();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("eventName", "ontvangVordering");
            JSONObject body = new JSONObject();
            body.put("heffingkenmerk", heffingkenmerk);
            body.put("middel", "MRB");
            body.put("belastingjaar", "2017");
            body.put("belasting", 99);
            body.put("betalingskenmerk", betalingskenmerk);
            jsonObject.put("body", body);
            eventProducer.send(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
     */

//    private Event mapEvent(String message) {
//        try {
//            Event event = new Event();
//            event.setProcesId(UUID.randomUUID().toString());
//            event.setEventName("vorderingOntvangen");
//            event.setProcesName("verwerkVordering");
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode jsonNode = mapper.readTree(message);
//            event.setBody(jsonNode.asText());
//        } catch (IOException e) {
//
//        }
//
//    }

    /*
    try {
            log.info("RECEIVE: " + message);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(message);
            String eventName = jsonNode.get("eventName").asText();
            if (EVENT_ONTVANG_VORDERING.equals(eventName)) {
                log.info("TODO parse vordering");
            }
        } catch (IOException e) {

        }
     */

}
