package nl.cge.toerekenen.betaling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/betaling")
public class BetalingController {

    @Autowired
    private BetalingDao betalingDao;

    @GetMapping
    public Iterable<Betaling> getVorderingen() {
        return betalingDao.findAll();
    }
}
