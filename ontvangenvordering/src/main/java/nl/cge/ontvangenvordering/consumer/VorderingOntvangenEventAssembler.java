package nl.cge.ontvangenvordering.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

public class VorderingOntvangenEventAssembler {

    public JSONObject assemble(String jsonVordering) {
        JSONObject event = new JSONObject();
        event.put("eventName", "vorderingOntvangen");
        event.put("processName", "verwerkVordering");
        event.put("processId", UUID.randomUUID().toString());

        JsonNode bodySrc = convert2JsonNode(jsonVordering);
        JSONObject eventBody = new JSONObject();
        event.put("body", eventBody);
        eventBody.put("heffingkenmerk", bodySrc.get("heffingkenmerk").asText());
        eventBody.put("middel", bodySrc.get("middel").asText());
        eventBody.put("belastingjaar", bodySrc.get("belastingjaar").asText());
        eventBody.put("belasting", bodySrc.get("belasting").asText());
        eventBody.put("betalingskenmerk", bodySrc.get("betalingskenmerk").asText());

        JsonNode periodeSrc = convert2JsonNode(bodySrc.get("periode").toString());
        JSONObject periode = new JSONObject();
        eventBody.put("periode", periode);
        periode.put("begindatum", periodeSrc.get("begindatum").asText());
        periode.put("einddatum", periodeSrc.get("einddatum").asText());

        JsonNode middelSpecifiekSrc = convert2JsonNode(bodySrc.get("middelspecifiek").toString());
        JSONObject middelspecifiek = new JSONObject();
        eventBody.put("middelspecifiek", middelspecifiek);
        middelspecifiek.put("begindatumVerkort", middelSpecifiekSrc.get("begindatumVerkort").asText());
        middelspecifiek.put("einddatumVerkort", middelSpecifiekSrc.get("einddatumVerkort").asText());

        return event;
    }

    private JsonNode convert2JsonNode(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.reader().readTree(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
