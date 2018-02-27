package nl.cge.ontvangenvordering.consumer;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VorderingOntvangenEventAssemblerTest {

    private String JSON_SRC = "{\n" +
            "  \"heffingkenmerk\": \"0d19e190-277f-430b-941a-b2962e483155\",\n" +
            "  \"middel\": \"MRB\",\n" +
            "  \"belastingjaar\": \"2017\",\n" +
            "  \"belasting\": \"96\",\n" +
            "  \"betalingskenmerk\": \"176eaf85-b272-4b54-b1fd-f4383ab2578a\",\n" +
            "  \"periode\": {\n" +
            "    \"begindatum\": \"2017-02-02\",\n" +
            "    \"einddatum\": \"2017-05-02\"\n" +
            "  },\n" +
            "  \"middelspecifiek\": {\n" +
            "    \"begindatumVerkort\": \"2017-02-20\",\n" +
            "    \"einddatumVerkort\": \"2017-05-02\"\n" +
            "  }\n" +
            "}";

    private VorderingOntvangenEventAssembler cut;

    @BeforeEach
    public void setup() {
        cut = new VorderingOntvangenEventAssembler();
    }

    @Test
    public void test() throws IOException, JSONException {
        // WHEN
        JSONObject jsonObject = cut.assemble(JSON_SRC);

        // THEN
        assertAll(
                () -> assertEquals("vorderingOntvangen", jsonObject.getString("eventName")),
                () -> assertEquals("verwerkVordering", jsonObject.getString("processName")),
                () -> assertNotNull(jsonObject.get("processId"))
        );

        JSONObject body = jsonObject.getJSONObject("body");
        assertAll(
                () -> assertEquals("0d19e190-277f-430b-941a-b2962e483155", body.getString("heffingkenmerk")),
                () -> assertEquals("MRB", body.getString("middel")),
                () -> assertEquals("2017", body.getString("belastingjaar")),
                () -> assertEquals("96", body.getString("belasting")),
                () -> assertEquals("176eaf85-b272-4b54-b1fd-f4383ab2578a", body.getString("betalingskenmerk"))
        );

        JSONObject periode = body.getJSONObject("periode");
        assertAll(
                () -> assertEquals("2017-02-02", periode.getString("begindatum")),
                () -> assertEquals("2017-05-02", periode.getString("einddatum"))
        );

        JSONObject middelspecifiek = body.getJSONObject("middelspecifiek");
        assertAll(
                () -> assertEquals("2017-02-20", middelspecifiek.getString("begindatumVerkort")),
                () -> assertEquals("2017-05-02", middelspecifiek.getString("einddatumVerkort"))
        );
    }

}