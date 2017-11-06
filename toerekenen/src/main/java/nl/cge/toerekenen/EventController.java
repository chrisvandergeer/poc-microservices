package nl.cge.toerekenen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/betalingen")
public class EventController {

    @GetMapping
    public String getEvents() {
        return "Microservice Toerekenen alive";
    }

}
