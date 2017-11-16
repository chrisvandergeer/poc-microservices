# ActiveMQ
Starten ActiveMQ:

~~~sh
/usr/local/Cellar/activemq/5.15.0/bin/activemq start
~~~

Console ActiveMQ:

~~~http
http://127.0.0.1:8161/admin/
~~~

# MySql

Database info

* Database: microservices
* Userid/password: microservices/microservices
* Tabel: event

# Json

Example JSON 2 Object and back

~~~java
public class Foo {
    
    @Test
    public void testObject2JsonAndBack() {
        try {
            Subscription subscription = Subscription.create();
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerFor(Subscription.class);
            String json = writer.writeValueAsString(subscription);
            Subscription read = (Subscription) objectMapper.readerFor(Subscription.class).readValue(json);
            assertEquals(subscription, read);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
~~~

~~~json
{
  "id": 1,
  "type": "vordering",
  "created": {
    "dayOfYear": 318,
    "dayOfWeek": "TUESDAY",
    "month": "NOVEMBER",
    "dayOfMonth": 14,
    "year": 2017,
    "monthValue": 11,
    "hour": 22,
    "minute": 19,
    "second": 13,
    "nano": 400000000,
    "chronology": {
      "id": "ISO",
      "calendarType": "iso8601"
    }
  },
  "body": "{\"belasting\":\"99\",\"middel\":\"MRB\",\"belastingjaar\":\"2017\",\"betalingskenmerk\":\"1cba8495-680c-49bd-a2e5-aed51e4bbdc5\",\"heffingkenmerk\":\"d3fcba96-9c25-4326-8330-3597de2cb966\"}",
  "objectId": "MRB/d3fcba96-9c25-4326-8330-3597de2cb966"
}
~~~