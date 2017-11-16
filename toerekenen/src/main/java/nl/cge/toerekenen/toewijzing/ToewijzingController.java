package nl.cge.toerekenen.toewijzing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/toewijzing")
public class ToewijzingController {

    @Autowired
    private ToewijzingDao toewijzingDao;

    @GetMapping
    public Iterable<Toewijzing> getToewijzingen() {
        return toewijzingDao.findAll();
    }
}
