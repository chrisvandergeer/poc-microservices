package nl.cge.springboot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/event")
public class EventController {

    @Autowired
    private EventDao taskDao;

    @Autowired
    private EventProducer eventProducer;

    @GetMapping
    public Iterable<Event> getEvents() {
        return taskDao.findAll();
    }

}