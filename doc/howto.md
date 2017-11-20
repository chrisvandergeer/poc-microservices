# ActiveMQ
Starten ActiveMQ:

~~~sh
/usr/local/Cellar/activemq/5.15.0/bin/activemq start
~~~

Console ActiveMQ:

~~~http
http://127.0.0.1:8161/admin/
~~~

# H2 Database

URL:
~~~
http://localhost:8080/h2-console
~~~
JDBC: 
~~~
jdbc:h2:mem:testdb
~~~


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
