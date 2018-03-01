package nl.cge.eventbus.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/event")
public class EventController {

    @Autowired
    private EventDao eventDao;

    @GetMapping
    public Iterable<Event> getEvents() {
        return eventDao.findAll();
    }

}
