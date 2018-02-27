package nl.cge.testberichten;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendVorderingenTest {

    @Autowired
    private EventProducer eventProducer;

    @Test
    public void sendVordering() {
        int bedrag = 99;
        String heffingkenmerk = UUID.randomUUID().toString();
        String betalingskenmerk = UUID.randomUUID().toString();
        try {
            JSONObject json = new JSONObject();
            json.put("heffingkenmerk", heffingkenmerk);
            json.put("middel", "MRB");
            json.put("belastingjaar", "2017");
            json.put("belasting", 99);
            json.put("betalingskenmerk", betalingskenmerk);

            JSONObject periode = new JSONObject();
            LocalDate begindatum = LocalDate.of(2017, 2, 20);
            periode.put("begindatum", begindatum);
            periode.put("einddatum", begindatum.plusMonths(3));
            json.put("periode", periode);

            JSONObject middelspecifiek = new JSONObject();
            middelspecifiek.put("begindatumVerkort", begindatum.plusDays(7));
            middelspecifiek.put("einddatumVerkort", begindatum.plusMonths(3));
            json.put("middelspecifiek", middelspecifiek);

            eventProducer.send(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendVorderingenEnBetalingen() {

        String betalingskenmerk = UUID.randomUUID().toString();
        sendVordering(betalingskenmerk, "99");
        sendBetaling(betalingskenmerk, "99");

        betalingskenmerk = UUID.randomUUID().toString();
        sendVordering(betalingskenmerk, "80");
        sendBetaling(betalingskenmerk, "97");

        betalingskenmerk = UUID.randomUUID().toString();
        sendVordering(betalingskenmerk, "96");
        sendBetaling(betalingskenmerk, "86");

        betalingskenmerk = UUID.randomUUID().toString();
        sendVordering(betalingskenmerk, "91");

        betalingskenmerk = UUID.randomUUID().toString();
        sendBetaling(betalingskenmerk, "87");

    }

    private void sendVordering(String betalingskenmerk, String bedrag) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "vordering");
            JSONObject body = new JSONObject();
            body.put("heffingkenmerk", UUID.randomUUID().toString());
            body.put("middel", "MRB");
            body.put("belasting", bedrag);
            body.put("belastingjaar", "2017");
            body.put("betalingskenmerk", betalingskenmerk);
            jsonObject.put("body", body);
            eventProducer.send(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendBetaling(String betalingskenmerk, String bedrag) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "betaling");
            JSONObject body = new JSONObject();
            body.put("betalingskenmerk", betalingskenmerk);
            body.put("bedrag", bedrag);
            jsonObject.put("body", body);
            eventProducer.send(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
