package nl.cge.springboot;

import nl.cge.springboot.event.EventProducer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendVorderingenTest {

    @Autowired
    private EventProducer eventProducer;

    @Test
    public void sendVordering() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "vordering");
            JSONObject body = new JSONObject();
            body.put("heffingkenmerk", UUID.randomUUID().toString());
            body.put("middel", "MRB");
            body.put("belasting", "99");
            body.put("belastingjaar", "2017");
            body.put("betalingskenmerk", UUID.randomUUID().toString());
            jsonObject.put("body", body);
            eventProducer.send(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sendBetaling() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "betaling");
            JSONObject body = new JSONObject();
            body.put("betalingskenmerk", UUID.randomUUID().toString());
            body.put("bedrag", "99");
            jsonObject.put("body", body);
            eventProducer.send(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
