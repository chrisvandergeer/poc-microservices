package nl.cge.toerekenen.vordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/vordering")
public class VorderingController {

    @Autowired
    private VorderingDao vorderingDao;

    @GetMapping
    public Iterable<Vordering> getVorderingen() {
        return vorderingDao.findAll();
    }
}
